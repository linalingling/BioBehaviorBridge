package com.linalingling.bbb.view;

import java.util.Scanner;

public class RegisterView {
    private Scanner scanner = new Scanner(System.in);

    public String[] getRegisterInput() {
        System.out.println("\n--- 建立新帳號 ---");
        System.out.print("請設定帳號: ");
        String username = scanner.nextLine();
        System.out.print("請設定密碼: ");
        String password = scanner.nextLine();
        System.out.print("為妳的角色取個名字 (例如: 黃小鴨): ");
        String charName = scanner.nextLine();

        System.out.println("請選擇初始目標 (1. RECOVERY 2. FOCUS 3. CONTROL): ");
        String choice = scanner.nextLine();
        String talent = choice.equals("1") ? "RECOVERY" : (choice.equals("2") ? "FOCUS" : "CONTROL");

        return new String[]{username, password, charName, talent};
    }

    public void showSuccess() {
        System.out.println("✨ 帳號與角色同步建立成功！請重新登入。");
    }
}