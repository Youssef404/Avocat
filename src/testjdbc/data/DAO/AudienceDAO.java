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

/**
 *
 * @author youss
 */
public class AudienceDAO extends AbstractDAO {

    private ArrayList<AbstractModel> audience;
    private int prevAffaire = -1;

    public AudienceDAO() {
        audience = new ArrayList<>();
        tableName = "affaire";
    }

    @Override
    public int insert(AbstractModel o) {
        Audience a = (Audience) o;
        int res = -1;
        String sql = "INSERT INTO Audience VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setInt(1, Types.NULL);
            ps.setString(2, a.getDateAudience().toString());
            ps.setString(3, a.getVerdict());
            ps.setString(4, a.getVille());
            ps.setString(5, a.getInstance());
            ps.setInt(6, a.getAffaire());
            res = ps.executeUpdate();
            if (res != -1) {
                ResultSet keys = ps.getGeneratedKeys();
                a.setId(keys.getInt(1));
                audience.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AudienceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public int update(AbstractModel o) {
        int res = -1;
        Audience a = (Audience) o;
        String sql = "update audience set date_audience = ?,verdict = ?,ville = ?,instance = ?,affaire = ? where id = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1,a.getDateAudience().toString());
            ps.setString(2, a.getVerdict());
            ps.setString(3, a.getVille());
            ps.setString(4, a.getInstance());
            ps.setInt(5, a.getAffaire());
            ps.setInt(6, a.getId());
            res = ps.executeUpdate();
            if (res != -1) {
                audience.set(a.getId(), a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<AbstractModel> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AbstractModel> get(int idAffaire) {
        ArrayList<AbstractModel> lc = audience;
        Audience a;
        if (prevAffaire != idAffaire) {
            lc.clear();
            String sql = "select * from audience WHERE affaire = " + idAffaire;
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    a = new Audience(r.getInt(1), r.getDate(2).toLocalDate(), r.getString(3), r.getString(4), r.getString(5), r.getInt(6));
                    lc.add(a);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            prevAffaire = idAffaire;
        }
        return lc;
    }

}
