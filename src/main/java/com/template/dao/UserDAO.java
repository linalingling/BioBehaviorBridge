package com.template.dao;

import com.template.config.DatabaseConfig;
import com.template.model.User;
import java.sql.*;

/**
 * 使用者 DAO — 註冊、登入、查詢
 *
 * 📝 DAO 的職責：
 *    - 只負責「資料庫存取」（SQL 操作）
 *    - 不做業務邏輯判斷（那是 Service 的工作）
 *    - 每個 public 方法對應一種 SQL 操作
 */
public class UserDAO {

    /**
     * 註冊新使用者
     * @return 新使用者的 ID，失敗回傳 -1
     */
    public int register(String username, String passwordHash) {
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, 'USER') RETURNING id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            // 帳號重複時 PostgreSQL 會拋出 unique violation
            if (e.getMessage().contains("duplicate key")) {
                System.out.println("❌ 帳號已存在！");
            } else {
                System.err.println("❌ 註冊失敗: " + e.getMessage());
            }
        }
        return -1;
    }

    /**
     * 登入驗證
     * @return 驗證成功回傳 User，失敗回傳 null
     */
    public User login(String username, String passwordHash) {
        String sql = "SELECT id, username, role FROM users WHERE username = ? AND password_hash = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ 登入查詢失敗: " + e.getMessage());
        }
        return null;
    }

    /** 依 ID 查詢使用者 */
    public User findById(int id) {
        String sql = "SELECT id, username, role FROM users WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.err.println("❌ 查詢使用者失敗: " + e.getMessage());
        }
        return null;
    }
}
