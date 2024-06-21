package Test;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class t extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();  //组
        Circle circle = new Circle(70, 70, 60); //圆
        circle.setStyle("-fx-stroke: black; -fx-fill: white;"); //圆设置样式（黑色画线、白色填充）
        group.getChildren().add(circle);    //组添加圆

        for (int i = 0, j = 90; i < 4; i++, j += 90) {  //创建4个扇叶
            Arc arc = new Arc(70, 70, 50, 50,30 + j, 35);
            arc.setType(ArcType.ROUND);
            group.getChildren().add(arc);
        }
        group.setScaleX(1.8);   //组设置水平大小
        group.setScaleY(1.8);

        Button pause = new Button("Pause"); //暂停按钮
        Button resume = new Button("Resume");   //继续按钮
        Button reverse = new Button("Reverse"); //反转按钮
        HBox hBox = new HBox(10, pause, resume, reverse);
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        //关键帧
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(10), event -> group.setRotate(group.getRotate()+1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(10), event -> group.setRotate(group.getRotate()-1));
        Timeline animation = new Timeline(keyFrame1);   //时间线动画
        animation.setCycleCount(Timeline.INDEFINITE);   //无限循环次数
        animation.play();   //启动动画

        pause.setOnAction(event -> animation.pause());  //按钮注册动作事件
        resume.setOnAction(event -> animation.play());
        reverse.setOnAction(event -> {
            animation.stop();
            animation.getKeyFrames().add(animation.getKeyFrames().remove(0).equals(keyFrame1) ? keyFrame2 : keyFrame1);
            animation.play();
        });

        Slider slider = new Slider();   //滑动条
        slider.setMax(25);  //滑动条设置最大值
        slider.valueProperty().addListener(observable -> animation.setRate(slider.getValue())); //滑动条添加监听器

        BorderPane borderPane = new BorderPane(new BorderPane(group));
        borderPane.setTop(hBox);
        borderPane.setBottom(slider);

        Scene scene = new Scene(borderPane, 350, 280);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise15_28");
        primaryStage.show();
    }
}