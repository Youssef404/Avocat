/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.model;

/**
 *
 * @author saado
 */
public class Piece extends AbstractModel{
    private int id,
            affaire;
    private String cheminImg;

    public Piece(int id, String cheminImg, int affaire) {
        this.id = id;
        this.affaire = affaire;
        this.cheminImg = cheminImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAffaire() {
        return affaire;
    }

    public void setAffaire(int affaire) {
        this.affaire = affaire;
    }

    public String getCheminImg() {
        return cheminImg;
    }

    public void setCheminImg(String cheminImg) {
        this.cheminImg = cheminImg;
    }
    
    
}
