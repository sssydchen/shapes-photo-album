package model;

import java.util.Objects;

/**
 * A concrete class extending the AbstractShape, representing a rectangle-like shape.
 * It includes additional properties specific to rectangles, such as width and height.
 */
public class Rectangle extends AbstractShape {

  public Rectangle(String name, double x, double y, double width, double height, Color color) throws IllegalArgumentException
  {
    super(name, x, y, width, height, color);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
  }

  /**
   * Default constructor.
   */
  public Rectangle() {
    super();
  }


  public Rectangle(Rectangle other) {
    super(other);
  }

  @Override
  public IShape copy() {
    return new Rectangle(this);
  }

  @Override
  public String toString() {
    return String.format("""
                    Name: %s
                    Type: rectangle
                    Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)""",
            this.getName(), this.getPoint().getX(), this.getPoint().getY(),
            this.getWidth(), this.getHeight(), this.getColor().getRed(),
            this.getColor().getGreen(), this.getColor().getBlue());
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Rectangle other = (Rectangle) obj;
    return Objects.equals(this.getName(), other.getName())
            && Objects.equals(this.getHeight(), other.getHeight())
            && Objects.equals(this.getWidth(), other.getWidth())
            && Objects.equals(this.getColor(), other.getColor())
            && Objects.equals(this.getPoint(), other.getPoint());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getHeight(), getColor(), getWidth(), getPoint(), getName());
  }

}
