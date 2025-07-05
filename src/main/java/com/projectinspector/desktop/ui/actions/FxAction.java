package com.projectinspector.desktop.ui.actions;

import javafx.scene.image.ImageView;

public interface FxAction {
    String getText();
    char getAccelerator();
    String getTooltip();
    ImageView getIcon();
    void execute();
}