package view

import java.awt.{Stroke, Graphics2D}

import scala.swing.Font

/** Has convenience methods for drawing complex graphics. */
object Draw {

  /** Draws a string of a certain size centered on coordinate
    *
    * @param s string to be rendered
    * @param size font size
    * @param x horizontal center of text
    * @param y vertical center of text
    * @param g graphics context to render against - must be implicit
    */
  def stringCenter(s: String, size: Int, x: Int, y: Int)(implicit g: Graphics2D) = {
    val oldFont = g.getFont
    val newFont = new Font(oldFont.getName, oldFont.getStyle, size)
    g.setFont(newFont)
    val strWidth = g.getFontMetrics.stringWidth(s)
    g.drawString(s, x/2 - strWidth/2, y)
    g.setFont(oldFont)
  }

}
