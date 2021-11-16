package com.company;
import java.util.Comparator;
/**
 *This is the CourseNameComparator that allows us to compare two course names
 * with the following priority: department and then number.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #6
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class CourseNameComparator implements Comparator {

    public CourseNameComparator(){

    }

    @Override
    public int compare(Object o1, Object o2) {
        Course left = (Course) o1;
        Course right = (Course) o2;
        return left.compareToCourse(right);
    }
}
