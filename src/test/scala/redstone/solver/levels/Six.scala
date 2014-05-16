package redstone.solver.levels

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver._
import redstone.solver.util._

@RunWith(classOf[JUnitRunner])
class Six extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  // Results
  //
  // priorityQ:  586 moves, 16275 iterations
  // DFS      : 1474 moves,  4198 iterations
  // BFS      :  138 moves, 30321 iterations
  
  // | <> |
  // |b<>b|
  // |b<>b|
  // |^BB^|
  // |UBBU|
  
  test("level 6") {
    var pieces: List[BoardPiece] = List()
    
    // Row 0
    var piece: BoardPiece = new UpRectangle(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new BigSquare(1, 1, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 0)
    pieces = pieces :+ piece
    
    // Row 1
    // Empty
    
    // Row 2
    piece = new SmallSquare(3, 0 , 2)
    pieces = pieces :+ piece
    
    piece = new SideRectangle(4, 1, 2)
    pieces = pieces :+ piece

    piece = new SmallSquare(5, 3 , 2)
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
