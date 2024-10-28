package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Color;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Rectangle;
import model.Snapshot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Photo Album class.
 */
class PhotoAlbumTest {
  private PhotoAlbum album;
  private Rectangle r1;
  private Oval o1;
  private Snapshot snapshot1;
  private Snapshot snapshot2;


  @BeforeEach
  void setUp() {
    album = new PhotoAlbum();
    r1 = new Rectangle("R1", 200.0, 200.0, 50.0, 100.0, Color.GREEN);
    Rectangle r2 = new Rectangle();
    o1 = new Oval("O1", 500.0, 100.0, 60.0, 30.0, Color.CYAN);
    Oval o2 = new Oval();
    List<IShape> shapes1 = new ArrayList<>();
    List<IShape> shapes2 = new ArrayList<>();
    shapes1.add(r1);
    shapes1.add(o1);
    shapes2.add(r2);
    shapes2.add(o2);
    String description1 = "selfie1";
    snapshot1 = new Snapshot(shapes1, description1);
    String description2 = "selfie2";
    snapshot2 = new Snapshot(shapes2, description2);
    album.addShape(r1);
    album.addShape(r2);
    album.addShape(o1);
    album.addShape(o2);
//    album.addSnapshot(snapshot1.getDescription());
//    album.addSnapshot(snapshot2.getDescription());
    album.addSnapshot(snapshot1);
    album.addSnapshot(snapshot2);
  }

  /**
   * Test the removeShape() method.
   */
  @Test
  void removeShape() {
    assertEquals(4, album.getShapes().size());
    album.removeShape(r1.getName());
    assertEquals(3, album.getShapes().size());

  }

  /**
   * Test moving a shape.
   */
  @Test
  void moveShape() {
    album.moveShape(r1.getName(), 100, 150);
    assertEquals(100, r1.getPoint().getX(), .001);
    assertEquals(150, r1.getPoint().getY(), .001);
  }

  /**
   * Test resizing a shape.
   */
  @Test
  void scaleShape() {
    album.scaleShape(r1.getName(), 80, 60);
    assertEquals(80, r1.getWidth(), .001);
    assertEquals(60, r1.getHeight(), .001);
  }

  /**
   * Test change the shape color.
   */
  @Test
  void changeShapeColor() {
    album.changeShapeColor(r1.getName(), Color.BLUE);
    assertEquals(Color.BLUE, r1.getColor());
  }

  /**
   * Test getting a shape by its name.
   */
  @Test
  void getShape() {
    assertEquals(r1, album.getShape(r1.getName()));
  }

  /**
   * Test adding a snapshot.
   */
  @Test
  void addSnapshot() {
    assertEquals(2, album.getSnapshots().size());
    Snapshot snapshot1copy = new Snapshot(snapshot1);
    album.addSnapshot(snapshot1copy);
    assertEquals(3, album.getSnapshots().size());

  }

  /**
   * Test removing a snapshot that is in the album.
   */
  @Test
  void removeSnapshotSuccessful() {

    assertEquals(2, album.getSnapshots().size());
    assertEquals(4, album.getShapes().size());
    album.removeSnapshot(snapshot2.getDescription());
    assertEquals(1, album.getSnapshots().size());
  }


  /**
   * Test getting a list of snapshots.
   */
  @Test
  void getSnapshots() {
    List<Snapshot> snapshots = album.getSnapshots();
    assertEquals(2, snapshots.size());
  }


  /**
   * Test printing the snapshots.
   */
  @Test
  void printSnapshots() {
    String expected = "Printing Snapshots\n"
            + snapshot1.toString() + "\n\n"
            + snapshot2.toString() + "\n\n";
    assertEquals(expected, album.printSnapshots());
  }

}