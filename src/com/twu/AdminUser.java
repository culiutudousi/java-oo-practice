package com.twu;

import java.util.Scanner;

public class AdminUser extends User {

    AdminUser(HotSearchList hotSearchList) {
        super(hotSearchList);
    }

    public void setEventAsSuper() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input event title that wants to be super:");
        String title = scan.nextLine().trim().toLowerCase();
        if (hotSearchList.setEventAsSuper(title)) {
            System.out.println("Set event " + title + " as super successfully.");
        } else {
            System.out.println("Set event " + title + " as super failed.");
        }
    }
}
