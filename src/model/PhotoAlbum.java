package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Manages a collection of Snapshot instances.
 * A photo album can add, remove, and print snapshots.
 */
public class PhotoAlbum implements IAlbumModel {
  private List<Snapshot> snapshots;
  private List<IShape> currentShapes;

  /**
   * Construct an album instance.
   */
  public PhotoAlbum() {
    this.snapshots = new ArrayList<>();
    this.currentShapes = new ArrayList<>();
  }


  @Override
  public void addShape(IShape shape) {
    currentShapes.add(shape);
  }


  @Override
  public void removeShape(String name) {
    IShape shapeToRemove = currentShapes.stream()
            .filter(shape -> shape.getName().equals(name))
            .findFirst()
            .orElse(null);
    currentShapes.remove(shapeToRemove);
  }

  @Override
  public void moveShape(String name, double x, double y) {
    IShape shape = this.getShape(name);
    if (shape != null) {
      shape.move(x, y);
    }
  }

  @Override
  public void scaleShape(String name, double width, double height) {
    IShape shape = this.getShape(name);
    if (shape != null) {
      shape.setWidth(width);
      shape.setHeight(height);
    }
  }

  @Override
  public void changeShapeColor(String name, Color newColor) {
    IShape shape = this.getShape(name);
    if (shape != null) {
      shape.changeColor(newColor);
    }
  }

  @Override
  public IShape getShape(String name) {
    return currentShapes.stream()
            .filter(s -> s.getName().equals(name))
            .findFirst()
            .orElse(null);
  }

//  @Override
//  public void addSnapshot(String description) {
//    List<IShape> shapesCopy = new ArrayList<>();
//    for (IShape shape : currentShapes) {
//      shapesCopy.add(shape.copy());
//    }
//    snapshots.add(new Snapshot(shapesCopy, description));
//  }

  @Override
  public void addSnapshot(Snapshot snapshot) {
    Snapshot new_snapshot = new Snapshot(snapshot);
    snapshots.add(new_snapshot);
  }


  /**
   * Remove a snapshot.
   * @param description the description of the snapshot
   */
  @Override
  public void removeSnapshot(String description) {
    Snapshot snapshotToRemove = snapshots.stream()
            .filter(snapshot -> snapshot.getDescription().equals(description))
            .findFirst()
            .orElse(null);
    snapshots.remove(snapshotToRemove);
  }

  @Override
  public List<Snapshot> getSnapshots() {
    return new ArrayList<>(snapshots);
  }

  public List<IShape> getShapes() {
    return new ArrayList<>(currentShapes);
  }


  @Override
  public String printSnapshots() {
    StringBuilder str = new StringBuilder();
    for (Snapshot snapshot : snapshots) {
      str.append(snapshot.toString()).append("\n\n");
    }
    return "Printing Snapshots\n" + str.toString();
  }
}
