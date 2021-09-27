/**
Grade Report Java Program
Created by Eddie Elvira
https://www.github.com/blitzymango
*/

import java.util.Scanner;
import java.util.Iterator;
import java.util.*;
import java.lang.Math;

public class GradeReport {
    // Initialize assignment hashmap which will store name-grade pairs
    private Map<String, Double> assignments = new HashMap<>();

    public static double rounded(double number) {
        /** Round up to two decimal places for a clean output */
        return Math.round(number*100)/100.0;
    }

    public void addAssignment(String name, double grade) {
        /** Add a new assignment to the hashmap */
        assignments.put(name, grade);
    }

    public double calculateAverage() {
        /**
        Use the hashmap to find the name-grade pairs and return the average.
        */
        int total = 0;
        double cumulative = 0;
        double average = 0;

        // Iterate through the hashmap to get name-grade pairs
        Iterator<HashMap.Entry<String,Double>> entries = assignments.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry<String,Double> entry = entries.next();
            String next_name = entry.getKey();
            double next_grade = entry.getValue();

            // Values used for calculating the average
            cumulative += next_grade;
            total++;

            // Print name and score
            System.out.println(next_name + " - " + rounded(next_grade));
        }

        // If there's 0 assignments, average = 0
        // Otherwise, calculate the average
        average = ((total != 0) ? cumulative / total : 0);
        return average;
    }

    public void reportGrades(int minimum) {
        /**
        Handle user inputs and output the assignments printing the name and the score.
        Use the addAssignment() and calculateAverage() functions to print the average.
        */
        int total = 0;
        Scanner input = new Scanner(System.in);
        String total_str = "How many assignments do you have?:";
        String total_retry = "(Enter a number larger than " + minimum + "):";
        String error_msg = "Can't calculate average.\nMake sure you add an assignment.";

        while(total < minimum) {
            System.out.println(total_str);
            total = input.nextInt();
            total_str = ((total < minimum) ? total_retry : total_str);
        }

        // Ask user for names and grades of assignments
        for(int i = 1; i <= total; i++) {
            String name_str = "Name of assignment " + i + ":";
            String grade_str = "Grade:";
            String grade_retry = "Invalid input\n Enter an integer or decimal number:";

            System.out.println(name_str);
            String name = input.next();
            System.out.println(grade_str);

            // Make sure that the grade is a double
            while(!input.hasNextDouble())
            {
                System.out.println(grade_retry);
                input.next();
            }
            double grade = input.nextDouble(); 

            // Add name-grade pair to the assignment hashmap
            addAssignment(name, grade);
        }

        input.close();
        double average = calculateAverage();

        // If no assignments were added, display error message
        // Otherwise, display the average
        String result = ((average != 0) ? "Average: " + rounded(average) : error_msg);
        System.out.println(result);
    }

    public static void main(String[] args) {
        int minimum_assignments = 2;        // Minimum # of assignments (can be changed)
        GradeReport a = new GradeReport();  // Instance of GradeTeport class
        a.reportGrades(minimum_assignments);
    }
}
