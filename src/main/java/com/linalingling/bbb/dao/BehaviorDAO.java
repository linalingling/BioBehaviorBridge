package com.linalingling.bbb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

public class BehaviorDAO {
    private Connection connection;

    public BehaviorDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveLog(int goalId, String action, int note, BigDecimal points) throws SQLException {
        String sql = "INSERT INTO public.behavior_logs (goal_id, action, note, calculated_points) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, goalId);
            pstmt.setString(2, action);
            pstmt.setInt(3, note);
            pstmt.setBigDecimal(4, points);
            pstmt.executeUpdate();
        }
    }
}