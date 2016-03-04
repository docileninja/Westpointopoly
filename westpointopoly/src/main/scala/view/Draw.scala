package view

import java.awt.{Stroke, Graphics2D}

import scala.swing.Font

/**
  * Created by x87039 on 3/4/2016.
  */
object Draw {
  def stringCenter(s: String, size: Int, w: Int, y: Int)(implicit g: Graphics2D) = {
    val oldFont = g.getFont
    val newFont = new Font(oldFont.getName, oldFont.getStyle, size)
    g.setFont(newFont)
    val strWidth = g.getFontMetrics.stringWidth(s)
    g.drawString(s, w/2 - strWidth/2, y)
    g.setFont(oldFont)
  }
}
