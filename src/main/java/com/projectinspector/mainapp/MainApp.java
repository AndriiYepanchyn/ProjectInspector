package com.projectinspector.mainapp;

import com.projectinspector.datamodel.FileNode;
import com.projectinspector.desktop.ui.FileNodeCellFactory;
import com.projectinspector.desktop.ui.actions.DummyAction;
import com.projectinspector.desktop.ui.actions.FxAction;
import com.projectinspector.desktop.ui.actions.OpenFilesAction;
import com.projectinspector.desktop.ui.actions.OpenFolderAction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Orientation;

public  class MainApp extends Application {
	private static Stage _primaryStage;
	
	private static FileNode FILE_NODE;
	private static TreeItem<FileNode> TREE_MODEL;
	
	
	static {
		FILE_NODE = new FileNode();
		TREE_MODEL = createTreeModel(FILE_NODE);
	}

	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		primaryStage.setTitle("Project Inspector");

		
		TreeView<FileNode> treeView = new TreeView<FileNode>(TREE_MODEL);
//		FileNodeCellFactory factory = new FileNodeCellFactory();
//		treeView.setCellFactory(factory);

		Canvas canvas = new Canvas(600, 400);
		SplitPane splitPane = new SplitPane();
		splitPane.setOrientation(Orientation.HORIZONTAL);
		splitPane.getItems().addAll(new VBox(treeView), new StackPane(canvas));
		splitPane.setDividerPositions(0.3);

		// Основний layout
		BorderPane root = new BorderPane();
		
		ToolBar toolBar1 = createToolBar();
		ToolBar toolBar2 = createToolBar();
		ToolBar toolBar3 = new ToolBar(toolBar2, toolBar1);
		
		root.setTop(new VBox(createMenuBar(), toolBar3));
		root.setCenter(splitPane);
		root.setBottom(createStatusBar());

		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {		
		launch(args);
	}

	private static TreeItem<FileNode> createTreeModel(FileNode fileNode) {

		return new TreeItem<FileNode>(fileNode);
	}

	private HBox createStatusBar() {
		Label zoomLabel = new Label("Zoom:");
		Slider zoomSlider = new Slider(10, 1000, 100);
		TextField zoomField = new TextField("100");
		zoomField.setPrefWidth(60);
		HBox statusBar = new HBox(10, zoomLabel, zoomSlider, zoomField);
		statusBar.setStyle("-fx-padding: 5; -fx-border-color: #ccc; -fx-border-width: 1 0 0 0;");

		return statusBar;
	}

	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");

		MenuItem fileSave = createMenuItem(new DummyAction("Save", 'S'));
		MenuItem exit = createMenuItem(new DummyAction("Exit", 'X'));
		MenuItem openFilesMenu = createMenuItem(new OpenFilesAction("Open files", 'O', "Opens one or several files in one folder", null)) ;
		MenuItem openFolderMenu = createMenuItem(new OpenFolderAction("Open folder", 'L', "Opens single folder", null)) ;
		
		fileMenu.getItems().add(openFolderMenu);
		fileMenu.getItems().add(openFilesMenu);
		fileMenu.getItems().add(fileSave);
		fileMenu.getItems().add(exit);
		
		
		menuBar.getMenus().add(fileMenu);
		return menuBar;

	}

	private MenuItem createMenuItem(FxAction action) {
		MenuItem item = new MenuItem(action.getText(), action.getIcon());
		item.setAccelerator(KeyCombination.keyCombination("Ctrl+" + action.getAccelerator()));
		item.setOnAction(e -> action.execute());
		return item;
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		
		Button openfileButton = createToolButton( new DummyAction(null, 'O'));
		toolBar.getItems().add(openfileButton);
		
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 2; j++) {
				int actionNum = (i - 1) * 2 + j;
				Button btn = createToolButton( new DummyAction("Action " + actionNum, 'B'));
				toolBar.getItems().add(btn);
			}
		}

		
		return toolBar;
	}
	
	public Button createToolButton(FxAction action) {
	    Button btn = new Button(action.getText(), action.getIcon());
	    btn.setTooltip(new Tooltip(action.getTooltip()));
	    btn.setOnAction(e -> action.execute());
	    return btn;
	}

	public static  Stage getPrimaryStage() {
		return _primaryStage;
	}
	
	public static FileNode getFileNode() {
		return FILE_NODE;
	}

}
