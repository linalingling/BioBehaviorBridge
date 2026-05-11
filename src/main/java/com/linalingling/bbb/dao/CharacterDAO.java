package com.linalingling.bbb.dao;
import com.linalingling.bbb.entity.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterDAO {
    private Connection connection;

    public CharacterDAO(Connection connection) {
        this.connection = connection;
    }
    public Character findById(int id) throws SQLException {
        //SQL
        String sql = "SELECT * FROM characters WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            Character character = new Character();
            character.setId(rs.getInt("id"));
            character.setCharName(rs.getString("char_name"));
            character.setGoalId(rs.getInt("goal_id"));
            character.setBonusDecimal(rs.getBigDecimal("bonus_decimal"));
            character.setTalentType(rs.getString("talent_type"));
            return character;
        }
        return null; //暫存
    }
    public int insert(Character character) throws SQLException {
        String sql = "INSERT INTO characters (char_name, bonus_decimal, talent_type) VALUES(?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,character.getCharName());
        pstmt.setBigDecimal(2,character.getBonusDecimal());
        pstmt.setString(3,character.getTalentType());

        return pstmt.executeUpdate();

        // 之後寫insert into

    }
}

