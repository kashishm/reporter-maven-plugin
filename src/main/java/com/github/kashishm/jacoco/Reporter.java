package com.github.kashishm.jacoco;

import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.logging.Log;

abstract class Reporter {

    private Boolean print;
    private Reporter reporter;

    Reporter(Boolean print, Reporter nextReporter) {
        this.print = print;
        this.reporter = nextReporter;
    }

    Reporter(Boolean print) {
        this.print = print;
    }

    Boolean print() {
        return print;
    }

    Boolean isNextReporterEnabled() {
        return reporter != null ? reporter.print() : false;
    }

    void next(Log log, Report report) {
        if (reporter != null) reporter.report(log, report);
    }

    abstract void report(Log log, Report report);
}
