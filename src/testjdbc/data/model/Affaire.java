/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc.data.model;

import java.time.LocalDate;

/**
 *
 * @author saado
 */
public class Affaire extends AbstractModel{
    private int id,
            dossier;
    private String motif;
    private double honoraires,
            restPaiement;
    private LocalDate dateCreation;

    public Affaire(int id, int dossier, String motif, double honoraires, double restPaiement, LocalDate dateCreation) {
        this.id = id;
        this.dossier = dossier;
        this.motif = motif;
        this.honoraires = honoraires;
        this.restPaiement = restPaiement;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDossier() {
        return dossier;
    }

    public void setDossier(int dossier) {
        this.dossier = dossier;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public double getHonoraires() {
        return honoraires;
    }

    public void setHonoraires(double honoraires) {
        this.honoraires = honoraires;
    }

    public double getRestPaiement() {
        return restPaiement;
    }

    public void setRestPaiement(double restPaiement) {
        this.restPaiement = restPaiement;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
}
