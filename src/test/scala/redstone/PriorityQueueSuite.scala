package redstone

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.collection.mutable.HashSet

import redstone._
import redstone.piece._
import redstone.solver._
import redstone.solver.util._

@RunWith(classOf[JUnitRunner])
class PriorityQueueSuite extends FunSuite {
  test("contains") {
    val movesAlreadyExplored: HashSet[Board] = HashSet()
    
    var pieces1: List[BoardPiece] = List()

    val boardPiece1 = new SmallSquare(0, 0, 0)
    pieces1 = pieces1 :+ boardPiece1
    val board1 = new Board(pieces1)
    
    movesAlreadyExplored.add(board1)
    
    assert(movesAlreadyExplored.contains(board1) === true)
    
    var pieces2: List[BoardPiece] = List()

    val boardPiece2 = new SmallSquare(0, 0, 0)
    pieces2 = pieces2 :+ boardPiece1

    val board2 = new Board(pieces2)

    assert(movesAlreadyExplored.contains(board2) === true)    
  }
}
