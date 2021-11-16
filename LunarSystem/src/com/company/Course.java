package com.company;
import java.io.Serializable;

/**
 *This is the Course class which contains a designated department, course
 * number, and semester associated with it.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #6
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class Course implements Serializable {
    //Member variables
    String department;
    int number;
    String semester;

    /**
     * This is the default Constructor used to create a new Course
     */
    public Course() {
    }

    /**
     * This is is used to create a new course given the department and number
     * parameters
     */
    public Course(String department, int number){
        this.department = department;
        this.number = number;
    }
    /**
     * This is is used to create a new course given the department, number
     * parameters, and the semester
     */
    public Course(String department, int number, String semester){
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    /**
     * This is a getter method
     *
     * @return getDepartment
     *      gets the department of the course
     */
    public String getDepartment(){
        return department;
    }

    /**
     * This is a getter method
     *
     * @return getNumber
     *      gets the number of the course
     */
    public int getNumber(){
        return number;
    }

    /**
     * This is a getter method
     *
     * @return getSemester
     *      gets the semester of the course
     */
    public String getSemester(){ return semester; }

    /**
     * This is a string method to print the semester
     *
     * @return getSemesterString
     *      returns string of printable semester
     */
    public String getSemesterString(){
        if (this.getSemester().charAt(0) == 'S' || this.getSemester().charAt(0) == 's'){
            return("S" + this.getSemester().substring(1));
        }else{
            return("F" + this.getSemester().substring(1));
        }
    }

    /**
     * This is a setter method
     *
     * @param department
     *      the department for this course
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * This is a setter method
     *
     * @param number
     *      the number for this course
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * This is a setter method
     *
     * @param semester
     *      the semester for this course
     */
    public void setSemester(String semester){
        this.semester = semester;
    }

    /**
     * This is a compare method to sort by courses
     *
     * @param courseTwo
     *      the course to compare to
     */
    public int compareToCourse(Course courseTwo) {
        for (int i = 0; i < 3; i++) {
            if (this.getDepartment().charAt(i) != courseTwo.getDepartment().charAt(i)) {
                return (int)(this.getDepartment().charAt(i) - courseTwo.getDepartment().charAt(i));
            }
        }
        if(this.getNumber() > courseTwo.getNumber()){
            return 1;
        }else if (this.getNumber() < courseTwo.getNumber()){
            return -1;
        }
        return 0;
    }

    /**
     * This is a compare method to sort by semesters
     *
     * @param courseTwo
     *      the course to compare to
     */
    public int compareToSem(Course courseTwo) {
        for (int i = 0; i < 5; i++) {
            if (this.getSemester().charAt(i) != courseTwo.getSemester().charAt(i)) {
                return (int)(this.getSemester().charAt(i) - courseTwo.getSemester().charAt(i));
            }
        }
        return 0;
    }

    /**
     * This is a string method to print the semester
     *
     * @return getSemesterString
     *      returns string of printable semester
     */
    public String toStringSem(){
        if (this.getSemester().charAt(0) == 'S' || this.getSemester().charAt(0) == 's'){
            return("Spring " + this.getSemester().substring(1) + ".");
        }else{
            return("Fall " + this.getSemester().substring(1) + ".");
        }
    }
}
