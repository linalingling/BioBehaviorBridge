package com.linalingling.bbb.dao;


import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CharacterDAO {


    public void insert(Character character) throws SQLException {
        // 這裡我們只填入必要的欄位，讓 ID 自動遞增
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
}