package redstone.solver.levels

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver.util._
import redstone.solver._

@RunWith(classOf[JUnitRunner])
class Four extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  // Results
  //
  // priorityQ: 124 moves,   852 iterations
  // DFS      : 995 moves, 28050 iterations
  // BFS      :  56 moves, 58199 iterations
  
  test("level") {
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
    
    piece = new SmallSquare(5, 3, 2)
    pieces = pieces :+ piece
    
    // Row 3    
    piece = new SmallSquare(6, 0, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(7, 1, 3)
    pieces = pieces :+ piece
    
    piece = new SmallSquare(8, 2, 3)
    pieces = pieces :+ piece

    piece = new SmallSquare(9, 3, 3)
    pieces = pieces :+ piece
    
    // Row 4
    piece = new SmallSquare(10, 0, 4)
    pieces = pieces :+ piece

    piece = new SmallSquare(11, 3, 4)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Board = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)
  }
}
