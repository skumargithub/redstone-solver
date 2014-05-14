package redstone

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone.piece._
import redstone._

@RunWith(classOf[JUnitRunner])
class BigSquareSuite extends FunSuite {
  test("BigSquare basicMove 1") { // In lower left corner, only possible to move Up and Right
    val piece = new BigSquare(0, 0, 0)
    
    var pieces: List[BoardPiece] = List()
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === false)
    assert(piece.canMoveTo(board, Direction.Left) === false)    
  }
  
  test("BigSquare basicMove 2") { // In lower right corner, only possible to move Up and Left
    val piece = new BigSquare(0, 2, 0)

    var pieces: List[BoardPiece] = List()
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === false)
    assert(piece.canMoveTo(board, Direction.Down) === false)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }

  test("BigSquare basicMove 3") { // In top right corner, only possible to move Down and Left
    val piece = new BigSquare(0, 2, 3)

    var pieces: List[BoardPiece] = List()
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === false)
    assert(piece.canMoveTo(board, Direction.Right) === false)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }
  
  test("BigSquare basicMove 4") { // In top left corner, only possible to move Down and Right
    var pieces: List[BoardPiece] = List()

    val piece = new BigSquare(0, 0, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === false)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === false)    
  }
  
  test("BigSquare basicMove 5") {	// In the middle of the board, should be possible to move in all directions
    var pieces: List[BoardPiece] = List()

    val piece = new BigSquare(0, 1, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }
  
  test("BigSquare with collision with BigSquare") {
    var pieces: List[BoardPiece] = List()
    
    val piece1 = new BigSquare(0, 0, 0)
    pieces = pieces :+ piece1

    val piece2 = new BigSquare(1, 2, 0)
    pieces = pieces :+ piece2
    
    val board = new Board(pieces)
     
    assert(piece1.canMoveTo(board, Direction.Right) === false)
    assert(piece2.canMoveTo(board, Direction.Left) === false)    
  }
}
