package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** ___________________________________
 * |          |           |     8      |
 * |          |           | Secret Rm) |
 * |    5     |     6     |_____  _____|
 * |(dinning) |  (Vault)  |     7      |
 * |          |           | (Parlor)   |
 * |_____ ____|___________|_____  _____|
 * |          |           |            |
 * |    3     |     2     |     4      |
 * |(Library) | (Front Rm)| (Kitchen)  |
 * |__________|_____ _____|____________|
 *            |           |
 *            |     1     |
 *            |  (Foyer)  |
 *            |_____ _____|
 */
public class Zork {
    static Map<Integer, String> items = new HashMap<>();
    static Map<Integer, Integer> money = new HashMap<>();
    static Scanner keyboard = new Scanner(System.in);
    static boolean found;

    static int totalMoney;
    static int roomCharacter;

    public static void main(String[] args) {
        welcome();
        process();
        exit();
    }

    public static void welcome() {
        System.out.println(
                " |___________________________________|\n" +
                        " |       WELCOME TO THE ZORK         |\n" +
                        " |        (Follow the map)           |\n" +
                        " |___________________________________|\n" +
                        " |          |           |     8      |\n" +
                        " |          |           | Secret Rm) |\n" +
                        " |     5    |    6      |____________|\n" +
                        " |(dinning) |  (Vault)  |     7      |\n" +
                        " |          |           | (Parlor)   |\n" +
                        " |____  ____|___________|_____  _____|\n" +
                        " |          |           |            |\n" +
                        " |    3     |    2      |     4      |\n" +
                        " |(Library) | (Front Rm)| (Kitchen)  |\n" +
                        " |__________|_____ _____|____________|\n" +
                        "            |           |\n" +
                        "            |     1     |\n" +
                        "            |  (Foyer)  |\n" +
                        "            |_____ _____|");
    }

    public static void process(){
        String answer;
        roomCharacter = getRandom(1, 8);
        char direction = 'q';
        int room = 1;
        do {
            switch (room) { //Room
                case 1:
                    room = foyer(direction);
                    break;
                case 2:
                    room = frontRoom(direction);
                    break;
                case 3:
                    room = library(direction);
                    break;
                case 4:
                    room = kitchen(direction);
                    break;
                case 5:
                    room = diningRoom(direction);
                    break;
                case 6:
                    room = vault(direction);
                    break;
                case 7:
                    room = parlor(direction);
                    break;
                case 8:
                    room = secretRoom(direction);
                    break;
                default:
                    System.out.println("Invalid Room");
                    break;
            }
            System.out.print("Do you want to go another room (y/n): ");
            answer = keyboard.next();
        } while (answer.equals("y"));
    }

