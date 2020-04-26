/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.ejb;

import com.fcastillo.capitulo.apachepoi.entity.Facultades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface FacultadesFacadeLocal {

    void create(Facultades facultades);

    void edit(Facultades facultades);

    void remove(Facultades facultades);

    Facultades find(Object id);

    List<Facultades> findAll();

    List<Facultades> findRange(int[] range);

    int count();
    
}
