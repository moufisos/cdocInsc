/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "t_branche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branche.findAll", query = "SELECT b FROM Branche b"),
    @NamedQuery(name = "Branche.findByIdBranche", query = "SELECT b FROM Branche b WHERE b.idBranche = :idBranche"),
    @NamedQuery(name = "Branche.findByOptimisticLock", query = "SELECT b FROM Branche b WHERE b.optimisticLock = :optimisticLock"),
    @NamedQuery(name = "Branche.findByIntitule", query = "SELECT b FROM Branche b WHERE b.intitule = :intitule"),
    @NamedQuery(name = "Branche.findByDescription", query = "SELECT b FROM Branche b WHERE b.description = :description")})
public class Branche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_branche")
    private Integer idBranche;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "branche", cascade = CascadeType.ALL)
    private List<Sujet> sujetList;

    public Branche() {
    }

    public Branche(Integer idBranche) {
        this.idBranche = idBranche;
    }

    public Branche(Integer idBranche, String intitule) {
        this.idBranche = idBranche;
        this.intitule = intitule;
    }

    public Integer getIdBranche() {
        return idBranche;
    }

    public void setIdBranche(Integer idBranche) {
        this.idBranche = idBranche;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Sujet> getSujetList() {
        return sujetList;
    }

    public void setSujetList(List<Sujet> sujetList) {
        this.sujetList = sujetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBranche != null ? idBranche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branche)) {
            return false;
        }
        Branche other = (Branche) object;
        if ((this.idBranche == null && other.idBranche != null) || (this.idBranche != null && !this.idBranche.equals(other.idBranche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Branche[ idBranche=" + idBranche + " ]";
    }
    
}
