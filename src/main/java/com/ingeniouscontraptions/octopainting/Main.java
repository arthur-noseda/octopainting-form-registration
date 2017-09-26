package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.engine.PdfFilesMerger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ExportException {
        if (args.length != 2) {
            System.err.println("Usage: java com.ingeniouscontraptions.octopainting.Main <tsv> <output>\n"
                    + " - <tsv>    the path to the registrations file (a TSV file downloaded from https://framaforms.org/)\n"
                    + " - <output> the path to the generated PDF file");
            System.exit(1);
        }
        String tsv = args[0];
        String output = args[1];
        RegistrationsReader reader = new RegistrationsReader();
        RegistrationExporter exporter = new RegistrationExporter(Main.class.getResource("/jasper/octopainting.jasper"));
        List<Registration> registrations = reader.readRegistrations(Paths.get(tsv));
        String tmpDir = System.getProperty("java.io.tmpdir");
        List<Path> pdfFiles = new ArrayList<>();
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            Path pdfFile = Paths.get(tmpDir, "registration-" + i);
            pdfFiles.add(pdfFile);
            exporter.export(registration, pdfFile);
        }
        PdfFilesMerger merger = new PdfFilesMerger();
        merger.mergePdfFiles(pdfFiles, Paths.get(output));
        for (Path pdfFile : pdfFiles) {
            Files.delete(pdfFile);
        }
    }

}
