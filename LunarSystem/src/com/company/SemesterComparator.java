package com.company;
import java.util.Comparator;
/**
 *This is the class named SemesterComparator that allows us to compare two
 *  * courses based on the semester they were offered.
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #6
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class SemesterComparator implements Comparator{

    public SemesterComparator(){
    }

    @Override
    public int compare(Object o1, Object o2) {
        Course left = (Course) o1;
        Course right = (Course) o2;
        return left.compareToSem(right);
    }
}
