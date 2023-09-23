package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.ui.OctopaintingPanel;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ExportException {
        if (args.length > 3) {
            System.err.println("Usage: java -jar octopainting-registration-form-1.0-SNAPSHOT-jar-with-dependencies.jar gui\n"
                    + "To display a graphical user interface.\n\n"
                    + "Or: java -jar octopainting-registration-form-1.0-SNAPSHOT-jar-with-dependencies.jar export <tsv> <output>\n"
                    + "To export registrations to PDF, with:\n"
                    + " - <tsv>    the path to the registrations file (a TSV file downloaded from https://framaforms.org/)\n"
                    + " - <output> the path to the generated registration PDF file\n\n"
                    + "Or: java -jar octopainting-registration-form-1.0-SNAPSHOT-jar-with-dependencies.jar blank <output>\n"
                    + "To export a blank registration to PDF, with:\n"
                    + " - <output> the path to the blank registration PDF file");
            System.exit(1);
        }
        if (args.length == 0) {
            Command.GUI.execute(new String[0]);
        } else {
            String[] newArgs = new String[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, newArgs.length);
            Command.valueOf(args[0].toUpperCase()).execute(newArgs);
        }
    }

    enum Command {

        GUI {

            @Override
            void execute(String[] args) throws IOException, ExportException {
                JFrame frame = new JFrame("Octopainting Form Registration");
                frame.getContentPane().add(new OctopaintingPanel(new Octopainting()));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }, EXPORT {

            @Override
            void execute(String[] args) throws IOException, ExportException {
                String tsv = args[0];
                String output = args[1];
                new Octopainting().exportRegistrations(Paths.get(tsv), Paths.get(output));
            }

        }, BLANK {

            @Override
            void execute(String[] args) throws IOException, ExportException {
                String output = args[0];
                new Octopainting().exportBlank(Paths.get(output));
            }

        };

        abstract void execute(String[] args) throws IOException, ExportException;

    }



}
