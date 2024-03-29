package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/AvocatV2.fxml"));
        primaryStage.setScene(new Scene(root));
        
        //setting stage icon
        primaryStage.getIcons().add(new Image("/images/icons8_Law_26px.png"));
        //set stage borderless
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        /*root.setOnMousePressed(event -> {
            x = event.getSceneX();&
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });*/
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
