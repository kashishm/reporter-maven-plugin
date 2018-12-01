package com.github.kashishm.jacoco.model;

import java.util.List;

public interface Model {
    String getName();

    List<Counter> getCounters();
}
