package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Point2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Point2D class.
 */
class Point2DTest {
  private Point2D p1;
  private Point2D p2;

  @BeforeEach
  void setUp() {
    p1 = new Point2D(25, 52);
    p2 = new Point2D();
  }

  @Test
  void testPrimaryConstructor() {
    assertEquals(25, p1.getX(), .001);
    assertEquals(52, p1.getY(), .001);
  }

  @Test
  void testDefaultConstructor() {
    assertEquals(0, p2.getX(), .001);
    assertEquals(0, p2.getY(), .001);
  }

  @Test
  void testCopyConstructor() {
    Point2D p3 = new Point2D(p1);
    assertEquals(25, p3.getX(), .001);
    assertEquals(52, p3.getY(), .001);
  }


  @Test
  void setX() {
    p1.setX(41);
    assertEquals(41, p1.getX());
  }

  @Test
  void setY() {
    p1.setY(17);
    assertEquals(17, p1.getY());
  }

  @Test
  void testEquals() {
    Point2D p3 = new Point2D(100, 300);
    Point2D p4 = new Point2D(100, 300);
    assertEquals(p3, p4);
    assertEquals(p3.hashCode(), p4.hashCode());
  }
}