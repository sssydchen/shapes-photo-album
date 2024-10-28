package view;

import java.io.FileWriter;
import java.io.IOException;

import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Rectangle;
import model.Snapshot;
import java.io.Writer;

/**
 * The HTML web view for the album.
 */
public class AlbumWebView implements IView {

  private PhotoAlbum album;
  private String outputPath;
  private Writer writer;

  /**
   * Construct a web view object.
   * @param album the album to be presented.
   * @param outputPath the output path.
   * @throws IOException if file io error occurs.
   */
  public AlbumWebView(PhotoAlbum album, String outputPath) throws IOException {
    this.album = album;
    this.outputPath = outputPath;
    resetWriter();  // reset writer when initialize
  }

  /**
   * Set the writer.
   * @param writer the writer.
   */
  public void setWriter(Writer writer) {
    this.writer = writer;
  }

  /**
   * Update the view.
   * @throws IOException if fail to generate HTML file.
   */
  @Override
  public void updateView() throws IOException {
    resetWriter();
    generateHTML();
  }

  /**
   * Reset the writer.
   * @throws IOException if file output error occurs.
   */
  public void resetWriter() throws IOException {
    if (writer != null) {
      writer.close();
    }
    writer = new FileWriter(outputPath, false);
  }



  /**
   * Generate the HTML file.
   * @throws IOException if file io error occurs.
   */
  public void generateHTML() throws IOException {
    try {
      writer.write(
              "<!DOCTYPE html>\n<html>\n<head>\n<title>Photo Album</title>\n</head>\n<body>\n");
      for (Snapshot snapshot : album.getSnapshots()) {
        writer.write("<div class='snapshot'>\n");
        writer.write("<h2>" + snapshot.getID() + "</h2>\n");
        writer.write("<p>Description: " + snapshot.getDescription() + "</p>\n");
        writer.write(generateSVG(snapshot));
        writer.write("</div>\n");
      }
      writer.write("</body>\n</html>");
    } finally {
      writer.close();
    }
  }


  /**
   * Generate the SVG for the snapshot.
   * @param snapshot the given snapshot.
   * @return an svg format string.
   */
  private String generateSVG(Snapshot snapshot) {
    StringBuilder svgBuilder = new StringBuilder();
    svgBuilder.append(String.format("<svg width='%d' height='%d' xmlns='http://www.w3.org/2000/svg'>\n", 1000, 800));
    for (IShape shape : snapshot.getShapes()) {
      svgBuilder.append(shapeToSVG(shape));
    }
    svgBuilder.append("</svg>\n");
    return svgBuilder.toString();
  }

  /**
   * Convert a shape to an SVG format string.
   * @param shape the shape to be converted.
   * @return an SVG format string.
   */
  private String shapeToSVG(IShape shape) throws IllegalArgumentException {
    if (shape instanceof Rectangle rect) {
      return String.format(
              "<rect x='%.2f' y='%.2f' width='%.2f' height='%.2f' fill='rgb(%d,%d,%d)' />\n",
              rect.getPoint().getX(), rect.getPoint().getY(), rect.getWidth(), rect.getHeight(),
              (int) (rect.getColor().getRed() * 255), (int) (rect.getColor().getGreen() * 255),
              (int) (rect.getColor().getBlue() * 255));
    } else {
      Oval oval = (Oval) shape;
      return String.format(
              "<ellipse cx='%.2f' cy='%.2f' rx='%.2f' ry='%.2f' fill='rgb(%d,%d,%d)' />\n",
              oval.getPoint().getX(), oval.getPoint().getY(), oval.getWidth(), oval.getHeight(),
              (int) (oval.getColor().getRed() * 255), (int) (oval.getColor().getGreen() * 255),
              (int) (oval.getColor().getBlue() * 255));

    }
  }
}


