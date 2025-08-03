package com.projectinspector.datamodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNode {
	private static String EMPTY_FILE = "Empty file";
	

	FileNode parent;
	ArrayList<FileNode> children;
	String name;
	boolean isLeaf;
	File originFile;
	
//	+
	public FileNode() {
		this.parent = null;
		this.isLeaf = true;
		this.children=new ArrayList<>();
		this.name = EMPTY_FILE;
		this.originFile = null;
	}
//	+
	public FileNode(File file) throws IllegalArgumentException{
		if(file == null || !file.exists() ) {
			throw new IllegalArgumentException("Can't create FileNode because file " + file + " doesn't exist");
		}

			this.parent = null; // TODO should I search for parent for new node
			this.isLeaf = !file.isDirectory();
			
			if(this.isLeaf) {
			this.children = null;
			} else {
				this.children = new ArrayList<>();
				addChildren(file.listFiles());
			}
			
			this.name = file.getName();
			this.originFile = file;		
	}
	
//	+  FIXME remake into iterable multithreading version
	public boolean addChildren(File[] files) {
		boolean result = false;
		if (files == null || files.length == 0) {
			return false;
		} else {
			for (File f : files) {
				result = result | addChild(f);
			}
		}
		return result;
	}
	
//	+
	public boolean addChildren(List<File> files) {
		boolean result = false;
		if (files == null || files.size() == 0) {
			return false;
		} else {
			for (File f : files) {
				result = result | addChild(f);
			}
		}
		return result;
	}
//	+  
	public boolean addChild(File file) {
		if(file == null || !file.exists()) {
			return false;
		}
		
		FileNode child = new FileNode(file);
		if(children!=null) {
		children.add(child);
		return true;
		} else {
			return false;
		}
	} 
	
//	+
	public FileNode createSubFolder(String name) {
		if(this.isLeaf) {
			throw new IllegalArgumentException("Can't create subfolder for " + getName() + " because it is leaf");
		} else {
			FileNode subfolder = new FileNode();
			subfolder.parent = this;
			this.children.add(subfolder);
			subfolder.isLeaf = false;
			subfolder.name = name;
			return subfolder;
		}
	}	
	
//	-----------------------  Getters ----------------------------------------
	public String getName() {
		return this.name;
	}
	
	public boolean isLeaf() {
		return this.isLeaf;
	}
	
	public FileNode getParent() {
		return this.parent;
	}
	
	public File getOriginFile() {
		return this.originFile;
	}
	
	public List<FileNode> getChildren(){
		return this.children;
	}
//	====================== Getters ------------------------------------------
	
	public boolean hasChildren() {
		return (this.children != null && this.children.size() > 0);
	}
	
	public boolean renameItem(String newName) {
		return false;
		
	}
	
	public boolean removeItem(File file) {
		return false;
	}

	public boolean removeItem(String name) {
		return false;
	}
	

	
	public String getTooltipText() {
		return this.originFile.getAbsolutePath();
	}

	
//	public FileNode getChildAt(int index);
//	public int getChildCount();
//	public int getIndexOfChild(FileNode child);
	
	
//  ------------------ Clone and serialization ----------------------------
	
//	public Transferable toTransferable();
//	public static FileNode fromTransferable(Transferable t);
//	public boolean contains(File file);
//	public void sortChildren(Comparator<FileNode> comparator);
	
//	================== Clone and serialization ============================
	
	
	public String toString() {
		return this.getName().isEmpty() ? EMPTY_FILE : this.getName();
	}
	
	public String printTree() {
		StringBuilder result = new StringBuilder();
		String sep = " ".repeat(4);
		
		if(!EMPTY_FILE.equals(getName())){
			result.append(toString());
		}
		if(hasChildren()) {
			result.append("\n");
			for(FileNode f : children) {
				result.append(sep).append(f.printTree()).append("\n");
			}
		}
		
		return result.toString();
	}

}
