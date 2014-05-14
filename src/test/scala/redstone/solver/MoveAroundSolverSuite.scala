package redstone.solver

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import redstone._
import redstone.piece._
import redstone.solver.util._
import redstone.solver._

@RunWith(classOf[JUnitRunner])
class MoveAroundSolverSuite extends FunSuite {
  
  // Which solver do we use?
  val priorityQueueSolver = true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = !true
  
  test("move around UpRectangle 1") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new BigSquare(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(1, 0, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Board = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }

    Utility.printSolution(board, solutionBoard)
    
//    assert(solutionBoard.movesSoFar.size === 4)
  }
  
  test("move around UpRectangle 2") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new BigSquare(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(1, 1, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Board = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }

    Utility.printSolution(board, solutionBoard)

//    assert(solutionBoard.movesSoFar.size === 5) // least # of moves = 5
  }
  
  test("move around 2 UpRectangles") {
    var pieces: List[BoardPiece] = List()
    
    var piece: BoardPiece = new BigSquare(0, 0, 0)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(1, 1, 2)
    pieces = pieces :+ piece
    
    piece = new UpRectangle(2, 3, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Board = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }

    Utility.printSolution(board, solutionBoard)

//    assert(solutionBoard.movesSoFar.size === 5) // least # of moves = 5
  }
}
