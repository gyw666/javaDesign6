package Test;

import fanType.Fan3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class test3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Color color = Fan3.getRandomColor();
        int radius = Fan3.getRandomRadius();
        Fan3 fan = new Fan3(color,radius);
        fan.setRadius(200);
        fan.drawFan(primaryStage);

    }
}
