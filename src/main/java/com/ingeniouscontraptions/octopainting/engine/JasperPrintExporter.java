package com.ingeniouscontraptions.octopainting.engine;

import java.nio.file.Path;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Exports {@link JasperPrint}s to PDF files.
 * 
 * @author Arthur Noseda
 */
public class JasperPrintExporter {

     /**
     * Exports {@code jasperPrint} to {@code outputFile}.
     * 
     * @param jasperPrint the {@code JasperPrint}
     * @param outputFile  the output file
     * @throws JRException if the {@code JasperPrint} could not be exported.
     */
    public void export(JasperPrint jasperPrint, Path outputFile) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, outputFile.toFile());
        exporter.exportReport();
    }

}
