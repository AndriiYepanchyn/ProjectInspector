package com.projectinspector.desktop.ui.actions;

import java.io.File;
import java.util.List;

import com.projectinspector.datamodel.FileNode;
import com.projectinspector.mainapp.MainApp;
import com.projectinspector.persistence.PersistanceMgr;

import javafx.scene.image.ImageView;

public class OpenFilesAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static FileNode fileNode = MainApp.getFileNode();
	String[] filter = {"*.java", "*.py", "*.jar", "*.cpp"}; //FIXME create getter for supported types.
	
public OpenFilesAction(String text, char accelerator, String tooltip, ImageView pic) {
	super(text, accelerator, tooltip,  pic);
}

	@Override
	public void execute() {
		
		List<File> files = PersistanceMgr.chooseMultipleFiles(MainApp.getPrimaryStage(),  filter);
		System.out.println("Selected files = " );
		files.forEach(e ->{System.out.println(e.getAbsolutePath());});
		System.out.println("=".repeat(40));
		
		fileNode.addChildren(files);
		System.out.println(fileNode.printTree());
		
		
		
	}

}
