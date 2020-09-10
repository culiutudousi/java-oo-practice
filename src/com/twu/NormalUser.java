package com.twu;

import java.util.Scanner;

public class NormalUser extends User {

    public Integer votes = 10;

    NormalUser(HotSearchList hotSearchList) {
        super(hotSearchList);
    }

    public void vote() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the title of event to vote:");
        String title = scan.nextLine().trim().toLowerCase();
        System.out.println("Please input the vote number (you have " + votes.toString() + "):");
        String numberString = scan.nextLine().trim().toLowerCase();
        if (!numberString.matches("^[0-9]+$")) {
            System.out.println("Failed, your input number is not a number");
            return;
        }
        Integer number = Integer.parseInt(numberString);
        if (number < 0 || number > votes) {
            System.out.println("Failed, your input number is out of range");
            return;
        }
        if (!hotSearchList.addHotValue(title, number)) {
            System.out.println("Failed, can not find a event from by your title");
            return;
        }
        votes -= number;
        System.out.println("Success.");
    }
}
