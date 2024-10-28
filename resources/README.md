# Shapes Photo Album

## Overview

The Photo Album Application is designed to manage digital photo collections effectively. It allows users to create, view, and modify a photo album using a graphical user interface.
It can also present the album on a web view. The project supports various photo manipulation tasks, enables users to creating 2D shapes starting with rectangles and ovals, and provides functionality operations like moving, scaling, and color changing. It can capture various states of the shapes as snapshots and compile these into a photo album, providing a visual narrative of their transformations.

## Design

The project is designed using MVC architecture. The structure is as follows:

-  **Model**: Defines the core data models and business logic of the application. 
   - AbstractShape: An abstract class that implements the IShape methods.
   - Color: Represents color with red, green, and blue components. 
   - IShape: Declares common operations for shapes.
   - IAlbumModel: Declares methods that outline the operations we can perform on the photo album.
   - ISnapshot: Declares methods of the Snapshot class.
   - Oval: A concrete class extending the AbstractShape, representing an oval-like shape. It includes additional properties specific to rectangles, such as x radius and y radius.
   - Rectangle: A concrete class extending the AbstractShape, representing a rectangle-like shape. It includes additional properties specific to rectangles, such as width and height.
   - PhotoAlbum: The central class representing a photo album. 
   - Point2D: Represents a 2-dimensional point with x and y coordinates. This class is used to define the positions of shapes.
   - Snapshot: Represents a state of the album at a specific moment.

-  **View**: Contains classes responsible for the user interface and presentation of the model.
   - AlbumSwingView: Implementations of the graphical view.
   - AlbumWebView: Implementations of the web view.
   - DrawPanel: A custom JPanel to draw shapes.
   - ISwingView: Declares swing view methods.
   - IView: Declares general view methods.

-  **Controller**: Contains controllers responsible for handling user actions and mediating between the view and the model.
   - ActionController: Listens and responds to user actions in the view.
   - AlbumController: Uses configuration data to set up the album and views.
   - CommandParser: Parses and executes commands from an input file.
   - ICommandParser: Contract for command parser.
   - IController: Contract for the controllers.

-  **Config**: Contains classes related to configuration parsing and holding configuration data.
   - Config: Holds the configuration data necessary to start the application.
   - Config Parser: Parses command-line arguments into a Config instance.

-  **IO**: Contains a class that handles input and output operations by reading commands from files.
   - ReadFile: Defines methods to read data from files.

-  **PhotoAlbumMain**: The entry point of the application, responsible for initializing the controllers and starting the application based on the provided configurations.

## Main Changes
There are several changes to my model. The PhotoAlbum class has been expanded with methods to directly handle shapes such as addition, removal, movement, resizing, and color changes, so that the user can interact with my album model directly when they give commands of shape transformations.  The Color enum is changed to a Color class to support arbitrary colors beyond predefined ones, allowing for dynamic color creation with custom RGB values.




