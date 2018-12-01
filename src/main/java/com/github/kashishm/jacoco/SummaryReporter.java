package com.github.kashishm.jacoco;

import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.logging.Log;

public class SummaryReporter extends Reporter {

    public SummaryReporter(Boolean print, ListReporter nextReporter) {
        super(print, nextReporter);
    }

    public SummaryReporter(Boolean print) {
        super(print);
    }

    @Override
    public void report(Log log, Report report) {
        if (print()) {
            System.out.println();
            System.out.println(" ------------------------------- Coverage summary -------------------------------");
            report.getCounters().forEach(counter -> {
                Double total = counter.getCovered() + counter.getMissed();
                System.out.println(String.format(Constants.SUMMARY_FORMAT, counter.getType(), (counter.getCovered() / total * 100), Constants.PERCENTAGE,
                        counter.getCovered().longValue(), total.longValue()));
            });
            System.out.println(" --------------------------------------------------------------------------------");
            System.out.println();
        }
        next(log, report);
    }
}
