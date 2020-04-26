/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.entity;

import java.util.Date;

/**
 *
 * @author fcastillo
 */
public class Persona {

    private String apellido;
    private String nombre;
    private String documento;
    private int sexo;
    private Date fnacimiento;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public Persona() {
    }

    public Persona(String apellido, String nombre, String documento, int sexo, Date fnacimiento) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.documento = documento;
        this.sexo = sexo;
        this.fnacimiento = fnacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "apellido=" + apellido + ", nombre=" + nombre + ", documento=" + documento + '}';
    }

}
