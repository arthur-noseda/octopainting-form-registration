package com.ingeniouscontraptions.octopainting;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ExportException {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: java -jar octopainting-registration-form-1.0-SNAPSHOT-jar-with-dependencies.jar export <tsv> <output>\n"
                    + " - <tsv>    the path to the registrations file (a TSV file downloaded from https://framaforms.org/)\n"
                    + " - <output> the path to the generated registration PDF file\n"
                    + "Or: java -jar octopainting-registration-form-1.0-SNAPSHOT-jar-with-dependencies.jar blank <output>\n"
                    + " - <output> the path to the blank registration PDF file\n");
            System.exit(1);
        }
        // String tsv = "/home/arthur/Documents/Development/Workspaces/Java/octopainting-form-registration/src/test/resources/formulaire_dinscription_a_octopainting_2023.tsv";
        // String output = "/home/arthur/Documents/Development/Workspaces/Java/octopainting-form-registration/output2.pdf";
        // String output = "/home/arthur/Documents/Development/Workspaces/Java/octopainting-form-registration/blank2023.pdf";
        Command.valueOf(args[0].toUpperCase()).execute(args);
    }

    enum Command {

        EXPORT {

            @Override
            void execute(String[] args) throws IOException, ExportException {
                String tsv = args[1];
                String output = args[2];
                new Octopainting().exportRegistrations(Paths.get(tsv), Paths.get(output));
            }

        }, BLANK {

            @Override
            void execute(String[] args) throws IOException, ExportException {
                String output = args[1];
                new Octopainting().exportBlank(Paths.get(output));
            }

        };

        abstract void execute(String[] args) throws IOException, ExportException;

    }



}
