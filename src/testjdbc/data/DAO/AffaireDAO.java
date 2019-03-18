/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Affaire;

/**
 *
 * @author youss
 */
public class AffaireDAO extends AbstractDAO{

    public AffaireDAO(){
        this.tableName = "Affaire";
    }
    
    @Override
    public int insert(AbstractModel o) {
        Affaire affaire = (Affaire) o;
        int res = -1;
        String sql = "INSERT INTO affaire VALUES(NULL, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, affaire.getMotif());
            ps.setString(2,affaire.getDateCreation().toString());
            ps.setDouble(3, affaire.getHonoraires());
            ps.setDouble(4,affaire.getRestPaiement());
            ps.setInt(5, affaire.getDossier());
            res = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AffaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    

    @Override
    public int update(AbstractModel o) {
        int res = -1;
        Affaire a = (Affaire)o;
        String sql = "UPDATE Affaire SET motif = ?, date_creation = ?, honoraires = ?, rest_paiement = ?, dossier = ? WHERE id = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try{
            ps.setString(1, a.getMotif());
            ps.setString(2, a.getDateCreation().toString());
            ps.setDouble(3, a.getHonoraires());
            ps.setDouble(4, a.getRestPaiement());
            ps.setInt(5, a.getDossier());
            ps.setInt(6, a.getId());
            res = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AffaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public ArrayList<AbstractModel> get() {
        ArrayList<AbstractModel> lc = new ArrayList<>();
           Affaire d;
        if (lc.isEmpty()) {
            String sql = "select * from Affaire";
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    d = new Affaire(r.getInt(1),r.getInt(6),r.getString(2),r.getDouble(4),r.getDouble(5),LocalDate.parse(r.getString(3)));
                    System.out.println(r.getInt(1));
                    lc.add(d);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return lc;
    }

    @Override
    public ArrayList<AbstractModel> get(int i) {
        ArrayList<AbstractModel> lc = new ArrayList<>();
           Affaire d;
        if (lc.isEmpty()) {
            String sql = "select * from Affaire WHERE Dossier ="+i;
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    d = new Affaire(r.getInt(1),r.getInt(6),r.getString(2),r.getDouble(4),r.getDouble(5),LocalDate.parse(r.getString(3)));
                    System.out.println(r.getInt(1));
                    lc.add(d);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return lc;
    }
    
}
