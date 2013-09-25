/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mab.salhi
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private CompteFacade compteService;
    private Compte newCompte;
    private Compte compte;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    /*
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params;
        params = fc.getExternalContext().getRequestParameterMap();
        long cne = Long.parseLong(params.get("j_username"));
        System.out.println("la personne logue est : " + cne);
        if (cne != 0) {
        compte = compteService.findByCne(cne);
           }    
    }
    */
    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();

        // remove data from beans:
        for (String bean : context.getExternalContext().getSessionMap().keySet()) {
            context.getExternalContext().getSessionMap().remove(bean);
        }

        // destroy session:
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();

        // using faces-redirect to initiate a new request:
        return "/index.xhtml?faces-redirect=true";

    }

    public Compte getNewCompte() {
        return newCompte;
    }

    public void setNewCompte(Compte newCompte) {
        this.newCompte = newCompte;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    
    
}
