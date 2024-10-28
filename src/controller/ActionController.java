package controller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import io.ReadFile;
import model.PhotoAlbum;
import model.Snapshot;
import view.AlbumSwingView;
import view.ISwingView;
import view.IView;

/**
 * An action controller that implements the ActionListener to handle the user actions.
 */
public class ActionController implements ActionListener {
  private PhotoAlbum album;
  private IView view;
  private int currentIndex = 0;

  /**
   * Constructs an action controller instance with the album model and the view.
   * @param album the album model.
   * @param view the view.
   */
  public ActionController(PhotoAlbum album, IView view) {
    this.album = album;
    this.view = view;
    if (view instanceof ISwingView) {
      ((ISwingView) view).setButtonListener(this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Previous":
        navigate(-1);
        break;
      case "Next":
        navigate(1);
        break;
      case "Select":
        selectSnapshot();
        break;
      case "Quit":
        System.exit(0);
        break;
    }
  }

  /**
   * Navigate the album.
   * @param delta the direction to navigate; -1 for prev, 1 for next.
   */
  private void navigate(int delta) {
    if (view instanceof AlbumSwingView swingView) {
      int newIndex = currentIndex + delta;
      if (newIndex >= 0 && newIndex < album.getSnapshots().size()) {
        currentIndex = newIndex;
        updateCurrentView();
      } else {
        String message;
        if (delta > 0) {
          // if the user has reached the end of the album
          message = "End of the album. No more snapshots to show.";
        } else {
          // if the user has reached the start of the album
          message = "Start of the album. No more snapshots to show.";
        }
        JOptionPane.showMessageDialog(swingView, message, "Message", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  /**
   * Select a snapshot from the combo box.
   */
  private void selectSnapshot() {
    if (view instanceof AlbumSwingView swingView) {
      // create a combo box
      JComboBox<String> snapshotSelections = new JComboBox<>();
      album.getSnapshots().forEach(s -> snapshotSelections.addItem(
              s.getID().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))));
      // create a panel and a label
      JPanel panel = new JPanel(new BorderLayout());
      JLabel label = new JLabel("Choose");
      label.setLabelFor(snapshotSelections);
      panel.add(label, BorderLayout.NORTH);
      panel.add(snapshotSelections, BorderLayout.CENTER);

      // display the dialog
      int result = JOptionPane.showConfirmDialog(swingView, panel,
              "Menu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
        currentIndex = snapshotSelections.getSelectedIndex();
        updateCurrentView();
      }
    }
  }

  /**
   * Update the current view when select another snapshot from the combo box.
   */
  private void updateCurrentView() {
    if (view instanceof AlbumSwingView swingView) {
      if (!album.getSnapshots().isEmpty()) {
        Snapshot snapshot = album.getSnapshots().get(currentIndex);
        swingView.updateDescription(String.format("Snapshot %d: %s (Taken at %s)",
                currentIndex + 1, snapshot.getDescription(), snapshot.getID()));
        swingView.updateDrawPanel(snapshot.getShapes());
      } else {
        swingView.updateDescription("No snapshots available.");
        swingView.updateDrawPanel(new ArrayList<>());
      }
    }

  }

  /**
   * Create the album according to the file.
   * @param file the input file.
   * @throws IOException if failed to read from file.
   */
  public void createAlbum(String file) throws IOException {
    List<String> commands = ReadFile.getCommands(file);
    CommandParser commandParser = new CommandParser(album);
    commandParser.parseCommands(commands);
    view.updateView();
  }
}
