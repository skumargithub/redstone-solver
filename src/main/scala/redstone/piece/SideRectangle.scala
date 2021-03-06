package redstone.piece

import redstone._
import redstone.Direction._

case class SideRectangle(_id: Int, _x: Int, _y: Int) extends BoardPiece("SideRectangle", _id, _x, _y, deltaX = 2, deltaY = 1) {  

  override def moveTo(direction: Direction): BoardPiece = {
    val movedPosition: (Int, Int) = coordinatesAfterMove(direction)
    
    new SideRectangle(_id, movedPosition._1, movedPosition._2)
  }
}