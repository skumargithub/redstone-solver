package redstone.solver

import scala.collection.mutable.HashSet
import redstone.Board

object DepthFirstSearchSolver {

  val movesAlreadyExplored: HashSet[Board] = HashSet()    
  var solved: Boolean = false;
  var solution: Board = null;
  
  var maxDepthToSearch: Int = 500
  
  def dfs(currentBoard: Board, currentDepth: Int = 0): Unit = {
  
    if(!solved && currentDepth <= maxDepthToSearch && !movesAlreadyExplored.contains(currentBoard)) {
      
      movesAlreadyExplored += currentBoard

      if(currentBoard.isSolution) {
        println("We have a solution! current iteration count: " + movesAlreadyExplored.size)

        solved = true
        solution = currentBoard
      } else {
        for(possibleMove <- currentBoard.possibleMoves) {
          val newBoard = Board(currentBoard, possibleMove)
          if(!movesAlreadyExplored.contains(newBoard)) {
            dfs(newBoard, currentDepth + 1)
          }
        }
      }
    }
  }
  
  def solve(board: Board, depthSearchLimit: Int = 1500): Board = {
    maxDepthToSearch = depthSearchLimit
    
    dfs(board)
    
    solution
  }
}