package com.linalingling.bbb;

import com.linalingling.bbb.controller.BehaviorController;
import com.linalingling.bbb.dao.CharacterDAO;
import com.linalingling.bbb.service.BehaviorService;

public class Main {
    public static void main(String[] args){

        CharacterDAO dao = new CharacterDAO(null);

        BehaviorService service = new BehaviorService(dao);

        BehaviorController controller = new BehaviorController(service);

        controller.logActivity(1,80);
    }

}



