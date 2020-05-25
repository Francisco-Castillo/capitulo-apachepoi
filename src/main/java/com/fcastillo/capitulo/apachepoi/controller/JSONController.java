
package com.fcastillo.capitulo.apachepoi.controller;

import com.fcastillo.capitulo.apachepoi.entity.Medallero;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author fcastillo
 */
@Named(value = "jSONController")
@ViewScoped
public class JSONController implements Serializable {

    private List<Medallero> lstMedallero;

    public List<Medallero> getLstMedallero() {
        return lstMedallero;
    }

    public void setLstMedallero(List<Medallero> lstMedallero) {
        this.lstMedallero = lstMedallero;
    }

    public void leerJSON() {
        try {
            String ruta = "/home/fcastillo/medallero.json";
            InputStream inputStream = new FileInputStream(ruta);
            Reader lector = new InputStreamReader(inputStream);
            JsonReader jsonLector = Json.createReader(lector);
            JsonArray arreglo = jsonLector.readArray();
            
            List<Medallero> lista = new ArrayList<>();

            arreglo.stream()
                    .forEach(x -> {
                        JsonObject objeto = (JsonObject) x;

                        Medallero medallero = new Medallero();

                        medallero.setPosicion(objeto.getInt("posicion"));
                        medallero.setPais(objeto.getString("pais"));
                        medallero.setOro(objeto.getInt("oro"));
                        medallero.setPlata(objeto.getInt("plata"));
                        medallero.setBronce(objeto.getInt("bronce"));
                        medallero.setTotal(medallero.calcularTotalMedallas());

                        lista.add(medallero);

                    });
            lista.sort((Medallero m1, Medallero m2) -> m2.getTotal() - m1.getTotal());
            lstMedallero = lista.subList(0, 3);

        } catch (FileNotFoundException e) {
            System.out.println("Ocurrio un error :" + e.getLocalizedMessage());
        }
    }
}
