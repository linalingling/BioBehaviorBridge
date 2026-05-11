package com.linalingling.bbb.controller;

import com.linalingling.bbb.service.BehaviorService;

import java.math.BigDecimal;

public class BehaviorController {
    private BehaviorService behaviorService;

    public BehaviorController(BehaviorService behaviorService) {
        this.behaviorService = behaviorService;
    }
    public void logActivity(int characerId, int weight){
        try{
            BigDecimal result = behaviorService.calculateGrowth(weight,characerId);
            System.out.println("成功!原始重量"+weight+"kg，經天賦加成後為:" +result);
        } catch (Exception e){
            System.out.println("操作失敗，原因"+ e.getMessage());
        }
    }
}
