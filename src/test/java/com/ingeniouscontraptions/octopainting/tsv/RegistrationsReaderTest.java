package com.ingeniouscontraptions.octopainting.tsv;

import com.ingeniouscontraptions.octopainting.RegistrationsReader;
import com.ingeniouscontraptions.octopainting.domain.Category;
import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import org.junit.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class RegistrationsReaderTest {

    @Test
    public void shouldReadEntries() throws Exception {
        RegistrationsReader registrationsReader = new RegistrationsReader();
        Path path = Paths.get(RegistrationsReaderTest.class.getResource("/octopainting_2017___formulaire_dinscription.tsv").toURI());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            assertThat(registrationsReader.readRegistrations(reader))
                    .hasSize(3)
                    .containsExactly(
                            new Registration(1L, "Mike", "McVey", "mike@mcvey.com", "1234567890", Collections.singletonList(
                                    new Entry("Teclis", new Category("Figurines - Masters peinture"))), "Indifférent"),
                            new Registration(2L, "Derek", "Schubert", "derek@schubert.com", "2345678901", Collections.singletonList(
                                    new Entry("Queen Ileosa of Korvosa", new Category("Greens"))), "Indifférent"),
                            new Registration(3L, "Raffaele", "Picca", "raffaele@picca.com", "3456789012", Arrays.asList(
                                    new Entry("Untold Honor", new Category("Figurines - Masters peinture")),
                                    new Entry("Major Tom", new Category("Figurines - Masters peinture"))), "Indifférent"));
        }
    }

    @Test
    public void shouldFilterHeaders() throws Exception {
        RegistrationsReader registrationsReader = new RegistrationsReader();
        Path path = Paths.get(RegistrationsReaderTest.class.getResource("/formulaire_dinscription_a_octopainting_2023_with_headers.tsv").toURI());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            assertThat(registrationsReader.readRegistrations(reader))
                    .hasSize(1);
        }
    }

}
