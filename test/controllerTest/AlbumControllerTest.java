package controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the AlbumController.
 */
class AlbumControllerTest {

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

    testFile = File.createTempFile("controller_test", ".html");
    writer = new FileWriter(testFile);
    webView = new AlbumWebView(album, testFile.getAbsolutePath());
    webView.setWriter(writer);
  }


  /**
   * Test the go() with the web view.
   * @throws IOException if file io error occurs.
   */
  @Test
  public void testGoWithWebView() throws IOException {
    Config config = new Config();
    config.setViewType("web");
    config.setOutputFileName("test_output.html");
    config.setInputFileName(String.valueOf(testFile));
    AlbumController albumController = new AlbumController();
    albumController.go(config);

    webView.updateView();
    String result = Files.readString(testFile.toPath());

    assertTrue(result.contains("<!DOCTYPE html>"));
    assertTrue(result.contains("Simple Snapshot"));
  }

  /**
   * Test giving no output file for web view.
   */
  @Test
  public void testNoOutputFileForWebView() {
    Config config = new Config();
    config.setViewType("web");
    config.setInputFileName(String.valueOf(testFile));
    AlbumController albumController = new AlbumController();
    assertThrows(IllegalStateException.class, () -> albumController.go(config));
  }


  /**
   * Test the go() with the graphical view.
   * @throws IOException
   */
  @Test
  public void testGoWithGraphicalView() throws IOException {
    Config config = new Config();
    config.setViewType("graphical");
    config.setInputFileName(String.valueOf(testFile));
    AlbumController albumController = new AlbumController();
    albumController.go(config);

    webView.updateView();
    String result = Files.readString(testFile.toPath());

    assertTrue(result.contains("<!DOCTYPE html>"));
    assertTrue(result.contains("Simple Snapshot"));
  }

  /**
   * Test the default settings.
   */
  @Test
  public void testGoDefault() {
    Config config = new Config();
    config.setViewType("unknown view");
    config.setInputFileName(String.valueOf(testFile));
    AlbumController albumController = new AlbumController();
    assertThrows(IllegalArgumentException.class, () -> albumController.go(config));
  }



}