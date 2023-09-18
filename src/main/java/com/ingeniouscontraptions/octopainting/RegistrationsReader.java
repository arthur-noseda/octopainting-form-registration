package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Category;
import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.tsv.EntriesReader;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader of registrations.
 * 
 * @author Arthur Noseda
 */
public class RegistrationsReader {

    private static final int SEQUENTIEL = 0;

    private static final int SID = 1;

    private static final int HEURE_DE_SOUMISSION = 2;

    private static final int HEURE_DE_COMPLETION = 3;

    private static final int HEURE_DE_MODIFICATION = 4;

    private static final int BROUILLON = 5;

    private static final int ADRESSE_IP = 6;

    private static final int UID = 7;

    private static final int NOM_D_UTILISATEUR = 8;

    private static final int PRENOM = 9;

    private static final int NOM_DE_FAMILLE = 10;

    private static final int E_MAIL = 11;

    private static final int NUMERO_DE_TELEPHONE = 12;

    private static final int ENTREES = 13;

    private static final int NOMBRE_CHAMPS_ENTREE = 2;

    private static final int NOMBRE_ENTREES = 5;

    private static final int RETOUR_DES_JUGES = ENTREES + NOMBRE_ENTREES * NOMBRE_CHAMPS_ENTREE;

    /**
     * Reads the list of registrations from the reader.
     * 
     * @param reader the reader
     * @return the list of registrations
     * @throws IOException if the file could not be read
     */
    public List<Registration> readRegistrations(BufferedReader reader) throws IOException {
        EntriesReader entriesReader = new EntriesReader();
        return entriesReader.readEntries(reader, line -> {
            String[] chunks = line.split("\\t");
            String[] values = normalize(chunks);
            if (!isValidEntry(values)) {
                return null;
            }
            List<Entry> entries = createEntries(values);
            String transferOfInformation = getTransferOfInformation(values);
            return new Registration(Long.valueOf(values[SEQUENTIEL]), values[NOM_DE_FAMILLE], values[PRENOM], values[E_MAIL], values[NUMERO_DE_TELEPHONE], entries, transferOfInformation);
        });
    }

    /**
     * Each entry starts with a sequential number.
     * This method tells if the values represent a valid entry or (probably) a header.
     *
     * @param values the normalized values
     * @return {@code true} if the entry is a valid one, {@code false} otherwise
     */
    private boolean isValidEntry(String[] values) {
        try {
            Integer.parseInt(values[SEQUENTIEL]);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }

    private String[] normalize(String[] chunks) {
        String[] values = new String[chunks.length];
        for (int i = 0; i < chunks.length; i++) {
            // Remove leading and trailing quotes.
            values[i] = chunks[i].replaceAll("^\"|\"$", "");
        }
        return values;
    }

    private List<Entry> createEntries(String[] values) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < NOMBRE_ENTREES; i++) {
            String name = values[ENTREES + NOMBRE_CHAMPS_ENTREE * i];
            if (!name.equals("")) {
                entries.add(new Entry(name, new Category(values[ENTREES + NOMBRE_CHAMPS_ENTREE * i + 1])));
            }
        }
        return entries;
    }

    private String getTransferOfInformation(String[] values) {
        // Field added in 2023. Backward compatibility is handled here.
        String transferOfInformation = values.length > RETOUR_DES_JUGES ? values[RETOUR_DES_JUGES] : null;
        if (StringUtils.isBlank(transferOfInformation)) {
            return "Indifférent";
        }
        return transferOfInformation;
    }

}
