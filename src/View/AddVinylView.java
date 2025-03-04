package View;

import Model.Model;
import Shared.Session;
import ViewModel.AddVinylViewModel;
import ViewModel.VinylListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;

public class AddVinylView
{
  @FXML private TextField titleField;
  @FXML private TextField artistNameField;
  @FXML private TextField releaseYearField;
  @FXML private Button addButton;

  private AddVinylViewModel addVinylViewModel;
  private Session session;

  public AddVinylView(AddVinylViewModel addVinylViewModel, Session session) {
    this.addVinylViewModel = addVinylViewModel;
    this.session = session;
  }


  public void initialize() {

    titleField.textProperty().bindBidirectional(addVinylViewModel.titleProperty());
    artistNameField.textProperty().bindBidirectional(addVinylViewModel.artistNameProperty());
    releaseYearField.textProperty().bindBidirectional(addVinylViewModel.releaseYearProperty(), new NumberStringConverter());

    addButton.disableProperty().bind(addVinylViewModel.shouldSubmitBeDisabledProperty());

    //text formatters for  validation
    titleField.setTextFormatter(new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      return newText.length() <= 20 ? change : null;
    }));

    artistNameField.setTextFormatter(new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      return newText.length() <= 20 ? change : null;
    }));

    releaseYearField.setTextFormatter(new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      return newText.matches("\\d*") ? change : null; //only numbers
    }));

    // Validate release year and update ViewModel state
    releaseYearField.textProperty().addListener((observable, oldValue, newValue) -> {
      int currentYear = java.time.Year.now().getValue();
      boolean isValidYear = !newValue.isEmpty() && newValue.matches("\\d+") && Integer.parseInt(newValue) >= currentYear;

      addVinylViewModel.validateForm(isValidYear); // Call ViewModel method to update validation state
    });
  }

  @FXML
  public void onAddButtonPressed() {
    //readfrom text fields
    String title = titleField.getText();
    String artistName = artistNameField.getText();
    int releaseYear = Integer.parseInt(releaseYearField.getText());

    addVinylViewModel.addVinyl(title, artistName, releaseYear);

    titleField.clear();
    artistNameField.clear();
    releaseYearField.clear();

    navigateToVinylListView();
  }

  private void navigateToVinylListView() {
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
      Parent root = loader.load();

      Model vinylModel = new Model();
      VinylListViewModel vinylListViewModel = new VinylListViewModel(vinylModel);
      VinylListView vinylListView = new VinylListView(vinylListViewModel, session);
      loader.setController(vinylListView);


      Stage stage = (Stage) titleField.getScene().getWindow();

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
