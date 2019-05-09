package sample;

import GUI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("WireWorld");
        primaryStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((this.getClass().getResource("WireWorldGUI.fxml")));
        AnchorPane anchorPane = loader.load();
        MainController mainController = loader.getController();
        mainController.setPrimaryStage(primaryStage);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();




        }



    public static void main(String[] args) {
        launch(args);
    }
}


