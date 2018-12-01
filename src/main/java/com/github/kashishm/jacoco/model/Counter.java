package com.github.kashishm.jacoco.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Counter {

    @XmlAttribute
    private Double missed;

    @XmlAttribute
    private Double covered;

    @XmlAttribute
    private String type;

    public Double getMissed() {
        return missed;
    }

    public void setMissed(Double missed) {
        this.missed = missed;
    }

    public Double getCovered() {
        return covered;
    }

    public void setCovered(Double covered) {
        this.covered = covered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
