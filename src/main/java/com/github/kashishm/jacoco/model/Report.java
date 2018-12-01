package com.github.kashishm.jacoco.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "report")
public class Report implements Model {

    @XmlAttribute
    private String name;

    @XmlElement(name = "package")
    private List<Package> packages;

    @XmlElement(name = "counter")
    private List<Counter> counters;

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public List<Counter> getCounters() {
        return counters;
    }

    public void setCounters(List<Counter> counters) {
        this.counters = counters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
