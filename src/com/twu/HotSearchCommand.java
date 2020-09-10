package com.twu;

import java.util.*;

public class HotSearchCommand {

    public HotSearchList hotSearchList;
    public Map<String, AdminUser> adminUsers;
    public Map<String, NormalUser> normalUsers;

    public HotSearchCommand() {
        hotSearchList = new HotSearchList();
        adminUsers = new HashMap<String, AdminUser>();
        adminUsers.put("admin", new AdminUser(hotSearchList, "123"));
        normalUsers = new HashMap<String, NormalUser>();
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to hot search rank list system, you are?");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Quit");
            String command = scan.nextLine().trim().toLowerCase();
            switch (command) {
                case "1":
                    NormalUser normalUser = loginNormalUser();
                    normalUserActivity(normalUser);
                    break;
                case "2":
                    AdminUser adminUser = loginAdminUser();
                    if (adminUser != null) {
                        adminUserActivity(adminUser);
                    }
                    break;
                case "3":
                    return;
                default:
                    break;
            }
        }
    }

    private NormalUser loginNormalUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Username:");
        String username = scan.nextLine().trim().toLowerCase();
        if (normalUsers.containsKey(username)) {
            return normalUsers.get(username);
        }
        NormalUser user = new NormalUser(hotSearchList);
        normalUsers.put(username, user);
        return user;
    }

    private AdminUser loginAdminUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Admin name:");
        String username = scan.nextLine().trim().toLowerCase();
        if (adminUsers.containsKey(username)) {
            System.out.println("Admin password:");
            String password = scan.nextLine().trim().toLowerCase();
            AdminUser user = adminUsers.get(username);
            if (user.password.equals(password)) {
                return user;
            }
            System.out.println("Failed, admin password does not match.");
            return null;
        }
        System.out.println("Failed, admin name does not exists.");
        return null;
    }

//    private User login() {
//        Scanner scan = new Scanner(System.in);
//        String command;
//        while (true) {
//            System.out.println("Please input a username to login,");
//            System.out.println("\tor CREATE to create a new normal user,");
//            System.out.println("\tor SHOWALL to list all users");
//            command = scan.nextLine().trim().toLowerCase();
//            switch (command) {
//                case "create":
//                    while (true) {
//                        System.out.println("Please input a new username:");
//                        command = scan.nextLine().trim().toLowerCase();
//                        if (!normalUsers.containsKey(command)) {
//                            normalUsers.put(command, new NormalUser(hotSearchList));
//                            System.out.println("Create a new user: " + command);
//                            break;
//                        } else {
//                            System.out.println("[ERROR] Username of " + command + "exists, please type a new one");
//                        }
//                    }
//                    break;
//                case "showall":
//                    System.out.println("Here are all users:");
//                    System.out.println("\tadmin");
//                    for (String name : normalUsers.keySet()) {
//                        System.out.println("\t" + name);
//                    }
//                    break;
//                case "admin":
//                    System.out.println("You have logged in with admin");
//                    return adminUser;
//                default:
//                    if (normalUsers.containsKey(command)) {
//                        System.out.println("You have logged in with " + command);
//                        return normalUsers.get(command);
//                    } else {
//                        System.out.println("[ERROR] Username of " + command + "does NOT exist, please try again");
//                    }
//                    break;
//            }
//        }
//    }

    private void adminUserActivity(AdminUser user) {
        Scanner scan = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Hi, you can:");
            System.out.println("1. Watch all hot search events");
            System.out.println("2. Add new hot search event");
            System.out.println("3. Add a super hot search event");
            System.out.println("4. Quit");
            command = scan.nextLine().trim().toLowerCase();
            switch (command) {
                case "1":
                    user.showHotSearchList();
                    break;
                case "2":
                    user.addEventToHotSearchList();
                    break;
                case "3":
                    user.addSuperEvent();
                    break;
                case "4":
                    return;
                default:
                    break;
            }
        }
    }

    private void normalUserActivity(NormalUser user) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Hi, you can:");
            System.out.println("1. Watch all hot search events");
            System.out.println("2. Add new hot search event");
            System.out.println("3. Vote to an event");
            System.out.println("4. Buy a position in rank list for an event");
            System.out.println("5. Quit");
            String command = scan.nextLine().trim().toLowerCase();
            switch (command) {
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
                    user.buyForAnEvent();
                    break;
                case "5":
                    return;
                default:
                    break;
            }
        }
    }
}
