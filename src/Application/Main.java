package Application;

import Model.DataModel;
import Model.User;
import View.ViewController;
import ViewModel.ViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
  public static void main(String[] args)
  {
    launch();
  }

  @Override public void start(Stage primaryStage) throws Exception
  {
    DataModel model = new DataModel();
    ViewModel viewModel = new ViewModel(model);

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/VinylList.fxml"));
    fxmlLoader.setControllerFactory(controllerClass -> new ViewController(viewModel));

    Scene scene = new Scene(fxmlLoader.load(), 700, 380);
    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(scene);
    primaryStage.show();

    User user1 = new User(model , 1);
    User user2 = new User(model , 2);
    User user3 = new User(model , 3);

    Thread th1 = new Thread(user1);
    Thread th2 = new Thread(user2);
    Thread th3 = new Thread(user3);

    th1.setDaemon(true);
    th2.setDaemon(true);
    th3.setDaemon(true);

    th1.start();
    th2.start();
    th3.start();
  }
}