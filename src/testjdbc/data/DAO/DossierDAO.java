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

import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Dossier;

/**
 * @author saado
 */
public class DossierDAO extends AbstractDAO {
    private ArrayList<AbstractModel> dossiers;

    public DossierDAO() {
        dossiers = new ArrayList<>();
        this.tableName = "Dossier";
    }

    @Override
    public int insert(AbstractModel o) {
        Dossier d = (Dossier) o;
        int res = -1;
        String sql = "insert into Dossier (date_creation) values (?, ?, ?, ?)";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, d.getDateCreation().toString());
            ps.setString(2, d.getTitre());
            ps.setString(3, d.getDemandeur());
            ps.setString(4, d.getDefendeur());
            res = ps.executeUpdate();
            if (res != -1) {
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                d.setId(res = keys.getInt(1));
                dossiers.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(AbstractModel o) {
        Dossier d = (Dossier) o;
        int res = -1;
        String sql = "update Dossier set date_creation = ?, titre = ?, demandeur = ?, defendeur = ? where id = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, d.getDateCreation().toString());
            ps.setString(2, d.getTitre());
            ps.setString(3, d.getDemandeur());
            ps.setString(4, d.getDefendeur());
            ps.setInt(5, d.getId());

            res = ps.executeUpdate();
            if (res != -1) {
                for (int i = 0; i < dossiers.size(); i++) {
                    if (dossiers.get(i).getId() == o.getId()) {
                        dossiers.set(i, o);
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }


    public ArrayList<AbstractModel> get() {
        Dossier d;
        if (dossiers.isEmpty()) {
            String sql = "select * from Dossier";
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    d = new Dossier(r.getInt(1), r.getDate(2).toLocalDate(), r.getString(3), r.getString(4), r.getString(5));
                    System.out.println(r.getInt(1));
                    dossiers.add(d);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dossiers;
    }

    @Override
    public ArrayList<AbstractModel> get(int i) {
        Dossier d;
        if (dossiers.isEmpty()) {
            String sql = "select * from Dossier WHERE ID = " + i;
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    d = new Dossier(r.getInt(1), r.getDate(2).toLocalDate(), r.getString(3), r.getString(4), r.getString(5));
                    dossiers.add(d);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dossiers;
    }

    public ArrayList<Integer> getIds() {
        String sql = "select id from Dossier";
        ArrayList<Integer> list = new ArrayList<>();

        try {
            ResultSet r = DAOUtil.getStatement().executeQuery(sql);
            while (r.next()) {
                list.add(r.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

