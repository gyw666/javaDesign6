package fanType;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class Fan3 extends Pane{
    private Color color;
    private int radius;
    Group group=new Group();
    Scene scene;
    public Fan3() {
        color = Color.RED;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Fan3(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    public void drawFan(Stage primaryStage) {
        double centerX = 400;
        double centerY = 300;
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(centerX,centerY,radius,radius,i*90+30,30);
            arc.setType(ArcType.ROUND);
            arc.setFill(color);
            arc.setStroke(Color.BLACK);
            arc.setStrokeWidth(1);
            group.getChildren().add(arc);
        }
        // 创建Path对象
        Path path = new Path();

        // 起点：在圆弧的顶部（3点钟位置）
        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX + radius); // 起点X坐标
        moveTo.setY(centerY);          // 起点Y坐标

        // 创建上半圆弧
        ArcTo arcTo1 = new ArcTo();
        arcTo1.setX(centerX - radius); // 上半圆弧终点X坐标
        arcTo1.setY(centerY);          // 上半圆弧终点Y坐标
        arcTo1.setRadiusX(radius);     // X轴方向的半径
        arcTo1.setRadiusY(radius);     // Y轴方向的半径
        arcTo1.setSweepFlag(false);    // 指定弧的方向为逆时针

        // 创建下半圆弧
        ArcTo arcTo2 = new ArcTo();
        arcTo2.setX(centerX + radius); // 下半圆弧终点X坐标
        arcTo2.setY(centerY);          // 下半圆弧终点Y坐标
        arcTo2.setRadiusX(radius);     // X轴方向的半径
        arcTo2.setRadiusY(radius);     // Y轴方向的半径
        arcTo2.setSweepFlag(false);    // 指定弧的方向为逆时针

        // 将起点和圆弧添加到路径
        path.getElements().add(moveTo);
        path.getElements().add(arcTo1);
        path.getElements().add(arcTo2);

        // 设置路径的样式
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        path.setFill(null);

        // 将路径添加到Pane
        group.getChildren().add(path);



        Button blueButton = new Button("蓝色");
        Button yellowButton = new Button("黄色");
        Button greenButton = new Button("绿色");
        Button orangeButton = new Button("橙色");
        Button redButton = new Button("红色");

        VBox hc=new VBox(10, blueButton, yellowButton, greenButton, orangeButton, redButton);
        hc.setAlignment(Pos.BOTTOM_RIGHT);



        Button radius_50=new Button("50");
        Button radius_100=new Button("100");
        Button radius_150=new Button("150");
        Button radius_200=new Button("200");
        Button radius_250=new Button("250");

        VBox r=new VBox(10, radius_50, radius_100, radius_150, radius_200, radius_250);
        r.setAlignment(Pos.BOTTOM_LEFT);

        radius_100.setOnAction(event -> {
            new Fan3(this.color,100).drawFan(primaryStage);
        });

        radius_150.setOnAction(event -> {
            new Fan3(this.color,150).drawFan(primaryStage);
        });

        radius_200.setOnAction(event -> {
            new Fan3(this.color,200).drawFan(primaryStage);
        });

        radius_50.setOnAction(event -> {
            new Fan3(this.color,50).drawFan(primaryStage);
        });

        radius_250.setOnAction(event -> {
            new Fan3(this.color,250).drawFan(primaryStage);
        });





        Button pause = new Button("暂停"); //暂停按钮
        Button resume = new Button("继续");   //继续按钮
        Button reverse = new Button("反转"); //反转按钮


        HBox hBox = new HBox(10, pause, resume, reverse);
        hBox.setAlignment(Pos.BOTTOM_CENTER);





        redButton.setOnAction(event ->{
            new Fan3(Color.RED,radius).drawFan(primaryStage);
        });

        blueButton.setOnAction(event ->{
            new Fan3(Color.BLUE,radius).drawFan(primaryStage);
        });

        yellowButton.setOnAction(event ->{
            new Fan3(Color.YELLOW,radius).drawFan(primaryStage);
        });

        greenButton.setOnAction(event ->{
            new Fan3(Color.GREEN,radius).drawFan(primaryStage);
        });

        orangeButton.setOnAction(event ->{
            new Fan3(Color.ORANGE,radius).drawFan(primaryStage);
        });

        //关键帧
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(10), event -> group.setRotate(group.getRotate()+1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(10), event -> group.setRotate(group.getRotate()-1));
        Timeline animation = new Timeline(keyFrame1);   //时间线动画
        animation.setCycleCount(Timeline.INDEFINITE);   //无限循环次数
        animation.play();   //启动动画

        pause.setOnAction(event -> animation.pause());
        resume.setOnAction(event -> animation.play());
        reverse.setOnAction(event -> {
            animation.stop();
            animation.getKeyFrames().add(animation.getKeyFrames().remove(0).equals(keyFrame1) ? keyFrame2 : keyFrame1);
            animation.play();
        });
        Slider slider = new Slider();   //滑动条
        slider.setMax(10);  //滑动条设置最大值
        slider.valueProperty().addListener(observable -> animation.setRate(slider.getValue())); //滑动条添加监听器

        BorderPane borderPane = new BorderPane(new BorderPane(group));
        borderPane.setTop(hBox);
        borderPane.setRight(hc);
        borderPane.setLeft(r);
        borderPane.setBottom(slider);

        scene=new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("顾砚文牌牛逼风扇");
        primaryStage.show();
    }


    public static Color getRandomColor() {
        int choice=new Random().nextInt(5);
        switch (choice) {
            case 0:{
                return Color.RED;
            }
            case 1:{
                return Color.BLUE;
            }
            case 2:{
                return Color.YELLOW;
            }
            case 3:{
                return Color.GREEN;
            }
            case 4:{
                return Color.ORANGE;
            }
        }
        return null;
    }

    public static int getRandomRadius(){
        return 50*(1+new Random().nextInt(4));
    }
}
