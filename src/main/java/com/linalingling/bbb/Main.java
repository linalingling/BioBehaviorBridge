package com.linalingling.bbb;

import com.linalingling.bbb.controller.BehaviorController;
import com.linalingling.bbb.dao.BehaviorDAO;
import com.linalingling.bbb.dao.CharacterDAO;
import com.linalingling.bbb.service.BehaviorService;
import com.linalingling.bbb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn;
        try {
            conn = DBUtil.getConnection();


        CharacterDAO charDao = new CharacterDAO(conn);
        BehaviorDAO behaviorDAO = new BehaviorDAO(conn);

        BehaviorService service = new BehaviorService(charDao,behaviorDAO);

        BehaviorController controller = new BehaviorController(service);

        controller.logActivity(5, 80);
        } catch (SQLException e) {
           System.out.println("啟動失敗,請檢查Docker是否執行中");
           e.printStackTrace();
        }
    }

}



