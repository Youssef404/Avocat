package gui.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PiecePageController implements Initializable {

    @FXML
    JFXTextField fxid_chemin_piece;


    public void fileChooser() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        JEditorPane actionStatus;
        if (selectedFile != null) {

            fxid_chemin_piece.setText("File selected: " + selectedFile.getName());
        } else {
            fxid_chemin_piece.setText("File selection cancelled.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
