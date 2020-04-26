/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.apachepoi.utilidades;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author fcastillo
 */
public class UCSV {

    public static void leerArchivo(String rutaCompleta, String[] headers) {
        try {
            Reader lector = Files.newBufferedReader(Paths.get(rutaCompleta));
            
            Iterable<CSVRecord> filas = CSVFormat.DEFAULT.
                    withHeader(headers).
                    withFirstRecordAsHeader().parse(lector);
            
            for (CSVRecord fila : filas) {
                System.out.println("Empleado : "+fila.get("empleado"));
                System.out.println("Sueldo : "+fila.get("sueldo"));
            }
        } catch (Exception e) {
        }
    }

}
