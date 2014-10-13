package redstone.solver.levels

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver.util._
import redstone.solver._

@RunWith(classOf[JUnitRunner])
class Escape extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  //
  // |<>BB|
  // |bbBB|
  // |bb<>|
  // |  <>|

  test("level 20 - Time to Escape") {
    // This is from the game "Time To Escape" for the android
    // Involves solving a board in level 20
    var pieces: List[BoardPiece] = List()
    
    // Row 0    
    var piece: BoardPiece = new SideRectangle(0, 2, 0)
    pieces = pieces :+ piece
    
    // Row 1
    piece = new SmallSquare(1, 0 , 1)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(2, 1, 1)
    pieces = pieces :+ piece

    piece = new SideRectangle(3, 2, 1)
    pieces = pieces :+ piece
    
    // Row 2    
    piece = new SmallSquare(4, 0, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(5, 1, 2)
    pieces = pieces :+ piece
    
    piece = new BigSquare(6, 2, 2)
    pieces = pieces :+ piece
    
    // Row 3    
    piece = new SideRectangle(7, 0, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces, 3, 3, 0, 0)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)

    System.err.println("Escape is done!")

    assert(solutionBoard.isDefined && solutionBoard.get.isSolution)
  }
}
