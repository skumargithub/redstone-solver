package redstone.solver

import scala.collection.mutable.MutableList
import scala.collection.mutable.HashSet
import scala.collection.mutable.PriorityQueue
import redstone.Board

object PriorityQueueSolver {
  def solve(board: Board): Board = {
    
    implicit def boardPriority(b: Board): Ordered[Board] = new Ordered[Board] {
//      def compare(other: Board): Int = b.manhattanPriority compare other.manhattanPriority
      
      def compare(other: Board): Int = b.priority compare other.priority      
    }
    
    val movesAlreadyExplored: HashSet[Board] = HashSet()    
    
    val priorityQ = new PriorityQueue[Board]()
    priorityQ.enqueue(board)
    
    while(!priorityQ.isEmpty) {
      var currentBoard = priorityQ.dequeue

      if(!movesAlreadyExplored.contains(currentBoard)) {
        movesAlreadyExplored.add(currentBoard)

        val numberOfIterations = movesAlreadyExplored.size

        if(currentBoard.isSolution) {
          println("We have a solution! current iteration count: " + numberOfIterations)
          return currentBoard
        }
      
        val possibleMoves = currentBoard.possibleMoves
        for(possibleMove <- possibleMoves) {
          val newBoard = Board(currentBoard, possibleMove)
          if(!movesAlreadyExplored.contains(newBoard)) {        
            priorityQ.enqueue(newBoard)
          }
        }
      
        if(numberOfIterations % 200 == 0) println("Current iteration #: " + numberOfIterations)
      }
    }
    
    board
  }
}