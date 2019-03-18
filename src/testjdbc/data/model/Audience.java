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
public class Audience extends AbstractModel{
    private int id,
            affaire;
    private LocalDate dateAudience;
    private String verdict,
            ville,
            instance;

    public Audience(int id, LocalDate dateAudience, String verdict, String ville, String instance, int affaire) {
        this.id = id;
        this.affaire = affaire;
        this.dateAudience = dateAudience;
        this.verdict = verdict;
        this.ville = ville;
        this.instance = instance;
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

    public LocalDate getDateAudience() {
        return dateAudience;
    }

    public void setDateAudience(LocalDate dateAudience) {
        this.dateAudience = dateAudience;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
    
    
}
