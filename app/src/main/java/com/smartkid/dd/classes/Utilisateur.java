package com.smartkid.dd.classes;

import com.smartkid.dd.classes.base.BaseClasse;
import com.smartkid.dd.classes.base.Constant;
import com.smartkid.dd.classes.base.Util;
import com.smartkid.dd.exception.AppException;

public class Utilisateur extends BaseClasse {
    private String email, mdp;

    public Utilisateur(){}

    public Utilisateur(String email, String mdp, String mode) throws Exception {
        this.setMode(mode);
        this.setEmail(email);
        this.setMdp(mdp);
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
}
