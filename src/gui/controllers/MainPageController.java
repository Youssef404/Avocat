/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class  MainPageController implements Initializable {

    @FXML
    private VBox alertContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label label = new Label("Aucune alerte");
        label.setStyle("-fx-font-family :  \"Comfortaa Light\";-fx-font-size: 15px");
        
        alertContainer.getChildren().add(label);
    }    
    
}
