package com.ingeniouscontraptions.octopainting.engine;

import java.net.URL;
import java.nio.file.Path;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation of {@link PdfExporter}.
 * 
 * @author Arthur Noseda
 */
public class JasperReportsExporter implements PdfExporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JasperReportsExporter.class);

    private final URL jasperFile;

    /**
     * Constructs a {@code JasperReportsExporter}.
     * 
     * @param jasperFile the URL of the JasperReports template
     */
    public JasperReportsExporter(URL jasperFile) {
        this.jasperFile = jasperFile;
    }

    @Override
    public void export(JasperPrint jasperPrint, Path outputFile) throws PrintException {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, outputFile.toFile());
            exporter.exportReport();
        } catch (JRException ex) {
            String errorMessage = String.format("Could not export %s to %s.", jasperPrint, outputFile);
            LOGGER.error(errorMessage, ex);
            throw new PrintException(errorMessage, ex);
        }
    }

    /**
     * Factory of {@link JasperPrintBuilder}.
     *
     * @return a new {@code JasperPrintBuilder}
     */
    public JasperPrintBuilder newBuilder() {
        return new JasperPrintBuilder(jasperFile);
    }

    @Override
    public String toString() {
        return "JasperReportsExporter{jasperFile=" + jasperFile + "}";
    }

}
