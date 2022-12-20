package com.example.TPSpring;

import dbtable.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import jdk.nashorn.internal.runtime.JSONFunctions;
import model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import token.*;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RestController  
public class TpSpringApplication {

    public static void main(String[] args) {
	SpringApplication.run(TpSpringApplication.class, args);
    }
    
@CrossOrigin
@GetMapping("/avion")
public Object getAvion(@RequestParam(value = "id") int id) throws Exception {
            Connection c=new Connexion().getConnection();
             DBTable[] tab=new Avion().find("select * from avion where id="+id+"",c);
    c.close();
    try{
    return new Data(tab);
    }
    catch(Exception e)
    {
        return new Erreur("500","Erreur SQL");
    }
}

@CrossOrigin
@GetMapping("/fiche")
public Object getFiche(@RequestParam(value = "id") int id) throws Exception {
    Connection c=new Connexion().getConnection();
    DBTable[] tab=new Avion().find("select * from avion join entretien on avion.id=entretien.idavion where avion.id="+id+"",c);
             
    c.close();
    try{
    return new Data(tab);
    }
    catch(Exception e)
    {
        return new Erreur("500","Erreur SQL");
    }
}

@CrossOrigin
@PostMapping("/Utilisateur")
    public Object connection (@RequestParam(value = "email") String email,@RequestParam(value = "Pwd") String Pwd) throws Exception {
    Connection con=new Connexion().getConnection();
    Utilisateur k = new Utilisateur();
    k.setEmail(email); 
       
    k=(Utilisateur)k.find(k, con)[0];
    if(k.getPwd().equals(Pwd)){
        con.close();
        return new Data("OK");
    }
    else{
       con.close();
        return new Erreur("999","Erreur d'authentification");
       }
    }
@CrossOrigin
@GetMapping("/avions")
public Object getAvions() throws Exception {
    Connection c=new Connexion().getConnection();
    DBTable[] tab=new Avion().find("select * from avion",c);
    c.close();
    
    try{
        return new Data(tab);
    }
    catch(Exception e)
    {
         return new Erreur("500","Erreur SQL");
    }
}
@CrossOrigin
@PostMapping("/avion")
public Object createAvion(@RequestParam(value = "nom") String nom,@RequestParam(value = "photo") String photo)
{
    Avion v = new Avion();
    v.setPhoto(photo);
    v.setNom(nom);

    try{
        Connection c=new Connexion().getConnection();
        v.insert(c);
        c.close();
        return new Data("Insertion reussie");
    }
    catch(Exception e)
    {
         return new Erreur("500","Erreur SQL");
    }
}
@CrossOrigin
@PutMapping("/avion")
public Object updateAvion(@RequestParam(value = "id") int id,
        @RequestParam(value = "photo") String photo,
        @RequestParam(value = "kilometrage") double kilometrage) throws Exception
{
    Avion v = new Avion();
    v.setId(id);
    v.setPhoto(photo);
    v.setKilometrage(kilometrage);
    try
    {
        Connection c=new Connexion().getConnection();
        v.update(c);
        c.close();
        return new Data("Modification reussie");
    }
    catch (Exception e) 
    {
         return new Erreur("500","Erreur SQL");
    }
}
@CrossOrigin
@PostMapping("/entretien")
public Object createEntretien(@RequestParam(value = "idAvion") int idAvion,@RequestParam(value = "date_entretien") String date_entretien,@RequestParam(value = "intitule") String intitule,@RequestParam(value = "montant") double montant)
{
    Entretien v = new Entretien();
    v.setDate_entretien(date_entretien);
    v.setIdAvion(idAvion);
    v.setIntitule(intitule);
    v.setMontant(montant);
    try{
        Connection c=new Connexion().getConnection();
        v.insert(c);
        c.close();
        return new Data("Insertion reussie");
    }
    catch(Exception e)
    {
         return new Erreur("500","Erreur SQL");
    }
}

@CrossOrigin
@PostMapping("/assurance")
public Object createAssurance(@RequestParam(value = "idAvion") int idAvion,@RequestParam(value = "date_paiment") String date_paiment,@RequestParam(value = "date_expiration") String date_expiration,@RequestParam(value = "montant") double montant)
{
    Assurance v = new Assurance();
    v.setDate_expiration(date_expiration);
    v.setDate_paiment(date_paiment);
    v.setIdAvion(idAvion);
    v.setMontant(montant);
    Avion a=new Avion();
    a.setId(idAvion);
    a.setDate_expiration_Assurance(date_expiration);
    try{
        Connection c=new Connexion().getConnection();
        v.insert(c);
        a.update(c);
        c.close();
        return new Data("Insertion reussie");
    }
    catch(Exception e)
    {
         return new Erreur("500","Erreur SQL");
    }
}

@CrossOrigin
@PostMapping("/assuranceexpirer")
public Object assuranceexpirer(@RequestParam(value = "mois") int mois)
{
    Date d=new Date();
    d.setMonth(d.getMonth()+mois);
    String date=Integer.toString(d.getYear()+1900)+"-"+Integer.toString(d.getMonth()+1)+"-"+Integer.toString(d.getDay());
    
    try{
        Connection c=new Connexion().getConnection();
        DBTable[] avion=(new Avion()).find("select * from avion where date_expiration_Assurance<'"+date+"' order by date_expiration_Assurance desc", c);    
        c.close();
        return new Data(avion);
    }
    catch(Exception e)
    {
         return new Erreur("500","Erreur SQL");
    }
}

}
