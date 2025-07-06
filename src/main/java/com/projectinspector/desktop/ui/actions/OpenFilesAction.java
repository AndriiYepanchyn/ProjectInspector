package com.projectinspector.desktop.ui.actions;

import java.io.File;
import java.util.List;

import com.projectinspector.mainapp.MainApp;
import com.projectinspector.persistence.PersistanceMgr;

import javafx.scene.image.ImageView;

public class OpenFilesAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
public OpenFilesAction(String text, char accelerator, String tooltip, ImageView pic) {
	super(text, accelerator, tooltip,  pic);
}

	@Override
	public void execute() {
		String[] filter = {"*.java", "*.py", "*.jar", "*.cpp"};
		
		List<File> files = PersistanceMgr.chooseMultipleFiles(MainApp.getPrimaryStage(),  filter);
		System.out.println("Selected files = " );
		files.forEach(e ->{System.out.println(e.getAbsolutePath());});
		
	}

}
