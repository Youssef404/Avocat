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

    public Dossier(int id, LocalDate dateCreation) {
        this.id = id;
        this.dateCreation = dateCreation;
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
    
    
}
