package com.github.kashishm.jacoco;

import com.github.kashishm.jacoco.model.Model;
import com.github.kashishm.jacoco.model.Package;
import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PackageReporter extends ListReporter {

    public PackageReporter(Boolean print, ListReporter nextReporter) {
        super(print, nextReporter);
    }

    public PackageReporter(Boolean print) {
        super(print);
    }

    @Override
    public void report(Log log, Report report) {
        List<Model> models = new ArrayList<>(report.getPackages());
        printCoverage(models, Constants.PACKAGE, log, report);
    }

    @Override
    List<Model> getMaxModels(List<Model> models, Report report) {
        return (isNextReporterEnabled() ? report.getPackages().stream()
                .map(Package::getClasses).flatMap(Collection::stream).collect(Collectors.toList()) : models);
    }
}
