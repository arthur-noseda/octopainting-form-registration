package com.ingeniouscontraptions.octopainting;

import com.ingeniouscontraptions.octopainting.domain.Category;
import com.ingeniouscontraptions.octopainting.domain.Entry;
import com.ingeniouscontraptions.octopainting.domain.Registration;
import com.ingeniouscontraptions.octopainting.engine.PrintException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws PrintException {
        RegistrationExporter rr = new RegistrationExporter(RegistrationExporter.class.getResource("/jasper/octopainting.jasper"));
        Registration r = new Registration(123L, "John", "Doe", "john.doe@greatminiatures.com", "",
                Arrays.asList(
                        new Entry("Eldar Farseer", new Category("Figurines - Masters peinture")),
                        new Entry("Zhar-Naggrund Ziggurats", new Category("Figurines - Masters création")),
                        new Entry("The Dark Knight", new Category("Greens"))));
        rr.export(r, Paths.get("C:\\Temp\\octopainting_johndoe.pdf"));
        rr.exportBlank(Paths.get("C:\\Temp\\octopainting_blank.pdf"));
    }

}
