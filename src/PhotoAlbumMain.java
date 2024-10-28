import config.Config;
import config.ConfigParser;
import controller.AlbumController;

/**
 * The Main class. It serves as the entry point for this program.
 */
public class PhotoAlbumMain {

  /**
   * The entry point for the program.
   * @param args the command-line arguments. The arguments will be in this form:
   *             -in "command-file-name" -view "view-type" [-out "output-place"] [xmax] [ymax]
   */
  public static void main(String[] args) {
    try {
      Config config = ConfigParser.parseArgsAndUpdateConfig(args);
      AlbumController appController = new AlbumController();
      appController.go(config);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}

