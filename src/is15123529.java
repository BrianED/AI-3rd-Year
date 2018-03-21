import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class is15123529
{
    public static void main(String[] args)
    {
        int[] params = new int[8];
        String[] dialogues = {
                "Generations: ",                // 0 g
                "Population: ",                 // 1 p
                "Students: ",                   // 2 s
                "Total modules: ",              // 3 e
                "Course modules: ",             // 4 c
                "Exam sessions/day: ",          // 5 d
                "Crossover probability: ",      // 6 cr
                "Mutation probability: "        // 7 mu
        };
        String[] errors = {
                "Generations must be positive.",
                "Population must be positive.",
                "Students must be positive.",
                "Total modules must be greater than course modules and both must be greater than 0",
                "Total modules must be greater than course modules and both must be greater than 0",
                "Exam sessions/day must be positive.",
                "Crossover probability must be positive.",
                "Mutation probability must be positive."
        };
        params = userInput(dialogues, errors, params);
        int[][] studentSchedule = new int[params[2]][params[4]];
        ArrayList<Integer> uniqueRow = generateRow(params[3]);
        studentSchedule = addRows(studentSchedule, uniqueRow);
        printStudentSchedule(studentSchedule);
    }

    private static int[] userInput(String[] dialogues, String[] errors, int[] params)
    {
        int re, ed;
        String userInput, tempCourse, tempMu;
        String pattern = "[0-9]+";
        boolean valid;

        for (int i = 0; i < params.length; i++) {
            valid = false;
            while (!valid) {
                userInput = JOptionPane.showInputDialog(null, dialogues[i]);
                if (userInput == null || userInput.length() == 0) System.exit(1);
                if (!userInput.matches(pattern))
                    JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                else { // Check specific cases
                    if (i == 3) { // Check total modules >= course modules
                        while (true) {
                            tempCourse = JOptionPane.showInputDialog(null, "Course Modules: ");
                            if (tempCourse == null || tempCourse.length() == 0) System.exit(1);
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
                            if (tempMu == null || tempMu.length() == 0) System.exit(1);
                            re = (100 - (Integer.parseInt(userInput) + (Integer.parseInt(tempMu)))); // Re = 100 - (Cr + Mu)
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
        ed = (int) Math.ceil((double)params[3] / params[5]);

        return params;
    }

    private static ArrayList<Integer> generateRow(int length)
    {
        ArrayList<Integer> uniqueRow = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            uniqueRow.add(i);
        }
        Collections.shuffle(uniqueRow);

        return uniqueRow;
    }

    private static int[][] addRows(int[][] studentSchedule, ArrayList<Integer> uniqueRow)
    {
        // Add unique rows to 2d array
        for (int row = 0, k = 0; row < studentSchedule.length; row++, k++) {
            for (int col = 0; col < studentSchedule[row].length; col++) {
                studentSchedule[row][col] = uniqueRow.get(col);
            }
            Collections.shuffle(uniqueRow);
        }

        return studentSchedule;
    }

    private static void printStudentSchedule(int[][] studentSchedule)
    {
        for(int i = 0; i < studentSchedule.length; i++) {
            System.out.print("Student " + (i+1) + ": ");
            for(int j = 0; j < studentSchedule[i].length; j++) {
                System.out.print(studentSchedule[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// System.out.println("Re: " + re);
// System.out.println("Sum of Re Cr Mu: " + (re + Integer.parseInt(userInput) + tempMu));