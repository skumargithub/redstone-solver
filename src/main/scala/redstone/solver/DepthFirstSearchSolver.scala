package redstone.solver

import scala.collection.mutable.HashSet
import redstone.Board

object DepthFirstSearchSolver {

  val movesAlreadyExplored: HashSet[Board] = HashSet()    

  var solution: Option[Board] = None;
  
  var maxDepthToSearch: Int = 500
  
  def dfs(currentBoard: Board, currentDepth: Int = 0): Option[Board] = {
  
    if(!solution.isDefined && currentDepth <= maxDepthToSearch && !movesAlreadyExplored.contains(currentBoard)) {
      
      movesAlreadyExplored += currentBoard

      if(currentBoard.isSolution) {
        println("We have a solution! current iteration count: " + movesAlreadyExplored.size)

        solution = Some(currentBoard)
      } else {
        for(possibleMove <- currentBoard.possibleMoves) {
          val newBoard = Board(currentBoard, possibleMove)
          if(!movesAlreadyExplored.contains(newBoard)) {
            dfs(newBoard, currentDepth + 1)
          }
        }
      }
    }
    
    solution
  }
  
  def solve(board: Board, depthSearchLimit: Int = 1500): Option[Board] = {
    maxDepthToSearch = depthSearchLimit
    
    dfs(board)
  }
}