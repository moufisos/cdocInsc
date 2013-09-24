/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_activation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activation.findAll", query = "SELECT a FROM Activation a"),
    @NamedQuery(name = "Activation.findByActivationKey", query = "SELECT a FROM Activation a WHERE a.activationKey = :activationKey"),
})
public class Activation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activation")
    private Integer idActivation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "activation_key")
    private String activationKey;
    @JoinColumn(name = "id_compte", referencedColumnName = "id_compte")
    @OneToOne
    private Compte compte;

    public Activation() {
    }

    public Activation(Integer idActivation) {
        this.idActivation = idActivation;
    }

    public Activation(Integer idActivation, String activationKey) {
        this.idActivation = idActivation;
        this.activationKey = activationKey;
    }

    public Integer getIdActivation() {
        return idActivation;
    }

    public void setIdActivation(Integer idActivation) {
        this.idActivation = idActivation;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivation != null ? idActivation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activation)) {
            return false;
        }
        Activation other = (Activation) object;
        if ((this.idActivation == null && other.idActivation != null) || (this.idActivation != null && !this.idActivation.equals(other.idActivation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Activation[ idActivation=" + idActivation + " ]";
    }
    
}
