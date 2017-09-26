package com.ingeniouscontraptions.octopainting.engine;

import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link JasperReport} loader.
 * 
 * @author Arthur Noseda
 */
public class JasperReportLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JasperReportLoader.class);

    /**
     * Loads the {@code JasperReport}.
     * 
     * @param path the path to the JasperReport
     * @return the {@code JasperReport}
     * @throws JRException if the {@code JasperReport} could not be loaded 
     */
    public JasperReport load(String path) throws JRException {
        InputStream is = JasperReportLoader.class.getResourceAsStream(path);
        if (is == null) {
            String error = String.format("Could not find %s.", path);
            LOGGER.error(error);
            throw new IllegalArgumentException(error);
        }
        return (JasperReport)JRLoader.loadObject(is);
    }

}
