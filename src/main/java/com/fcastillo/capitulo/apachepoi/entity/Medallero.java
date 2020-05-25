/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.entity;

/**
 *
 * @author fcastillo
 */
public class Medallero implements Comparable<Medallero> {

    private int posicion;
    private String pais;
    private int oro;
    private int plata;
    private int bronce;
    private int total;

    public Medallero() {
    }

    public Medallero(int posicion, String pais, int oro, int plata, int bronce, int total) {
        this.posicion = posicion;
        this.pais = pais;
        this.oro = oro;
        this.plata = plata;
        this.bronce = bronce;
        this.total = total;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getPlata() {
        return plata;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public int getBronce() {
        return bronce;
    }

    public void setBronce(int bronce) {
        this.bronce = bronce;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int calcularTotalMedallas() {
        return oro + plata + bronce;
    }

    @Override
    public String toString() {
        return "Medallero{" + "total=" + total + '}';
    }

    @Override
    public int compareTo(Medallero o) {
        return getTotal() - o.getTotal();
    }

}
