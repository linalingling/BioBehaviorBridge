package com.linalingling.bbb.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BehaviorLog {
    private int id;
    private int characterId;
    private String behaviorType;
    private int rawValue;
    private BigDecimal calculatedPoints;

    private Timestamp logDate;


    public BehaviorLog() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    public int getRawValue() {
        return rawValue;
    }

    public void setRawValue(int rawValue) {
        this.rawValue = rawValue;
    }

    public BigDecimal getCalculatedPoints() {
        return calculatedPoints;
    }

    public void setCalculatedPoints(BigDecimal calculatedPoints) {
        this.calculatedPoints = calculatedPoints;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }
}