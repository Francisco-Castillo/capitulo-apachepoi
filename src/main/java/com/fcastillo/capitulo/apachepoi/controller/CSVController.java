/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.controller;

import com.fcastillo.capitulo.apachepoi.entity.Empleado;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author fcastillo
 */
@Named(value = "cSVController")
@ViewScoped
public class CSVController implements Serializable {

    private List<Empleado> lstEmpleados;

    public List<Empleado> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleado> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }

    public void leerCSV() {
        String[] encabezados = {"id", "empleado", "sueldo"};

        // Leemos archivo de configuración
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String directorio = rb.getString("directorio.docs").trim();
        String nombreArchivo = "empleados.csv";
        String rutaCompleta = directorio.concat(nombreArchivo);

        lstEmpleados = new ArrayList<>();
        
        try {
            Reader lector = Files.newBufferedReader(Paths.get(rutaCompleta));

            Iterable<CSVRecord> filas = CSVFormat.DEFAULT.
                    withHeader(encabezados).
                    withFirstRecordAsHeader().parse(lector);

            for (CSVRecord fila : filas) {
                lstEmpleados.add(new Empleado(
                        Integer.parseInt(fila.get("id")),
                        fila.get("empleado"),
                        Double.parseDouble(fila.get("sueldo"))
                ));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error : " + e.getLocalizedMessage());
        }
    }

}
