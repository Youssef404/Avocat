/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.tools;

import java.sql.Date;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import testjdbc.data.DAO.ClientDAO;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Client;
import testjdbc.data.model.Dossier;

/**
 *
 * @author youss
 */
public class TableClientUtil extends AbstractUtil{
    
    private ClientDAO cDAO = new ClientDAO();
    
    @Override
    public ObservableList<AbstractModel> getListe(){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        ArrayList<AbstractModel> data = cDAO.get();
        list.addAll(data);
        return list;
    }
    
    public ObservableList<AbstractModel> getListe(int idDossier){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        ArrayList<AbstractModel> data = cDAO.get(idDossier);
        list.addAll(data);
        return list;
    }
    
    public TableColumn<AbstractModel, String> getNomCol(){
        TableColumn<AbstractModel, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return colNom;
    }
    
    public TableColumn<AbstractModel, String> getPrenomCol(){
        TableColumn<AbstractModel, String> colPrenom = new TableColumn<>("Prenom");
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        return colPrenom;
    }
    
    public TableColumn<AbstractModel, String> getAdresseCol(){
        TableColumn<AbstractModel, String> colAdresse = new TableColumn<>("Adresse");
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        return colAdresse;
    }
    
    public TableColumn<AbstractModel, String> getTelCol(){
        TableColumn<AbstractModel, String> colTel = new TableColumn<>("Telephone");
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        return colTel;
    }
}
