/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.tools;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import testjdbc.data.DAO.AffaireDAO;
import testjdbc.data.model.AbstractModel;

/**
 *
 * @author youss
 */
public class TableAffaireUtil extends AbstractUtil{

    AffaireDAO aDAO = new AffaireDAO();
    @Override
    public ObservableList<AbstractModel> getListe() {
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        ArrayList<AbstractModel> data = aDAO.get();
        list.addAll(data);
        return list;
    }
    
    public ObservableList<AbstractModel> getListe(int dossier){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        ArrayList<AbstractModel> data = aDAO.get(dossier);
        list.addAll(data);
        return list;
    }
    
    public TableColumn<AbstractModel,String> getMotifCol(){
        TableColumn<AbstractModel,String> motifCol = new TableColumn<>("Motif");
        motifCol.setCellValueFactory(new PropertyValueFactory<>("motif"));
        return motifCol;
    }
    
    public TableColumn<AbstractModel,LocalDate> getDateCreaCol(){
        TableColumn<AbstractModel,LocalDate > motifCol = new TableColumn<>("Création");
        motifCol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        return motifCol;
    }
    
    public TableColumn<AbstractModel,Double> getHonorairesfCol(){
        TableColumn<AbstractModel,Double> motifCol = new TableColumn<>("Honoraires");
        motifCol.setCellValueFactory(new PropertyValueFactory<>("honoraires"));
        return motifCol;
    }
    
    public TableColumn<AbstractModel,Double> getRestPaiementCol(){
        TableColumn<AbstractModel,Double> motifCol = new TableColumn<>("Reste");
        motifCol.setCellValueFactory(new PropertyValueFactory<>("restPaiement"));
        return motifCol;
    }

}
