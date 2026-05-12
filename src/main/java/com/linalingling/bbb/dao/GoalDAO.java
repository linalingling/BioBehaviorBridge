package com.linalingling.bbb.dao;

import com.linalingling.bbb.entity.Goal;
import com.linalingling.bbb.util.DBUtil;
import java.sql.*;

public class GoalDAO {
    public Goal findById(int id) throws SQLException {
        String sql = "SELECT * FROM goals WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Goal goal = new Goal();
                    goal.setId(rs.getInt("id"));
                    goal.setCharId(rs.getInt("char_id"));
                    goal.setTitle(rs.getString("title"));
                    goal.setActive(rs.getBoolean("is_active"));
                    return goal;
                }
            }
        }
        return null;
    }
}