/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Activation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class ActivationFacade extends AbstractFacade<Activation> {
    @PersistenceContext(unitName = "cdocInscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActivationFacade() {
        super(Activation.class);
    }
    
    public Activation getAccountByKey(String key){
        return em.createNamedQuery("Activation.findByActivationKey", Activation.class)
                .setParameter("activationKey", key).getSingleResult();
        
    }
    
}
