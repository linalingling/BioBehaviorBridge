package com.linalingling.bbb.service;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.dao.CharacterDAO;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BehaviorService {
    private CharacterDAO characterDAO;

    public BehaviorService(CharacterDAO characterDAO) {
        this.characterDAO = characterDAO;
    }

    public BigDecimal calaulateGrowth (int baseValue, int characterId) throws SQLException{
        //1.先用characterDAO透過id找到Character物件
        Character character = characterDAO.findById(characterId);

        //2.從物件中取出我定義的1.15(bonus decimal)
        BigDecimal bonus = character.getBonusDecimal();
        BigDecimal base = new BigDecimal(baseValue);
        BigDecimal result = bonus.multiply(base);
        return result;
    }






}
