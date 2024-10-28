package viewTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Arrays;

import config.Config;
import controller.AlbumController;
import model.Color;
import model.IShape;
import model.Oval;
import model.PhotoAlbum;
import model.Rectangle;
import model.Snapshot;
import view.AlbumWebView;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the web view.
 */
class AlbumWebViewTest {

  private AlbumWebView webView;
  private PhotoAlbum album;
  private Writer writer;
  private File testFile;

  @BeforeEach
  public void setUp() throws IOException {
    album = new PhotoAlbum();

    IShape rect = new Rectangle("Rect", 50, 50, 100, 50, Color.RED);
    IShape oval = new Oval("Oval", 80, 40, 10, 60, Color.BLUE);
    album.addShape(rect);
    album.addShape(oval);

    Snapshot snapshot = new Snapshot(Arrays.asList(rect, oval), "Simple Snapshot");
    album.addSnapshot(snapshot);

    testFile = File.createTempFile("WebViewTest", ".html");
    writer = new FileWriter(testFile);
    webView = new AlbumWebView(album, testFile.getAbsolutePath());
    webView.setWriter(writer);
  }

  /**
   * Test the generateHTML() method.
   * @throws IOException if file io error occurs.
   */
  @Test
  public void testGenerateHTML() throws IOException {
    webView.generateHTML();
    String result = Files.readString(testFile.toPath());

    assertTrue(result.contains(
            "<rect x='50.00' y='50.00' width='100.00' height='50.00' fill='rgb(255,0,0)' />\n"));
    assertTrue(result.contains(
            "<ellipse cx='80.00' cy='40.00' rx='10.00' ry='60.00' fill='rgb(0,0,255)' />\n"));

  }

  /**
   * Test the updateView() method.
   * @throws IOException if file io error occurs.
   */
  @Test
  public void testUpdateView() throws IOException {
    webView.updateView();
    String result = Files.readString(testFile.toPath());

    // Assertions to check if the output is as expected
    assertTrue(result.contains(
            "<rect x='50.00' y='50.00' width='100.00' height='50.00' fill='rgb(255,0,0)' />\n"));
    assertTrue(result.contains(
            "<ellipse cx='80.00' cy='40.00' rx='10.00' ry='60.00' fill='rgb(0,0,255)' />\n"));
  }

  /**
   * Test the resetWriter() method/
   * @throws IOException if file io error occurs.
   */
  @Test
  public void testResetWriter() throws IOException {
    writer.write("some content");

    // reset and set a new writer
    webView.resetWriter();
    StringWriter newWriter = new StringWriter();
    webView.setWriter(newWriter);

    newWriter.write("some new content");
    newWriter.flush();

    assertEquals("some new content", newWriter.toString());
  }



}