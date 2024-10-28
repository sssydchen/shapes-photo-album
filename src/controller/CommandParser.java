package controller;

import java.util.Arrays;
import java.util.List;

import model.Color;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Rectangle;
import model.Snapshot;

/**
 * A controller that parses and execute the commands.
 * It parses the commands in the input file and update the model accordingly.
 */
public class CommandParser implements ICommandParser {
  private PhotoAlbum album;

  /**
   * Construct a new command parser.
   *
   * @param album the photo album model.
   */
  public CommandParser(PhotoAlbum album) {
    this.album = album;
  }

  @Override
  public void parseCommands(List<String> commands) {
    for (String command : commands) {
      command = command.trim().replaceAll("\\s+", " ");
      // if command starts with # or is empty, then skip
      if (command.startsWith("#") || command.isEmpty()) continue;
      // split the commands into tokens
      String[] tokens = command.split("\\s+");
      if (tokens.length == 0) continue;

      // execute the command
      executeCommand(tokens);
    }
  }

  /**
   * Execute the command.
   * @param tokens an array of string tokens with each token represents a command.
   */
  private void executeCommand(String[] tokens) {
    if (tokens.length < 1) return;
    String command = tokens[0].toLowerCase().trim();
    //System.out.println("Command: " + command + "\nTokens: " + Arrays.toString(tokens));

    switch (command) {
      case "shape":
        createShape(tokens);
        break;
      case "move":
        moveShape(tokens);
        // System.out.println("Moved");
        break;
      case "color":
        changeColor(tokens);
        break;
      case "resize":
        resizeShape(tokens);
        break;
      case "remove":
        removeShape(tokens[1]);
        break;
      case "snapshot":
        takeSnapshot(tokens);
        break;
      default:
        System.err.println("Unknown command: " + tokens[0]);
    }
  }

  /**
   * Create the shapes and add them to album.
   * @param tokens the command tokens.
   */
  private void createShape(String[] tokens) {
    String name = tokens[1];
    String type = tokens[2];
    int x = Integer.parseInt(tokens[3]);
    int y = Integer.parseInt(tokens[4]);
    int width = Integer.parseInt(tokens[5]);
    int height = Integer.parseInt(tokens[6]);
    int red = Integer.parseInt(tokens[7]);
    int green = Integer.parseInt(tokens[8]);
    int blue = Integer.parseInt(tokens[9]);

    Color color = new Color(red / 255.0, green / 255.0, blue / 255.0);
    IShape shape;
    if (type.equalsIgnoreCase("rectangle")) {
      shape = new Rectangle(name, x, y, width, height, color);
    } else {
      shape = new Oval(name, x, y, width, height, color);
    }
    album.addShape(shape);
    // System.out.println("Adding shape: " + Arrays.toString(tokens));
  }

  /**
   * Move the shape to a new position.
   * @param tokens the command.
   */
  private void moveShape(String[] tokens) {
    String id = tokens[1];
    int newX = Integer.parseInt(tokens[2]);
    int newY = Integer.parseInt(tokens[3]);
    album.moveShape(id, newX, newY);
  }

  /**
   * Change the color of the shape.
   * @param tokens the command.
   */
  private void changeColor(String[] tokens) {
    String id = tokens[1];
    int red = Integer.parseInt(tokens[2]);
    int green = Integer.parseInt(tokens[3]);
    int blue = Integer.parseInt(tokens[4]);
    Color newColor = new Color(red / 255.0, green / 255.0, blue / 255.0);
    album.changeShapeColor(id, newColor);
  }

  /**
   * Resize the shape.
   * @param tokens the command.
   */
  private void resizeShape(String[] tokens) {
    String name = tokens[1];
    int newWidth = Integer.parseInt(tokens[2]);
    int newHeight = Integer.parseInt(tokens[3]);
    album.scaleShape(name, newWidth, newHeight);
  }

  /**
   * Remove the shape.
   * @param name the name of the shape.
   */
  private void removeShape(String name) {
    album.removeShape(name);
  }

  /**
   * Take a snapshot and add it to album.
   * @param tokens an array of strings that represents the description of the snapshot.
   */
  private void takeSnapshot(String[] tokens) {
    String description;
    if (tokens.length > 1) {
      description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    } else {
      description = "No description";
    }
   //  album.addSnapshot(description);
    album.addSnapshot(new Snapshot(album.getShapes(), description));
  }
}

