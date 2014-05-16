package redstone.solver.levels

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver._
import redstone.solver.util._

@RunWith(classOf[JUnitRunner])
class Seven extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  // Results
  //
  // priorityQ:  746 moves, 17656 iterations
  // DFS      : 1XXX moves,  4XXX iterations
  // BFS      :  158 moves, 36041 iterations
  
  // | <> |
  // |b<>b|
  // |^<>^|
  // |UBBU|
  // |bBBb|
  
  test("level 7 - Topaz") {
    var pieces: List[BoardPiece] = List()
    
    // Row 0
    var piece: BoardPiece = new SmallSquare(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(2, 3, 0)
    pieces = pieces :+ piece
    
    // Row 1
    piece = new UpRectangle(3, 0, 1)
    pieces = pieces :+ piece

    piece = new UpRectangle(4, 3, 1)
    pieces = pieces :+ piece
    
    // Row 2
    piece = new SideRectangle(5, 1, 2)
    pieces = pieces :+ piece

    // Row 3
    piece = new SmallSquare(6, 0, 3)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(7, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(8, 3, 3)
    pieces = pieces :+ piece
    
    // Row 4
    piece = new SideRectangle(9, 1, 4)
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