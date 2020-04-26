/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.ejb;

import com.fcastillo.capitulo.apachepoi.entity.Facultades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fcastillo
 */
@Stateless
public class FacultadesFacade extends AbstractFacade<Facultades> implements FacultadesFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-apachepoi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadesFacade() {
        super(Facultades.class);
    }
    
}
