package com.linalingling.bbb.entity;
import java.math.BigDecimal;

public class Character {
    private int id;
    private String charName;
    private int goalId;
    private BigDecimal bonusDecimal;
    private String talentType;

    public Character() {
    }

    public Character(int id, String charName, BigDecimal bonusDecimal, String talentType) {
        this.id = id;
        this.charName = charName;
        this.bonusDecimal = bonusDecimal;
        this.talentType = talentType;
    }

    public int getId() {
        return id;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public BigDecimal getBonusDecimal() {
        return bonusDecimal;
    }

    public void setBonusDecimal(BigDecimal bonusDecimal) {
        this.bonusDecimal = bonusDecimal;
    }

    public String getTalentType() {
        return talentType;
    }

    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }
}
