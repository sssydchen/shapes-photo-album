package model;

/**
 * Defines common operations for shapes.
 */
public interface IShape {
  /**
   * Validate the coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @throws IllegalArgumentException if x or y is out of boundary
   */
  void validateCoordinates(double x, double y) throws IllegalArgumentException;


  /**
   * Get the position of the shape.
   * @return the point that represents its position.
   */
  Point2D getPoint();

  /**
   * Get the color of the shape.
   * @return the color of the shape.
   */
  Color getColor();

  /**
   * Get the name of the shape.
   * @return the name of the shape.
   */
  String getName();

  /**
   * Set the name of the shape.
   * @param name the name of the shape.
   */
  void setName(String name);

  /**
   * Move to another position.
   * @param x the target x-coordinate
   * @param y the target y-coordinate
   */
  void move(double x, double y);

  /**
   * Change the color of the shape.
   * @param newColor the new color.
   */
  void changeColor(Color newColor);


  /**
   * Set the width of the shape.
   * @param width the width of the shape.
   */
  void setWidth(double width);

  /**
   * Set the height of the shape.
   * @param height the height of the shape.
   */
  void setHeight(double height);

  /**
   * Get the width of the shape.
   * @return the width of the shape.
   */
  double getWidth();

  /**
   * Get the height of the shape.
   * @return the height of the shape.
   */
  double getHeight();

  /**
   * A copy method.
   * @return a copied shape.
   */
  IShape copy();

}
