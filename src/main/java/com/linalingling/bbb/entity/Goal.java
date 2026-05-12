package com.linalingling.bbb.entity;

import java.sql.Timestamp;

public class Goal {
    private int id;
    private int charId;      // 修改：對齊 DB，綁定角色 ID
    private String title;    // 對應 goal_type 枚舉字串
    private boolean isActive; // 修改：對應 is_active 欄位
    private Timestamp createdAt;

    public Goal() {}

    public Goal(int id, int charId, String title, boolean isActive) {
        this.id = id;
        this.charId = charId;
        this.title = title;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCharId() { return charId; }
    public void setCharId(int charId) { this.charId = charId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
