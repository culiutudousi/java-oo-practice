package com.twu;

import java.util.List;
import java.util.Scanner;

public abstract class User {

    public HotSearchList hotSearchList;

    User(HotSearchList hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    public void showHotSearchList() {
        hotSearchList.showAll();
    };

    public void addEventToHotSearchList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input new event title:");
        String title = scan.nextLine().trim().toLowerCase();
        System.out.println("Please input new event description:");
        String description = scan.nextLine().trim().toLowerCase();
        HotSearchEvent event = new HotSearchEvent(title, description);
        if (hotSearchList.add(event)) {
            System.out.println("Add new event successfully.");
        } else {
            System.out.println("Add new event failed.");
        }
    }
}
