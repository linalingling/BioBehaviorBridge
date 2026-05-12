package com.linalingling.bbb;

import com.linalingling.bbb.dao.UserDAO;
import com.linalingling.bbb.entity.User;
import com.linalingling.bbb.view.LoginView;

public class Main {
    public static void main(String[] args) {
        // 1. 初始化組件
        LoginView loginView = new LoginView();
        UserDAO userDAO = new UserDAO();

        // 2. 顯示歡迎畫面
        loginView.showWelcome();

        // 3. 執行登入循環 (直到登入成功或程式關閉)
        User loggedInUser = null;
        while (loggedInUser == null) {
            String[] credentials = loginView.getLoginInput();
            String username = credentials[0];
            String password = credentials[1];

            // 4. 調用 DAO 進行資料庫驗證
            loggedInUser = userDAO.login(username, password);

            if (loggedInUser != null) {
                loginView.showLoginSuccess(loggedInUser.getUsername());
                // 這裡之後可以串接「進入角色選單」或「查看目標」的邏輯
            } else {
                loginView.showLoginFailed();
            }
        }

        System.out.println("系統運行中... (目前使用者角色: " + loggedInUser.getRole() + ")");
    }
}