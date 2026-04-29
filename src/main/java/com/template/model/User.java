package com.template.model;

/**
 * 使用者模型
 *
 * 📝 對應資料表：users
 * 📝 大部分專題都需要登入功能，這個類別可以直接沿用。
 *    如果你的系統有不同角色（如：管理者/客戶/房務員），
 *    可以用 role 欄位區分，或建立子類別（繼承）。
 */
public class User {
    private int id;
    private String username;
    private String role;   // "USER" or "ADMIN"
    private int totalItems;

    // === 建構子 ===

    /** 註冊用（還沒有 id） */
    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    /** 從資料庫讀取用 */
    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // === 業務方法 ===

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, username, role);
    }

    // === Getters & Setters ===
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
