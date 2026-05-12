package com.linalingling.bbb.entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class BehaviorLog {
    private int id;
    private int goalId; // 修改：從原本可能的 characterId 改為 goalId，因為紀錄屬於計畫
    private String action; // 修改：由 behaviorType 改為 action，對齊 DB 欄位
    private String note;
    private BigDecimal baseValue;
    private BigDecimal calculatedPoints;
    private Timestamp createdAt;

    public BehaviorLog() {}

    // Getters and Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public BigDecimal getBaseValue() { return baseValue; }
    public void setBaseValue(BigDecimal baseValue) { this.baseValue = baseValue; }
    public BigDecimal getCalculatedPoints() { return calculatedPoints; }
    public void setCalculatedPoints(BigDecimal calculatedPoints) { this.calculatedPoints = calculatedPoints; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}