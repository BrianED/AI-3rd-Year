import javax.swing.*;

public class is15123529 {
    public static void main(String[] args) {

        int re, ed;
        String userInput = "", tempCourse = "", tempMu = "";
        String pattern = "[0-9]+";
        boolean valid;
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
                            // System.out.println("Re: " + re);
                            // System.out.println("Sum of Re Cr Mu: " + (re + Integer.parseInt(userInput) + tempMu));
                            if ((Integer.parseInt(userInput) < 100) && (Integer.parseInt(tempMu) < 100) &&
                                    ((re + Integer.parseInt(userInput) + Integer.parseInt(tempMu)) == 100 ) &&
                                    (Integer.parseInt(tempMu) > 0)) {
                                params[i] = Integer.parseInt(userInput); // Add Crossover probability num to array
                                params[++i] = (Integer.parseInt(tempMu)); // Add Mutation probability num to array
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

        //ed is the ceil of e/d
        ed = (int) Math.ceil((double)params[3] / params[5]);

        //2-D array Student Schedule (ss)
        int[][] ss = new int[params[2]][params[4]];
        int[] totalCourses = new int[params[3]];

        for(int i = 0, j = 1; i < totalCourses.length; i++, j++)
            totalCourses[i] = j;
        for(int i = 0; i < totalCourses.length; i++)
            System.out.print(totalCourses[i] + " ");
//        for(int row = 0; row < s; row++) {
//            for(int col = 0; col < c; col++) {
//                ss[row][col] = (int)(Math.random() * e + 1);
//            }
//        }
//        for (int[] s1 : ss) {
//            for (int aS1 : s1) {
//                System.out.print(aS1 + " ");
//            }
//            System.out.println();
//        }
    }
}
