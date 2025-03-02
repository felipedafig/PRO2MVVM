package Application;

import Model.Model;
import Shared.Session;
import View.LoginView;
import View.VinylListView;
import ViewModel.VinylListViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Vinyl;

import java.io.IOException;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    // Create the shared Session object
    Session session = new Session();

    // Show the login window
    showLoginWindow(session);

    // Initialize Model and ViewModel
    Model model = new Model();
    VinylListViewModel vinylListViewModel = new VinylListViewModel(model);

    // Load the VinylListView
    FXMLLoader listViewLoader = new FXMLLoader(getClass().getResource("/path/to/VinylListView.fxml")); // Adjust the path to your FXML file
    listViewLoader.setControllerFactory(controllerClass -> new VinylListView(vinylListViewModel, session)); // Pass the Session object
    Scene listViewScene = new Scene(listViewLoader.load(), 800, 600);

    // Show the VinylListView
    primaryStage.setTitle("Vinyl Manager");
    primaryStage.setScene(listViewScene);
    primaryStage.show();

    // Simulate multiple users (optional, for testing)
    simulateUsers(model);
  }

  private void showLoginWindow(Session session) throws IOException {
    Stage loginStage = new Stage();
    FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/path/to/LoginView.fxml")); // Adjust the path to your FXML file
    loginLoader.setControllerFactory(controllerClass -> new LoginView(session, loginStage)); // Pass the Session object
    Scene loginScene = new Scene(loginLoader.load(), 300, 200);

    loginStage.setTitle("Login");
    loginStage.setScene(loginScene);
    loginStage.showAndWait(); // Wait for the login window to close
  }

  private void simulateUsers(Model model) {
    // Create and start threads for three users
    Thread user1 = new Thread(() -> simulateUserActions(model, 1));
    Thread user2 = new Thread(() -> simulateUserActions(model, 2));
    Thread user3 = new Thread(() -> simulateUserActions(model, 3));

    user1.start();
    user2.start();
    user3.start();
  }

  private void simulateUserActions(Model model, int userId) {
    // Simulate user actions (borrow, return, reserve)
    try {
      while (true) {
        // Randomly select a vinyl to act on
        Vinyl vinyl = model.getVinyls().get((int) (Math.random() * model.getVinyls().size()));

        // Randomly choose an action
        int action = (int) (Math.random() * 3);
        switch (action) {
          case 0:
            model.borrowVinyl(vinyl, userId);
            break;
          case 1:
            model.returnVinyl(vinyl, userId);
            break;
          case 2:
            model.reserveVinyl(vinyl, userId);
            break;
        }

        // Sleep for a random time (1-3 seconds)
        Thread.sleep((long) (Math.random() * 2000 + 1000));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}