    //1
    public static int foyer(char direction) {
        int room = 1;
        System.out.println("\nYou are in Room# 1 foyer \tcontains : dead scorpion");
        items.put(room, "Dead Scorpion");
        money(room);
        System.out.print("You can only go (n/q). Enter Direction: ");
        direction = keyboard.next().charAt(0);

        switch (direction) {
            case 'n':
                room = 2;
                break;
            case 'q':
                exit();
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //2
    public static int frontRoom(char direction) {
        int room = 2;
        System.out.println("\nYou are in Room# 2 front Room \tContains : piano.");
        items.put(room, "Piano");
        money(room);
        System.out.print("You can only go (s/w/e). Enter Direction: ");
        direction = keyboard.next().charAt(0);
        switch (direction) {
            case 's':
                room = 1;
                break;
            case 'w':
                room = 3;
                break;
            case 'e':
                room = 4;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //3
    public static int library(char direction) {
        System.out.println("\nYou are in Room# 3 library \tIt contains : spider.");
        items.put(3, "Spider");
        money(3);
        System.out.print("You can only go (e/n). Enter Direction: ");
        direction = keyboard.next().charAt(0);
        int room = 3;
        switch (direction) {
            case 'e':
                room = 2;
                break;
            case 'n':
                room = 5;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //4
    public static int kitchen(char direction) {
        int room = 4;
        System.out.println("\nYou are in Room# 4 Kitchen \tIt contains : bats");
        items.put(room, "Bats");
        money(room);
        System.out.print("You can only go (w/n). Enter Direction: ");
        direction = keyboard.next().charAt(0);
        switch (direction) {
            case 'w':
                room = 2;
                break;
            case 'n':
                room = 7;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //5
    public static int diningRoom(char direction) {
        int room = 5;
        System.out.println("\nYou are in Room# 5 Dining Room \tIt contains : dust empty box");
        items.put(room, "Dust Empty Box");
        money(room);
        System.out.print("You can only go (s).Enter Direction: ");
        direction = keyboard.next().charAt(0);
        switch (direction) {
            case 's':
                room = 3;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //6
    public static int vault(char direction) {
        int room = 6;
        System.out.println("\nYou are in Room# 6 vault\tIt contains : 3 walking skeletons");
        items.put(room, "3 Walking Skeletons");
        money(room);
        System.out.print("You can only go (e).Enter Direction: ");
        direction = keyboard.next().charAt(0);

        switch (direction) {
            case 'e':
                if (isFound()) {
                    System.out.print("Enter the room to go (7/8): ");
                    int roomSel = keyboard.nextInt();
                    switch (roomSel) {
                        case 7:
                            room = 7;
                            break;
                        case 8:
                            room = 8;
                            break;
                        default:
                            System.out.println("Invalid direction '" + direction + "' in room "+ room);
                            break;
                    }
                } else {
                    int random = getRandom(1, 100);
                    if (random > 25) {
                        room = 7;
                    } else {
                        room = 8;
                        found = true;
                    }
                }
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //7
    public static int parlor(char direction) {
        int room = 7;
        System.out.println("\nYou are in Room# 7 parlor\tContains : treasure chest");
        items.put(room, "Treasure Chest");
        money(room);
        System.out.print("You can only go (w/s). Enter Direction: ");
        direction = keyboard.next().charAt(0);
        switch (direction) {
            case 'w':
                room = 6;
                break;
            case 's':
                room = 4;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    //8
    public static int secretRoom(char direction) {
        int room = 8;
        System.out.println("\nHOORAH!!! You have found SECRET Room !!!");
        System.out.println("You are in Room# 8 Secret \tContains : piles of gold");
        items.put(room, "Piles of Gold");
        money(room);
        System.out.print("You can only go (w). Enter Direction: ");
        direction = keyboard.next().charAt(0);

        switch (direction) {
            case 'w':
                room = 6;
                break;
            default:
                System.out.println("Invalid direction '" + direction + "' in room "+ room);
        }
        return room;
    }

    public static boolean isFound() {
        return found;
    }

    public static int getRandom(int min, int max) {
        return min + ((int) (Math.random() * max));
    }

    public static void money(int room) {
        if (!money.containsKey(room)) {
            int random = getRandom(1, 1000);
            System.out.print("You found " + "$" + random + " in the room! Would you like to take it? (y/n): ");
            char answer = keyboard.next().charAt(0);
            if (answer == 'y') {
                money.put(room, random);
                totalMoney += random;
                //System.out.println("You now have $" + totalMoney);
            } else {
                System.out.println("You did not take the money.");
            }
            //robbery
            robbery(room);
        } else {
            System.out.println("You have already taken the money");
        }
    }

    public static void robbery(int room) {
        if (room == roomCharacter) {
            System.out.println("A robber has appeared in the room! He has taken all of your money!");
            totalMoney = 0;//money.clear();
            System.out.println("You now have $" + totalMoney);
            roomCharacter = 9;
        }
    }

    public static void exit() {
        int random = getRandom(1, 100);
        if (random <= 25) {
            System.out.println("You are followed by ghost");
        }
        System.out.println("You have visited: " + items.size() + " room(s)");
        System.out.println("You have $" + totalMoney);
        System.out.println("You have seen the following items: " + items);
        System.exit(0);
    }
}
