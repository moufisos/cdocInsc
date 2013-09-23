/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_qualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualification.findAll", query = "SELECT q FROM Qualification q"),
    @NamedQuery(name = "Qualification.findByIdQualification", query = "SELECT q FROM Qualification q WHERE q.idQualification = :idQualification"),
    @NamedQuery(name = "Qualification.findByOptimisticLock", query = "SELECT q FROM Qualification q WHERE q.optimisticLock = :optimisticLock"),
    @NamedQuery(name = "Qualification.findByDateObtention", query = "SELECT q FROM Qualification q WHERE q.dateObtention = :dateObtention"),
    @NamedQuery(name = "Qualification.findByDatePremiereInscription", query = "SELECT q FROM Qualification q WHERE q.datePremiereInscription = :datePremiereInscription"),
    @NamedQuery(name = "Qualification.findByDiplome", query = "SELECT q FROM Qualification q WHERE q.diplome = :diplome"),
    @NamedQuery(name = "Qualification.findByMension", query = "SELECT q FROM Qualification q WHERE q.mension = :mension"),
    @NamedQuery(name = "Qualification.findByClassement", query = "SELECT q FROM Qualification q WHERE q.classement = :classement"),
    @NamedQuery(name = "Qualification.findByPartieDelivrante", query = "SELECT q FROM Qualification q WHERE q.partieDelivrante = :partieDelivrante"),
    @NamedQuery(name = "Qualification.findByNote1", query = "SELECT q FROM Qualification q WHERE q.note1 = :note1"),
    @NamedQuery(name = "Qualification.findByNote2", query = "SELECT q FROM Qualification q WHERE q.note2 = :note2"),
    @NamedQuery(name = "Qualification.findByNote3", query = "SELECT q FROM Qualification q WHERE q.note3 = :note3"),
    @NamedQuery(name = "Qualification.findByNote4", query = "SELECT q FROM Qualification q WHERE q.note4 = :note4"),
    @NamedQuery(name = "Qualification.findByNote5", query = "SELECT q FROM Qualification q WHERE q.note5 = :note5"),
    @NamedQuery(name = "Qualification.findByNote6", query = "SELECT q FROM Qualification q WHERE q.note6 = :note6")})
public class Qualification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_qualification")
    private Integer idQualification;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_obtention")
    @Temporal(TemporalType.DATE)
    private Date dateObtention;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_premiere_inscription")
    @Temporal(TemporalType.DATE)
    private Date datePremiereInscription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "diplome")
    private String diplome;
    @Size(max = 45)
    @Column(name = "mension")
    private String mension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "classement")
    private int classement;
    @Size(max = 255)
    @Column(name = "partie_delivrante")
    private String partieDelivrante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "note1")
    private Double note1;
    @Column(name = "note2")
    private Double note2;
    @Column(name = "note3")
    private Double note3;
    @Column(name = "note4")
    private Double note4;
    @Column(name = "note5")
    private Double note5;
    @Column(name = "note6")
    private Double note6;
    @JoinColumn(name = "etudiant", referencedColumnName = "id_etudiant")
    @ManyToOne
    private Etudiant etudiant;

    public Qualification() {
    }

    public Qualification(Integer idQualification) {
        this.idQualification = idQualification;
    }

    public Qualification(Integer idQualification, Date dateObtention, Date datePremiereInscription, String diplome, int classement) {
        this.idQualification = idQualification;
        this.dateObtention = dateObtention;
        this.datePremiereInscription = datePremiereInscription;
        this.diplome = diplome;
        this.classement = classement;
    }

    public Integer getIdQualification() {
        return idQualification;
    }

    public void setIdQualification(Integer idQualification) {
        this.idQualification = idQualification;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Date getDatePremiereInscription() {
        return datePremiereInscription;
    }

    public void setDatePremiereInscription(Date datePremiereInscription) {
        this.datePremiereInscription = datePremiereInscription;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getMension() {
        return mension;
    }

    public void setMension(String mension) {
        this.mension = mension;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public String getPartieDelivrante() {
        return partieDelivrante;
    }

    public void setPartieDelivrante(String partieDelivrante) {
        this.partieDelivrante = partieDelivrante;
    }

    public Double getNote1() {
        return note1;
    }

    public void setNote1(Double note1) {
        this.note1 = note1;
    }

    public Double getNote2() {
        return note2;
    }

    public void setNote2(Double note2) {
        this.note2 = note2;
    }

    public Double getNote3() {
        return note3;
    }

    public void setNote3(Double note3) {
        this.note3 = note3;
    }

    public Double getNote4() {
        return note4;
    }

    public void setNote4(Double note4) {
        this.note4 = note4;
    }

    public Double getNote5() {
        return note5;
    }

    public void setNote5(Double note5) {
        this.note5 = note5;
    }

    public Double getNote6() {
        return note6;
    }

    public void setNote6(Double note6) {
        this.note6 = note6;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQualification != null ? idQualification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualification)) {
            return false;
        }
        Qualification other = (Qualification) object;
        if ((this.idQualification == null && other.idQualification != null) || (this.idQualification != null && !this.idQualification.equals(other.idQualification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Qualification[ idQualification=" + idQualification + " ]";
    }
    
}
