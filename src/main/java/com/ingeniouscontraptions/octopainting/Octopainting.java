package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.engine.PdfFilesMerger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Octopainting {

    private final RegistrationExporter exporter;

    public Octopainting() {
        exporter = new RegistrationExporter(Octopainting.class.getResource("/jasper/octopainting.jasper"));
    }

    public void exportRegistrations(Path tsv, Path output) throws IOException, ExportException {
        RegistrationsReader registrationsReader = new RegistrationsReader();
        try (BufferedReader reader = Files.newBufferedReader(tsv)) {
            List<Registration> registrations = registrationsReader.readRegistrations(reader);
            String tmpDir = System.getProperty("java.io.tmpdir");
            List<Path> pdfFiles = new ArrayList<>();
            for (int i = 0; i < registrations.size(); i++) {
                Registration registration = registrations.get(i);
                Path pdfFile = Paths.get(tmpDir, "registration-" + i);
                pdfFiles.add(pdfFile);
                exporter.export(registration, pdfFile);
            }

            PdfFilesMerger merger = new PdfFilesMerger();
            merger.mergePdfFiles(pdfFiles, output);
            for (Path pdfFile : pdfFiles) {
                Files.delete(pdfFile);
            }
        }
    }

    public void exportBlank(Path output) throws ExportException {
        exporter.exportBlank(output);
    }

}
