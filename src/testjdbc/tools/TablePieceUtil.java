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
import testjdbc.data.DAO.PieceDAO;
import testjdbc.data.model.AbstractModel;

/**
 *
 * @author youss
 */
public class TablePieceUtil extends AbstractUtil{

    @Override
    public ObservableList<AbstractModel> getListe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<AbstractModel> getListe(int id){
        ObservableList<AbstractModel> list = FXCollections.<AbstractModel>observableArrayList();
        PieceDAO pd = new PieceDAO();
        list.addAll(pd.get(id));
        return list;
    }
    
    public TableColumn<AbstractModel, String> getIdCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Numero de pièce");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
    
    public TableColumn<AbstractModel, String> getImgCol(){
        TableColumn<AbstractModel, String> idCol = new TableColumn<>("Numero de pièce");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }
}
