package redstone.piece

import redstone._
import redstone.Direction._

case class SmallSquare(_id: Int, _x: Int, _y: Int) extends BoardPiece("SmallSquare", _id, _x, _y, deltaX = 1, deltaY = 1) {
  override def moveTo(direction: Direction): BoardPiece = {
    val movedPosition: (Int, Int) = coordinatesAfterMove(direction)
    
    new SmallSquare(_id, movedPosition._1, movedPosition._2)
  }
}