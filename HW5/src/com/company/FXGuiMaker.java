package com.company;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *This is the FXGuiMaker class which takes in a text file, generates a
 * FXComponentTree and provides an interface for the user to manipulate the
 * tree.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class FXGuiMaker extends FXComponentTree{
    public static void main(String[] args) throws IOException, MaximumChildrenException, ElementNotFoundException, NotAllowedException, ArrayHoleException {
        Scanner input = new Scanner(System.in);
        FXComponentTree tree = new FXComponentTree(); //Tree generated
        String userResp = "";
        ComponentType type;
        String text;
        int index;
        FXTreeNode node;

        System.out.println("Welcome to counterfeit SceneBuilder.");

        /** Prompts the user for a menu command selecting the operation until
         * the user quits the program
         */
        do {
            printMenu();
            System.out.print("Please select an option: ");
            char selection = input.next().toUpperCase().charAt(0);

            switch(selection) {
                case 'L':
                    System.out.print("Please enter filename: ");
                    String txtFile = input.next();
                    try {
                        tree.readFromFile(txtFile);
                        if (tree.getRoot().getChildrenNode(0) != null) {
                            System.out.println(txtFile + " loaded.");
                        } else {
                            System.out.println(txtFile + " not found.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(txtFile + " not found.");
                    }
                    break;
                case 'P':
                    if (tree.getRoot().getChildrenNode(0) != null)
                        tree.treeToString();
                    else {
                        System.out.println("There is currently no tree to " +
                                "print");
                    }
                    break;
                case 'C':
                    System.out.println("Please enter number of child " +
                            "(starting with 1): ");
                    try {
                        tree.cursorToChild(input.nextInt() - 1);
                        System.out.println("Cursor Moved to " + tree.getCursor().toString());
                    } catch (IllegalArgumentException | NotAllowedException | InputMismatchException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'R':
                    tree.cursorToRoot();
                    System.out.println("Cursor is at root.");
                    break;
                case 'A': //Fix
                    try {
                        if(tree.getCursor() == tree.getRoot()){
                            System.out.println("Cannot add children to root.");
                            break;
                        }
                        if (tree.getCursor().isContainer()) {
                            System.out.println("Select component type (HBox, VBox, TextArea, " +
                                    "Button, Label): ");
                            userResp = input.next();
                            if (!ComponentType.isValid(ComponentType.setType(userResp))) {
                                System.out.println(userResp + " is not a valid " +
                                        "component type");
                                break;
                            } else {
                                type = ComponentType.valueOf(ComponentType.setType(userResp));
                                if (!ComponentType.isContainer(type.toString())) {
                                    System.out.println("Please enter text: ");
                                    input.nextLine();
                                    text = input.nextLine();
                                    System.out.println("Please enter an index: ");
                                    index = input.nextInt();
                                    node = new FXTreeNode(text, type);
                                } else {
                                    System.out.println("Please enter an index: ");
                                    index = input.nextInt();
                                    node = new FXTreeNode(type);
                                }
                                node.setParent(tree.getCursor());
                                tree.addChild(index - 1, node);
                                tree.setCursor(node);
                            }
                        } else {
                            System.out.println("Cannot add a child to this node.");
                        }
                    }catch(ArrayHoleException | InputMismatchException e){
                        e.printStackTrace();
                    }
                    break;
                case 'U':
                    if (tree.getCursor() != tree.getRoot()) {
                        tree.cursorToParent();
                        System.out.println("Cursor moved to " + tree.getCursor().toString());
                    } else {
                        System.out.println("Cursor is at root.");
                    }
                    break;
                case 'E': //Fix
                    try {
                        System.out.println("Please enter new text: ");
                        input.nextLine();
                        String newText = input.nextLine();
                        tree.setTextAtCursor(newText);
                        System.out.println("Text Edited.");
                    } catch(NotAllowedException e){
                        e.printStackTrace();
                    }
                    break;
                case 'D':
                    try {
                        if(tree.getCursor() == tree.getRoot()){
                            System.out.println("Cannot delete child of " +
                                    "root.");
                            break;
                        }
                        if (!tree.getCursor().isContainer()) {
                            System.out.println("Node has no children to " +
                                    "delete");
                                break;
                        } else {
                            System.out.println("Please enter number of child " +
                                    "(starting with 1):");
                            index = input.nextInt();
                            if(tree.getCursor().getChildrenNode(index) == null){
                                System.out.println("There is no children at " +
                                        "this index to delete.");
                                break;
                            }
                            text =
                                    tree.getCursor().getChildrenNode(index-1).toString();
                            tree.deleteChild(index-1);
                            System.out.println(text + " removed.");
                        }
                    }catch(ElementNotFoundException | InputMismatchException e){
                        e.printStackTrace();
                    }
                    break;
                case 'S':
                    try {
                        System.out.println("Please enter a filename: ");
                        txtFile = input.next();
                        tree.writeToFile(tree, txtFile);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 'X':   //Fix
                    break;
                case 'Q':
                    System.out.println("Make like a tree and leave!");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect selection. Try again!\n");
            }
        } while (!input.equals("Q"));
    }

    /**
     * Prints menu
     */
    public static void printMenu() {
        System.out.println("\nMenu:\n" +
                "L) Load from file\n" +
                "P) Print tree\n" +
                "C) Move cursor to a child node\n" +
                "R) Move cursor to root\n" +
                "A) Add a child\n" +
                "U) Cursor up (to parent)\n" +
                "E) Edit text of cursor\n" +
                "D) Delete child\n" +
                "S) Save to file\n" +
                "X) Export FXML \n" +
                "Q) Quit\n");
    }
}
