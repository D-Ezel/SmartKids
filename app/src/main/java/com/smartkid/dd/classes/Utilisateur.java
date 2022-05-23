package com.smartkid.dd.classes;

import com.smartkid.dd.classes.base.BaseClasse;
import com.smartkid.dd.classes.base.Constant;
import com.smartkid.dd.classes.base.Util;
import com.smartkid.dd.exception.AppException;

public class Utilisateur extends BaseClasse {
    private String email, mdp, pseudo, reMdp;

    public Utilisateur(){}

    public Utilisateur(String email, String mdp, String mode) throws Exception {
        this.setMode(mode);
        this.setEmail(email);
        this.setMdp(mdp);
    }

    public Utilisateur(String pseudo, String email, String mdp, String reMdp, String mode) throws Exception {
        this.setMode(mode);
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setMdp(mdp);
        this.setReMdp(reMdp);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(this.getMode() == Constant.MODE_MODIF){
            if(email == null || email.trim().isEmpty()){
                throw new AppException("Email vide.", 1);
            }
            if(!Util.isValidEmail(email)){
                throw new AppException("Email non valide.", 1);
            }
        }
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) throws Exception {
        if(this.getMode() == Constant.MODE_MODIF){
            if(mdp == null || mdp.trim().isEmpty()){
                throw new AppException("Mot de passe vide.", 2);
            }
        }
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) throws Exception {
        if(this.getMode() == Constant.MODE_MODIF){
            if(pseudo == null || pseudo.trim().isEmpty()){
                throw new AppException("Pseudo vide.", 3);
            }
        }
        this.pseudo = pseudo;
    }

    public String getReMdp() {
        return reMdp;
    }

    public void setReMdp(String reMdp) throws Exception {
        if(this.getMode() == Constant.MODE_MODIF){
            if(reMdp == null || reMdp.trim().isEmpty()){
                throw new AppException("Retaper votre mot de passe.", 4);
            }
            if(!reMdp.equals(this.mdp)){
                throw new AppException("Confirmation non valid.", 4);
            }
        }
        this.reMdp = reMdp;
    }
}
