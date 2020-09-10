package com.twu;

import java.util.Scanner;

public class AdminUser extends User {

    String password;

    AdminUser(HotSearchList hotSearchList, String password) {
        super(hotSearchList);
        this.password = password;
    }

    public void addSuperEvent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input event title:");
        String title = scan.nextLine().trim().toLowerCase();
        if (hotSearchList.addSuper(new HotSearchEvent(title))) {
            System.out.println("Success, add event " + title + " as super.");
        } else {
            System.out.println("Failed, event " + title + " already exists.");
        }
    }
}
