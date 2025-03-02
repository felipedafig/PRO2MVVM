package View;

import Model.Vinyl;
import Shared.Session;
import ViewModel.AddVinylViewModel;
import ViewModel.VinylListViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VinylListView {

  @FXML private TableView<Vinyl> vinylTableView;
  @FXML private TableColumn<Vinyl, String> titleColumn;
  @FXML private TableColumn<Vinyl, String> artistNameColumn;
  @FXML private TableColumn<Vinyl, Integer> releaseYearColumn;
  @FXML private TableColumn<Vinyl, String> stateColumn;

  @FXML private Button borrowButton;
  @FXML private Button returnButton;
  @FXML private Button reserveButton;
  @FXML private Button cancelReservationButton;
  @FXML private Button markForRemovalButton;
  @FXML private Button unMarkForRemovalButton;
  @FXML private Button addItemsButton;

  @FXML private ListView<Vinyl> vinylListView;

  private VinylListViewModel vinylListViewModel;
  private AddVinylViewModel addVinylViewModel;
  private Session session; // Add the Session object

  public VinylListView(VinylListViewModel vinylListViewModel, Session session) {
    this.vinylListViewModel = vinylListViewModel;
    this.session = session; // Initialize the Session object
  }

  @FXML
  public void initialize() {
    // Bind the ListView to the ViewModel's data
    vinylListView.setItems(vinylListViewModel.getVinyls());

    // Configure TableView columns
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    artistNameColumn.setCellValueFactory(new PropertyValueFactory<>("artistName"));
    releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

    // Configure the state column dynamically
    stateColumn.setCellValueFactory(cellData -> {
      Vinyl vinyl = cellData.getValue();
      String state;
      if (vinyl.getBorrowedByID() != null) {
        state = "Borrowed by User ID " + vinyl.getBorrowedByID();
      } else if (vinyl.getReservedByID() != null) {
        state = "Reserved by User ID " + vinyl.getReservedByID();
      } else {
        state = "Available";
      }
      return new SimpleStringProperty(state);
    });

    // Bind button disable properties
    borrowButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
    returnButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
    reserveButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
    cancelReservationButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
    markForRemovalButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
    unMarkForRemovalButton.disableProperty().bind(vinylListView.getSelectionModel().selectedItemProperty().isNull());
  }

  @FXML
  private void onBorrowButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId(); // Get the user ID from the Session
      vinylListViewModel.borrowVinyl(selectedVinyl, Integer.parseInt(userId)); // Pass the user ID to the ViewModel
    }
  }

  @FXML
  private void onReturnButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId(); // Get the user ID from the Session
      vinylListViewModel.returnVinyl(selectedVinyl, Integer.parseInt(userId)); // Pass the user ID to the ViewModel
    }
  }

  @FXML
  private void onReserveButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId(); // Get the user ID from the Session
      vinylListViewModel.reserveVinyl(selectedVinyl, Integer.parseInt(userId)); // Pass the user ID to the ViewModel
    }
  }

  @FXML
  private void onCancelReservationButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.cancelReservation(selectedVinyl);
    }
  }

  @FXML
  private void onMarkForRemovalButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.markForRemoval(selectedVinyl);
    }
  }

  @FXML
  private void onUnMarkForRemovalButtonPressed() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.unMarkForRemoval(selectedVinyl);
    }
  }

  @FXML
  private void onAddItemsButtonPressed() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddView.fxml"));

      // Set controller
      AddVinylView controller = new AddVinylView(addVinylViewModel);
      loader.setController(controller);

      Parent root = loader.load();

      Stage stage = new Stage();
      stage.setTitle("Add New Vinyl");
      stage.setScene(new Scene(root));

      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to load the Add Vinyl form.");
    }
  }

  @FXML
  private void onRefreshButtonPressed() {
    vinylListViewModel.refresh();
  }
}