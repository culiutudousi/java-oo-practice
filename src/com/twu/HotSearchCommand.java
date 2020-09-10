package com.twu;

import java.util.*;

public class HotSearchCommand {

    public HotSearchList hotSearchList;
    public AdminUser adminUser;
    public Map<String, NormalUser> normalUsers;

    public HotSearchCommand() {
        hotSearchList = new HotSearchList();
        adminUser = new AdminUser(hotSearchList);
        normalUsers = new HashMap<String, NormalUser>();
    }

    public void open() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("There is no user logged in,");
            System.out.println("\tinput LOGIN to login,");
            System.out.println("\tor input Q to quit.");
            String command = scan.nextLine().trim().toLowerCase();
            if (command.equals("q")) {
                break;
            } else if (command.equals("login")) {
                User currentUser = login();
                if (currentUser instanceof AdminUser) {
                    adminUserActivity((AdminUser) currentUser);
                } else {
                    normalUserActivity((NormalUser) currentUser);
                }
            }
        }
    }

    private User login() {
        Scanner scan = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Please input a username to login,");
            System.out.println("\tor CREATE to create a new normal user,");
            System.out.println("\tor SHOWALL to list all users");
            command = scan.nextLine().trim().toLowerCase();
            switch (command) {
                case "create":
                    while (true) {
                        System.out.println("Please input a new username:");
                        command = scan.nextLine().trim().toLowerCase();
                        if (!normalUsers.containsKey(command)) {
                            normalUsers.put(command, new NormalUser(hotSearchList));
                            System.out.println("Create a new user: " + command);
                            break;
                        } else {
                            System.out.println("[ERROR] Username of " + command + "exists, please type a new one");
                        }
                    }
                    break;
                case "showall":
                    System.out.println("Here are all users:");
                    System.out.println("\tadmin");
                    for (String name : normalUsers.keySet()) {
                        System.out.println("\t" + name);
                    }
                    break;
                case "admin":
                    System.out.println("You have logged in with admin");
                    return adminUser;
                default:
                    if (normalUsers.containsKey(command)) {
                        System.out.println("You have logged in with " + command);
                        return normalUsers.get(command);
                    } else {
                        System.out.println("[ERROR] Username of " + command + "does NOT exist, please try again");
                    }
                    break;
            }
        }
    }

    private void adminUserActivity(AdminUser user) {
        Scanner scan = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Please input number to choose activity, or Q to quit:");
            System.out.println("\t1. Watch all hot search events");
            System.out.println("\t2. Add new hot search event");
            System.out.println("\t3. Set a hot search event as super one");
            command = scan.nextLine().trim().toLowerCase();
            switch (command) {
                case "q":
                    return;
                case "1":
                    user.showHotSearchList();
                    break;
                case "2":
                    user.addEventToHotSearchList();
                    break;
                case "3":
                    user.setEventAsSuper();
                    break;
                default:
                    break;
            }
        }
    }

    private void normalUserActivity(NormalUser user) {
        Scanner scan = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Please input number to choose activity, or Q to quit:");
            System.out.println("\t1. Watch all hot search events");
            System.out.println("\t2. Add new hot search event");
            System.out.println("\t3. Vote to an event");
            System.out.println("\t4. Buy a position in rank list for an event");
            command = scan.nextLine().trim().toLowerCase();
            switch (command) {
                case "q":
                    return;
                case "1":
                    user.showHotSearchList();
                    break;
                case "2":
                    user.addEventToHotSearchList();
                    break;
                case "3":
                    user.vote();
                    break;
                case "4":
                    System.out.println("not finished yet");
                default:
                    break;
            }
        }
    }
}
