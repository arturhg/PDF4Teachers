/*
 * Copyright (c) 2021-2023. Clément Grennerat
 * All rights reserved. You must refer to the licence Apache 2.
 */

package fr.clementgre.pdf4teachers.utils.dialogs.alerts;

import fr.clementgre.pdf4teachers.utils.panes.PaneUtils;
import fr.clementgre.pdf4teachers.utils.style.StyleManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import jfxtras.styles.jmetro.Style;

public class TextInputAlert extends CustomAlert{
    
    
    protected final TextField input = new TextField();
    protected final HBox contentHBox = new HBox();
    
    public TextInputAlert(String title, String header, String details){
        super(AlertType.CONFIRMATION, title, header, null);
        
        contentHBox.setPadding(new Insets(15, 0, 15, 0));
        if(details != null){
            Label beforeText = new Label();
            beforeText.setText(details);
            contentHBox.setSpacing(10);
            PaneUtils.setHBoxPosition(beforeText, 0, 25, 0);
            contentHBox.getChildren().addAll(beforeText, input);
        }else{
            contentHBox.getChildren().addAll(input);
        }
    
        addOKButton(ButtonPosition.DEFAULT);
        addCancelButton(ButtonPosition.CLOSE);
    
        StyleManager.putCustomStyle(getDialogPane(), "someDialogs.css");
        if(StyleManager.DEFAULT_STYLE == Style.LIGHT) StyleManager.putCustomStyle(getDialogPane(), "someDialogs-light.css");
        else StyleManager.putCustomStyle(getDialogPane(), "someDialogs-dark.css");
        
        getDialogPane().setContent(contentHBox);
    
        Platform.runLater(input::requestFocus);
    }
    
    public void setText(String text){
        input.setText(text);
    }
    public String getText(){
        return input.getText();
    }
    
}
