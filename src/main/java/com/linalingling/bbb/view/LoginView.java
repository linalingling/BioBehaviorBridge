package com.linalingling.bbb.view;

import java.util.Scanner;

public class LoginView {
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("====================================");
        System.out.println("    BioBehaviorBridge (BBB)  ");
        System.out.println("      - 讓行為數據化，精確看見微小變化! -      ");
        System.out.println("====================================");
    }

    public String[] getLoginInput() {
        System.out.print("請輸入帳號: ");
        String username = scanner.nextLine();
        System.out.print("請輸入密碼: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    public void showLoginSuccess(String username) {
        System.out.println("\n✅ 登入成功！歡迎回來，" + username + "！");
    }

    public void showLoginFailed() {
        System.out.println("\n❌ 帳號或密碼錯誤，請重新輸入。");
    }
}