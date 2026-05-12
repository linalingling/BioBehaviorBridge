package com.linalingling.bbb.controller;

// 1. 確保所有 Import 都正確
import com.linalingling.bbb.dao.BehaviorLogDAO;
import com.linalingling.bbb.dao.CharacterDAO;
import com.linalingling.bbb.dao.GoalDAO;
import com.linalingling.bbb.entity.BehaviorLog;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.entity.Goal;
import com.linalingling.bbb.service.BehaviorService;
import java.math.BigDecimal;

public class BehaviorController {
    // 2. 成員變數必須宣告在類別層級 (方法外面)，紅字才會消失
    private BehaviorService behaviorService = new BehaviorService();
    private CharacterDAO characterDAO = new CharacterDAO();
    private GoalDAO goalDAO = new GoalDAO();
    private BehaviorLogDAO behaviorLogDAO = new BehaviorLogDAO();

    public void logActivity(int charId, int goalId, String action, BigDecimal baseValue) {
        try {
            // 3. 呼叫 DAO 抓取實體
            Character character = characterDAO.findById(charId);
            Goal goal = goalDAO.findById(goalId);

            if (character == null || goal == null) {
                System.out.println("❌ 找不到對應的角色或目標");
                return;
            }

            // 4. 建立 Log 物件
            BehaviorLog log = new BehaviorLog();
            log.setGoalId(goalId);
            log.setAction(action);
            log.setBaseValue(baseValue);

            // 5. 計算並存檔
            BigDecimal finalPoints = behaviorService.calculatePoints(character, goal, log);
            log.setCalculatedPoints(finalPoints);

            behaviorLogDAO.insert(log);

            System.out.println("✅ 紀錄成功！判定後點數: " + finalPoints);

        } catch (Exception e) {
            System.err.println("❌ 操作失敗: " + e.getMessage());
            e.printStackTrace();
        }
    }
}