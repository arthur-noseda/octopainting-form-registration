package com.ingeniouscontraptions.octopainting.engine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

/**
 * PDF files merger.
 * 
 * @author Arthur Noseda
 */
public class PdfFilesMerger {

    /**
     * Merges PDF files.
     * 
     * @param sources     the list of PDF files
     * @param destination the path to the merged PDF file
     * @throws IOException if something goes wrong while merging the PDF files
     */
    public void mergePdfFiles(List<Path> sources, Path destination) throws IOException {
        try {
            PDFMergerUtility pmu = new PDFMergerUtility();
            for (Path source : sources) {
                pmu.addSource(source.toFile());
            }
            pmu.setDestinationFileName(destination.toString());
            pmu.mergeDocuments();
        } catch (COSVisitorException ex) {
            // No need to be that specific about the problem.
            throw new IOException(ex);
        }
    }

}
