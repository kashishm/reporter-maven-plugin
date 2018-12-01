package com.github.kashishm.jacoco;

import com.github.kashishm.jacoco.model.Counter;
import com.github.kashishm.jacoco.model.Model;
import com.github.kashishm.jacoco.model.Report;
import org.apache.maven.plugin.logging.Log;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.github.kashishm.jacoco.Constants.*;
import static java.util.stream.Collectors.joining;

abstract class ListReporter extends Reporter {

    ListReporter(Boolean print, Reporter nextReporter) {
        super(print, nextReporter);
    }

    ListReporter(Boolean print) {
        super(print);
    }

    List<Model> getMaxModels(List<Model> models, Report report) {
        return models;
    }

    void printCoverage(List<Model> models, String type, Log log, Report report) {
        if (print()) {
            String template = COLUMN_FORMAT.replace(PLACEHOLDER, getMaxModels(models, report)
                    .stream().max(Comparator.comparingInt(o -> o.getName().length()))
                    .map(model -> Integer.toString(model.getName().length() + 2)).orElse(DEFAULT_LENGTH));
            String header = String.format(template, type, "|", LINE, "|", COMPLEXITY);
            String b = Arrays.stream(header.split("")).map(a -> "").collect(joining(DELIMITER));
            String border = " |" + (b.substring(0, b.length() - 2) + "|");
            String headerBorder = border.replace("|", "-");
            System.out.println(headerBorder);
            System.out.println(header);
            System.out.println(headerBorder);
            models.forEach(m -> System.out.println(String.format(template, m.getName(), "|",
                    getValue(m, LINE), "|", getValue(m, COMPLEXITY))));
            System.out.println(border);
            System.out.println();
        }
        next(log, report);
    }

    private String getValue(Model m, String type) {
        Optional<Counter> instructionCounter = m.getCounters().stream()
                .filter(c -> c.getType().equalsIgnoreCase(type)).findFirst();
        if (!instructionCounter.isPresent()) return "";
        Counter counter = instructionCounter.get();
        Double total = counter.getCovered() + counter.getMissed();
        return String.format(COVERAGE_FORMAT, counter.getCovered() / total * 100, PERCENTAGE);
    }

}
