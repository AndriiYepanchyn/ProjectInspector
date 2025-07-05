package com.projectinspector.desktop.ui.actions;

import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

public abstract class AbstractAction extends ActionEvent implements FxAction {

	private static final long serialVersionUID = 1L;
	private String _text;
	private char _accelerator;
	private String _tooltip;
	private ImageView _pic;
	
	public AbstractAction() {
		super();
		this._text = "";
		this._accelerator = '0';
		this._tooltip = "";
		this._pic = null;
	}
	
	
	/**
	 * Creates Action with preset parameters. 
	 * @param text - Text which will be shown in menuItem or button. Empty string if null.
	 * @param tooltip - Tooltip which will be shown on button. Empty String if null.
	 * @param pic - Pic which will be shown on menuItem or command button.
	 * @param app - MainApp object
	 */
	public AbstractAction(String text, char accelerator, String tooltip, ImageView pic) {
		super();
		this._text = text == null?  "" : text;
		this._accelerator = accelerator;
		this._tooltip = tooltip == null? "" : tooltip;
		this._pic = pic == null? null : pic;
	}

	@Override
	public String getText() {
		return _text;
	}

	@Override
	public String getTooltip() {
		return _tooltip;
	}

	@Override
	public ImageView getIcon() {
		return _pic;
	}

	@Override
	public char getAccelerator() {
		return _accelerator;
	}
	
	public abstract void execute() ;


}
