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
import Model.Model;


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

//  @FXML private ListView<Vinyl> vinylListView;

  private VinylListViewModel vinylListViewModel;
  private AddVinylViewModel addVinylViewModel;
  private Session session;

  public VinylListView(VinylListViewModel vinylListViewModel, Session session) {
    this.vinylListViewModel = vinylListViewModel;
    this.session = session;
  }

  @FXML
  public void initialize() {
    // Bind the ListView to the ViewModel's data
    vinylTableView.setItems(vinylListViewModel.getVinyls());

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    artistNameColumn.setCellValueFactory(new PropertyValueFactory<>("artistName"));
    releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

    // Custom state column
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

    borrowButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
    returnButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
    reserveButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
    cancelReservationButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
    markForRemovalButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
    unMarkForRemovalButton.disableProperty().bind(vinylTableView.getSelectionModel().selectedItemProperty().isNull());
  }

  @FXML
  private void onBorrowButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId(); //userID from shared Session
      vinylListViewModel.borrowVinyl(selectedVinyl, Integer.parseInt(userId));
    }
  }

  @FXML
  private void onReturnButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId();
      vinylListViewModel.returnVinyl(selectedVinyl, Integer.parseInt(userId));
    }
  }

  @FXML
  private void onReserveButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      String userId = session.getUserId();
      vinylListViewModel.reserveVinyl(selectedVinyl, Integer.parseInt(userId));
    }
  }

  @FXML
  private void onCancelReservationButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.cancelReservation(selectedVinyl);
    }
  }

  @FXML
  private void onMarkForRemovalButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.markForRemoval(selectedVinyl);
    }
  }

  @FXML
  private void onUnMarkForRemovalButtonPressed() {
    Vinyl selectedVinyl = vinylTableView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      vinylListViewModel.unMarkForRemoval(selectedVinyl);
    }
  }

  @FXML
  private void onAddItemsButtonPressed() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddView.fxml"));

      // Create the controller and pass the ViewModel and Session
      AddVinylView controller = new AddVinylView(addVinylViewModel, session);
      loader.setController(controller); // Set the controller explicitly

      Parent root = loader.load();

      Model vinylModel = new Model();
      AddVinylViewModel addVinylViewModel = new AddVinylViewModel(vinylModel);
      AddVinylView addVinylView = loader.getController();
      loader.setController(addVinylView);

      Stage stage = (Stage) vinylTableView.getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to load the Add Vinyl form.");
    }
  }

//  @FXML
//  private void onRefreshButtonPressed() {
//    vinylListViewModel.refresh();
//  }
}