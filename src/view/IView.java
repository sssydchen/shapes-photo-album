package view;

import java.io.IOException;

/**
 * The IView interface.
 */
public interface IView {
  /**
   * Update the view.
   * @throws IOException if file io error occurs.
   */
  void updateView() throws IOException;


}
