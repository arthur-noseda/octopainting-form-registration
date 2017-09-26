package com.ingeniouscontraptions.octopainting.tsv;

import com.ingeniouscontraptions.octopainting.RegistrationsReader;
import com.ingeniouscontraptions.octopainting.domain.Category;
import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationsReaderTest {

    @Test
    public void testRead() throws Exception {
        RegistrationsReader reader = new RegistrationsReader();
        Path path = Paths.get(RegistrationsReaderTest.class.getResource("/octopainting_2017___formulaire_dinscription.tsv").toURI());
        List<Registration> actual = reader.readRegistrations(path);
        List<Registration> expected = Arrays.asList(
                new Registration(1L, "Mike", "McVey", "mike@mcvey.com", "1234567890", Arrays.asList(
                        new Entry("Teclis", new Category("Figurines - Masters peinture")))),
                new Registration(2L, "Derek", "Schubert", "derek@schubert.com", "2345678901", Arrays.asList(
                        new Entry("Queen Ileosa of Korvosa", new Category("Greens")))),
                new Registration(3L, "Raffaele", "Picca", "raffaele@picca.com", "3456789012", Arrays.asList(
                        new Entry("Untold Honor", new Category("Figurines - Masters peinture")),
                        new Entry("Major Tom", new Category("Figurines - Masters peinture")))));
        Assert.assertEquals(expected, actual);
    }

}
