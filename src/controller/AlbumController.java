package controller;

import java.io.IOException;

import config.Config;
import model.PhotoAlbum;
import view.AlbumSwingView;
import view.AlbumWebView;

/**
 * Controls the overall behavior of the photo album.
 */
public class AlbumController implements IController {
  /**
   * Initiates the album with the given configs.
   * @param config the configurations of the album.
   */
  @Override
  public void go(Config config) throws IOException {
    PhotoAlbum album = new PhotoAlbum();
    ActionController controller;

    switch (config.getViewType().toLowerCase()) {
      case "graphical":
        AlbumSwingView swingView = new AlbumSwingView(album, config.getXmax(), config.getYmax());
        controller = new ActionController(album, swingView);
        controller.createAlbum(config.getInputFileName());
        swingView.setVisible(true);
        break;
      case "web":
        if (config.getOutputFileName() == null) {
          throw new IllegalStateException("Output file must be specified for web view.");
        }
        AlbumWebView webView = new AlbumWebView(album, config.getOutputFileName());
        controller = new ActionController(album, webView);
        controller.createAlbum(config.getInputFileName());
        webView.updateView();
        break;
      default:
        throw new IllegalArgumentException("Unsupported view type: " + config.getViewType());
    }
  }

}

