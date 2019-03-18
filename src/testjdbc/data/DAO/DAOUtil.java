/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import testjdbc.tools.FileUtil;

/**
 *
 * @author saado
 */
public class DAOUtil {
    
    
    static Connection c;
    static Statement s;
    
    public static Connection getConnection(){
        
        ArrayList<String> lName = new ArrayList<>();
        lName.add("driver");lName.add("url");lName.add("userPass");
        lName.add("dbName");lName.add("user");lName.add("pass");
        
        ArrayList<String> lData = FileUtil.lirePropreties(lName);
        
        try {
            Class.forName(lData.get(0));
            c = DriverManager.getConnection(lData.get(1)+ lData.get(3),lData.get(4),lData.get(5));
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public static Statement getStatement(){
        try {
            s = getConnection().createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s;
    }
    
    public static PreparedStatement getPStatement(String sql){
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ps;
    }
}
