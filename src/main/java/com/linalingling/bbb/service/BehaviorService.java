package com.linalingling.bbb.service;
import com.linalingling.bbb.dao.BehaviorDAO;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.dao.CharacterDAO;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BehaviorService {
    private CharacterDAO characterDAO;
    private BehaviorDAO behaviorDAO;

    public BehaviorService(CharacterDAO characterDAO, BehaviorDAO behaviorDAO) {
        this.characterDAO = characterDAO;
        this.behaviorDAO = behaviorDAO;
    }

    public BigDecimal calculateGrowth(int baseValue, int characterId) throws SQLException{
        //1.先用characterDAO透過id找到Character物件
        Character character = characterDAO.findById(characterId);
        if (character == null){
            throw new RuntimeException("找不到編號為"+characterId+"的角色!");
        }

        //2.從物件中取出我定義的1.15(bonus decimal)
        BigDecimal bonus = character.getBonusDecimal();
        BigDecimal base = new BigDecimal(baseValue);
        BigDecimal result = bonus.multiply(base);

        int actualGoalId = character.getGoalId();

        System.out.println("準備存檔：GoalID=" + actualGoalId + ", Points=" + result);
        behaviorDAO.saveLog(actualGoalId, character.getTalentType(), baseValue, result);
        return result;




    }






}
