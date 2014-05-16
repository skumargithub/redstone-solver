package redstone.solver

import scala.collection.mutable.HashSet
import scala.collection.mutable.Queue
import redstone.Board

object BreadthFirstSearchSolver {
  def solve(board: Board): Option[Board] = {
    
    val movesAlreadyExplored: HashSet[Board] = HashSet()    
    
    val queue = new Queue[Board]()
    queue.enqueue(board)
    
    var solution: Option[Board] = None
    
    while(!queue.isEmpty && !solution.isDefined) {
      val currentBoard = queue.dequeue

      if(!movesAlreadyExplored.contains(currentBoard)) {
        movesAlreadyExplored += currentBoard
      
        val numberOfIterations = movesAlreadyExplored.size

        if(currentBoard.isSolution) {
          println("We have a solution! current iteration count: " + numberOfIterations)        
          
          solution = Some(currentBoard)
        }

        val possibleMoves = currentBoard.possibleMoves
        for(possibleMove <- possibleMoves) {
          val newBoard = Board(currentBoard, possibleMove)
          if(!movesAlreadyExplored.contains(newBoard)) {
            queue.enqueue(newBoard)
          }
        }
      
        if(numberOfIterations % 200 == 0) println("Current iteration #: " + numberOfIterations)
      }
    }
    
    solution
  }
}