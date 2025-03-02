package View;

import Shared.Session;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView {

  @FXML private TextField userIdField;

  private Session session;
  private Stage stage;

  public LoginView(Session session, Stage stage) {
    this.session = session;
    this.stage = stage;
  }

  @FXML
  public void onLoginButtonPressed() {
    String userId = userIdField.getText(); // Get the user ID from the text field
    session.setUserId(userId); // Store the user ID in the Session object

    // Close the login window
    stage.close();
  }
}