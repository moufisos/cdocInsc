/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Activation;
import com.sos.fso.cdoc.insc.entities.Branche;
import com.sos.fso.cdoc.insc.entities.ChoixSujet;
import com.sos.fso.cdoc.insc.entities.Compte;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Qualification;
import com.sos.fso.cdoc.insc.entities.Sujet;
import com.sos.fso.cdoc.insc.helpers.Hash;
import com.sos.fso.cdoc.insc.services.ActivationFacade;
import com.sos.fso.cdoc.insc.services.BrancheFacade;
import com.sos.fso.cdoc.insc.services.CompteFacade;
import com.sos.fso.cdoc.insc.services.EtudiantFacade;
import com.sos.fso.cdoc.insc.services.MailerBean;
import com.sos.fso.cdoc.insc.services.QualificationFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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
    private Compte compte;
    @Inject
    private ActivationFacade activationservice;
    private Activation activation;

    @Inject
    protected MailerBean mailerBean;
    protected String email;
    protected String status;
    private static final Logger logger = Logger.getLogger(EtudiantController.class.getName());
    private Future<String> mailStatus;

    @Inject
    private QualificationFacade qualificationService;
    private Qualification newQualification;
    
    @Inject
    private BrancheFacade brancheService;
    private List<Branche> branches;
    private Branche branche = new Branche();
    
        
    private ChoixSujet choix = new ChoixSujet();
    
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showDetails(Etudiant item) {
        if (!item.getChoixSujetList().isEmpty()) {
            choix = item.getChoixSujetList().get(1);
            branche = choix.getSujet().getBranche();
            System.out.println("la branche : " + branche.getIntitule());
            System.out.println("les choix " + current.getChoixSujetList().get(1).getSujet().getIntitule());
        System.out.println("les choix " + current.getChoixSujetList().get(2).getSujet().getIntitule());
        System.out.println("les choix " + current.getChoixSujetList().get(3).getSujet().getIntitule());
        
        }
        return "view?faces-redirect=true";
    }

    public String showCreate() {
        newEtudiant = new Etudiant();
        newCompte = new Compte();
        activation = new Activation();
        return "etudiant/new?faces-redirect=true";
    }

    public String showEdit() {
        return "edit?faces-redirect=true";
    }

    public String showList() {
        return "list?faces-redirect=true";
    }

    public String showRestricted() {
        return "/secured/logedEtudiant?faces-redirect=true";
    }

    public String showLoggedDetails() {

        long cne = compte.getCne();
        System.out.println("le cne est : " + cne);
        current = etudiantService.findByCne(cne);
        System.out.println("La personne est : " + current.getNom() + "--> " + current.getCin());

        if (!current.getChoixSujetList().isEmpty()) {
            choix = current.getChoixSujetList().get(1);
            branche = choix.getSujet().getBranche();
            System.out.println("la branche : " + branche.getIntitule());
        }else {
            branche = null;
        }
        
        return "/etudiant/view?faces-redirect=true";
    }

    public String showAddQualification() {
        newQualification = new Qualification();
        return "addQualification?faces-redirect=true";
    }
    
    public String showChoixSujet(){
        return "choixSujet?faces-redirect=true";
    }
    
    public String showSelectSujet(){
        if(branche == null){
            branche = new Branche();
            return "addCandidature?faces-redirect=true";
        }
        return "choixSujet?faces-redirect=true";
    }
    
    // ======================================
    // = Business Methods =
    // ======================================
    /*@PostConstruct
     public void init(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params;
     params = fc.getExternalContext().getRequestParameterMap();
     String cne = params.get("j_username");
     if (cne != null) {
     compte = compteService.findByCne(cne);
     }    
     }*/

    public void SendEmail(String email, String key) {
        String response = "response?faces-redirect=true";

        try {
            mailStatus = mailerBean.sendVerificationMail(email, key);
            this.setStatus("Envoie en cours ...(veuillez rafraishir !!!)");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        //return response;
    }

    public List<Branche> getBranches() {
        return brancheService.findAll();
    }
    
    public List<Etudiant> getAll() {
        return etudiantService.findAll();
    }

    public String doCreate() {
        //creation du compte a partir des infos de l'etudiant
        newCompte.setCne(newEtudiant.getCne());
        newCompte.setEmail(newEtudiant.getEmail());
        newCompte.setActif(Boolean.FALSE);
        newCompte.setGroupeName("candidat");
        //Generation de la cle d'identification et envoie de mail d'activation
        final String key = UUID.randomUUID().toString();
        System.out.println("La cle generer est " + key);
        SendEmail(newEtudiant.getEmail(), key);

        //Creation Du compte
        String password = newCompte.getPassword();
        String hashedPassword = Hash.hash(password);
        System.out.println("the hashed password is " + hashedPassword);

        newCompte.setPassword(hashedPassword);
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

    public String doEdit() {
        if (current != null) {
            try {
                etudiantService.edit(current);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return "view?faces-redirect=true";
    }
    
    public String doChoisirBranche(){
        
        return "view?faces-redirect=true";
    }

    public String doAddQualification() {
        logger.log(Level.INFO, "Debut de la procedure d'ajout de diplome !!");
        if (current != null) {
            newQualification.setEtudiant(current);

            try {
                qualificationService.create(newQualification);
                current.getQualificationList().add(newQualification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.log(Level.SEVERE, "Erreur de donnee the current entity is null !!");
        }
        return "view?faces-redirect=true";
    }

    public String doAddSujetToEtudiantChoiceList(Sujet item){
        
        int nbChoix = current.getChoixSujetList().size();
        System.out.println("nombre de choix : " + nbChoix);
        choix.setEtudiant(current);
        System.out.println(" le choix est " + item.getIntitule());
        choix.setSujet(item);
        current.getChoixSujetList().add(choix);        
        etudiantService.edit(current);
    return "view?faces-redirect=true";
    }
    
    // ======================================
    // = Constructors et Helpers=
    // ======================================
    public EtudiantController() {
    }

    private void addMessage(String key, FacesMessage.Severity severity, String message, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        Flash flash = context.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesMessage msg = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(key, msg);
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

    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Compte getCompte() {

        if (compte == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                long cne = Long.parseLong(principal.getName());
                compte = compteService.findByCne(cne);
            }
        }
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Qualification getNewQualification() {
        return newQualification;
    }

    public void setNewQualification(Qualification newQualification) {
        this.newQualification = newQualification;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

     
    
}
