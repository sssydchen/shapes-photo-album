package model;


/**
 * An abstract class that implements the IShape methods.
 */
public abstract class AbstractShape implements IShape{
  private String name;
  private Point2D point;
  private Color color;
  private double width;
  private double height;
  private final double minX = 0.0, maxX = 2000.0; // default screen size
  private final double minY = 0.0, maxY = 2000.0; // edit this according to GUI

  public AbstractShape(String name, double x, double y, double width, double height, Color color) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
    validateCoordinates(x, y);
    this.name = name;
    this.point = new Point2D(x, y);
    this.color = color;
    this.width = width;
    this.height = height;
  }

  @Override
  public void validateCoordinates(double x, double y) throws IllegalArgumentException {
    if (x < minX || x > maxX || y < minY || y > maxY) {
      throw new IllegalArgumentException("Point coordinates should be within the range: " +
              String.format("X(%.1f, %.1f), Y(%.1f, %.1f))", minX, maxX, minY, maxY));
    }
  }

  /**
   * The default constructor for abstract shape.
   */
  public AbstractShape() {
    this("unknown name", 0,0,50, 50, Color.WHITE);
  }

  /**
   * Copy constructor.
   * @param other the shape to be copied
   */
  public AbstractShape(IShape other) {
    this.name = other.getName();
    this.color = other.getColor();
    this.height = other.getHeight();
    this.width = other.getWidth();
    this.point = new Point2D(other.getPoint());
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Point2D getPoint() {
    return this.point;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void move(double x, double y) throws IllegalArgumentException{
    validateCoordinates(x, y);
    this.point.setX(x);
    this.point.setY(y);
  }

  @Override
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }



}
