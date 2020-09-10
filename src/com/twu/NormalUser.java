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

    public void buyForAnEvent() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the title of event to buy:");
        String title = scan.nextLine().trim().toLowerCase();

        System.out.println("Please input the position:");
        String positionString = scan.nextLine().trim().toLowerCase();
        if (!positionString.matches("^[0-9]+$")) {
            System.out.println("Failed, your input number is not a number");
            return;
        }
        Integer position = Integer.parseInt(positionString);

        System.out.println("Please input the price:");
        String priceString = scan.nextLine().trim().toLowerCase();
        if (!priceString.matches("^[0-9]+$")) {
            System.out.println("Failed, your input number is not a number");
            return;
        }
        Integer price = Integer.parseInt(priceString);

        if (!hotSearchList.buyPositionForHotEvent(title, position, price)) {
            System.out.println("Failed to buy.");
        }
        System.out.println(String.format("Success to buy %s at %d by %d.", title, position, price));
    }
}
