/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import dbtable.Connexion;
import dbtable.DBTable;
import model.Utilisateur;
import java.sql.Connection;

/**
 *
 * @author Fanjava-P14A-V46
 */
public class Token extends DBTable{
    private int id;
    private int idUsers;
    private String valeur;
    private String expiration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    
    public void getToken(Utilisateur u,int minute) throws Exception{
        Connection c=(new Connexion()).getConnection();
        String concat=u.getNom()+u.getPwd();
        String min="0:"+minute+":0";
        String req="select md5(concat('"+concat+"',now())) as valeur,now()+'"+min+"' as expiration";
        DBTable[] token=(new ValToken()).find(req, c);
        System.out.println(req);
        ValToken val=(ValToken)(token[0]);
        Token t=new Token();
        t.setValeur(val.getValeur());
        t.setExpiration(val.getExpiration());
        t.setIdUsers(u.getId());
        t.insert(c);
        c.close();
    }
    
}
