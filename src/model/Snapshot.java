package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Captures and stores the state of a collection of shapes at a particular moment.
 * Each snapshot includes an ID, a timestamp, a description, and a list of shapes.
 */
public class Snapshot implements ISnapshot {
  private LocalDateTime timestamp;
  private List<IShape> shapes;
  private String description;

  public Snapshot(List<IShape> shapes, String description) {
    this.timestamp = LocalDateTime.now();
    // create a copy of the shapes list so that changes to the original list
    // would not affect the Snapshot's list
    this.shapes = new ArrayList<>(shapes);
    this.description = description;
  }

  /**
   * Copy constructor
   * @param other the snapshot to be copied
   */
  public Snapshot(Snapshot other) {
    this.timestamp = other.timestamp;
    List<IShape> shapesCopy = new ArrayList<>();
    for (IShape shape : other.shapes) {
      shapesCopy.add(shape.copy());
    } // make a deep copy of the shapes list
    this.shapes = shapesCopy;
    this.description = other.description;
  }

  @Override
  public LocalDateTime getID() {
    return timestamp;
  }

  @Override
  public String getFormattedTimestamp() {
    return timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  }

  /**
   * Get the shapes of the snapshot object.
   * @return a copy of the shapes list so that external code would not change
   * the state of the Snapshot object.
   */
  public List<IShape> getShapes() {
    return new ArrayList<>(shapes);
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    StringBuilder str;
    str = new StringBuilder("Snapshot ID: " + this.getID() + "\n" + "Timestamp: "
            + this.getFormattedTimestamp() + "\n"
            + "Description: " + description + "\n" + "Shape Information:\n");
    for (IShape shape : shapes) {
      str.append(shape.toString()).append("\n\n");
    }
    return str.toString();
  }

}
