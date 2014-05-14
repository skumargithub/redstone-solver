package redstone.piece

import redstone.Direction._
import redstone.Board

abstract class BoardPiece(val description: String, val id: Int, val x: Int, val y: Int, val deltaX: Int, val deltaY: Int) {
  
  def canMoveTo(board: Board, direction: Direction): Boolean = {
    var positionsToCheck = List[(Int, Int)]()
    
    direction match {
      case Up => {
        for(xpos <- x until x + deltaX) {
          positionsToCheck = (xpos, y + deltaY) :: positionsToCheck
        }
      }
      case Down => {
        for(xpos <- x until x + deltaX) {
          positionsToCheck = (xpos, y - 1) :: positionsToCheck
        }
      }
      
      case Right => {
        for(ypos <- y until y + deltaY) {
          positionsToCheck = (x + deltaX, ypos) :: positionsToCheck
        }
      }
      
      case Left => {
        for(ypos <- y until y + deltaY) {
          positionsToCheck = (x - 1, ypos) :: positionsToCheck
        }
      }
    }
    
    positionsToCheck.foldLeft(true) { (isItTrue, position) => if (!isItTrue) false else board.isPositionOpen(position._1, position._2) }
  }
  
  def coordinatesAfterMove(direction: Direction): (Int, Int) = {
    val X: Int = direction match {
      case Right => x + 1 
      case Left => x - 1
      case _ => x
    }
    
    val Y: Int = direction match {
      case Up => y + 1 
      case Down => y - 1
      case _ => y
    }
    
    (X, Y) 
  }
  
  def moveTo(direction: Direction): BoardPiece
  
  def isPositionOpen(xPos: Int, yPos: Int): Boolean = {
    val result = (xPos < x || xPos >= x + deltaX) || (yPos < y || yPos >= y + deltaY)
    
    result
  }
  
  override def toString: String = {
    description + " " + id + " -> (" + x + ", " + y + ")"
  }
}