package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read commands from the given file.
 */
public class ReadFile {
  /**
   * Read all lines in the given file and returns a list of strings
   * with each representing a line in the file.
   * @param file the given file.
   * @return a list of lines.
   * @throws IOException if an I/O error occurs.
   */
  public static List<String> getCommands(String file) throws IOException {
    return Files.readAllLines(Paths.get(file));
  }
}

