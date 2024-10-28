package view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import model.IShape;
import model.Rectangle;
import model.Oval;

/**
 * This class defines the drawing methods for shapes and paint a list of shapes.
 */
public class DrawPanel extends JPanel {
  private List<IShape> shapes;

  /**
   * Construct a draw panel with a list of shapes.
   * @param shapes a list of shapes.
   */
  public DrawPanel(List<IShape> shapes) {
    this.shapes = shapes;
  }

  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.BLUE);
    for (IShape shape : shapes) {
      if (shape instanceof Rectangle rect) {
        drawRectangle(g, rect);
      } else if (shape instanceof Oval oval) {
        drawOval(g, oval);
      }
    }
  }

  private void drawRectangle(Graphics g, Rectangle rect) {
    java.awt.Color awtColor = new java.awt.Color((float) rect.getColor().getRed(),
            (float) rect.getColor().getGreen(),
            (float) rect.getColor().getBlue());
    g.setColor(awtColor);
    g.fillRect((int) rect.getPoint().getX(),
            (int) rect.getPoint().getY(),
            (int) rect.getWidth(),
            (int) rect.getHeight());
  }

  private void drawOval(Graphics g, Oval oval) {
    g.setColor(oval.getColor().toAWTColor());
    g.fillOval((int) oval.getPoint().getX() - (int) oval.getWidth(),
            (int) oval.getPoint().getY() - (int) oval.getHeight(),
            (int) oval.getWidth() * 2,
            (int) oval.getHeight() * 2);
  }
}

