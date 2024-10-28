package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is the IModel interface.
 * It declares methods that outline the operations we can perform on the photo album.
 */
public interface IAlbumModel {

  /**
   * Add a shape to the album.
   * @param shape a shape
   */
  void addShape(IShape shape);

  /**
   * Remove a shape from the album.
   * @param name the name of the shape.
   */
  public void removeShape(String name);

  /**
   * Move shape to a different position.
   * @param name name of shape.
   * @param x x position.
   * @param y y position.
   */
  public void moveShape(String name, double x, double y);

  /**
   * Resize the shape.
   * @param name name of shape
   * @param width width of shape
   * @param height height of shape
   */
  public void scaleShape(String name, double width, double height);

  /**
   * Change the color of the shape
   * @param name name of the shape
   * @param newColor new color
   */
  public void changeShapeColor(String name, Color newColor);

  /**
   * Get the shape by its name.
   * @param name name of the shape.
   * @return a shape.
   */
  public IShape getShape(String name);

  /**
   * Add a snapshot to the album.
   * @param snapshot the snapshot
   */
  // void addSnapshot(String description);
  void addSnapshot(Snapshot snapshot);

  /**
   * Remove a snapshot from the album.
   * @param description the snapshot description.
   * @throws NoSuchElementException if no such snapshot is found in the album.
   */
  void removeSnapshot(String description) throws NoSuchElementException;

  /**
   * Get the list of snapshots.
   * @return a list of snapshots.
   */
  List<Snapshot> getSnapshots();

  /**
   * Print all the snapshots.
   * @return a formatted string.
   */
  String printSnapshots();
}
