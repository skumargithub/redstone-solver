package redstone

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._

@RunWith(classOf[JUnitRunner])
class UpRectangleSuite extends FunSuite {
  test("UpRectangle basicMove 1") { // In lower left corner, only possible to move Up and Right
    var pieces: List[BoardPiece] = List()

    val piece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === false)
    assert(piece.canMoveTo(board, Direction.Left) === false)    
  }
  
  test("UpRectangle basicMove 2") { // In lower right corner, only possible to move Up and Left
    var pieces: List[BoardPiece] = List()

    val piece = new UpRectangle(0, 3, 0)
    pieces = pieces :+ piece

    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === false)
    assert(piece.canMoveTo(board, Direction.Down) === false)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }

  test("UpRectangle basicMove 3") { // In top right corner, only possible to move Down and Left
    var pieces: List[BoardPiece] = List()

    val piece = new UpRectangle(0, 3, 3)
    pieces = pieces :+ piece

    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === false)
    assert(piece.canMoveTo(board, Direction.Right) === false)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }
  
  test("UpRectangle basicMove 4") { // In top left corner, only possible to move Down and Right
    var pieces: List[BoardPiece] = List()

    val piece = new UpRectangle(0, 0, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === false)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === false)    
  }
  
  test("UpRectangle basicMove 5") {	// In the middle of the board, should be possible to move in all directions
    var pieces: List[BoardPiece] = List()

    val piece = new UpRectangle(0, 1, 2)
    pieces = pieces :+ piece

    val board = new Board(pieces)
     
    assert(piece.canMoveTo(board, Direction.Up) === true)
    assert(piece.canMoveTo(board, Direction.Right) === true)
    assert(piece.canMoveTo(board, Direction.Down) === true)
    assert(piece.canMoveTo(board, Direction.Left) === true)    
  }
  
  test("UpRectangle with collision with UpRectangle") {
    var pieces: List[BoardPiece] = List()

    val piece1 = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece1

    val piece2 = new UpRectangle(1, 1, 0)
    pieces = pieces :+ piece2

    val board = new Board(pieces)
    
    assert(piece1.canMoveTo(board, Direction.Right) === false)
    assert(piece2.canMoveTo(board, Direction.Left) === false)    
  }
}
