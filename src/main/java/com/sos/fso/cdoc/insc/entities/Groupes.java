/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_groupes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupes.findAll", query = "SELECT g FROM Groupes g"),
    @NamedQuery(name = "Groupes.findByIdGroupes", query = "SELECT g FROM Groupes g WHERE g.idGroupes = :idGroupes"),
    @NamedQuery(name = "Groupes.findByOptimisticLock", query = "SELECT g FROM Groupes g WHERE g.optimisticLock = :optimisticLock"),
    @NamedQuery(name = "Groupes.findByIntitule", query = "SELECT g FROM Groupes g WHERE g.intitule = :intitule"),
    @NamedQuery(name = "Groupes.findByCne", query = "SELECT g FROM Groupes g WHERE g.cne = :cne")})
public class Groupes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_groupes")
    private Integer idGroupes;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "intitule")
    private String intitule;
    @Column(name = "cne")
    private long cne;

    public Groupes() {
    }

    public Groupes(Integer idGroupes) {
        this.idGroupes = idGroupes;
    }

    public Groupes(Integer idGroupes, String intitule) {
        this.idGroupes = idGroupes;
        this.intitule = intitule;
    }

    public Integer getIdGroupes() {
        return idGroupes;
    }

    public void setIdGroupes(Integer idGroupes) {
        this.idGroupes = idGroupes;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public long getCne() {
        return cne;
    }

    public void setCne(long cne) {
        this.cne = cne;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupes != null ? idGroupes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupes)) {
            return false;
        }
        Groupes other = (Groupes) object;
        if ((this.idGroupes == null && other.idGroupes != null) || (this.idGroupes != null && !this.idGroupes.equals(other.idGroupes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Groupes[ idGroupes=" + idGroupes + " ]";
    }
    
}
