package com.company;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the MailroomManager class which contains the six package stacks
 * for storing mail. Packages whose recipient’s name begins with A-G will be
 * stored in the first stack, H-J in second, K-M in the third, N-R in the fourth,
 * and S-Z in the fifth. The stacks[0] stack will serve as the Floor stack,
 * which is used when moving packages of a specific recipient. This allows
 * the user to create the six instances of the PackageStack class and go
 * through a whole menu of operations.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #3
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class MailroomManager{
    public static void main(String[] args) throws
            FullStackException, EmptyStackException {
        int day = 0; //Current day
        int currStack = 0; //Which stack we are currently going through
        Scanner sc = new Scanner(System.in);
        String userResp = "";
        PackageStack[] stacks = new PackageStack[6];
        //These are the six instances of the PackageStack class
        stacks[0] = new PackageStack();
        stacks[1] = new PackageStack();
        stacks[2] = new PackageStack();
        stacks[3] = new PackageStack();
        stacks[4] = new PackageStack();
        stacks[5] = new PackageStack();

        System.out.println("Welcome to the Irving Mailroom Manager. You can try "+
                "to make it better, but the odds are stacked against you. It is "+
                "day " + day + ".");

        /* Prompts the user for a menu command selecting the operation until
         * the user quits the program
         **/
        do{
            menu();
            userResp = sc.nextLine().toLowerCase();
            switch(userResp){
                case "d":
                    boolean d = false;
                    try {
                        System.out.print("Please enter the recipient name: ");
                        String recipient = sc.next();
                        System.out.print("Please enter the weight (lbs): ");
                        double weight = sc.nextDouble();
                        Package p = new Package(recipient, day, weight);
                        System.out.println("\nA " + weight + " lb package is " +
                                "awaiting pickup by " + recipient + ".");
                        char i = recipient.charAt(0);
                        if ((i >= 'a' && i <= 'g') || (i >= 'A' && i <= 'G')) {
                            currStack = 1;
                        }else if ((i >= 'h' && i <= 'j') || (i >= 'H' && i <= 'J')) {
                            currStack = 2;
                        }else if ((i >= 'k' && i <= 'm') || (i >= 'K' && i <= 'M')) {
                            currStack = 3;
                        }else if ((i >= 'n' && i <= 'r') || (i >= 'N' && i <= 'R')) {
                            currStack = 4;
                        }else if ((i >= 's' && i <= 'z') || (i >= 'S' && i <= 'Z')) {
                            currStack = 5;
                        }
                        if (!stacks[currStack].isFull()) {
                            stacks[currStack].push(p);
                        }else {
                            int next = 0;
                            while(!d) {
                                next++;
                                if (currStack-next > 0 && currStack-next < 6){
                                    if (!stacks[currStack-next].isFull()) {
                                        stacks[currStack-next].push(p);
                                        d = true;
                                        System.out.print("\nAs stack " + currStack + " was " +
                                                "full, it was placed" +
                                                " in stack " +
                                                (currStack - next) + ".");
                                    }
                                }else if (currStack+next > 0 && currStack+next < 6) {
                                    if (!stacks[currStack+next].isFull()) {
                                        stacks[currStack+next].push(p);
                                        d = true;
                                        System.out.print("\nAs stack " + currStack
                                                + " was " + "full, it was placed"
                                                + " in stack " + (currStack + next)
                                                + ".");
                                    }
                                }else{
                                    stacks[0].push(p);
                                    d = true;
                                    System.out.print("\nAs stack " + currStack + " was " +
                                            "full, it was placed" +
                                            " on the floor stack.");
                                }
                            }
                        }
                    }catch (InputMismatchException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    int count = 0;
                    try {
                        System.out.print("Please enter the recipient name: ");
                        String recipient = sc.nextLine().toLowerCase();
                        char i = recipient.charAt(0);
                        if ((i >= 'a' && i <= 'g')) {
                            currStack = 1;
                        }else if ((i >= 'h' && i <= 'j')) {
                            currStack = 2;
                        }else if ((i >= 'k' && i <= 'm')) {
                            currStack = 3;
                        }else if ((i >= 'n' && i <= 'r')) {
                            currStack = 4;
                        }else if ((i >= 's' && i <= 'z')) {
                            currStack = 5;
                        }
                        boolean found = false;
                        boolean unknown = false;
                        while(!found) {
                            if(stacks[currStack].peek() == null){
                                unknown = true;
                                found = true;
                            }else if (stacks[currStack].peek().getRecipient().equalsIgnoreCase(recipient)) {
                                found = true;
                            }else{
                                stacks[0].push(stacks[currStack].pop());
                                count++;
                            }
                        }
                        if (unknown){
                            System.out.println("This recipient doesn't have " +
                                    "any packages at Iriving Mailroom.");
                        }else if (count == 0) {
                            System.out.println("\nGive " + recipient + " " +
                                    stacks[currStack].peek().getWeight() + " lb " +
                                    "package delivered on day " +
                                    stacks[currStack].peek().getArrivalDate());
                            stacks[currStack].pop();
                            System.out.println("\nCurrent Packages:\n" +
                                    "--------------------------------");
                            System.out.println("Stack 1 (A-G):|" + stacks[1].toString());
                            System.out.println("Stack 2 (H-J):|" + stacks[2].toString());
                            System.out.println("Stack 3 (K-M):|" + stacks[3].toString());
                            System.out.println("Stack 4 (N-R):|" + stacks[4].toString());
                            System.out.println("Stack 5 (S-Z):|" + stacks[5].toString());
                            System.out.println("Floor: |" + stacks[0].toString());
                        }else {
                            System.out.println("\nMove " + count + " " +
                                    "packages from " +
                                    "Stack " + currStack + " to floor.");

                            System.out.println("Current Packages:\n" +
                                    "--------------------------------");
                            System.out.println("Stack 1 (A-G):|" + stacks[1].toString());
                            System.out.println("Stack 2 (H-J):|" + stacks[2].toString());
                            System.out.println("Stack 3 (K-M):|" + stacks[3].toString());
                            System.out.println("Stack 4 (N-R):|" + stacks[4].toString());
                            System.out.println("Stack 5 (S-Z):|" + stacks[5].toString());
                            System.out.println("Floor: |" + stacks[0].toString());

                            System.out.println("\nGive " + recipient + " " +
                                    stacks[currStack].peek().getWeight() + " lb " +
                                    "package delivered on day " +
                                    stacks[currStack].peek().getArrivalDate());
                            stacks[currStack].pop();

                            System.out.println("\nReturn " + count + " packages " +
                                    "to " +
                                    "stack " + currStack + " from floor.");

                            while (stacks[0].peek() != null) {
                                stacks[currStack].push(stacks[0].pop());
                            }

                            System.out.println("\nCurrent Packages:\n" +
                                    "--------------------------------");
                            System.out.println("Stack 1 (A-G):|" + stacks[1].toString());
                            System.out.println("Stack 2 (H-J):|" + stacks[2].toString());
                            System.out.println("Stack 3 (K-M):|" + stacks[3].toString());
                            System.out.println("Stack 4 (N-R):|" + stacks[4].toString());
                            System.out.println("Stack 5 (S-Z):|" + stacks[5].toString());
                            System.out.println("Floor: |" + stacks[0].toString());
                        }
                    }catch (InputMismatchException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "t":
                    day++;
                    count = 0;
                    System.out.println("It is now day " + day + ".");
                    PackageStack temp = new PackageStack();
                    for(int i = 0; i < 6; i++){
                        while(stacks[i].peek() != null) {
                            if ((day - stacks[i].peek().getArrivalDate()) == 5) {
                                stacks[i].pop();
                                count++;
                            }else {
                                temp.push(stacks[i].pop());
                            }
                        }
                        try{
                            while(temp.peek() != null){
                                stacks[i].push(temp.pop());
                            }
                        }catch (EmptyStackException e){
                            e.getMessage();
                        }
                    }
                    if (count > 0) System.out.println(count + " packages have" +
                            " been returned to sender.");
                    break;
                case "p":
                    System.out.println("Current Packages:\n" +
                            "--------------------------------");
                    System.out.println("Stack 1 (A-G):|" + stacks[1].toString());
                    System.out.println("Stack 2 (H-J):|" + stacks[2].toString());
                    System.out.println("Stack 3 (K-M):|" + stacks[3].toString());
                    System.out.println("Stack 4 (N-R):|" + stacks[4].toString());
                    System.out.println("Stack 5 (S-Z):|" + stacks[5].toString());
                    System.out.println("Floor: |" + stacks[0].toString());
                    break;
                case "m":
                    System.out.println("Please enter the source stack (enter " +
                            "0 for floor): ");
                    int source = sc.nextInt();
                    System.out.println("Please enter the destination stack: ");
                    int dest = sc.nextInt();
                    if (!stacks[source].isEmpty() && !stacks[dest].isFull()) {
                        stacks[dest].push(stacks[source].pop());
                        System.out.println("Package from stack " + source +
                                " has been moved to stack " + dest + ".");
                    }
                    break;
                case "f":
                    temp = new PackageStack();
                    for (int j = 0; j < 6; j++) {
                        while(stacks[j].peek() != null) {
                            char i = stacks[j].peek().getRecipient().charAt(0);
                            if ((i >= 'a' && i <= 'g')) {
                                currStack = 1;
                            } else if ((i >= 'h' && i <= 'j')) {
                                currStack = 2;
                            } else if ((i >= 'k' && i <= 'm')) {
                                currStack = 3;
                            } else if ((i >= 'n' && i <= 'r')) {
                                currStack = 4;
                            } else if ((i >= 's' && i <= 'z')) {
                                currStack = 5;
                            }

                            if (j != currStack){
                                stacks[0].push(stacks[j].pop());
                            }else{
                                temp.push(stacks[j].pop());
                            }
                        }

                        while (temp.peek() != null){
                            stacks[j].push(temp.pop());
                        }
                    }
                    System.out.println("Misplaced packages moved to floor.");
                    break;
                case "l":
                    count = 0;
                    String exp = "";
                    temp = new PackageStack();
                    System.out.println("Please enter the recipient name: ");
                    String recipient = sc.nextLine();
                    for(int j = 0; j < 6; j++){
                        while(stacks[j].peek() != null) {
                                temp.push(stacks[j].pop());
                        }
                        while(temp.peek() != null){
                            if (temp.peek().getRecipient().equalsIgnoreCase(recipient)) {
                                count++;
                                exp += ("\nPackage " + count + " is in " +
                                        "Stack " + j
                                        + ", it " +
                                        "was delivered on day " +
                                        temp.peek().getArrivalDate() +
                                        ", and weighs " +
                                        temp.peek().getWeight() +
                                        " lbs.");
                                stacks[j].push(temp.pop());
                            }
                        }
                    }
                    System.out.println(recipient + " has " + count + " " +
                        "packages total." + exp);
                    break;
                case "e":
                    stacks[0].clear();
                    System.out.println("The floor has been emptied. Mr. Trash "
                            + "Can is no longer hungry.");
                    break;
                case "q":
                    System.out.println("Use Amazon Locker next time.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("That is an invalid option. Please try" +
                            " again\n");
                    break;
            }
        }while (!userResp.equals("q"));
    }

    public static void menu(){
        System.out.println("\nMenu:\n" +
                "D) Deliver a package\n" +
                "G) Get someone's package\n" +
                "T) Make it tomorrow\n" +
                "P) Print the stacks\n" +
                "M) Move a package from one stack to another\n" +
                "F) Find packages in the wrong stack and move to floor\n" +
                "L) List all packages awaiting a user\n" +
                "E) Empty the floor\n" +
                "Q) Quit\n" +
                "Please select an option: ");
    }
}