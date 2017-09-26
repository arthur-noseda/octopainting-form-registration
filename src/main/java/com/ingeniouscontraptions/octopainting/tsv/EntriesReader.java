package com.ingeniouscontraptions.octopainting.tsv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader of entries.
 * 
 * @author Arthur Noseda
 */
public class EntriesReader {

    /**
     * Reads a list of entries from the file.
     * 
     * @param <T>          the type of entries
     * @param file         the file
     * @param entryHandler the entry handler
     * @return a list of instances of type {@code T}
     * @throws IOException if the file could not be read
     */
    public <T> List<T> readEntries(Path file, EntryHandler<T> entryHandler) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(file);) {
            List<T> entries = new ArrayList<>();
            for (String line; (line = in.readLine()) != null;) {
                T entry = entryHandler.handleEntry(line);
                entries.add(entry);
            }
            return entries;
        }
    }

    /**
     * The entry handler.
     * 
     * @param <T> the type of entries
     */
    public interface EntryHandler<T> {

        /**
         * Converts the entry into an instance of type {@code T}.
         * 
         * @param entry the entry
         * @return an instance of type {@code T}
         */
        T handleEntry(String entry);

    }

}
