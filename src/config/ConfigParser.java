package config;

/**
 * Parse the command-line arguments and update the config object.
 */
public class ConfigParser {
  /**
   *
   * @param args the command-line arguments.
   * @return an updated Config object
   * @throws IllegalArgumentException if the input command is invalid.
   */
  public static Config parseArgsAndUpdateConfig(String[] args) throws IllegalArgumentException {
    Config config = new Config();
    int i = 0;

    while (i < args.length) {
      String arg = args[i];
      switch (arg) {
        case "-in":
          if (++i < args.length) {
            config.setInputFileName(args[i]);
          } else {
            throw new IllegalArgumentException("Invalid argument after -in");
          }
          break;
        case "-out":
          if (++i < args.length) {
            config.setOutputFileName(args[i]);
          } else {
            throw new IllegalArgumentException("Invalid argument after -out");
          }
          break;
        case "-view":
        case "-v":
          if (++i < args.length) {
            config.setViewType(args[i]);
            // if the next arguments are numbers
            if (i + 1 < args.length && isNumeric(args[i + 1])
                    && i + 2 < args.length && isNumeric(args[i + 2])) {
              config.setMax(Integer.parseInt(args[i + 1]), Integer.parseInt(args[i + 2]));
              i += 2;
            }
          } else {
            throw new IllegalArgumentException("Invalid argument after -view/-v");
          }
          break;
        default:
          throw new IllegalArgumentException("Invalid command-line argument");
      }
      i++;
    }

    if (config.getInputFileName() == null || config.getViewType() == null) {
      throw new IllegalArgumentException(
              "Providing an input file (the -in pair) and a view (the -view pair) are mandatory");
    }

    // output file name is only necessary for web view
    if ("web".equals(config.getViewType().toLowerCase()) && config.getOutputFileName() == null) {
      throw new IllegalArgumentException("Must specify where output should go for the web view.");
    }
    return config;
  }

  /**
   * Check if a given string is numeric.
   * @param str the given string command.
   * @return true if is numeric, false otherwise.
   */
  private static boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}


