package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Category;
import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.tsv.EntriesReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader of registrations.
 * 
 * @author Arthur Noseda
 */
public class RegistrationsReader {

    /**
     * Reads the list of registrations from the file.
     * 
     * @param file the file
     * @return the list of registrations
     * @throws IOException if the file could not be read
     */
    public List<Registration> readRegistrations(Path file) throws IOException {
        EntriesReader reader = new EntriesReader();
        return reader.readEntries(file, new EntriesReader.EntryHandler<Registration>() {

            @Override
            public Registration handleEntry(String line) {
                String[] chunks = line.split("\\t");
                int size = chunks.length;
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    // Remove leading and trailing quotes.
                    values[i] = chunks[i].replaceAll("^\"|\"$", "");
                }
                List<Entry> entries = new ArrayList<>();
                for (int i = 14; i < size; i += 2) {
                    String name = values[i];
                    if (!name.equals("")) {
                        entries.add(new Entry(name, new Category(values[i + 1])));
                    }
                }
                return new Registration(new Long(values[0]), values[10], values[9], values[11], values[12], entries);
            }

        });
    }

}
