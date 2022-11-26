package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class App extends Application {

    // commento

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root=FXMLLoader.load(getClass().getResource("mainInterface.fxml"));
            primaryStage.setTitle("Draw Shape");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
          e.printStackTrace();
        }   
    }

    public static void main(String[] args) {
        launch(args);
    }
}
