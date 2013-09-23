/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Etudiant;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class EtudiantFacade extends AbstractFacade<Etudiant> {
    @PersistenceContext(unitName = "cdocInscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }
    
}
