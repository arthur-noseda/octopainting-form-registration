package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.engine.JasperPrintBuilder;
import com.ingeniouscontraptions.octopainting.engine.JasperPrintExporter;
import com.ingeniouscontraptions.octopainting.engine.JasperReportLoader;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exports registrations to PDF files.
 * 
 * @author Arthur Noseda
 */
public class RegistrationExporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationExporter.class);

    private static final String JASPER_REPORT_LOADER = "JASPER_REPORT_LOADER";

    private static final String BLANK_PARAMETER_NAME = "BLANK";

    private final URL jasperFile;

    /**
     * Constructs a {@code RegistrationExporter}.
     * 
     * @param jasperFile the url of the JasperReports template
     */
    public RegistrationExporter(URL jasperFile) {
        this.jasperFile = jasperFile;
    }

    /**
     * Exports this registration to a PDF file.
     * 
     * @param registration the registration
     * @param outputFile   the path to the PDF file
     * @throws ExportException if the registration could not be exported
     */
    public void export(Registration registration, Path outputFile) throws ExportException {
        export(registration, outputFile, false);
    }

    /**
     * Exports a blank registration to a PDF file.
     * 
     * @param outputFile the path to the PDF file
     * @throws ExportException if the blank registration could not be exported
     */
    public void exportBlank(Path outputFile) throws ExportException {
        int size = 5;
        List<Entry> entries = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            entries.add(new Entry(null, null));
        }
        Registration registration = new Registration(null, null, null, null, null, entries, null);
        export(registration, outputFile, true);
    }

    private void export(Registration registration, Path outputFile, boolean blankRegistration) throws ExportException {
        try {
            JasperPrint jasperPrint = new JasperPrintBuilder(jasperFile)
                    .setDataSource(registration)
                    .setParameter(JASPER_REPORT_LOADER, new JasperReportLoader())
                    .setParameter(BLANK_PARAMETER_NAME, blankRegistration)
                    .toJasperPrint();
            JasperPrintExporter exporter = new JasperPrintExporter();
            exporter.export(jasperPrint, outputFile);
        } catch (JRException ex) {
            String error = String.format("Could not export %s to %s.", registration, outputFile);
            LOGGER.error(error);
            throw new ExportException(error, ex);
        }
    }

}
