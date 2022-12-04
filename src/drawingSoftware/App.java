package drawingSoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class App extends Application {

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


        /*

         * 
         *  Prove
         */

        // Pane root = new Pane();


        // /*
        //  * Model
        //  */
        // Rectangle rect = new Rectangle();
        // rect.setWidth(10);
        // rect.setHeight(20);
        // rect.setX(20);
        // rect.setY(40);
        // rect.setFill(Paint.valueOf("red"));

        // Rectangle rect2 = new Rectangle();
        // rect2.setWidth(10);
        // rect2.setHeight(20);
        // rect2.setX(200);
        // rect2.setY(500);

        // ObservableList<Node> nodes = FXCollections.observableArrayList();
        // nodes.add(rect);
        // nodes.add(rect2);

        // /*

        //  * ChangeListener: quando la lista cambia, viene eseguito il changeListener
        //  * ObservableValue -> ChangeListener
        //  */

        // Button btn = new Button();
        // btn.setLayoutX(50);
        // btn.setText("click");
        // btn.setOnAction(e ->{
        //     nodes.remove(rect);
        // }); 
        
        // nodes.addListener(new ListChangeListener<Node>() {

        //     @Override
        //     public void onChanged(Change<? extends Node> arg0) {
        //         root.getChildren().clear();
        //         root.getChildren().addAll(nodes);
        //         root.getChildren().add(btn);
        //     }
        // });

        
        // root.getChildren().addAll(nodes);
        // root.getChildren().add(btn);
        
        // primaryStage.setScene(new Scene(root));
        // primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}