package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.engine.JasperReportsExporter;
import com.ingeniouscontraptions.octopainting.engine.PrintException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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

    private static final String BLANK_PARAMETER_NAME = "BLANK";

    private final JasperReportsExporter exporter;

    /**
     * Constructs a {@code RegistrationExporter}.
     * 
     * @param template the url of the JasperReports template
     */
    public RegistrationExporter(URL template) {
        exporter = new JasperReportsExporter(template);
    }

    /**
     * Exports this registration to a PDF file.
     * 
     * @param registration the registration
     * @param outputFile   the path to the PDF file
     * @throws PrintException if the registration could not be exported
     */
    public void export(Registration registration, Path outputFile) throws PrintException {
        export(registration, outputFile, false);
    }

    /**
     * Exports a blank registration to a PDF file.
     * 
     * @param outputFile the path to the PDF file
     * @throws PrintException if the blank registration could not be exported
     */
    public void exportBlank(Path outputFile) throws PrintException {
        int size = 5;
        List<Entry> entries = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            entries.add(new Entry(null, null));
        }
        Registration registration = new Registration(null, null, null, null, null, entries);
        export(registration, outputFile, true);
    }

    private void export(Registration registration, Path outputFile, boolean blankRegistration) throws PrintException {
        try {
            JasperPrint jasperPrint = exporter.newBuilder()
                    .setDataSource(Collections.singleton(registration))
                    .setParameter(BLANK_PARAMETER_NAME, blankRegistration)
                    .toJasperPrint();
            exporter.export(jasperPrint, outputFile);
        } catch (JRException ex) {
            String errorMessage = String.format("Could not export %s to %s.", registration, outputFile);
            LOGGER.error(errorMessage);
            throw new PrintException(errorMessage, ex);
        }
    }

}
