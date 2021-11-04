package com.company;
import java.io.*;
/**
 *This is the FXComponentTree class which represents the tree manager for the
 *  tree full of FXTreeNodes
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class FXComponentTree {
    //Member Variables
    private FXTreeNode root;
    private FXTreeNode cursor;

    /**
     * This is the default Constructor used to create a new FXComponentTree
     * object
     */
    public FXComponentTree(){
        root = new FXTreeNode(ComponentType.AnchorPane);
        cursor = root;
    }

    /**
     * This is a setter method
     *
     * @param root
     *      the root to set for this tree
     *
     * @return setRoot
     *      sets the root of the FXComponent tree to FXTreeNode root
     */
    public void setRoot(FXTreeNode root){ this.root = root; }

    /**
     * This is a setter method
     *
     * @param cursor
     *      the node to set the cursor to
     *
     * @return setCursor
     *      sets the cursor of the FXComponent tree to FXTreeNode cursor
     */
    public void setCursor(FXTreeNode cursor){
        this.cursor = cursor;
    }

    /**
     * This is a getter method
     *
     * @return getRoot
     *      gets the root of the FXComponentTree
     */
    public FXTreeNode getRoot(){
        return root;
    }

    /**
     * This is a getter method
     *
     * @return getCursor
     *      gets the cursor of the FXComponentTree
     */
    public FXTreeNode getCursor(){
        return cursor;
    }

    /**
     * This is a method that returns the cursor to the root of the
     * FXComponentTree
     */
    public void cursorToRoot() {
        cursor = root;
    }

    /**
     * This is a method to remove the child at the specified index of the
     * FXComponentTree as well as all its children
     *
     * @throws ElementNotFoundException
     *      if there is no child at the index
     */
    public void deleteChild(int index) throws ElementNotFoundException,
            NotAllowedException, MaximumChildrenException {
        FXTreeNode parent;
        boolean found = false;
        if(cursor.getChildrenNode(index) != null) {
            found = true;
            setCursor(cursor.getChildrenNode(index));
            if(cursor.isContainer()) {
                cursor.setChildren(null);
            }
            cursorToParent();
            cursor.setChildrenNode(index, null);
            if (cursor.getChildrenNode(index+1) != null) {
                for (int j = index; j < cursor.getChildren().length - 2; j++) {
                    cursor.setChildrenNode(j,
                            cursor.getChildrenNode(j + 1));
                }
            }
        }
        if (!found){
            throw new ElementNotFoundException("There is no element at this " +
                    "index.");
        }
    }

    /**
     * This is a method to add the child node at the specified index of the
     * FXComponentTree
     *
     * @throws IllegalArgumentException
     *      if the node input is null
     *
     * @throws MaximumChildrenException
     *      if the index is greater than the children array length or there
     *      is a maximum amount of chilren in the children array
     *
     * @throws ArrayHoleException
     *      if adding the node at the specified index makes a hole in the array
     *
     */
    public void addChild(int index, FXTreeNode node) throws MaximumChildrenException,
            ArrayHoleException, NotAllowedException {
        if (node == null) {
            throw new IllegalArgumentException("This node is null.");
        }
        if (index > 9) {
            throw new MaximumChildrenException("Index greater than allowed " +
                    "number of children.");
        }
        if (cursor == null) {
            throw new IllegalArgumentException("There is no tree.");
        } if (cursor.getChildrenNode(9) != null){
            throw new MaximumChildrenException("Max amount of children for " +
                    "this node.");
        }else{
            int i = 9;
            int count = 0;
            while (i > -1) {
                if(cursor.getChildrenNode(i) != null){
                   count++;
                }
                if (count == 10) {
                    throw new MaximumChildrenException("Maximum amount of " +
                            "children for this node");
                }
                i--;
            }
            if (cursor.getChildrenNode(index) != null) {
                node.setParent(cursor);
                for (i = cursor.getChildren().length - 2; i >= 0; i--) {
                    if (i == index) {
                        cursor.setChildrenNode(i + 1, cursor.getChildrenNode(i));
                        cursor.setChildrenNode(index, node);
                        i = -1;
                    } else {
                        cursor.setChildrenNode(i + 1, cursor.getChildrenNode(i));
                    }
                    if(i == 0){
                        cursor.setChildrenNode(0, node);
                    }
                }
            } else {
                if(index == 0){
                    cursor.setChildrenNode(index, node);
                }else {
                    for (int j = index - 1; j >= 0; j--) {
                        if (cursor.getChildrenNode(j) == null) {
                            throw new ArrayHoleException("Creates hole in " +
                                    "array.");
                        }
                    }
                    node.setParent(cursor);
                    cursor.setChildrenNode(index, node);
                }
            }
        }
    }

    /**
     * This is a setter method
     *
     * @param text
     *      the text to set at the cursor
     *
     * @return setTextAtCursor
     *      sets the text of the cursor
     */
    public void setTextAtCursor(String text) throws NotAllowedException {
        cursor.setText(text);
    }

    /**
     * This is a method to move the cursor to the child of the current cursor
     * at a specified index
     *
     * @param index
     *      the index of where to move the child to
     *
     * @throws IllegalArgumentException
     *      if there is no child at the specified index
     */
    public void cursorToChild(int index) throws NotAllowedException {
        if(cursor.getChildrenNode(index) == null){
            throw new IllegalArgumentException("There is no child at this " +
                    "index.");
        }
        cursor = cursor.getChildrenNode(index);
    }

    /**
     * This is a method to move the cursor to the parent of the current cursor
     */
    public void cursorToParent(){ cursor = cursor.getParent(); }

    /**
     * This is a method that generates a FXComponentTree based on the file
     * name that is passed in
     *
     * @param filename
     *      the filename to read from
     *
     * @return FXComponentTree
     *      returns the newly generated FXComponentTree
     */
    public FXComponentTree readFromFile(String filename) throws FileNotFoundException {
        String rline;
        FXComponentTree tree = new FXComponentTree();
        try {
            FileReader file = new FileReader(filename);
            BufferedReader input = new BufferedReader(file);

            while ((rline = input.readLine()) != null) {
                String[] line = rline.split(" ");
                String[] line1 = line[0].split("-");
                int i = line1.length - 1;
                int j = 1;
                FXTreeNode node = new FXTreeNode(ComponentType.valueOf(line[1]));

                if(!line[0].equals("0")){
                    cursorToRoot();
                    while (i > 1) {
                        cursorToChild(Integer.parseInt(line1[j]));
                        i--;
                        j++;
                    }
                    node.setParent(cursor);
                    addChild(Integer.parseInt(line1[j]), node);
                    if (!node.isContainer()) {
                        StringBuilder text = new StringBuilder();
                        for (int k = 2; k < line.length; k++) {
                            text.append(line[k]);
                            text.append(" ");
                        }
                        node.setText(text.toString());
                    }
                }
            }
        } catch (NotAllowedException | ArrayHoleException | MaximumChildrenException | IOException e) {
            e.printStackTrace();
        }
        cursorToRoot();
        return tree;
    }

    /**
     * This is a method that generates a text file that reflects the
     * structure of the FXComponent tree
     *
     * @param tree
     *      the tree to generate the text file from
     *
     * @param filename
     *      the name of the file to export the tree to
     */
    public void writeToFile(FXComponentTree tree, String filename) throws IOException {
        try {
            File newFile = new File(filename);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter saveFile = new FileWriter(newFile);
            cursorToRoot();
            int i = 0;
            while(i != -1) {
                if (cursor == root) {
                    saveFile.write("0 AnchorPane\n");
                    cursorToChild(0);
                } else {
                    saveFile.write("0-" + i + " " + cursor.toString() + "\n");
                    if (cursor.getChildrenNode(0) != null) {
                        saveFile.write(allChildrenWrite(cursor, "0-" + i));
                    }
                    i = -1;
                }
            }
            saveFile.close();
        }catch(IOException | NotAllowedException e){
            e.printStackTrace();
        }
    }

    /**
     * This is a helper method in writing to the file by recursively getting
     * all the children as well as the text needed to represent every node in
     * the text file
     *
     * @param current
     *      the current node
     *
     * @param place
     *      the place of the nodes matching the format of the input file
     *
     * @return String
     *      returns the text that reflects the children of the node current
     */
    public String allChildrenWrite(FXTreeNode current, String place) throws NotAllowedException {
        String childrenStr = "";
        String newPlace = "";
        int i = 0;
        while (current.getChildrenNode(i) != null){
            FXTreeNode temp = current.getChildrenNode(i);
            childrenStr += place + "-" + i + " " + temp.toString() + "\n";
            newPlace = place + "-" + i;
            if(temp.isContainer()) {
                if (temp.getChildrenNode(0) != null) {
                    childrenStr += allChildrenWrite(temp, newPlace);
                }
            }
            i++;
        }
        return childrenStr;
    }

    /**
     * This is a helper method used in the allChildren method needed to print
     * the right amount of spacing when printing out the children
     *
     * @param n
     *      the amount of spaces relative to the placement of the nodes in
     *      the tree
     *
     * @return FXComponentTree
     *      returns the newly generated FXComponentTree
     */
    private void printTab(int n){
        while(n > 0){
            System.out.print("\t");
            n--;
        }
    }

    /**
     * This is a helper method used in the toString method to get all the
     * children when printing
     *
     * @param current
     *      the current node
     *
     * @param indent
     *      the amount of spacing needed which is then put into the printTab
     *      method
     *
     * @return String
     *      prints all the children in toString format
     */
    public void allChildren(FXTreeNode current, int indent) throws NotAllowedException {
        int i = 0;
        while (current.getChildrenNode(i) != null){
            FXTreeNode temp = current.getChildrenNode(i);
            printTab(indent);
            System.out.println((temp == cursor) ? "==>" + temp.toString() :
                     "+--" + temp.toString());
            if(temp.isContainer()) {
                if (temp.getChildrenNode(0) != null) {
                    allChildren(temp, indent + 1);
                }
            }
            i++;
        }
    }

    /**
     * This is the toString method for the Package object
     *
     * @return toString
     *      returns the printable representation of the tree
     *
     * @throws ElementNotFoundException
     *      if there is no tree to print
     */
    public void treeToString() {
        int indent = 1;
        System.out.println(((root == cursor) ? "==>" + root.toString() : root.toString()));
        try {
            FXTreeNode current = root.getChildrenNode(0);;
            if (current == null){
                throw new ElementNotFoundException("There is no tree.");
            }else{
                if(current == root.getChildrenNode(0)){
                    System.out.println(((current == cursor) ?
                            "==>" + current.toString() : "--+" + current.toString()));
                    if(current.isContainer()) {
                        if (current.getChildrenNode(0) != null) {
                            allChildren(current, indent);
                        }
                    }
                }
            }
        } catch (ElementNotFoundException | NotAllowedException e) {
            e.printStackTrace();
        }
    }
}
