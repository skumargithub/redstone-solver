package redstone.solver

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import redstone._
import redstone.piece._
import redstone.solver._

import redstone.solver.util._

@RunWith(classOf[JUnitRunner])
class SolverSuite extends FunSuite {

  // Which solver do we use?
  val priorityQueueSolver = !true
  val depthFirstSearchSolver = !true
  val breadthFirstSearchSolver = true
  
  test("solve in 0 steps") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 1, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)
    
    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 0)
  }
  
  test("solve in 1 step, Up") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 1, 2)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)
    
    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 1)
  }
  
  test("solve in 1 step, Right") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 0, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)    
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)    
    
    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 1)
  }
  
  test("solve in 1 step, Left") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 2, 3)
    pieces = pieces :+ piece
    
    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }
    
    Utility.printSolution(board, solutionBoard)
    
    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 1)
  }
  
  test("solve in 2 steps") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 1, 1)
    pieces = pieces :+ piece

    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }

    Utility.printSolution(board, solutionBoard)

    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 2)
  }
  
  test("solve in 3 steps") {
    var pieces: List[BoardPiece] = List()
    
    val piece = new BigSquare(0, 1, 0)
    pieces = pieces :+ piece

    val board = new Board(pieces)
    val solutionBoard: Option[Board] = {
      if(priorityQueueSolver) PriorityQueueSolver.solve(board)
      else if(depthFirstSearchSolver) DepthFirstSearchSolver.solve(board)
      else BreadthFirstSearchSolver.solve(board)
    }

    Utility.printSolution(board, solutionBoard)

    assert(solutionBoard.isDefined && solutionBoard.get.movesSoFar.size === 3)
  }    
}
