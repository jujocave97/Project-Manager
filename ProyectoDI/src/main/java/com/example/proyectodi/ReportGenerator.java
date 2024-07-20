package com.example.proyectodi;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class ReportGenerator {

    public void generarInforme() {
        try {
            // Cargar el informe compilado (.jasper)
            String rutaInforme = "C:\\Users\\jujoc\\IdeaProjects\\ProyectoDI\\informeProyecto.jrxml";
            JasperDesign design= JRXmlLoader.load(rutaInforme);
            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException ex) {
            ex.printStackTrace();
            // Manejar la excepción apropiadamente
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}