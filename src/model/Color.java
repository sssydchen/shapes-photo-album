package model;

/**
 * Represents the color of shapes.
 * Defines basic colors and allows for setting custom colors through RGB values.
 */

public class Color {
  private final double red;
  private final double green;
  private final double blue;

  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  // Getters for each color component
  public double getRed() {
    return red;
  }

  public double getGreen() {
    return green;
  }

  public double getBlue() {
    return blue;
  }

  public java.awt.Color toAWTColor() {
    return new java.awt.Color((float) red, (float) green, (float) blue);
  }

  public static final Color RED = new Color(1.0, 0.0, 0.0);
  public static final Color GREEN = new Color(0.0, 1.0, 0.0);
  public static final Color BLUE = new Color(0.0, 0.0, 1.0);
  public static final Color YELLOW = new Color(1.0, 1.0, 0.0);
  public static final Color CYAN = new Color(0.0, 1.0, 1.0);
  public static final Color MAGENTA = new Color(1.0, 0.0, 1.0);
  public static final Color WHITE = new Color(1.0, 1.0, 1.0);
  public static final Color BLACK = new Color(0.0, 0.0, 0.0);
}

