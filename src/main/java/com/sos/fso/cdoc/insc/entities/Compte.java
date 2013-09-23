/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_compte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdCompte", query = "SELECT c FROM Compte c WHERE c.idCompte = :idCompte"),
    @NamedQuery(name = "Compte.findByOptimisticLock", query = "SELECT c FROM Compte c WHERE c.optimisticLock = :optimisticLock"),
    @NamedQuery(name = "Compte.findByCne", query = "SELECT c FROM Compte c WHERE c.cne = :cne"),
    @NamedQuery(name = "Compte.findByPassword", query = "SELECT c FROM Compte c WHERE c.password = :password"),
    @NamedQuery(name = "Compte.findByEmail", query = "SELECT c FROM Compte c WHERE c.email = :email"),
    @NamedQuery(name = "Compte.findByActif", query = "SELECT c FROM Compte c WHERE c.actif = :actif")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compte")
    private Integer idCompte;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cne")
    private long cne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Column(name = "actif")
    private Boolean actif;
    @OneToMany(mappedBy = "compte")
    private List<Activation> activationList;

    public Compte() {
    }

    public Compte(Integer idCompte) {
        this.idCompte = idCompte;
    }

    public Compte(Integer idCompte, long cne, String password, String email) {
        this.idCompte = idCompte;
        this.cne = cne;
        this.password = password;
        this.email = email;
    }

    public Integer getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public long getCne() {
        return cne;
    }

    public void setCne(long cne) {
        this.cne = cne;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @XmlTransient
    public List<Activation> getActivationList() {
        return activationList;
    }

    public void setActivationList(List<Activation> activationList) {
        this.activationList = activationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompte != null ? idCompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idCompte == null && other.idCompte != null) || (this.idCompte != null && !this.idCompte.equals(other.idCompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Compte[ idCompte=" + idCompte + " ]";
    }
    
}
