package com.ingeniouscontraptions.octopainting.engine;

import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * A builder of {@link JasperPrint}.
 * 
 * @author Arthur Noseda
 */
public class JasperPrintBuilder {

    private final URL jasperFile;

    private final Map<String, Object> parameters;

    private JRDataSource dataSource;

    /**
     * Constructs a builder.
     * 
     * @param jasperFile the URL of the JasperReports template
     */
    public JasperPrintBuilder(URL jasperFile) {
        this.jasperFile = jasperFile;
        parameters = new HashMap<>();
    }

    /**
     * Sets the parameter.
     * 
     * @param name  the name of the parameter
     * @param value the value of the parameter
     * @return this builder
     */
    public JasperPrintBuilder setParameter(String name, Object value) {
        parameters.put(name, value);
        return this;
    }

    /**
     * Sets the data source.
     * 
     * @param dataSource the data source
     * @return this builder
     */
    public JasperPrintBuilder setDataSource(JRDataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    /**
     * Sets the data source.
     * 
     * @param beanCollection a collection of objects
     * @return the builder
     */
    public JasperPrintBuilder setDataSource(Collection<?> beanCollection) {
        dataSource = new JRBeanCollectionDataSource(beanCollection);
        return this;
    }

    /**
     * Sets the data source.
     * 
     * @param bean an object
     * @return this builder
     */
    public JasperPrintBuilder setDataSource(Serializable bean) {
        return setDataSource(Collections.singletonList(bean));
    }

    /**
     * Sets the locale.
     * 
     * @param locale the locale
     * @return this builder
     */
    public JasperPrintBuilder setLocale(Locale locale) {
        return setParameter(JRParameter.REPORT_LOCALE, locale);
    }

    /**
     * Builds the {@code JasperPrint}.
     * 
     * @return a {@code JasperPrint} instance
     * @throws JRException if the {@code JasperPrint} could not be built
     */
    public JasperPrint toJasperPrint() throws JRException {
        JasperReport report = (JasperReport) JRLoader.loadObject(jasperFile);
        if (dataSource == null) {
            return JasperFillManager.fillReport(report, parameters);
        } else {
            return JasperFillManager.fillReport(report, parameters, dataSource);
        }
    }

}
