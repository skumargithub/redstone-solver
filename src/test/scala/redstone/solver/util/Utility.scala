package redstone.solver.util

import redstone._

object Utility {
  def printSolution(initialBoard: Board, solutionBoard: Board) = {
    println("\nThis is how the initial board looks like")
    println(initialBoard)
    
    val moves = solutionBoard.movesSoFar.reverse
    println("These are the moves for the solution, # of moves: " + moves.size)
    var solutionByStepBoard = initialBoard
    
    var currentMove: Int = 1
    for(move <- moves) {
      println(currentMove + ": Move piece id: " + move._1 + " in direction: " + move._2)
      solutionByStepBoard = Board(solutionByStepBoard, move)
      println(solutionByStepBoard)
      
      currentMove += 1
    }
    
    println("\nThis is how the final board looks like")
    println(solutionBoard)
  }
}
