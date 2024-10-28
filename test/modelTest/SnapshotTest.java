package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import model.Color;
import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for the Snapshot class.
 */
class SnapshotTest {
  private Snapshot snapshot;
  private Rectangle rectangle;
  private Oval oval;
  private List<IShape> shapes;

  @BeforeEach
  void setUp() {
    shapes = new ArrayList<>();
    rectangle = new Rectangle("R1", 200.0, 200.0, 50.0, 100.0, Color.WHITE);
    oval = new Oval("O1", 500.0, 100.0, 60.0, 30.0, Color.BLACK);
    shapes.add(rectangle);
    shapes.add(oval);
    String description = "A new selfie";
    snapshot = new Snapshot(shapes, description);
  }

  /**
   * Test the copy constructor.
   */
  @Test
  public void testCopyConstructor() {
    Snapshot copy = new Snapshot(snapshot);

    assertEquals(snapshot.getDescription(), copy.getDescription());
    assertFalse(copy.getShapes().isEmpty());
    assertEquals(snapshot.getShapes().size(), copy.getShapes().size());
  }

  /**
   * Test the getShapes() method.
   */
  @Test
  void getShapes() {
    assertEquals(2, snapshot.getShapes().size());
    assertEquals(rectangle, snapshot.getShapes().get(0));
    assertEquals(oval, snapshot.getShapes().get(1));
  }

  /**
   * Test the getDescription() method.
   */
  @Test
  void getDescription() {
    assertEquals("A new selfie", snapshot.getDescription());
  }

  /**
   * Test the toString() method.
   */
  @Test
  void testToString() {
    assertEquals("Snapshot ID: " + snapshot.getID() +"\n"
                    + "Timestamp: " + snapshot.getFormattedTimestamp() + "\n"
                    + "Description: A new selfie\n" + "Shape Information:\n" + "Name: R1\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, "
                    + "Height: 100.0, Color: (1.0,1.0,1.0)\n\n" + "Name: O1\n" + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (0.0,0.0,0.0)\n\n",
            snapshot.toString());
  }


  /**
   * Test if a taken snapshot is unmodifiable.
   */
  @Test
  void snapshotUnmodifiable() {
    List<IShape> shapes = new ArrayList<>();
    Rectangle rectangle2 = new Rectangle(
            "R2", 300.0, 400.0, 50.0, 100.0, Color.YELLOW);
    shapes.add(rectangle2);
    Snapshot snapshot = new Snapshot(shapes, "Immutable Test");

    // try modifying the original list after taking the snapshot
    Oval oval2 = new Oval("R3", 500.0, 200.0, 40.0, 70.0, Color.BLUE);
    shapes.add(oval2);

    assertEquals(1, snapshot.getShapes().size());
    assertEquals(rectangle2, snapshot.getShapes().getFirst());
  }

  /**
   * Test if the shapes list can successfully track the state change of move.
   */
  @Test
  void takeSnapshotAfterMove() {
    oval.move(600, 800);
    // after moving, the center of oval presented in the snapshot changes.
    assertEquals("Snapshot ID: " + snapshot.getID() + "\n"
                    + "Timestamp: " + snapshot.getFormattedTimestamp() + "\n"
                    + "Description: A new selfie\n"
                    + "Shape Information:\n"
                    + "Name: R1\n" + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, "
                    + "Height: 100.0, Color: (1.0,1.0,1.0)\n\n"
                    + "Name: O1\n" + "Type: oval\n"
                    + "Center: (600.0,800.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (0.0,0.0,0.0)\n\n",
            snapshot.toString());
  }

  /**
   * Test if the shapes list can track the state change of removing a shape.
   */
  @Test
  void takeSnapshotAfterRemove() {
    shapes.remove(rectangle);
    assertEquals(1, shapes.size());

    Snapshot newsnapshot = new Snapshot(shapes, "selfie after removing rectangle");
    assertEquals("Snapshot ID: " + newsnapshot.getID() +"\n"
                    + "Timestamp: " + newsnapshot.getFormattedTimestamp() + "\n"
                    + "Description: selfie after removing rectangle\n"
                    + "Shape Information:\n"
                    + "Name: O1\n" + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (0.0,0.0,0.0)\n\n",
            newsnapshot.toString());
  }



  /**
   * Test if the shapes list can track the state change of changing color.
   */
  @Test
  void takeSnapshotAfterChangingColor() {
    oval.changeColor(Color.BLUE);
    // after changing color, the Color of oval should be (0,0, 0,0, 1,0)
    assertEquals("Snapshot ID: " + snapshot.getID() + "\n"
                    + "Timestamp: " + snapshot.getFormattedTimestamp() + "\n"
                    + "Description: A new selfie\n"
                    + "Shape Information:\n"
                    + "Name: R1\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, "
                    + "Height: 100.0, Color: (1.0,1.0,1.0)\n\n"
                    + "Name: O1\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (0.0,0.0,1.0)\n\n",
            snapshot.toString());
  }

}