package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.IShape;
import model.PhotoAlbum;
import model.Snapshot;

/**
 * The graphic view for the album.
 */
public class AlbumSwingView extends JFrame implements IView, ISwingView {
  private final PhotoAlbum album;
  private int currentIndex;
  private JLabel descriptionLabel;
  private JButton prevButton, nextButton, selectButton, quitButton;
  private DrawPanel drawPanel;

  /**
   * Construct a swing view object.
   * @param album the album to be presented.
   * @param xmax the max width of the view.
   * @param ymax the max height of the view.
   */
  public AlbumSwingView(PhotoAlbum album, int xmax, int ymax) {
    this.album = album;
    this.currentIndex = 0;
    setSize(xmax, ymax);
    initializeWindow();
  }

  /**
   * Initialize the window.
   */
  private void initializeWindow() {
    setTitle("Photo Album Viewer");

    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    descriptionLabel = new JLabel("Select a snapshot", SwingConstants.CENTER);
    add(descriptionLabel, BorderLayout.NORTH);

    drawPanel = new DrawPanel(List.of());
    add(drawPanel, BorderLayout.CENTER);

    prevButton = new JButton("Previous");
    nextButton = new JButton("Next");
    selectButton = new JButton("Select");
    quitButton = new JButton("Quit");

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER));
    panel.setBackground(Color.ORANGE);
    panel.add(prevButton);
    panel.add(nextButton);
    panel.add(selectButton);
    panel.add(quitButton);

    add(panel, BorderLayout.SOUTH);
  }

  @Override
  public void setButtonListener(ActionListener listener) {
    prevButton.addActionListener(listener);
    nextButton.addActionListener(listener);
    selectButton.addActionListener(listener);
    quitButton.addActionListener(listener);
  }


  @Override
  public void updateDescription(String text) {
    descriptionLabel.setText(text);
  }


  @Override
  public void updateDrawPanel(List<IShape> shapes) {
    drawPanel.setShapes(shapes);
    drawPanel.repaint();
  }

  /**
   * Update the entire view.
   */
  @Override
  public void updateView() {
    if (album.getSnapshots().isEmpty()) {
      updateDescription("No snapshots available. But have updated view.");
      updateDrawPanel(new ArrayList<>());
    } else {
      Snapshot snapshot = album.getSnapshots().get(currentIndex);
      String description = String.format("Snapshot %d: %s (Taken at %s)",
              currentIndex + 1,
              snapshot.getDescription(),
              snapshot.getFormattedTimestamp());
      updateDescription(description);
      updateDrawPanel(snapshot.getShapes());
    }
  }

}


