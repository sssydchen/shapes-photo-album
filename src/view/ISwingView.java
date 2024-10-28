package view;

import java.awt.event.ActionListener;
import java.util.List;

import model.IShape;

public interface ISwingView {

  /**
   * Assigns the given listener to respond to actions from the buttons.
   * @param listener the action listener to be added to the button.
   */
  void setButtonListener(ActionListener listener);

  /**
   * Update the description.
   * @param text the content of the description.
   */
  void updateDescription(String text);

  /**
   * Update the draw panel.
   * @param shapes a list of shapes.
   */
  void updateDrawPanel(List<IShape> shapes);
}
