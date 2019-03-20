/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.model;

import java.time.LocalDate;




public class Dossier extends AbstractModel{
    private int id;
    private LocalDate dateCreation;
    private String titre, demandeur, defendeur;

    public Dossier(int id, LocalDate dateCreation, String titre, String demandeur, String defendeur) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.titre = titre;
        this.demandeur = demandeur;
        this.defendeur = defendeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public void setDefendeur(String defendeur) {
        this.defendeur = defendeur;
    }

    public String getTitre() {
        return titre;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public String getDefendeur() {
        return defendeur;
    }
}
