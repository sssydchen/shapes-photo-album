package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A concrete class extending the AbstractShape, representing an oval-like shape.
 * It includes additional properties specific to rectangles, such as x radius and y radius.
 */
public class Oval extends AbstractShape{

  public Oval(String name, double x, double y, double width, double height, Color color)
          throws IllegalArgumentException{
    super(name, x, y, width, height, color);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Radius must be positive.");
    }

  }

  /**
   * Default constructor.
   */
  public Oval() {
    super();
  }

  /**
   * Copy constructor.
   * @param other the oval to be copied.
   */
  public Oval(Oval other) {
    super(other);
  }

  @Override
  public IShape copy() {
    return new Oval(this);
  }

  @Override
  public String toString() {
    return String.format("""
                    Name: %s
                    Type: oval
                    Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, Color: (%.1f,%.1f,%.1f)""",
            this.getName(), this.getPoint().getX(), this.getPoint().getY(),
            this.getWidth(), this.getHeight(), this.getColor().getRed(),
            this.getColor().getGreen(), this.getColor().getBlue());
  }
}
