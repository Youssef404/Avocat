/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import animatefx.animation.Flash;
import animatefx.animation.Jello;
import animatefx.animation.Swing;
import animatefx.animation.Tada;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Dossier;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class AvocatV2Controller implements Initializable {

    @FXML
    private HBox iconeHome;
    @FXML
    private ImageView userImage;
    @FXML
    private Text userName;
    @FXML
    private FontAwesomeIconView userMenuDrop;
    @FXML
    private HBox tabAccueil;
    @FXML
    private HBox tabDossiers;
    @FXML
    private HBox tabClients;
    @FXML
    private HBox tabAffaires;
    @FXML
    private HBox tabAudience;
    @FXML
    private HBox tabPieces;
    @FXML
    private AnchorPane contentPane;

    @FXML
    private TableView<AbstractModel> tDossier;

    /**
     * Initializes the controller class.
     */
    Pane mainPane, dossierPane, clientPane, affairePane, audiencePane;
    FXMLLoader mainLoader, dossierLoader, clientLoader, affaireLoader, audienceLoader;
    DossierPageController dossierController;
    ClientPageController clientController;
    MainPageController mainController;
    AffairePageController affaireController;
    AudiencePageController audienceController;

    public void setAnchors(Pane pane){
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            //Initialisation du pane de l'accueil
            mainLoader = new FXMLLoader(getClass().getResource("../fxmls/mainPage.fxml"));
            mainPane = mainLoader.load();
            mainController = mainLoader.getController();

            //Initialisation du pane de la liste des clients
            clientLoader = new FXMLLoader(getClass().getResource("../fxmls/clientPage.fxml"));
            clientPane = clientLoader.load();
            clientController = clientLoader.getController();

            //Initialisation du pane de la liste de dossiers
            dossierLoader = new FXMLLoader(getClass().getResource("../fxmls/dossierPage.fxml"));
            dossierPane = dossierLoader.load(); //FXMLLoader.load(getClass().getResource("../fxmls/dossierPage.fxml"));
            dossierController = dossierLoader.getController();

            //Initialisation du pane de la liste des affaires
            affaireLoader = new FXMLLoader(getClass().getResource("../fxmls/affairePage.fxml"));
            affairePane = affaireLoader.load();
            affaireController = affaireLoader.getController();

            //Initialisation du pane de la liste de l'audince
            audienceLoader = new FXMLLoader(getClass().getResource("../fxmls/audiencePage.fxml"));
            audiencePane = audienceLoader.load();
            audienceController = audienceLoader.getController();

            //Attacher les ancres des panes à l'anchorpane mère
            setAnchors(clientPane);
            setAnchors(dossierPane);
            setAnchors(mainPane);
            setAnchors(affairePane);
            setAnchors(audiencePane);

            //Double click = chargement des tables client et affaire, un click = remplissage du formulaire
            dossierController.getTable().setOnMouseClicked((me)-> {
                if(me.getClickCount()==2){
                    clientController.setIdDossier(dossierController.getSelectedId());
                    affaireController.setIdDossier(dossierController.getSelectedId());
                    new Flash(tabClients).play();
                    new Flash(tabAffaires).play();
                    pressClient();
                }else{
                    if(me.getClickCount() == 1){
                        dossierController.getDcCreation().setValue(((Dossier)dossierController.getTable().getSelectionModel().getSelectedItem()).getDateCreation());
                    }
                }
            });

            contentPane.getChildren().add(mainPane);

        } catch (IOException ex) {
            Logger.getLogger(AvocatV2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Methode pour charger l'FXML de dossiers
    public void pressDossier() {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(dossierPane);
    }

    //Methode pour charger l'FXML de l'accueil
    public void pressAccueil() {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(mainPane);
    }

    //Methode pour charger l'FXML des clients
    public void pressClient(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(clientPane);
    }

    //Methode pour charger l'FXML des affaires
    public void pressAffaire(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(affairePane);
    }

    //Methode pour charger l'FXML de l'audience
    public void pressAudience(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(audiencePane);
    }
}
