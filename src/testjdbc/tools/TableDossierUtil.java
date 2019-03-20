/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.tools;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import testjdbc.data.DAO.DossierDAO;
import testjdbc.data.model.AbstractModel;

/**
 *
 * @author youss
 * 
 */
public class TableDossierUtil extends AbstractUtil{
    
    @Override
    public ObservableList<AbstractModel> getListe(){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        DossierDAO dDAO = new DossierDAO();
        ArrayList<AbstractModel> data = dDAO.get();
        list.addAll(data);
        return list;
    }
    
    public TableColumn<AbstractModel,Integer> getNumCol(){
        TableColumn<AbstractModel,Integer>colNum = new TableColumn<>("Numéro du dossier");
        colNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNum.getStyleClass().add("bold");
        return colNum;
    }

    public TableColumn<AbstractModel,String> getDateCol(){
        TableColumn<AbstractModel,String>colDate = new TableColumn<>("Date de création");
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        return colDate;
    }

    public TableColumn<AbstractModel, String> getTitreCol() {
        TableColumn<AbstractModel, String> colTitre = new TableColumn<>("Titre");
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        return colTitre;
    }

    public TableColumn<AbstractModel, String> getDemandeurCol() {
        TableColumn<AbstractModel, String> colDemandeur = new TableColumn<>("Demandeur");
        colDemandeur.setCellValueFactory(new PropertyValueFactory<>("demandeur"));
        return colDemandeur;
    }

    public TableColumn<AbstractModel, String> getDefendeurCol() {
        TableColumn<AbstractModel, String> colDefendeur = new TableColumn<>("Defendeur");
        colDefendeur.setCellValueFactory(new PropertyValueFactory<>("defendeur"));
        return colDefendeur;
    }
}
