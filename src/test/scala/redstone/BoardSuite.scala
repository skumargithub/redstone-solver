package redstone

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._

@RunWith(classOf[JUnitRunner])
class BoardSuite extends FunSuite {
  test("print Board") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(3, 0 , 2)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(5, 0, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(6, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(7, 2, 3)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(8, 3, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(9, 0, 4)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(10, 3, 4)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    println(board)
  }
  
  test("simple move 1") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(3, 0 , 2)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(5, 0, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(6, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(7, 2, 3)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(8, 3, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(9, 0, 4)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(10, 3, 4)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)    
//    println("This is the original board")
//    println(board)

//    println("This is the board after the move")
    val afterMoveBoard = Board(board, (7, Direction.Up))
//    println(afterMoveBoard)
    
    val pieceInOriginalBoard = board.findPiece(7)
//    println(pieceInOriginalBoard)
//    println(board.boardPieces)

    val pieceInAfterMoveBoard = afterMoveBoard.findPiece(7)
//    println(pieceInAfterMoveBoard)
//    println(afterMoveBoard.boardPieces)
    
    assert(pieceInOriginalBoard.x == pieceInAfterMoveBoard.x)
    assert(pieceInOriginalBoard.y + 1 == pieceInAfterMoveBoard.y)
  }
    
  test("possible moves") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(3, 0 , 2)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(5, 0, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(6, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(7, 2, 3)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(8, 3, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(9, 0, 4)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(10, 3, 4)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)    
    println("This is the original board")
    println(board)
    
    val possibleMoves = board.possibleMoves
    println(possibleMoves)
    
    for(possibleMove <- possibleMoves) {
      val tmpBoard = Board(board, possibleMove)
      
      println("Board after move")
      println(tmpBoard)
    }
  }
  
  test("priority at row 0") {
    var pieces: List[BoardPiece] = List()
        
    var piece = new BigSquare(0, 0, 0)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)

    assert(board.priority === 0.5)
  }

  test("priority at row 1") {
    var pieces: List[BoardPiece] = List()
        
    var piece = new BigSquare(0, 0, 1)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)

    assert(board.priority === 1.5)
  }

  test("priority at row 2") {
    var pieces: List[BoardPiece] = List()
        
    var piece = new BigSquare(0, 0, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)

    assert(board.priority === 2.5)
  }

  test("priority at row 3") {
    var pieces: List[BoardPiece] = List()
        
    var piece = new BigSquare(0, 0, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)

    assert(board.priority === 3.25)
  }

  test("hashCode") {
    var pieces1: List[BoardPiece] = List()
    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces1 = pieces1 :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces1 = pieces1 :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(3, 0 , 2)
    pieces1 = pieces1 :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(5, 0, 3)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(6, 1, 3)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(7, 2, 3)
    pieces1 = pieces1 :+ piece
    
    piece = new UpRectangle(8, 3, 2)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(9, 0, 4)
    pieces1 = pieces1 :+ piece
    
    piece = new SmallSquare(10, 3, 4)
    pieces1 = pieces1 :+ piece
    
    val board1 = new Board(pieces1)

    var pieces2: List[BoardPiece] = List()

    piece = new SmallSquare(0, 3, 4)
    pieces2 = pieces2 :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces2 = pieces2 :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces2 = pieces2 :+ piece
    
    piece = new SmallSquare(3, 0 , 2)
    pieces2 = pieces2 :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces2 = pieces2 :+ piece
    
    piece = new SmallSquare(5, 0, 3)
    pieces2 = pieces2 :+ piece
    
    piece = new SmallSquare(6, 1, 3)
    pieces2 = pieces2 :+ piece
    
    piece = new SmallSquare(7, 2, 3)
    pieces2 = pieces2 :+ piece
    
    piece = new UpRectangle(8, 3, 2)
    pieces2 = pieces2 :+ piece
    
    piece = new SmallSquare(9, 0, 4)
    pieces2 = pieces2 :+ piece
    
    piece = new UpRectangle(10, 0, 0)
    pieces2 = pieces2 :+ piece
    
    val board2 = new Board(pieces2)
    
    assert(board1.hashCode === board2.hashCode)
  }
}
