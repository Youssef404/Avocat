/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import testjdbc.data.model.AbstractModel;

/**
 *
 * @author youss
 */
public abstract class AbstractDAO {
    protected String tableName;
    protected String[] columns;

    //Methode pour inserer une ligne dans une table
    public abstract int insert(AbstractModel o);

    //Methode pour supprimer une ligne d'une table
    public int delete(int id){
        int res = -1;
        String sql = "DELETE FROM "+ tableName +" WHERE ID = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try{
            ps.setInt(1, id);
            res = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AffaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public abstract int update(AbstractModel o);

    //Methode pour obtenir tous les elements d'une table
    public abstract ArrayList<AbstractModel> get();

    //Methode pour obtenir tous les elements qui ont une clé etrangere particulière
    public abstract ArrayList<AbstractModel> get(int i);
}
