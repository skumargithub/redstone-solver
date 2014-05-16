package redstone.solver.levels

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver._
import redstone.solver.util._

@RunWith(classOf[JUnitRunner])
class RealFive extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = !true
  
  // Results
  //
  // priorityQ: 307 moves,  2131 iterations
  // DFS      : ??? moves, ????? iterations
  // BFS      :  46 moves, 22828 iterations
  
  // |b  b|
  // |^bb^|
  // |UbbU|
  // |^BB^|
  // |UBBU|
  
  test("level 5.0 - Roselite") {
    var pieces: List[BoardPiece] = List()
    
    // Row 0    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    // Row 2    
    piece = new UpRectangle(3, 0, 2)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(4, 1, 2)
    pieces = pieces :+ piece

    piece = new SmallSquare(5, 2, 2)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(6, 3, 2)
    pieces = pieces :+ piece
    
    // Row 3    
    piece = new SmallSquare(7, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(8, 2, 3)
    pieces = pieces :+ piece

    // Row 4
    piece = new SmallSquare(9, 0, 4)
    pieces = pieces :+ piece

    piece = new SmallSquare(10, 3, 4)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)

    assert(solutionBoard.isDefined && solutionBoard.get.isSolution)
  }
}