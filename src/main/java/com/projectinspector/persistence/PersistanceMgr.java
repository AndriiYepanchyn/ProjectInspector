package com.projectinspector.persistence;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PersistanceMgr {
	
	public static File chooseDirectory(Stage owner) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Оберіть папку");
        return chooser.showDialog(owner);
    }
	
	public static List<File> chooseMultipleFiles(Stage owner, String[] extensions) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Оберіть файли");
        
        String allFilesDesc ="All Files";
        chooser.getExtensionFilters().add(new ExtensionFilter(allFilesDesc, "*.*"));
        
        String description ="All available";
        chooser.getExtensionFilters().add(new ExtensionFilter(description, extensions));
        
        for(String s: extensions) {
        	String desc = s.substring(s.indexOf('.') +1 ) + " ";
        	chooser.getExtensionFilters().add(new ExtensionFilter(desc, s));
        }
        
        return Optional.ofNullable(chooser.showOpenMultipleDialog(owner))
                .orElse(new LinkedList<File>());
    }
	
//----------------------------------------	
	
//========================================
}
