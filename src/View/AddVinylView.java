package View;

import ViewModel.AddVinylViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

public class AddVinylView
{
  @FXML
  private TextField titleField;
  @FXML private TextField artistNameField;
  @FXML private TextField releaseYearField;
  @FXML private Button addButton;

  private AddVinylViewModel addVinylViewModel;

  public AddVinylView(AddVinylViewModel addVinylViewModel) {
    this.addVinylViewModel = addVinylViewModel;
  }

  public void initialize() {

    titleField.textProperty().bindBidirectional(addVinylViewModel.titleProperty());
    artistNameField.textProperty().bindBidirectional(addVinylViewModel.artistNameProperty());
    releaseYearField.textProperty().bindBidirectional(addVinylViewModel.releaseYearProperty(), new NumberStringConverter());

    addButton.disableProperty().bind(addVinylViewModel.shouldSubmitBeDisabledProperty());

    //text formatters forvalidation
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
      return newText.matches("\\d*") ? change : null; // Only allow numbers
    }));

    // Validate release year and update ViewModel state
    releaseYearField.textProperty().addListener((observable, oldValue, newValue) -> {
      int currentYear = java.time.Year.now().getValue();
      boolean isValidYear = !newValue.isEmpty() && newValue.matches("\\d+") && Integer.parseInt(newValue) >= currentYear;

      addVinylViewModel.validateForm(isValidYear); // Call ViewModel method to update validation state
    });
  }


  public void onAddButtonPressed() {
    addVinylViewModel.addVinyl(); //???? fix this to get the textfields and create a new one
  }
}
