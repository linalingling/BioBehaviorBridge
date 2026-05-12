package com.linalingling.bbb.dao;

import com.linalingling.bbb.entity.User;
import com.linalingling.bbb.util.DBUtil;
import java.sql.*;

public class UserDAO {

    // 登入驗證邏輯
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password); // 提醒：目前先對齊資料庫明文，未來再升級加密

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 找不到人就回傳 null
    }
}