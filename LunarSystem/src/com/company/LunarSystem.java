package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 *This is the class named LunarSystem that will allow the user to interact
 * with a database of Students. Provide the user with a menu-based interface
 * that allows them to add, remove, and edit Students, as well as their courses.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #6
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class LunarSystem {
    public static void main(String[] args) {
        //Member Variables
        HashMap<String, Student> database = new HashMap<>();
        Scanner input = new Scanner(System.in);
        String webid = "";
        boolean run = true;
        Student current;

        //Intro Text
        System.out.println("Welcome to the Lunar System, a second place course " +
                "registration system for second rate courses at a second class " +
                "school.");

        //Reproduce HashMap from a serialized file if it exists
        if(Files.exists(Path.of("Lunar.ser"))) {
            try {
                FileInputStream fis = new FileInputStream("Lunar.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                database = (HashMap) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("No previous data found.");
                c.printStackTrace();
                return;
            }
        }else{
            System.out.println("No previous data found.");
        }

        //Start program
        do {
            printMenu();
            System.out.print("Please select an option: ");
            char selection = input.next().toUpperCase().charAt(0);

            switch(selection) {
                //WebID or Registrar for login
                case 'L':
                    System.out.println("Please enter webid: ");
                    webid = input.next().toLowerCase();

                    //Registrar Login
                    if (webid.equalsIgnoreCase("registrar")){
                        System.out.println("\nWelcome Registrar.");
                        do{
                            printRegistrarMenu();
                            System.out.print("Please select an option: ");
                            selection = input.next().toUpperCase().charAt(0);

                            switch(selection) {
                                case 'R':
                                    try {
                                        Student register = new Student();
                                        System.out.println("Please enter a webid " +
                                                "for the new student: ");
                                        register.setWebID(input.next().toLowerCase());
                                        if(database.containsKey(register.getWebID())){
                                            System.out.println(register.getWebID() + " is already registered.");
                                        }else {
                                            database.put(register.getWebID(),
                                                    register);
                                            System.out.println(register.getWebID() +
                                                    " registered.");
                                        }
                                    }catch(InputMismatchException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 'D':
                                    try {
                                        System.out.println("Please enter a webid for " +
                                                "the student to be deregistered: ");
                                        webid = input.next().toLowerCase();
                                        if(database.containsKey(webid)) {
                                            database.remove(webid);
                                            System.out.println(webid + " " +
                                                    "deregistered");
                                        }else{
                                            System.out.println(webid +
                                                    "doesn't exist.");
                                        }
                                    }catch (InputMismatchException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 'E':
                                    System.out.println("Please enter course: ");
                                    input.nextLine();
                                    try {
                                        String[] parts = input.nextLine().split(
                                                " ");
                                        String dept = parts[0];
                                        int num =
                                                Integer.parseInt(parts[1]);
                                        System.out.println("\nStudent       " +
                                                "Semester" + "\n" +
                                                "---------------------");
                                        for(Map.Entry<String, Student> entry
                                                : database.entrySet()){
                                            Student student = entry.getValue();
                                            student.isEnrolled(dept, num);
                                        }
                                    }catch(ArrayIndexOutOfBoundsException e){
                                        System.out.println("Incorrect input.");
                                    }
                                    System.out.println();
                                    break;
                                case 'L':
                                    break;
                                default:
                                    System.out.println("Incorrect selection. Try again!\n");
                                    break;
                            }
                        }while(!(selection == 'L'));
                        System.out.println("Registrar logged out.");
                        break;
                    }else if (database.containsKey(webid)){ //Student Login
                        System.out.println("\nWelcome " + webid);
                        do {
                            current = database.get(webid);
                            printLoginMenu();
                            System.out.print("Please select an option: ");
                            selection = input.next().toUpperCase().charAt(0);
                            switch (selection) {
                                case 'A':
                                    Course course = new Course();
                                    System.out.println("Please enter course name: ");
                                    input.nextLine();
                                    try {
                                        String[] parts = input.nextLine().split(
                                                " ");
                                        course.setDepartment(parts[0].toUpperCase());
                                        course.setNumber(Integer.parseInt(parts[1]));
                                        System.out.println("Please select a " +
                                                "semester: ");
                                        course.setSemester(input.next());
                                        database.get(webid).getCourses().add(course);
                                        System.out.println(course.getDepartment() +
                                                " " + course.getNumber() + " " +
                                                "added in " +
                                                course.toStringSem());
                                    }catch(ArrayIndexOutOfBoundsException e){
                                        System.out.println("Incorrect input.");
                                    }
                                    break;
                                case 'D':
                                    System.out.println("Please enter course name: ");
                                    input.nextLine();
                                    try {
                                        String[] parts = input.nextLine().split(
                                                " ");
                                        String dept = (parts[0]);
                                        int num =
                                                Integer.parseInt(parts[1]);
                                        database.get(webid).isEnrolledDereg(dept, num);
                                    }catch(ArrayIndexOutOfBoundsException e){
                                        System.out.println("Incorrect input.");
                                    }
                                    break;
                                case 'C':
                                    Collections.sort(database.get(webid).getCourses(),
                                            new CourseNameComparator());
                                    current = database.get(webid);
                                    toStringCourses(current);
                                    System.out.println();
                                    break;
                                case 'S':
                                    Collections.sort(database.get(webid).getCourses(),
                                            new SemesterComparator());
                                    current = database.get(webid);
                                    toStringCourses(current);
                                    System.out.println();
                                    break;
                                case 'L':
                                    break;
                                default:
                                    System.out.println("Incorrect selection. Try again!\n");
                                    break;
                            }
                        }while (!(selection == 'L')) ;
                        System.out.println(database.get(webid).getWebID() + " logged out.");
                    }else{
                        System.out.println("Student doesn't exist.");
                    }
                    break;
                case 'X':
                    //Storing the HashMap content in a serialized file
                    try
                    {
                        FileOutputStream fos =
                                new FileOutputStream("Lunar.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(database);
                        oos.close();
                        fos.close();
                        System.out.printf("System state saved, system shut down for maintenance.");
                    }catch(IOException ioe) {
                        ioe.printStackTrace();
                    }
                    input.close();
                    System.exit(0);
                    break;
                case 'Q':
                    System.out.println("Good bye, please pick the right SUNY next time!");
                    input.close();
                    run = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect selection. Try again!\n");
                    break;
            }
        } while (run);
    }

    /**
     * Prints menu
     */
    public static void printMenu() {
        System.out.println("\nMenu:\n" +
        "L) Login\n" +
        "X) Save state and quit\n" +
        "Q) Quit without saving state\n");
    }

    public static void printLoginMenu(){
        System.out.println("\nOptions\n" +
                "A) Add a class\n" +
                "D) Drop a class\n" +
                "C) View your classes sorted by course name/department\n" +
                "S) View your courses sorted by semester\n" +
                "L) Logout\n");
    }

    public static void printRegistrarMenu(){
        System.out.println("\nOptions:\n" +
                "R) Register a student\n" +
                "D) De-register a student\n" +
                "E) View course enrollment\n" +
                "L) Logout\n");
    }

    public static void toStringCourses(Student current){
        System.out.println("\nDept.   Course  Semester"
        + "\n--------------------------");
        for(int i = 0; i < current.getCourses().size(); i++){
            if(current.getCoursesIndex(i)!= null){
                if(i == 0) {
                    System.out.printf("%-8s%-8s%-14s",
                            current.getCoursesIndex(i).getDepartment(),
                            current.getCoursesIndex(i).getNumber(),
                            current.getCoursesIndex(i).getSemesterString());
                }else {
                    System.out.printf("\n%-8s%-8s%-14s",
                            current.getCoursesIndex(i).getDepartment(),
                            current.getCoursesIndex(i).getNumber(),
                            current.getCoursesIndex(i).getSemesterString());
                }
            }
        }
    }
}
