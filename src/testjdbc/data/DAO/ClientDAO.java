/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import testjdbc.data.model.AbstractModel;
import testjdbc.data.model.Client;

/**
 *
 * @author saado
 */
public class ClientDAO extends AbstractDAO {

    private HashMap<Integer,AbstractModel> clients;
    private int prevDossier = -1;

    public ClientDAO(){
        clients = new HashMap<>();
        this.tableName = "client";
    }
    
    @Override
    public int insert(AbstractModel o) {
        Client c = (Client) o;
        int res = -1;
        String sql = "insert into client (nom,prenom,adresse,tel,dossier) values (?,?,?,?,?)";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getTel());
            ps.setString(4, c.getAdresse());
            ps.setInt(5, c.getDossier());
            res = ps.executeUpdate();
            if (res != -1) {
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                c.setId(keys.getInt(1));
                clients.put(c.getId(),c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(AbstractModel o) {
        int res = -1;
        Client c = (Client) o;
        String sql = "update client set nom = ?,prenom = ?,tel = ?,adresse = ?,dossier = ? where id = ?";
        PreparedStatement ps = DAOUtil.getPStatement(sql);
        try {
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getTel());
            ps.setString(4, c.getAdresse());
            ps.setInt(5, c.getDossier());
            ps.setInt(6, c.getId());
            res = ps.executeUpdate();
            if (res != -1) {
                clients.replace(c.getId(), c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<AbstractModel> get() {
        ArrayList<AbstractModel> lc = new ArrayList<> ();
        lc.addAll( clients.values());
        Client c;
        if (prevDossier != -1) {
            lc.clear();
            String sql = "select * from client";
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    c = new Client(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6));
                    lc.add(c);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            prevDossier = -1;
        }
        return lc;
    }

    @Override
    public ArrayList<AbstractModel> get(int idDossier) {
        ArrayList<AbstractModel> lc = new ArrayList<>();
        lc.addAll(clients.values());
        Client c;
        if(prevDossier!=idDossier){
            lc.clear();
            String sql = "select * from client WHERE dossier = " + idDossier;
            try {
                ResultSet r = DAOUtil.getStatement().executeQuery(sql);
                while (r.next()) {
                    c = new Client(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6));
                    lc.add(c);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            prevDossier = idDossier;
        }
        return lc;
    }

}
