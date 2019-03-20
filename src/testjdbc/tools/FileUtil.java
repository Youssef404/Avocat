/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saado
 */
public class FileUtil {
    public static String readTextFile(String chemin){
        Path ch = Paths.get(chemin);
        byte[] b = null;
        String str = null;
        try {
            b = Files.readAllBytes(ch);
            str = new String(b, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return str;
    }
    
   public static ArrayList<String> lirePropreties(ArrayList<String> lp){
       ArrayList<String> lProp = new ArrayList<>();
       Properties p = new Properties();
       ClassLoader cl = Thread.currentThread().getContextClassLoader();
       InputStream fp = cl.getResourceAsStream("testjdbc/config/params.properties");
        try {
            p.load(fp);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < lp.size(); i++) {
            lProp.add(p.getProperty(lp.get(i)));
        }

       return lProp;
   }
}
