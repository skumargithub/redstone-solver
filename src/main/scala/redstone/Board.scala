package redstone

import Direction._
import redstone.piece._

class Board(val boardPieces: List[BoardPiece], val maxX: Int = 3, val maxY: Int = 4, val bigSquareTargetX: Int = 1, val bigSquareTargetY: Int = 3,
            val movesSoFar: List[(Int, Direction)] = List()) {
  
  def findPiece(id: Int): BoardPiece = boardPieces(id)

  def findPieceAt(xPos: Int, yPos: Int): Option[BoardPiece] = {
    boardPieces.find(bp => bp.x == xPos && bp.y == yPos)
  }
  
  def findBigSquare: BoardPiece = {
    val bigSquare = boardPieces.find(p =>
      p match {
        case BigSquare(id, x, y) => true
        case _ => false
        } )
        
    bigSquare.get
  }
  
  def isPositionOpen(x: Int, y: Int): Boolean = {
    x >= 0 && x <= maxX &&
    y >= 0 && y <= maxY &&
    boardPieces.foldLeft(true) { (isItTrue, boardPiece) => isItTrue && boardPiece.isPositionOpen(x, y) }
  }
  
  def possibleMoves: List[(Int, Direction)] = {
    
    var moves: List[(Int, Direction)] = List()
    for(boardPiece <- boardPieces) {
      for(direction <- Direction.values) {
        if(boardPiece.canMoveTo(this, direction)) {
          moves = (boardPiece.id, direction) :: moves
        }
      }
    }
    
    moves
  }
  
  def priority: Double = {
    val bigSquare = findBigSquare
    
    var result: Double = bigSquare.y
    
    if(bigSquare.canMoveTo(this, Direction.Up)) result + 0.5
    else if(bigSquare.canMoveTo(this, Direction.Right) || bigSquare.canMoveTo(this, Direction.Left)) result + 0.25
    else result
  }
  
  def manhattanPriority: Double = {
    val bigSquare = findBigSquare
    
    val yDistance = bigSquare.y
    val xDistance = if(bigSquare.x == 2) 0 else bigSquare.x
    var result: Double = yDistance + xDistance 
    
    if(bigSquare.canMoveTo(this, Direction.Up)) result + 0.5
    else if(bigSquare.canMoveTo(this, Direction.Right) || bigSquare.canMoveTo(this, Direction.Left)) result + 0.25
    else result
    
    result
  }
  
  // We have solved it if the big square has reached the top
  def isSolution: Boolean = {
    val bigSquare = findBigSquare
    
    bigSquare.y == bigSquareTargetY && bigSquare.x == bigSquareTargetX
  }
  
  override def toString: String = {
    import Array._

    val positions: Array[Array[String]] = Array.fill(maxX + 1, maxY + 1) { " " } //ofDim(4, 5)
    for (boardPiece <- boardPieces) {
      val X = boardPiece.x
      val Y = boardPiece.y

      boardPiece match {
        case SmallSquare(id, x, y) => {
          positions(X)(Y) = "b"
        }
        case BigSquare(id, x, y) => {
          positions(X)(Y) = "B"
          positions(X + 1)(Y) = "B"
          positions(X)(Y + 1) = "B"
          positions(X + 1)(Y + 1) = "B"
        }
        case SideRectangle(id, x, y) => {
          positions(X)(Y) = "<"
          positions(X + 1)(Y) = ">"
        }
        case UpRectangle(id, x, y) => {
          positions(X)(Y + 1) = "^"          
          positions(X)(Y) = "U"
        }
      }
    }

    var result: String = ""    
    //boardPieces.foreach(bp => result += (bp.description + ": " + bp.id + " -> (" + bp.getX + ", " + bp.getY + ")\n"))
    for (y <- maxY to 0 by -1) {
      result += "|"
      for (x <- 0 to maxX) {
        result += positions(x)(y)
      }
      result += "|\n"
    }

    result
  }
  
  override def equals(other: Any): Boolean = {
//    println("In equals")
    other match {
      case that: Board => {
        that.boardPieces.foldLeft(true) { (isItTrue, boardPiece) => isItTrue && {
            val bp = findPieceAt(boardPiece.x, boardPiece.y)

            !bp.isEmpty && bp.get.description == boardPiece.description
          }
        }
      }
        
      case _ => false
    }
  }
  
  val hashValue: Int = {
    var hashGen: String = ""

    for(x <- 0 until maxX) {
      for(y <- 0 until maxY) {
        val bp: Option[BoardPiece] = findPieceAt(x, y)
        if(bp.isDefined) {
          val b: BoardPiece = bp.get
          hashGen += Integer.toString(b.x) + Integer.toString(b.y) + Integer.toString(b.deltaX) + Integer.toString(b.deltaY)
        }
      }
    }
    
    hashGen.hashCode
  }
    
  override def hashCode() = {
    hashValue
  }
}

object Board {
  def apply(board: Board, nextMove: (Int, Direction)): Board = {
    
    var newBoardPieces: List[BoardPiece] = List()
    
    for(boardPiece <- board.boardPieces) {
      val newBoardPiece = {
        if(nextMove._1 == boardPiece.id) boardPiece.moveTo(nextMove._2)
        else boardPiece
      }
      
      newBoardPieces = newBoardPieces :+ newBoardPiece
    }
    
    val newBoard = new Board(newBoardPieces, board.maxX, board.maxY, board.bigSquareTargetX, board.bigSquareTargetY, nextMove :: board.movesSoFar)
    
    newBoard
  }
}
