/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.controller;

import com.fcastillo.capitulo.apachepoi.ejb.FacultadesFacadeLocal;
import com.fcastillo.capitulo.apachepoi.entity.Facultades;
import com.fcastillo.capitulo.apachepoi.utilidades.UWord;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fcastillo
 */
@Named(value = "wordController")
@RequestScoped
public class WordController {

    @EJB
    FacultadesFacadeLocal facultadFacade;
    private String texto;
    private String usuario;
    private String password;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public void crear() {
        UWord.crearDocumentoVacio();
        // Mostramos mensaje informativo
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo generado exitosamente", null));
    }

    public void redactar() {
        UWord.redactar(texto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo generado", null));
        clear();
    }

    public void clear() {
        texto = "";
    }

    public void crearTabla() {
        List<Facultades> lstFacultadas = facultadFacade.findAll();
        UWord.crearTablaFacultad(lstFacultadas);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo generado", null));
    }
}
