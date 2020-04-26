
package com.fcastillo.capitulo.apachepoi.utilidades;

import com.fcastillo.capitulo.apachepoi.ejb.FacultadesFacadeLocal;
import com.fcastillo.capitulo.apachepoi.entity.Facultades;
import com.fcastillo.capitulo.apachepoi.entity.Persona;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author fcastillo
 */
@Named(value = "uFile")
@ViewScoped
public class UFile implements Serializable {

    @EJB
    FacultadesFacadeLocal facultadFacade;

    private String directorio;
    private UploadedFile archivo;
    private List<Persona> lstPersonas;
    private List<Facultades> lstFacultades;

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    public List<Facultades> getLstFacultades() {
        return lstFacultades;
    }

    public void setLstFacultades(List<Facultades> lstFacultades) {
        this.lstFacultades = lstFacultades;
    }

    public void subir(FileUploadEvent event) {

        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            directorio = rb.getString("directorio");
            archivo = event.getFile();
            byte[] contenido = archivo.getContents();
            escribirEnDirectorio(directorio, archivo.getFileName(), contenido);
        } catch (Exception e) {
        }
    }

    public void escribirEnDirectorio(String directorio, String nombreArchivo, byte[] contenidoArchivo) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(directorio + nombreArchivo));
            fos.write(contenidoArchivo);
            System.out.println("Archivo subido exitosamente");
        } catch (IOException e) {
            System.out.println("ocurrio un error : " + e.getLocalizedMessage());
        }
    }

    public void procesar() {
        try {
            File archivoExcel = new File(directorio + archivo.getFileName());
            FileInputStream fis = new FileInputStream(archivoExcel);

            Workbook libro = new HSSFWorkbook(fis);
            Sheet hoja = libro.getSheetAt(0);
            Iterator<Row> iterador = hoja.iterator();
            DataFormatter df = new DataFormatter();
            int i = -1;
            lstPersonas = new ArrayList<>();
            while (iterador.hasNext()) {
                i++;
                Row siguienteFila = iterador.next();
                Iterator<Cell> iteradorCelda = siguienteFila.cellIterator();
                Persona persona = new Persona();
                while (iteradorCelda.hasNext()) {
                    Cell celda = iteradorCelda.next();

                    persona.setApellido(hoja.getRow(i).getCell(0).getStringCellValue());
                    persona.setNombre(hoja.getRow(i).getCell(1).getStringCellValue());
                    persona.setDocumento(df.formatCellValue(hoja.getRow(i).getCell(2)));
                    persona.setSexo(Integer.parseInt(df.formatCellValue(hoja.getRow(i).getCell(3))));
                    persona.setFnacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(df.formatCellValue(hoja.getRow(i).getCell(4))));
                }
                lstPersonas.add(persona);

            }
        } catch (IOException | NumberFormatException | ParseException e) {
            System.out.println("ocurrio un error : " + e.getLocalizedMessage());
        }
    }

    public void generarExcel() {
        try {
            // Realizamos la consulta a la BD
            lstFacultades = facultadFacade.findAll();

            // Leemos archivo de configuración
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String directorio = rb.getString("directorio").trim();
            String nombreArchivo = "listado-facultades.xlsx";
            String rutaCompleta = directorio.concat(nombreArchivo);

            // Creamos el libro de trabajo y su hoja
            XSSFWorkbook libro = new XSSFWorkbook();
            XSSFSheet hoja = libro.createSheet("Facultades");

            // Creamos la primer fila que sera el encabezado
            XSSFRow fila = hoja.createRow(0);
            XSSFCell celda;

            celda = fila.createCell(0);
            celda.setCellValue("Cod.");

            celda = fila.createCell(1);
            celda.setCellValue("Nombre de Facultad");

            celda = fila.createCell(2);
            celda.setCellValue("Abreviatura");

            for (int i = 1; i < lstFacultades.size(); i++) {
                fila = hoja.createRow(i);

                celda = fila.createCell(0);
                celda.setCellValue(Double.parseDouble(lstFacultades.get(i).getIdfacultad().toString()));

                celda = fila.createCell(1);
                celda.setCellValue(lstFacultades.get(i).getNombre());

                celda = fila.createCell(2);
                celda.setCellValue(lstFacultades.get(i).getAbreviatura());

            }

            FileOutputStream fos = new FileOutputStream(new File(rutaCompleta));

            libro.write(fos);

            fos.close();

            // Mostramos mensaje informativo
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo generado en: ", rutaCompleta));
        } catch (IOException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error: ", e.getLocalizedMessage()));
        }
    }

}
