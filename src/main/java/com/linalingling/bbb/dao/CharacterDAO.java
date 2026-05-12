package com.linalingling.bbb.dao;

import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterDAO {

    // 1. 寫入角色
    public void insert(Character character) throws SQLException {
        String sql = "INSERT INTO characters (user_id, char_name, talent_type, bonus_decimal) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, character.getUserId());
            pstmt.setString(2, character.getCharName());
            pstmt.setString(3, character.getTalentType());
            pstmt.setBigDecimal(4, character.getBonusDecimal());
            pstmt.executeUpdate();
        }
    }

    // 2. 透過角色 ID 查找 (供資料庫關聯、Controller 計算使用)
    public Character findById(int id) throws SQLException {
        String sql = "SELECT * FROM characters WHERE id = ?";
        return executeQuery(sql, id);
    }

    // 3. 透過 User ID 查找 (供登入流程、Main 選單使用)

    public Character findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM characters WHERE user_id = ?";
        return executeQuery(sql, userId);
    }

    /**
     * 內部工具方法：減少重複的 ResultSet 賦值邏輯 (Refactoring)
     */
    private Character executeQuery(String sql, int paramId) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paramId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Character character = new Character();
                    character.setId(rs.getInt("id"));
                    character.setUserId(rs.getInt("user_id"));
                    character.setCharName(rs.getString("char_name"));
                    character.setTalentType(rs.getString("talent_type"));
                    character.setBonusDecimal(rs.getBigDecimal("bonus_decimal"));
                    return character;
                }
            }
        }
        return null;
    }
}