package com.company;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *This is the Student class that will serve as the stored element of the Lunar
 * System database.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #6
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class Student implements Serializable {
    //Member Variables
    String webID;
    ArrayList<Course> courses = new ArrayList<>();

    /**
     * This is the default Constructor used to create a new Student
     */
    public Student(){
    }

    /**
     * This is the default Constructor used to create a new Student parameters
     *
     * @param webID
     *      the webID of student
     *
     * @param courses
     *      courses the student is enrolled in
     */
    public Student(String webID, ArrayList<Course> courses){
        this.webID = webID;
        this.courses = courses;
    }

    /**
     * This is a getter method
     *
     * @return getWebID
     *      gets the webID of student
     */
    public String getWebID(){
        return webID;
    }

    /**
     * This is a getter method
     *
     * @return getCourses
     *      gets the courses the student is enrolled in
     */
    public ArrayList<Course> getCourses(){ return courses; }

    /**
     * This is a getter method
     *
     * @param i
     *      the index of course to get
     *
     * @return getCoursesIndex
     *      gets the course at index i
     */
    public Course getCoursesIndex(int i){ return courses.get(i); }

    /**
     * This is a setter method
     *
     * @param webID
     *      the webID to set for this student
     */
    public void setWebID(String webID) { this.webID = webID; }

    /**
     * This is a setter method
     *
     * @param courses
     *      the courses to set for this student
     *
     * @return setParen
     *      sets the courses for this student
     */
    public void setCourses(ArrayList<Course> courses) { this.courses = courses; }

    /**
     *This method searches for the course that matches the paramters and
     * removes it
     *
     * @param dept
     *      the department to search for
     *
     * @param num
     *      the number for the class to search
     */
    public void isEnrolledDereg(String dept, int num){
        for(int i = 0; i < courses.size(); i++){
            if((courses.get(i).getDepartment().equalsIgnoreCase(dept)) && (courses.get(i).getNumber() == num)){
                System.out.println(courses.get(i).getDepartment() + " " + courses.get(i).getNumber() + " dropped from " + courses.get(i).toStringSem());
                courses.remove(i);
            }
        }
    }

    /**
     *This method searches checks to see if the student is enrolled in a class
     *
     * @param dept
     *      the department to search for
     *
     * @param num
     *      the number for the class to search
     */
    public void isEnrolled(String dept, int num){
        for(int i = 0; i < courses.size(); i++){
            if((courses.get(i).getDepartment().equalsIgnoreCase(dept)) && (courses.get(i).getNumber() == num)){
                System.out.printf("%-14s%-16s", this.getWebID(),
                            courses.get(i).getSemesterString());
                System.out.println();
            }
        }
    }
}

