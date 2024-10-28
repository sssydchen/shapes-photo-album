package controller;

import java.util.List;

/**
 * The command parser interface.
 */
public interface ICommandParser {
  /**
   * Parse a list of commands.
   * @param commands a list of strings.
   */
  void parseCommands(List<String> commands);
}
