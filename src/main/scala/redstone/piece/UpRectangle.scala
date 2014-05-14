package redstone.piece

import redstone._
import redstone.Direction._

case class UpRectangle(_id: Int, _x: Int, _y: Int) extends BoardPiece("UpRectangle", _id, _x, _y, deltaX = 1, deltaY = 2) {

  override def moveTo(direction: Direction): BoardPiece = {
    val movedPosition: (Int, Int) = coordinatesAfterMove(direction)
    
    new UpRectangle(_id, movedPosition._1, movedPosition._2)
  }
}