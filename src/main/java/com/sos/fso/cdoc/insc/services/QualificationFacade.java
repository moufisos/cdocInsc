/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Qualification;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class QualificationFacade extends AbstractFacade<Qualification> {
    @PersistenceContext(unitName = "cdocInscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QualificationFacade() {
        super(Qualification.class);
    }
    
}
