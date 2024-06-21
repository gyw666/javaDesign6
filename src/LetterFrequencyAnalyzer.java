

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LetterFrequencyAnalyzer extends Application {

    private BarChart<String, Number> barChart;
    private Map<Character, Integer> letterCount;

    @Override
    public void start(Stage primaryStage) {
        // 创建柱状图
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Letter");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Letter Frequency");
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("letterStatistics");

        // 创建按钮
        Button viewButton = new Button("view");
        viewButton.setOnAction(event -> displayBarChart());

        // 布局
        VBox root = new VBox(barChart, viewButton);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("letterStatistics");
        primaryStage.show();
    }

    private void displayBarChart() {
        // 选择文件
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");
        File selectedFile = fileChooser.showOpenDialog(barChart.getScene().getWindow());
        if (selectedFile != null) {
            // 读取文件并统计字母数量
            readFile(selectedFile.getAbsolutePath());

            // 创建柱状图数据
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            List<Character> sortedKeys = new ArrayList<>(letterCount.keySet());
            sortedKeys.sort(Character::compareTo);
            for (Character c : sortedKeys) {
                String label = c.toString() + "_" + letterCount.get(c);
                series.getData().add(new XYChart.Data<>(label, letterCount.get(c)));
            }

            // 清空柱状图并添加新数据
            barChart.getData().clear();
            barChart.getData().add(series);
            barChart.setBarGap(2); // 增加柱子之间的间距
            barChart.setCategoryGap(2);
            barChart.setMaxWidth(999);
            barChart.setScaleX(1);

        }
    }

    private void readFile(String filePath) {
        letterCount = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        letterCount.merge(Character.toLowerCase(c), 1, Integer::sum);
                    }
                }
            }

            // 确保所有字母都在图表中显示,即使数量为0
            for (char c = 'a'; c <= 'z'; c++) {
                letterCount.putIfAbsent(c, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}