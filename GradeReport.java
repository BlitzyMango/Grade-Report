/**
Grade Report Java Program
Created by Eddie Elvira
https://www.github.com/blitzymango
*/

import java.util.Scanner;
import java.util.*;
import java.lang.Math;

public class GradeReport {
    // Initialize assignment treemap which will store name-grade pairs in
    // alphabetical order
    private TreeMap<String, Double> assignments = new TreeMap<String, Double>();

    public static double rounded(double number) {
        /** Round up to two decimal places for a clean output */
        return Math.round(number * 100) / 100.0;
    }

    public void addAssignment(String name, double grade) {
        /** Add a new assignment to the treemap */

        // Check if assignment name matches any existing name in the treemap to "allow"
        // for duplicates
        int num = 1;
        while (true) {
            if (assignments.containsKey(name)) {
                String numname = name + "(" + num + ")";
                if (assignments.containsKey(numname)) {
                    num++;
                } else {
                    name = numname;
                    break;
                }
            } else {
                break;
            }
        }

        // Add name-grade pair to assignments
        assignments.put(name, grade);
    }

    public double calculateAverage() {
        /**
         * Use the treemap to find the name-grade pairs and return the average.
         */
        int total = 0;
        double cumulative = 0;
        double average = 0;

        System.out.println("\n---Grade Report---");

        // Iterate through the treemap (using for-each loop) to get name-grade pairs
        for (Map.Entry<String, Double> entry : assignments.entrySet()) {
            String next_name = entry.getKey();
            Double next_grade = entry.getValue();

            // Values used for calculating the average
            cumulative += next_grade;
            total++;

            System.out.println(next_name + " - " + rounded(next_grade));
        }

        average = ((total != 0) ? cumulative / total : 0);
        return average;
    }

    public void reportGrades(int minimum) {
        /**
         * Handle user inputs and output the assignments printing the name and the
         * score. Use the addAssignment() and calculateAverage() functions to print the
         * average.
         */
        int total = 0;
        Scanner input = new Scanner(System.in);
        String total_str = "How many assignments do you have?:";
        String min_retry = "Amount must be greater than at least " + minimum + ":";
        String total_retry = "Invalid input\n(Enter an integer greater than at least " + minimum + "):";
        String error_msg = "Can't calculate average.\nMake sure you add an assignment.";

        System.out.println(total_str);

        // Make sure that the number of assignments is an integer
        while (total < minimum) {
            while (!input.hasNextInt()) {
                System.out.println(total_retry);
                input.next();
            }
            total = input.nextInt();
            System.out.println(min_retry);
        }

        // Ask user for names and grades of assignments
        for (int i = 1; i <= total; i++) {
            String name_str = "Name of assignment " + i + ":";
            String grade_str = "Grade:";
            String grade_retry = "Invalid input\nEnter an integer or decimal number:";

            System.out.println(name_str);
            String name = input.next();
            System.out.println(grade_str);

            // Make sure that the grade is a double
            while (!input.hasNextDouble()) {
                System.out.println(grade_retry);
                input.next();
            }
            double grade = input.nextDouble();

            // Add name-grade pair to the assignment treemap
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
        int minimum_assignments = 3; // Make sure that user enters more than 3 assignments
        GradeReport a = new GradeReport(); // Instance of GradeReport class
        a.reportGrades(minimum_assignments);
    }
}
