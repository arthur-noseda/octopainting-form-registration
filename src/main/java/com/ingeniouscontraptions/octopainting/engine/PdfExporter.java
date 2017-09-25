package com.ingeniouscontraptions.octopainting.engine;

import java.nio.file.Path;
import net.sf.jasperreports.engine.JasperPrint;

public interface PdfExporter {

    /**
     * Exports the {@link JasperPrint} to the {@code outputFile}.
     * 
     * @param jasperPrint the {@code JasperPrint}
     * @param outputFile  the output file
     * @throws PrintException if the {@code JasperPrint} could not be exported.
     */
    void export(JasperPrint jasperPrint, Path outputFile) throws PrintException;

}
