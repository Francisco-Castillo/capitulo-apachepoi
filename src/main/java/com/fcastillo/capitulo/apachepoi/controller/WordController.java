/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.controller;

import com.fcastillo.capitulo.apachepoi.utilidades.UWord;
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

    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
}
