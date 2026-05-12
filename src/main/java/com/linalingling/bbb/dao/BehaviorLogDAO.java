package com.linalingling.bbb.dao;

import com.linalingling.bbb.entity.BehaviorLog;
import com.linalingling.bbb.util.DBUtil;
import java.sql.*;

public class BehaviorLogDAO {

    /**
     * 儲存行為紀錄
     * 實踐將計算後的點數 (calculated_points) 永久存檔
     */
    public void insert(BehaviorLog log) throws SQLException {
        String sql = "INSERT INTO behavior_logs (goal_id, action, note, base_value, calculated_points) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, log.getGoalId());
            pstmt.setString(2, log.getAction());
            pstmt.setString(3, log.getNote());
            pstmt.setBigDecimal(4, log.getBaseValue());
            pstmt.setBigDecimal(5, log.getCalculatedPoints());

            pstmt.executeUpdate();
            System.out.println("💾 行為數據已成功同步至資料庫。");
        } catch (SQLException e) {
            System.err.println("❌ 寫入行為紀錄失敗：" + e.getMessage());
            throw e;
        }
    }
}