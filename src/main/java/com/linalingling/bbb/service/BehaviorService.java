package com.linalingling.bbb.service;

import com.linalingling.bbb.entity.BehaviorLog;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.entity.Goal;
import java.math.BigDecimal;

public class BehaviorService {
    /**
     * 核心判定矩陣: 根據目標類型與行為類別計算最終點數
     */
    public BigDecimal calaulatePoint (Character character, Goal goal, BehaviorLog log) {
        BigDecimal baseValue = log.getBaseValue();
        BigDecimal bonus = character.getBonusDecimal();
        String goalType = goal.getTitle();
        String action = log.getAction();

        //邏輯判定
        if (goalType.equals("WEIGHT_LOSS")) {
            if (action.equals("TRAING")) {
                //正向行為: 給予天賦加成
                return baseValue.multiply(bonus);
            } else if (action.equals("SEDENTARY")) {
                //負向行為:判定為扣分(基數呈上負值)
                return baseValue.multiply(new BigDecimal("-1.0"));
            }
        }
        if (goalType.equals("RECOVERY")) {
            if (action.equals("MEDITATION") || action.equals("MEDICAL")) {
                // 恢復目標下的正向行為
                return baseValue.multiply(bonus);
            } else if (action.equals("TRAINING")) {
                // 恢復期過度訓練可能判定為負擔 (扣分)
                return baseValue.multiply(new BigDecimal("-0.5"));
            }
        }
        //若無兔書匹配: 回傳原始數值
        return baseValue;


    }
}

