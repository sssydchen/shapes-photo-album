package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Color;
import model.Oval;
import model.Rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for shapes.
 */
class IShapeTest {
  private Rectangle r1;
  private Rectangle r2;
  private Oval o1;
  private Oval o2;


  @BeforeEach
  void setUp() {
    r1 = new Rectangle("R1", 120.0, 230.0, 80.0, 70.0, Color.RED);
    o1 = new Oval("O1", 499.0, 186.0, 60.0, 30.0, Color.CYAN);
    r2 = new Rectangle();
    o2 = new Oval();
  }

  /**
   * Test the copy constructor.
   */
  @Test
  void testCopyConstructor() {
    Rectangle rect = new Rectangle("R1", 120.0, 230.0, 80.0, 70.0, Color.RED);
    Rectangle copy = new Rectangle(rect);

    assertEquals(rect.getPoint(), copy.getPoint());
    assertEquals(rect.getName(), copy.getName());
    assertEquals(rect.getColor(), copy.getColor());
    assertEquals(rect.getHeight(), copy.getHeight());
    assertEquals(rect.getWidth(), copy.getWidth());

  }

  /**
   * Test the invalid setup.
   */
  @Test
  void invalidSetUp() {
    assertThrows(IllegalArgumentException.class, () ->
            new Rectangle("", 120.0, 230.0, 80.0, 70.0, Color.RED));
    assertThrows(IllegalArgumentException.class, () ->
            new Rectangle("what", -30, 60.0, 80.0, 70.0, Color.RED));
    assertThrows(IllegalArgumentException.class, () ->
            new Rectangle("is", 90, 60.0, 0.0, -70.0, Color.BLUE));
    assertThrows(IllegalArgumentException.class, () ->
            new Oval("this", 20, -50.0, 30.0, -50.0, Color.BLUE));
    assertThrows(IllegalArgumentException.class, () ->
            new Oval("that", 90, 60.0, 30.0, -50.0, Color.BLUE));
  }

  /**
   * Test default constructor.
   */
  @Test
  void testDefaultInitialization() {
    assertEquals(0.0, r2.getPoint().getX(), .001);
    assertEquals(0.0, r2.getPoint().getY(), .001);
    assertEquals("unknown name", r2.getName());
    assertEquals(Color.WHITE, r2.getColor());
    assertEquals(50.0, r2.getWidth());
    assertEquals(50.0, r2.getHeight());
    assertEquals(50.0, o2.getWidth());
    assertEquals(50.0, o2.getHeight());
  }

  /**
   * Test the setName() method.
   */
  @Test
  void setName() {
    r1.setName("doge");
    assertEquals("doge", r1.getName());
  }

  /**
   * Test the setWidth() method.
   */
  @Test
  void setWidth() {
    r1.setWidth(300);
    assertEquals(300, r1.getWidth());
  }

  /**
   * Test the setHeight() method.
   */
  @Test
  void setHeight() {
    r1.setHeight(300);
    assertEquals(300, r1.getHeight());
  }

  /**
   * Test the getPoint() method.
   */
  @Test
  void getPoint() {
    assertEquals(120.0, r1.getPoint().getX(), .001);
    assertEquals(230.0, r1.getPoint().getY(), .001);
  }

  /**
   * Test the getColor() method.
   */
  @Test
  void getColor() {
    assertEquals(Color.RED, r1.getColor());
    assertEquals(Color.CYAN, o1.getColor());
  }

  /**
   * Test move to a valid position.
   */
  @Test
  void validMove() {
    r1.move(312.2, 450.789);
    assertEquals(312.2, r1.getPoint().getX(), .001);
    assertEquals(450.789, r1.getPoint().getY(), .001);
  }

  /**
   * Test move to an invalid position.
   */
  @Test
  void invalidMove() {
    assertThrows(IllegalArgumentException.class, () -> r1.move(-120, -370));
  }

  /**
   * Test changing the color of the shape.
   */
  @Test
  void changeColor() {
    r1.changeColor(Color.MAGENTA);
    assertEquals(Color.MAGENTA, r1.getColor());
  }


  /**
   * Test the toString() method.
   */
  @Test
  void testToString() {
    assertEquals("Name: R1\n"
                    + "Type: rectangle\n"
                    + "Min corner: (120.0,230.0), Width: 80.0, Height: 70.0, Color: (1.0,0.0,0.0)",
            r1.toString());
    assertEquals("Name: O1\n"
                    + "Type: oval\n"
                    + "Center: (499.0,186.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,1.0,1.0)",
            o1.toString());
  }

  /**
   * Test the equals method.
   */
  @Test
  void testEquals() {
    Rectangle r3 = new Rectangle("Rect", 120.0, 230.0, 80.0, 70.0, Color.RED);
    Rectangle r4 = new Rectangle("Rect", 120.0, 230.0, 80.0, 70.0, Color.RED);
    assertEquals(r3, r4);
    assertEquals(r3.hashCode(), r4.hashCode());
  }



}