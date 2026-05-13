package com.linalingling.bbb;

import com.linalingling.bbb.dao.CharacterDAO;
import com.linalingling.bbb.dao.UserDAO;
import com.linalingling.bbb.entity.Character;
import com.linalingling.bbb.entity.User;
import com.linalingling.bbb.view.LoginView;
import com.linalingling.bbb.view.RegisterView;
import com.linalingling.bbb.controller.BehaviorController;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        com.linalingling.bbb.controller.BehaviorController controller = new com.linalingling.bbb.controller.BehaviorController();
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        CharacterDAO charDAO = new CharacterDAO();
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();

        loginView.showWelcome();

        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n請選擇操作: [1] 登入  [2] 註冊  [0] 離開");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // --- 執行登入邏輯 ---
                String[] credentials = loginView.getLoginInput();
                loggedInUser = userDAO.login(credentials[0], credentials[1]);
                if (loggedInUser != null) {
                    loginView.showLoginSuccess(loggedInUser.getUsername());
                } else {
                    loginView.showLoginFailed();
                }

            } else if (choice.equals("2")) {
                // --- 執行註冊邏輯 (User + Character 同步) ---
                try {
                    String[] regInfo = registerView.getRegisterInput();
                    // 1. 先建立 User 並取得自動生成的 ID
                    int newUserId = userDAO.register(regInfo[0], regInfo[1]);

                    if (newUserId != -1) {
                        // 2. 緊接著建立 Character
                        Character newChar = new Character();
                        newChar.setUserId(newUserId);
                        newChar.setCharName(regInfo[2]);
                        newChar.setTalentType(regInfo[3]);
                        newChar.setBonusDecimal(new BigDecimal("1.15")); // 預設加成

                        charDAO.insert(newChar);
                        registerView.showSuccess();
                    }
                } catch (Exception e) {
                    System.err.println("註冊過程中發生錯誤: " + e.getMessage());
                }

            } else if (choice.equals("0")) {
                System.out.println("系統關閉中，期待下次見面！");
                return;
            } else {
                System.out.println("❌ 無效的選項，請重新選擇。");
            }
        }

        // 登入成功後：獲取角色 ID 以便後續行為綁定
        int charId = userDAO.getCharacterIdByUserId(loggedInUser.getId());
        System.out.println("當前載入角色 ID: " + charId);


        // --- 登入後的行為錄入選單 ---
        while (true) {
            System.out.println("\n--- [角色行動選單] ---");
            System.out.println("1. 紀錄行為 (如: TRAINING, SEDENTARY)");
            System.out.println("0. 登出系統");
            System.out.print("請選擇: ");
            String subChoice = scanner.nextLine();

            if (subChoice.equals("1")) {
                // 💡 實戰邏輯：目前假設 goalId = 1 (請確保 DataGrip 裡有這筆 Goal)
                int goalId = 1;

                System.out.print("輸入行為名稱: ");
                String action = scanner.nextLine();

                System.out.print("輸入原始數值 請只輸入純數字，如: 80): ");

                String userInput = "";
                do {
                    userInput = scanner.nextLine().trim();
                } while (userInput.isEmpty());
                String cleanInput = userInput.replaceAll("[^0-9.]", "");
                if (cleanInput.isEmpty()) {
                    cleanInput = "0";
                }


                BigDecimal baseValue = new BigDecimal(cleanInput);

                // 執行計算與存檔
                controller.logActivity(charId, goalId, action, baseValue);

            } else if (subChoice.equals("0")) {
                System.out.println("正在登出角色...");
                break;
            } else {
                System.out.println("❌ 無效選項。");
            }
        }
    }
}