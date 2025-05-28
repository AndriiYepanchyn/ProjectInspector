package com.example.projectinspector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Orientation;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Project Inspector");

        // Меню
        MenuBar menuBar = new MenuBar();
        ToolBar toolBar = new ToolBar();

        for (int i = 1; i <= 3; i++) {
            Menu menu = new Menu("Menu " + i);
            for (int j = 1; j <= 2; j++) {
                int actionNum = (i - 1) * 2 + j;
                MenuItem item = new MenuItem("Action" + actionNum);
                item.setAccelerator(KeyCombination.keyCombination("Ctrl+" + actionNum));
                int finalActionNum = actionNum;
                item.setOnAction(e -> showActionDialog(finalActionNum));
                menu.getItems().add(item);

                Button btn = new Button("Action" + actionNum);
                btn.setOnAction(e -> showActionDialog(finalActionNum));
                toolBar.getItems().add(btn);
            }
            menuBar.getMenus().add(menu);
        }

        // Статус бар
        Label zoomLabel = new Label("Zoom:");
        Slider zoomSlider = new Slider(10, 1000, 100);
        TextField zoomField = new TextField("100");
        zoomField.setPrefWidth(60);
        HBox statusBar = new HBox(10, zoomLabel, zoomSlider, zoomField);
        statusBar.setStyle("-fx-padding: 5; -fx-border-color: #ccc; -fx-border-width: 1 0 0 0;");

        // Центр: розділений Pane
        TreeView<String> treeView = new TreeView<>(new TreeItem<>("Root"));
        Canvas canvas = new Canvas(600, 400);
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.getItems().addAll(new VBox(treeView), new StackPane(canvas));
        splitPane.setDividerPositions(0.3);

        // Основний layout
        BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar, toolBar));
        root.setCenter(splitPane);
        root.setBottom(statusBar);

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    private void showActionDialog(int number) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action Performed");
        alert.setHeaderText(null);
        alert.setContentText("Акшн номер " + number + " виконано.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
