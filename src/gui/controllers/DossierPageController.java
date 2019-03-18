/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Dossier;
import testjdbc.tools.TableDossierUtil;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class DossierPageController implements Initializable {

    @FXML
    private TableView<AbstractModel> tDossier;
    @FXML
    JFXDatePicker dcCreation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableDossierUtil tDU = new TableDossierUtil();

        //Initialisation du tableau
        tDossier.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tDossier.getColumns().addAll(tDU.getNumCol(), tDU.getDateCol());
        remplirTableView();
    }

    //Methode qui remplit la TableView
    public void remplirTableView() {
        TableDossierUtil tDU = new TableDossierUtil();
        tDossier.setItems(tDU.getListe());
        tDossier.refresh();
    }

    //Methode qui ajoute un dossier
    public void ajouterDossier(ActionEvent e) {
        Dossier d = new Dossier(0, dcCreation.getValue());
        DossierDAO dDao = new DossierDAO();
        dDao.insert(d);
        tDossier.getItems().add(d);
        tDossier.refresh();
    }

    //Methode qui modifie un dossier
    public void modifierDossier(ActionEvent e){
        Dossier selectedD = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        int i = tDossier.getSelectionModel().getSelectedIndex();
        selectedD.setDateCreation(dcCreation.getValue());
        DossierDAO dDao = new DossierDAO();
        dDao.update(selectedD);
        tDossier.getItems().set(i,selectedD);
        tDossier.refresh();
    }

    //Methode qui supprime un dossier
    public void supprimerDossier(ActionEvent e){
        Dossier selectedD = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        int i = tDossier.getSelectionModel().getSelectedIndex();
        DossierDAO dDAO = new DossierDAO();
        dDAO.delete(selectedD.getId());
        tDossier.getItems().remove(i);
        tDossier.refresh();
    }

    //Une méthode qui retourne l'id du dossier selectionnée
    public int getSelectedId(){
        Dossier selectedD = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        return selectedD.getId();
    }

    public TableView<AbstractModel> getTable(){
        return tDossier;
    }

    public JFXDatePicker getDcCreation() {
        return dcCreation;
    }
}
