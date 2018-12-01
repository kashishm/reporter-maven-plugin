package com.github.kashishm.jacoco;

import com.github.kashishm.jacoco.model.Model;
import com.github.kashishm.jacoco.model.Package;
import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.logging.Log;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReporter extends ListReporter {

    public ClassReporter(Boolean print, ListReporter nextReporter) {
        super(print, nextReporter);
    }

    public ClassReporter(Boolean print) {
        super(print);
    }

    @Override
    public void report(Log log, Report report) {
        List<Model> models = report.getPackages().stream()
                .map(Package::getClasses).flatMap(Collection::stream).collect(Collectors.toList());
        printCoverage(models, Constants.CLASS, log, report);
    }
}
