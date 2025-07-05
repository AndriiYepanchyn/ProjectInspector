package com.projectinspector.desktop.ui.actions;


import javafx.scene.control.Alert;

public class DummyAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private String _actionText;
	
	public DummyAction(String text, char accelerator) {
		super(text, accelerator, "Perform Action: " + text , null);
		this._actionText = text;
	}
	
	public void execute() {
		showActionDialog(_actionText);
	}
	
    private void showActionDialog(String _number) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(_number);
        alert.setHeaderText(null);
        alert.setContentText("Акшн номер " + _number + " виконано.");
        alert.showAndWait();
    }

}
