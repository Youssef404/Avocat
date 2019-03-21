package gui.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import testjdbc.data.DAO.ClientDAO;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.Client;
import testjdbc.tools.TableClientUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientPageController implements Initializable {

    @FXML
    private TableView tClient;
    @FXML
    private TextField tfNom, tfPrenom, tfTelephone, tfAdresse;
    @FXML
    private JFXComboBox<Integer> cbDossier;

    private int idDossier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DossierDAO dDAO = new DossierDAO();
        TableClientUtil tCU = new TableClientUtil();
        //Initialisation de la table
        tClient.getColumns().addAll(tCU.getNomCol(), tCU.getPrenomCol(), tCU.getTelCol(), tCU.getAdresseCol());

        //Initialisation du combobox
        cbDossier.getItems().addAll(dDAO.getIds());

        //Remplissage du formulaire quand le tableau est cliqué
        tClient.setOnMouseClicked((me)->{
            Client c = (Client)tClient.getSelectionModel().getSelectedItem();
            if(c!=null){
                tfAdresse.setText(c.getAdresse());
                tfNom.setText(c.getNom());
                tfPrenom.setText(c.getPrenom());
                tfTelephone.setText(c.getPrenom());
                cbDossier.setValue(c.getDossier());
            }
        });
    }

    /**
     * Methode qui definir l'id du dossier à charger
     */
    public void setIdDossier(int idDossier) {
        if(idDossier!=this.idDossier){
            this.idDossier = idDossier;
            fillTable();
            resetForm();
            cbDossier.setValue(idDossier);
        }
    }

    /**
     * Methode qui remplit la TableView
     */
    public void fillTable() {
        TableClientUtil tCU = new TableClientUtil();
        tClient.getItems().clear();
        tClient.getItems().addAll(tCU.getListe(idDossier));
        tClient.refresh();
    }

    /**
     * Methode qui vide le formulaire
     */
    public void resetForm(){
        tfTelephone.clear();
        tfPrenom.clear();
        tfNom.clear();
        tfAdresse.clear();
        cbDossier.setValue(null);
    }

    /**
     * Methode qui ajoute un client
     */
    public void ajouterClient() {
        ClientDAO cDAO = new ClientDAO();
        Client c = new Client(0, tfNom.getText(), tfPrenom.getText(), tfTelephone.getText(), tfAdresse.getText(), idDossier);
        int id = cDAO.insert(c);
        c.setId(id);
        tClient.getItems().add(c);
        tClient.refresh();
    }

    /**
     * Methode qui modifie un client
     */
    public void modifierClient() {
        ClientDAO cDAO = new ClientDAO();
        Client c = (Client) tClient.getSelectionModel().getSelectedItem();
        int pos = tClient.getSelectionModel().getSelectedIndex();
        c.setAdresse(tfAdresse.getText());
        c.setNom(tfNom.getText());
        c.setPrenom(tfPrenom.getText());
        c.setTel(tfTelephone.getText());
        int newDossier = cbDossier.getValue();
        c.setDossier(newDossier);
        cDAO.update(c);
        //Si l'id du dossier est la même, l'affaire est mise à jour, sinon elle est deplacée
        if(newDossier == idDossier){
            tClient.getItems().set(pos,c);
        } else{
            tClient.getItems().remove(pos);
        }
        tClient.refresh();
    }

    /**
     * Methode qui supprime un client
     */
    public void supprimerClient(){
        ClientDAO cDAO = new ClientDAO();
        Client c = (Client) tClient.getSelectionModel().getSelectedItem();
        int pos = tClient.getSelectionModel().getSelectedIndex();
        cDAO.delete(c.getId());
        tClient.getItems().remove(pos);
        tClient.refresh();
    }
}
