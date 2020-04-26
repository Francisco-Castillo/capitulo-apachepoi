package com.fcastillo.capitulo.apachepoi.utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

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
            System.out.println("error : "+e.getLocalizedMessage());
        }
        System.out.println("createparagraph.docx written successfully");
    }
}
