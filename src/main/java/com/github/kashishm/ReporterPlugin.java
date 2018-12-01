package com.github.kashishm;

import com.github.kashishm.jacoco.ClassReporter;
import com.github.kashishm.jacoco.SummaryReporter;
import com.github.kashishm.jacoco.PackageReporter;
import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

@Mojo(name = "report")
public class ReporterPlugin extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/site/jacoco/jacoco.xml", property = "reportDir")
    private File reportFile;

    @Parameter(defaultValue = "true", property = "summary")
    private Boolean summary;

    @Parameter(defaultValue = "false", property = "packages")
    private Boolean packages;

    @Parameter(defaultValue = "false", property = "classes")
    private Boolean classes;

    public void execute() {
        Log log = getLog();
        try {
            XMLInputFactory xif = XMLInputFactory.newInstance();
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(reportFile.getAbsolutePath()));
            Unmarshaller unmarshaller = JAXBContext.newInstance(Report.class).createUnmarshaller();
            Report report = (Report) unmarshaller.unmarshal(xsr);
            new SummaryReporter(summary, new PackageReporter(packages, new ClassReporter(classes))).report(log, report);
        } catch (Throwable e) {
            log.error("Exception occurred while printing coverage.", e);
        }
    }
}
