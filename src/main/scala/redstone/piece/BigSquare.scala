package redstone.piece

import redstone._
import redstone.Direction._

case class BigSquare(_id: Int, _x: Int, _y: Int) extends BoardPiece("BigSquare", _id, _x, _y, deltaX = 2, deltaY = 2) {

  override def moveTo(direction: Direction): BoardPiece = {
    val movedPosition: (Int, Int) = coordinatesAfterMove(direction)
    
    new BigSquare(_id, movedPosition._1, movedPosition._2)
  }
}