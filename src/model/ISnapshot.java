package model;

import java.time.LocalDateTime;

/**
 * An interface that declares the common methods of snapshot.
 */
public interface ISnapshot {

  /**
   * Get the ID of the snapshot.
   * @return the ID.
   */
  LocalDateTime getID();

  /**
   * Get the timestamp of the snapshot.
   * @return the timestamp.
   */
  String getFormattedTimestamp();

  /**
   * Get the description of the snapshot.
   * @return the description.
   */
  String getDescription();
}
