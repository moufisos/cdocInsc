/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Activation;
import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.helpers.SendMail;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@Named(value = "etudiantController")
@SessionScoped
public class EtudiantController implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private EtudiantFacade etudiantService;
    private Etudiant current;
    private Etudiant newEtudiant;
    private List<Etudiant> Etudiants;
    
    @Inject
    private CompteFacade compteService;
    private Compte newCompte;
    @Inject
    private ActivationFacade activationservice;
    private Activation activation;
    
    private SendMail sendMail = new SendMail();
    
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showDetails(Etudiant item) {
        return "view?faces-redirect=true";
    }

    public String showCreate() {
        newEtudiant = new Etudiant();
        newCompte = new Compte();
        activation = new Activation();
        return "etudiant/new?faces-redirect=true";
    }
    
    public String showEdit(Etudiant item) {
        current = item;
        return "edit?faces-redirect=true";
    }

    public String showList() {
        return "list?faces-redirect=true";
    }
    // ======================================
    // = Business Methods =
    // ======================================
    public List<Etudiant> getAll(){
        return etudiantService.findAll();
    }
    
    public String doCreate(){
        //creation du compte a partir des infos de l'etudiant
        newCompte.setCne(newEtudiant.getCne());
        newCompte.setEmail(newEtudiant.getEmail());
        newCompte.setActif(Boolean.FALSE);
        //Generation de la cle d'identification et envoie de mail d'activation
        final String key = UUID.randomUUID().toString();
        System.out.println("La cle generer est " + key);
        try {
            sendMail.SendEmail(newEtudiant.getEmail(), key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //Creation Du compte
        compteService.create(newCompte);
        System.out.println("Compte creer");
        //Definition de l'activation
        
        activation.setActivationKey(key);
        activation.setCompte(newCompte);
        activationservice.create(activation);
        //Creation de l'etudiant
        etudiantService.create(newEtudiant);
        return "waitValidation?faces-redirect=true";
    }
    
    // ======================================
    // = Constructors =
    // ======================================
    public EtudiantController() {
    }

    // ======================================
    // = Getters & setters =
    // ======================================
    public Etudiant getCurrent() {
        return current;
    }

    public void setCurrent(Etudiant current) {
        this.current = current;
    }

    public Etudiant getNewEtudiant() {
        return newEtudiant;
    }

    public void setNewEtudiant(Etudiant newEtudiant) {
        this.newEtudiant = newEtudiant;
    }

    public Compte getNewCompte() {
        return newCompte;
    }

    public void setNewCompte(Compte newCompte) {
        this.newCompte = newCompte;
    }
    
}
