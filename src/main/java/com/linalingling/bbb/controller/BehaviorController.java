package com.linalingling.bbb.controller;

import com.linalingling.bbb.dao.BehaviorLogDAO;
import com.linalingling.bbb.dao.CharacterDAO;
import com.linalingling.bbb.dao.GoalDAO;
import com.linalingling.bbb.entity.BehaviorLog;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.entity.Goal;
import com.linalingling.bbb.service.BehaviorService;
import java.math.BigDecimal;
import java.sql.SQLException; // 記得補上這個 import

public class BehaviorController {

    // 💡 邏輯修正：必須先 new 出這些「搬運工」實例
    private GoalDAO goalDAO = new GoalDAO();
    private CharacterDAO characterDAO = new CharacterDAO();
    private BehaviorService behaviorService = new BehaviorService();
    private BehaviorLogDAO behaviorLogDAO = new BehaviorLogDAO();

    public void logActivity(int charId, int goalId, String action, BigDecimal baseValue) {
        try {
            // ✅ 修正：使用小寫的變數名 (實例) 來呼叫方法
            Goal goal = goalDAO.findById(goalId);
            Character character = characterDAO.findById(charId);

            if (goal != null && character != null) {
                BehaviorLog log = new BehaviorLog();
                log.setGoalId(goalId);
                log.setAction(action);
                log.setBaseValue(baseValue);

                // ✅ 修正：同樣使用實例呼叫計算邏輯
                BigDecimal finalPoints = behaviorService.calculatePoints(character, goal, log);
                log.setCalculatedPoints(finalPoints);

                // ✅ 修正：使用實例進行存檔
                behaviorLogDAO.insert(log);
                System.out.println("✅ 點數計算完成，已存入資料庫。");
            } else {
                System.out.println("❌ 找不到對應的角色或目標，請檢查 ID。");
            }
        } catch (SQLException e) {
            System.err.println("❌ 資料庫操作失敗: " + e.getMessage());
            e.printStackTrace();
        }
    }
}