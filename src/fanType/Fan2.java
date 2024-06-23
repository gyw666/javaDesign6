package fanType;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

import java.util.Random;


public class Fan2 extends Pane{
    private Color color;
    private int radius;
    public Fan2() {
        color = Color.RED;
        drawFan();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        drawFan();
    }

    public int getRadius() {
        return radius;

    }

    public void setColor(Color color) {
        this.color = color;
        drawFan();
    }

    public Color getColor() {
        return color;
    }

    public Fan2(Color color, int radius) {
        this.color = color;
        this.radius = radius;
        drawFan();
    }

    private void drawFan() {
        double centerX = 400;
        double centerY = 300;
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(centerX,centerY,radius,radius,i*90+30,30);
            arc.setType(ArcType.ROUND);
            arc.setFill(color);
            arc.setStroke(Color.BLACK);
            arc.setStrokeWidth(1);
            this.getChildren().add(arc);
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
        this.getChildren().add(path);

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
