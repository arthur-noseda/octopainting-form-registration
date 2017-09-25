package com.ingeniouscontraptions.octopainting.engine;

import java.nio.file.Path;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exports {@link JasperPrint}s to PDF files.
 * 
 * @author Arthur Noseda
 */
public class JasperReportsExporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JasperReportsExporter.class);

     /**
     * Exports the {@code JasperPrint} to the {@code outputFile}.
     * 
     * @param jasperPrint the {@code JasperPrint}
     * @param outputFile  the output file
     * @throws PrintException if the {@code JasperPrint} could not be exported.
     */
    public void export(JasperPrint jasperPrint, Path outputFile) throws PrintException {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, outputFile.toFile());
            exporter.exportReport();
        } catch (JRException ex) {
            String error = String.format("Could not export %s to %s.", jasperPrint, outputFile);
            LOGGER.error(error, ex);
            throw new PrintException(error, ex);
        }
    }

}
