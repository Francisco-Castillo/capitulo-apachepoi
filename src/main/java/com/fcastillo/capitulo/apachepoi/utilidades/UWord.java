package com.fcastillo.capitulo.apachepoi.utilidades;

import com.fcastillo.capitulo.apachepoi.entity.Facultades;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author fcastillo
 */
@Named
@RequestScoped
public class UWord {

    public static void crearDocumentoVacio() {
        try {
            XWPFDocument documento = new XWPFDocument();
            // Leemos archivo de configuración
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String directorio = rb.getString("directorio").trim();
            String nombreArchivo = "nuevo-documento.docx";
            String rutaCompleta = directorio.concat(nombreArchivo);

            FileOutputStream fos = new FileOutputStream(new File(rutaCompleta));
            documento.write(fos);
            fos.close();
        } catch (Exception e) {
        }

    }

    public static void redactar(String texto) {

        try {
            XWPFDocument documento = new XWPFDocument();

            // Leemos archivo de configuración
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String directorio = rb.getString("directorio").trim();
            String nombreArchivo = "documento-con-parrafo.docx";
            String rutaCompleta = directorio.concat(nombreArchivo);

            FileOutputStream fos = new FileOutputStream(new File(rutaCompleta));

            XWPFParagraph parrafo = documento.createParagraph();
            XWPFRun run = parrafo.createRun();
            run.setText(texto);

            documento.write(fos);
            fos.close();
        } catch (IOException e) {
            System.out.println("error : " + e.getLocalizedMessage());
        }
       
    }

    public static void crearTablaFacultad(List<Facultades> lista) {
        try {
            XWPFDocument documento = new XWPFDocument();

            // Leemos archivo de configuración
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String directorio = rb.getString("directorio").trim();
            String nombreArchivo = "documento-tabla-facultad.docx";
            String rutaCompleta = directorio.concat(nombreArchivo);

            FileOutputStream fos = new FileOutputStream(new File(rutaCompleta));

            XWPFTable tabla = documento.createTable();

            // Creamos la primer fila que sera el encabezado
            XWPFTableRow primerFila = tabla.getRow(0);
            primerFila.getCell(0).setText("codigo");
            primerFila.addNewTableCell().setText("Nombre de Facultad");
            primerFila.addNewTableCell().setText("Abreviatura");

            for (int i = 0; i < lista.size(); i++) {
                Facultades facultad = lista.get(i);

                XWPFTableRow fila = tabla.createRow();

                fila.getCell(0).setText(facultad.getIdfacultad().toString());
                fila.getCell(1).setText(facultad.getNombre());
                fila.getCell(2).setText(facultad.getAbreviatura());
            }

            documento.write(fos);
            fos.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error: " + e.getLocalizedMessage());
        }

    }
}
