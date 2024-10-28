package model;

import java.util.Objects;
public class Point2D {
  private double x, y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Point2D() {
    this.x = this.y = 0;
  }
  public Point2D(Point2D original) {
    this.x = original.x;
    this.y = original.y;
  }

  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }

  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Point2D point = (Point2D) obj;
    return Double.compare(point.x, x) == 0
            && Double.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
