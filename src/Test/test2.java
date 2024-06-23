package Test;

import fanType.Fan2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class test2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Color color = Fan2.getRandomColor();
        int radius = Fan2.getRandomRadius();
        Fan2 fan = new Fan2(color,radius);

        Scene scene =new Scene(fan,800,600);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("顾砚文牌牛逼风扇");
        primaryStage.show();

    }
}
