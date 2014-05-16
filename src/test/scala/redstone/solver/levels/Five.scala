package redstone.solver.levels


import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver.util._
import redstone.solver._

@RunWith(classOf[JUnitRunner])
class Five extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  // Results
  //
  // priorityQ: 315 moves,  4066 iterations
  // DFS      : 994 moves, 24587 iterations
  // BFS      :  64 moves, 54107 iterations
  
  // |b  b|
  // |bbb^|
  // |b<>U|
  // |^BB^|
  // |UBBU|
  
  test("level 5") {
    var pieces: List[BoardPiece] = List()
    
    // Row 0    
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    // Row 2    
    piece = new SmallSquare(3, 0, 2)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(5, 3, 2)
    pieces = pieces :+ piece
    
    // Row 3    
    piece = new SmallSquare(6, 0, 3)
    pieces = pieces :+ piece
    
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
