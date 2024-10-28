package config;

/**
 * This class represents a config object.
 * It defines the basic get and set behaviors of this object.
 */
public class Config {
  private String inputFileName;
  private String outputFileName;
  private String viewType;
  private int xmax;
  private int ymax;

  /**
   * Construct a config object and set the default value of 1000
   * for both x (width) and y (height) of the view.
   */
  public Config() {
    this.xmax = 1000;
    this.ymax = 1000;
  }

  /**
   * Getter for input file name.
   * @return the input file name.
   */
  public String getInputFileName() {
    return this.inputFileName;
  }

  /**
   * Getter for output file name.
   * @return the output file name.
   */
  public String getOutputFileName() {
    return this.outputFileName;
  }

  /**
   * Get the view type.
   * @return the view type.
   */
  public String getViewType() {
    return this.viewType;
  }

  /**
   * Get the xmax of the view.
   * @return the xmax of the view.
   */
  public int getXmax() {
    return this.xmax;
  }

  /**
   * Get the ymax of the view.
   * @return the ymax of the view.
   */
  public int getYmax() {
    return this.ymax;
  }

  /**
   * Set the input file name.
   * @param str the input file name.
   */
  public void setInputFileName(String str) {
    this.inputFileName = str;
  }

  /**
   * Set the output file name.
   * @param str the output file name.
   */
  public void setOutputFileName(String str) {
    this.outputFileName = str;
  }

  /**
   * Set the view type.
   * @param str the view type.
   */
  public void setViewType(String str) {
    this.viewType = str;
  }

  /**
   * Set the max of width and height of the view.
   * @param x the max width
   * @param y the max height
   */
  public void setMax(int x, int y) {
    this.xmax = x;
    this.ymax = y;
  }
}

