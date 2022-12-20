/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbtable.DBTable;

/**
 *
 * @author P14A_122_Njato
 */
public class Avion extends DBTable{
    private int id;
    private String photo;
    private String nom;
    private double kilometrage;
    private String date_expiration_Assurance;

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_expiration_Assurance() {
        return date_expiration_Assurance;
    }

    public void setDate_expiration_Assurance(String date_expiration_Assurance) {
        this.date_expiration_Assurance = date_expiration_Assurance;
    }
    
}
