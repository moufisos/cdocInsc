/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Sujet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class SujetFacade extends AbstractFacade<Sujet> {
    @PersistenceContext(unitName = "cdocInscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SujetFacade() {
        super(Sujet.class);
    }
    
}
