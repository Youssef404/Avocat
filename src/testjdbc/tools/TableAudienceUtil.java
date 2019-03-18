/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import testjdbc.data.DAO.AudienceDAO;
import testjdbc.data.model.AbstractModel;

/**
 *
 * @author youss
 */
public class TableAudienceUtil extends AbstractUtil{

    @Override
    public ObservableList<AbstractModel> getListe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<AbstractModel> getListe(int id){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        AudienceDAO ad = new AudienceDAO();
        list.addAll(ad.get(id));
        return list;
    }
    
    public TableColumn<AbstractModel, String> getIdCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Numero d'audience");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
    
    public TableColumn<AbstractModel, String> getDateCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Date");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
    
    public TableColumn<AbstractModel, String> getVerdictCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Verdict");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
    
    public TableColumn<AbstractModel, String> getVilleCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Ville");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
    
    public TableColumn<AbstractModel, String> getInstanceCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Instance");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
}
