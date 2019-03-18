package gui.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import testjdbc.data.DAO.AffaireDAO;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.Affaire;
import testjdbc.tools.TableAffaireUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AffairePageController implements Initializable {


    @FXML
    TableView tAffaire;

    @FXML
    JFXTextField tfMotif, tfHonoraires, tfRestPaiement;

    @FXML
    JFXComboBox<Integer> cbDossier;

    @FXML
    JFXDatePicker dpCreation;

    private int idDossier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DossierDAO dDAO = new DossierDAO();
        TableAffaireUtil tAU = new TableAffaireUtil();
        tAffaire.getColumns().addAll(tAU.getMotifCol(),tAU.getDateCreaCol(),tAU.getHonorairesfCol(),tAU.getRestPaiementCol());
        cbDossier.getItems().addAll(dDAO.getIds());
        tAffaire.setOnMouseClicked((me)->{
            Affaire a = (Affaire)tAffaire.getSelectionModel().getSelectedItem();
            if(a!=null){
                tfRestPaiement.setText(String.valueOf(a.getRestPaiement()));
                tfMotif.setText(a.getMotif());
                tfHonoraires.setText(String.valueOf(a.getHonoraires()));
                dpCreation.setValue(a.getDateCreation());
                cbDossier.setValue(a.getDossier());
            }
        });
    }

    public void setIdDossier(int idDossier) {
        if(idDossier!=this.idDossier){
            this.idDossier = idDossier;
            fillTable();
            resetForm();
            cbDossier.setValue(idDossier);
        }
    }

    public void fillTable() {
        TableAffaireUtil tCU = new TableAffaireUtil();
        tAffaire.getItems().clear();
        tAffaire.getItems().addAll(tCU.getListe(idDossier));
        tAffaire.refresh();
    }

    public void resetForm(){
        tfHonoraires.clear();
        tfMotif.clear();
        tfRestPaiement.clear();
        cbDossier.setValue(null);
        dpCreation.setValue(null);
    }

    public void ajouterAffaire() {
        AffaireDAO aDAO = new AffaireDAO();
        Affaire a = new Affaire(0, idDossier, tfMotif.getText(), Double.valueOf(tfHonoraires.getText()), Double.valueOf(tfHonoraires.getText()), dpCreation.getValue());
        int id = aDAO.insert(a);
        a.setId(id);
        tAffaire.getItems().add(a);
        tAffaire.refresh();
    }

    public void modifierAffaire(){
        AffaireDAO aDAO = new AffaireDAO();
        Affaire a = (Affaire)tAffaire.getSelectionModel().getSelectedItem();
        int pos = tAffaire.getSelectionModel().getSelectedIndex();
        a.setDateCreation(dpCreation.getValue());
        a.setHonoraires(Double.valueOf(tfHonoraires.getText()));
        a.setRestPaiement(Double.valueOf(tfRestPaiement.getText()));
        a.setDossier(cbDossier.getValue());
        a.setMotif(tfMotif.getText());
        int newDossier = cbDossier.getValue();
        a.setDossier(newDossier);
        if(newDossier==idDossier){
            tAffaire.getItems().set(pos,a);
        }
        else{
            tAffaire.getItems().remove(pos);
        }
        tAffaire.refresh();
    }

    public void supprimerAffaire(){
        AffaireDAO aDAO = new AffaireDAO();
        Affaire a = (Affaire)tAffaire.getSelectionModel().getSelectedItem();
        int pos = tAffaire.getSelectionModel().getSelectedIndex();
        aDAO.delete(a.getId());
        tAffaire.getItems().remove(pos);
        tAffaire.refresh();
    }
}
