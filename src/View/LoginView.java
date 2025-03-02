package View;

import Shared.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView {

  @FXML private TextField userIdField;
  @FXML private Button loginButton;

  private Session session;
  private Stage stage;

  public LoginView(Session session, Stage stage) {
    this.session = session;
    this.stage = stage;
  }

  @FXML
  public void onLoginButtonPressed() {
    String userId = userIdField.getText();
    session.setUserId(userId); // Stores the user ID in the Session object

    stage.close();
  }
}