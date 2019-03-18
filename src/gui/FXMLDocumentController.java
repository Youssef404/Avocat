/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Affaire;
import testjdbc.data.model.Dossier;
import testjdbc.tools.TableAffaireUtil;
import testjdbc.tools.TableAudienceUtil;
import testjdbc.tools.TableClientUtil;
import testjdbc.tools.TableDossierUtil;
import testjdbc.tools.TablePieceUtil;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<AbstractModel> tClient;
    
    @FXML
    private TableView<AbstractModel> tAudience;
    
    @FXML
    
    private TableView<AbstractModel> tPiece;
    
    @FXML
    private TableView tDossier;
    
    @FXML
    private TableView<AbstractModel> tAffaire;
    
    @FXML
    private TitledPane accClient;
    
    @FXML
    private TitledPane accAffaire;
    
    @FXML
    private VBox formDossier;
    
    @FXML
    private AnchorPane viewDossier;
    
    @FXML
    private Button bValiderDossier;
    
    @FXML
    private DatePicker dcDateDossier;
    
    @FXML
    public void remplirTableView(){
        TableDossierUtil tDU = new TableDossierUtil();
        tDossier.setItems(tDU.getListe());
        tDossier.refresh();
    }
    
    @FXML
    public void ouvrirDossier(){
        Dossier selectedF = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        int selected = selectedF.getId();
        
        //Remplir client
        TableClientUtil tCU = new TableClientUtil();
        tClient.setItems(tCU.getListe(selected));
        tClient.refresh();
        if(!accAffaire.isExpanded()){
           accClient.setExpanded(true);
        } 
        
        //Remplir affaire
        TableAffaireUtil tAU = new TableAffaireUtil();
        tAffaire.setItems(tAU.getListe(selected));
        tAffaire.refresh();
    }
    
    @FXML
    public void ouvrirAffaire(){
        Affaire selectedA = (Affaire)tAffaire.getSelectionModel().getSelectedItem();
        int selected = selectedA.getId();
        //Remplir audience
        TableAudienceUtil tAuU = new TableAudienceUtil();
        tAudience.setItems(tAuU.getListe(selected));
        tAudience.refresh();
        //Remplir Affaire
        TablePieceUtil tPU = new TablePieceUtil();
        tPiece.setItems(tPU.getListe(selected));
        tPiece.refresh();
    }
    
    //Action 0 = ajouter // Action 1 = modifier
    int actionDossier = -1;
    
    @FXML
    public void toggleAjouterDossier(){
        bValiderDossier.setText("Ajouter");
        bValiderDossier.setOnAction(this::ajouterDossier);
        toggleDossier();
    }
    
    @FXML
    public void toggleModifierDossier(){
        Dossier selectedD = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        dcDateDossier.setValue(selectedD.getDateCreation());
        bValiderDossier.setText("Modifier");
        bValiderDossier.setOnAction(this::modifierDossier);
        actionDossier = 1;
        toggleDossier();
    }
    
    @FXML 
    public void ajouterDossier(ActionEvent e){
        Dossier d = new Dossier(0, dcDateDossier.getValue());
        DossierDAO dDao = new DossierDAO();
        dDao.insert(d);
        toggleDossier();
        tDossier.refresh();
    }
    
    @FXML
    public void modifierDossier(ActionEvent e){
        Dossier selectedD = (Dossier)tDossier.getSelectionModel().getSelectedItem();
        selectedD.setDateCreation(dcDateDossier.getValue());
        DossierDAO dDao = new DossierDAO();
        dDao.update(selectedD);
        toggleDossier();
        tDossier.refresh();
    }
    int i = 0;
    public void toggleDossier(){
        if(i==0){
            //Showing form and hiding the table
            formDossier.visibleProperty().set(true);
            viewDossier.visibleProperty().set(false);
            i=1;
        }else{
            formDossier.visibleProperty().set(false);
            viewDossier.visibleProperty().set(true);
            i=0;
        }
    }
    /**
     * Initializes the controller class
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initializing table utils
        TableClientUtil tCU = new TableClientUtil();
        TableDossierUtil tDU = new TableDossierUtil();
        TableAffaireUtil tAU = new TableAffaireUtil();
        TableAudienceUtil tAuU = new TableAudienceUtil();
        TablePieceUtil tPU = new TablePieceUtil();
        
        //Making column resize policy
        tDossier.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tClient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tAffaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tAudience.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tPiece.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Adding columns to each table
        tDossier.getColumns().addAll(tDU.getNumCol(),tDU.getDateCol());
        tClient.getColumns().addAll(tCU.getNomCol(),tCU.getPrenomCol(),tCU.getAdresseCol(),tCU.getTelCol());
        tAffaire.getColumns().addAll(tAU.getMotifCol(),tAU.getDateCreaCol(),tAU.getHonorairesfCol(),tAU.getRestPaiementCol());
        tAudience.getColumns().addAll(tAuU.getDateCol(),tAuU.getVerdictCol(),tAuU.getVilleCol(),tAuU.getInstanceCol());
        tPiece.getColumns().addAll(tPU.getImgCol());
        
        //Filling Dossier table
        remplirTableView();
        
        //Hiding the forms
        formDossier.visibleProperty().set(false);
    }    
    
}
