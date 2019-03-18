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
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Audience;
import testjdbc.data.model.Client;
import testjdbc.data.model.Piece;

/**
 *
 * @author youss
 */
public class PieceDAO extends AbstractDAO{
    private ArrayList<AbstractModel> pieces;
    private int prevAffaire = -1;

    public PieceDAO(){
        pieces = new ArrayList<>();
    }
    
    @Override
    public int insert(AbstractModel o) {
        Piece p = (Piece) o;
        int res = -1;
        String sql = "INSERT INTO Piece VALUES (?,?,?)";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setInt(1, Types.NULL);
            ps.setString(2, p.getCheminImg());
            ps.setInt(3, p.getAffaire());
            res = ps.executeUpdate();
            if (res != -1) {
                ResultSet keys = ps.getGeneratedKeys();
                p.setId(keys.getInt(1));
                pieces.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AudienceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;    }

    @Override
    public int update(AbstractModel o) {
        //Mise à jour piece
        int res = -1;
        Piece p = (Piece) o;
        String sql = "update piece set chemin_img = ?,affaire = ? where id = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, p.getCheminImg());
            ps.setInt(2, p.getAffaire());
            ps.setInt(3, p.getId());
            res = ps.executeUpdate();
            if (res != -1) {
                pieces.set(p.getId(), p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;    }

    @Override
    public ArrayList<AbstractModel> get() {
        //Methode non utilisée
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AbstractModel> get(int idAffaire) {
        //Selection de toutes les peices appartenant à une affaire
        ArrayList<AbstractModel> lc = pieces;
        Piece p;
        if(prevAffaire!=idAffaire){
            lc.clear();
            String sql = "select * from piece WHERE affaire = " + idAffaire;
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    p = new Piece(r.getInt(1), r.getString(2), r.getInt(3));
                    lc.add(p);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            prevAffaire = idAffaire;
        }
        return lc;    }
    
    
}
