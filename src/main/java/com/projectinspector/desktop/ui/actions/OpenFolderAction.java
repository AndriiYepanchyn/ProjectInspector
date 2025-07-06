package com.projectinspector.desktop.ui.actions;

import java.io.File;
import com.projectinspector.mainapp.MainApp;
import com.projectinspector.persistence.PersistanceMgr;

import javafx.scene.image.ImageView;

public class OpenFolderAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
public OpenFolderAction(String text, char accelerator, String tooltip, ImageView pic) {
	super(text, accelerator, tooltip,  pic);
}

	@Override
	public void execute() {
		File folder = PersistanceMgr.chooseDirectory(MainApp.getPrimaryStage());
		System.out.println("Selected folder = " + folder.getAbsolutePath());
	}

}
