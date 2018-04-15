import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class is15123529
{
    public static void main(String[] args)
    {
        int ed;
        int[] params = new int[8];                              // Array to hold user input parameters
        String[] dialogues = {
                "Generations: ",                                // params[0]
                "Population: ",                                 // params[1]
                "Students: ",                                   // params[2]
                "Total modules: ",                              // params[3]
                "Course modules: ",                             // params[4]
                "Exam sessions/day: ",                          // params[5]
                "Crossover probability: ",                      // params[6]
                "Mutation probability: "                        // params[7]
        };
        String[] errors = {
                "Generations must be a positive integer.",
                "Population must be a positive integer.",
                "Students must be a positive integer.",
                "Total modules must be a positive integer (Total modules must be greater then course modules)",
                "Total modules must be a positive integer (Total modules must be greater then course modules)",
                "Exam sessions/day must be a positive integer.",
                "Crossover probability must be a positive integer (1 - 100).",
                "Mutation probability must be a positive integer (1 - 100)."
        };

        /* User input */
        params = userInput(dialogues, errors, params);          // Collect user input

        ed = (int) Math.ceil((double)params[3] / params[5]);    // Total modules / Exam sessions per day

        /* Generate unique rows and add these rows to the student schedule */
        ArrayList<Integer> uniqueRow = generateRow(params[3]);  // Generate unique row from total number of modules
        int[][] studentSchedule = new int[params[2]][params[4]];// Student schedule (num of students * course modules)
        studentSchedule = addRows(studentSchedule, uniqueRow);  // Add shuffled unique rows to each row in the 2D array

        /* Print the student schedule */
        printResults(studentSchedule, null, "Student");    // Print student schedule to console

        /* Wait for the end-user to press Enter to continue */
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Population\n");
        int[][] orderings = new int[params[1]][params[3]];      // [Population][total modules]
        ArrayList<Integer> uniqueOrdering;
        int[] results = new int[params[1]];

        int uniqueCounter = 0;
        boolean rowDuplicateCheck = false;
        while (!rowDuplicateCheck) {

            /* Generate orderings and add these to 2D array */

            uniqueOrdering = generateRow(params[3]);
            orderings = addRows(orderings, uniqueOrdering);

            /* Split the orderings and return the fitness cost */

            results = splitOrdering(orderings, ed, params[5], studentSchedule);
            uniqueCounter++;
            if(uniqueCounter > params[1]) {
                JOptionPane.showMessageDialog(null, "Duplicate rows\nExiting");
                System.exit(0);
            }
            rowDuplicateCheck = uniqueOrderings(orderings);
        }

        /* Print orderings and fitness cost */
        printResults(orderings, results, "Ordering");
    }

    /**
     * This method takes the orderings 2D array and takes two rows and sends them to another method
     * @param orderings 2D Orderings array
     * @return
     */
    private static boolean uniqueOrderings(int [][] orderings)
    {
        for(int i=0; i < orderings.length; i++) {
            for(int j = i + 1; j < orderings.length; j++) {
                if (orderingsMatch(orderings[i], orderings[j])) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * This method takes 2 parameters and compares the same element of each to check for duplicate rows
     * @param orderingA An ordering
     * @param orderingB The next ordering
     * @return
     */
    private static boolean orderingsMatch(int [] orderingA, int[] orderingB) {
        for (int i = 0; i < orderingA.length; i++) {
            if (orderingA[i] != orderingB[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method used to print results to screen
     * @param arr Use the 2D array that has been passed in
     * @param results Contains the fitness cost numbers for each ordering
     * @param s Used for printing students and orderings
     */
    private static void printResults(int[][] arr, int[] results, String s)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(s + " " + (i + 1) + ": ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            if (s.equalsIgnoreCase("Ordering"))
                System.out.print(": Fitness Cost: " + results[i]);
            System.out.println();
        }
    }

    /**
     * This method takes the orderings and splits them at each session segment
     * @param orderings Used for the number of orderings
     * @param ed This is the value of the Total modules / Exam sessions per day
     * @param params Exam sessions per day
     * @param studentSchedule used to calculate fitness cost
     * @return an array with the fitness cost of each ordering
     */
    private static int[] splitOrdering(int[][] orderings, int ed, int params, int[][] studentSchedule)
    {
        int count = 0;
        int fitnessCost = 0;
        int[] results = new int[orderings.length]; // ordering.length == params[1]
        int[][] split = new int[params][ed];

        for (int k = 0; k < orderings.length; k++) {
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < split[i].length; j++) {
                    /*
                    Fill rest of row with -1 if the count is greater than the length of the array
                     */
                    if (count > orderings[k].length - 1)
                        split[i][j] = -1;
                    else if (count < orderings[k].length) {
                        split[i][j] = orderings[k][count];
                        count++;
                    }
                }
            }
            /*
            Calculate fitness cost
             */
            results[k] = calculateFitnessCost(studentSchedule, split, fitnessCost);
            fitnessCost = 0;    // Reset fitness cost for next ordering
            count = 0;          // Reset count
        }
        return results;
    }

    /**
     * Returns the number of overlaps that have been found
     * @param studentSchedule takes the student schedule array
     * @param split takes the split array of orderings
     * @param fitnessCost The number of overlaps
     * @return The number of overlaps
     */
    private static int calculateFitnessCost(int[][] studentSchedule, int[][] split, int fitnessCost)
    {
        for (int i = 0; i < studentSchedule.length; i++) {
            for (int j = 0; j < studentSchedule[0].length; j++) {
                if(checkIfOverlap(studentSchedule[i], split[j])) {
                    fitnessCost++;
                }
            }
        }
        return fitnessCost;
    }

    /**
     * Check each row of the student schedule against each row of the orderings
     * that have been split
     * @param studentSchedule student schedule row
     * @param split split row
     * @return true if more than 1 duplicate has been found
     */
    private static boolean checkIfOverlap(int[] studentSchedule, int[] split)
    {
        int ctr = 0;

        for(int i = 0; i < studentSchedule.length; i++)
            for(int j = 0; j < split.length; j++)
                if(studentSchedule[i] == split[j])
                    ctr++;

        return ctr > 1;
    }

    /**
     * Get user input and store in array
     * @param dialogues String array containing prompts
     * @param errors String array containing errors
     * @param params User input parameters
     * @return array of user input
     */
    private static int[] userInput(String[] dialogues, String[] errors, int[] params)
    {
        int re;
        String userInput, tempCourse, tempMu;
        String pattern = "[0-9]+"; // Numbers 0 to 9 (1 or more)
        boolean valid;

        for (int i = 0; i < params.length; i++) {
            valid = false;
            while (!valid) {
                userInput = JOptionPane.showInputDialog(null, dialogues[i]);
                if (userInput == null) { // Cancel button
                    JOptionPane.showMessageDialog(null, "Exiting program");
                    System.exit(0);
                }
                if (!userInput.matches(pattern))
                    JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                else { // Check specific cases
                    if (i == 3) { // Check total modules >= course modules
                        while (true) {
                            tempCourse = JOptionPane.showInputDialog(null, "Course Modules: ");
                            if (tempCourse == null) {
                                JOptionPane.showMessageDialog(null, "Exiting program");
                                System.exit(0);
                            }
                            else if (tempCourse.length() == 0) {
                                JOptionPane.showMessageDialog(null, errors[4], "Error", JOptionPane.ERROR_MESSAGE);
                                tempCourse = JOptionPane.showInputDialog(null, "Course Modules: ");
                            }
                            if (Integer.parseInt(tempCourse) <= Integer.parseInt(userInput) &&
                                    Integer.parseInt(tempCourse) > 0) {
                                params[i] = Integer.parseInt(userInput); // Add total module num to array
                                params[++i] = Integer.parseInt(tempCourse); // Add course module num to array
                                break; // Exit loop
                            }
                            JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        valid = true; // Break out of while loop
                    } else if (i == 6) {
                        while (true) {
                            tempMu = JOptionPane.showInputDialog(null, "Mutation probability: ");
                            if (tempMu == null) {
                                JOptionPane.showMessageDialog(null, "Exiting program");
                                System.exit(0);
                            }
                            else if (tempMu.length() == 0) {
                                JOptionPane.showMessageDialog(null, errors[4], "Error", JOptionPane.ERROR_MESSAGE);
                                tempMu = JOptionPane.showInputDialog(null, "Course Modules: ");
                            }
                            re = (100 - (Integer.parseInt(userInput) + (Integer.parseInt(tempMu)))); // Re=100-(Cr + Mu)
                            if ((Integer.parseInt(userInput) < 100) && (Integer.parseInt(tempMu) < 100) &&
                                    ((re + Integer.parseInt(userInput) + Integer.parseInt(tempMu)) == 100 ) &&
                                    (Integer.parseInt(tempMu) > 0)) {
                                params[i] = Integer.parseInt(userInput); // Add Crossover probability num to array
                                params[++i] = Integer.parseInt(tempMu); // Add Mutation probability num to array
                                break; // Exit inner loop
                            }
                            if ((Integer.parseInt(tempMu) < 0)) // Print error message for Mutation probability
                                JOptionPane.showMessageDialog(null, errors[7], "Error", JOptionPane.ERROR_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        valid = true; // Break out of while loop
                    } else {
                        params[i] = Integer.parseInt(userInput);
                        valid = true; // Break out of while loop
                    }
                }
            }
        }

        return params;
    }

    /**
     * Generate a random shuffling which is the length of the total modules
     * @param length is the parameter entered for the total modules
     * @return a shuffled one dimensional list
     */
    private static ArrayList<Integer> generateRow(int length)
    {
        ArrayList<Integer> shuffledList = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            shuffledList.add(i);
        }
        Collections.shuffle(shuffledList);

        return shuffledList;
    }

    /**
     * Add shuffled unique rows to each row in the student schedule
     * @param studentSchedule
     * @param uniqueRow added to each row of the student schedule
     * @return the student schedule with the rows filled in
     */
    private static int[][] addRows(int[][] studentSchedule, ArrayList<Integer> uniqueRow)
    {
        // Add unique rows to 2d array
        for (int row = 0; row < studentSchedule.length; row++) {
            for (int col = 0; col < studentSchedule[row].length; col++) {
                studentSchedule[row][col] = uniqueRow.get(col);
            }
            Collections.shuffle(uniqueRow);
        }

        return studentSchedule;
    }
}