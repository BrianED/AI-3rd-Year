import javax.swing.*;

public class is15123529 {
    public static void main(String[] args) {

        int tempCourse, tempMu, re, ed;
        String userInput;
        String pattern = "[0-9]+";
        boolean valid;
        int[] values = new int[8];
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

        for (int i = 0; i < values.length; i++) {
            valid = false;
            while (!valid) {
                userInput = JOptionPane.showInputDialog(null, dialogues[i]);
                if (!userInput.matches(pattern))
                    JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                else { // Check specific cases
                    if (i == 3) { // Check total modules >= course modules
                        while (true) {
                            tempCourse = Integer.parseInt(JOptionPane.showInputDialog(null, "Course Modules: "));
                            if (tempCourse <= Integer.parseInt(userInput) && tempCourse > 0) {
                                values[i] = Integer.parseInt(userInput); // Add total module num to array
                                values[++i] = tempCourse; // Add course module num to array
                                break; // Exit loop
                            }
                            JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        valid = true; // Break out of while loop
                    } else if (i == 6) { // && ((values[3] + values[7]) < Integer.parseInt(userInput))
                        while (true) {
                            tempMu = Integer.parseInt(JOptionPane.showInputDialog(null, "Mutation probability: "));
                            re = (100 - (Integer.parseInt(userInput) + tempMu));
                            System.out.println("Re: " + re);
                            System.out.println("Sum of Re Cr Mu: " + (re + Integer.parseInt(userInput) + tempMu));
                            if ((Integer.parseInt(userInput) < 100 && tempMu < 100) &&
                                    (re + (Integer.parseInt(userInput) + tempMu) == 100 ) && tempMu > 0) {
                                values[i] = Integer.parseInt(userInput); // Add Crossover probability num to array
                                values[++i] = tempMu; // Add Mutation probability num to array
                                break; // Exit loop
                            }
                            if (tempMu < 0)
                                JOptionPane.showMessageDialog(null, errors[7], "Error", JOptionPane.ERROR_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        valid = true; // Break out of while loop
                    } else {
                        values[i] = Integer.parseInt(userInput);
                        valid = true; // Break out of while loop
                    }
                }
            }
        }
        System.out.println("Generations: "+values[0]);
        System.out.println("Population: "+values[1]);
        System.out.println("Students: "+values[2]);
        System.out.println("Total modules: "+values[3]);
        System.out.println("Course modules: "+values[4]);
        System.out.println("Exam sessions/day: "+values[5]);
        System.out.println("Crossover probability: "+values[6]);
        System.out.println("Mutation probability: "+values[7]);
System.exit(1);


        // ed is the ceil of e/d
        //System.out.println((double)e / d);
        //ed = Math.ceil((double)e / d);
        //System.out.println(ed);

        // 2-D array Student Schedule (ss)        int[][] ss = new int[s][c];
        ////        for(int row = 0; row < s; row++) {
        ////            for(int col = 0; col < c; col++) {
        ////                ss[row][col] = (int)(Math.random() * e + 1);
        ////            }
        ////        }
        ////        for (int[] s1 : ss) {
        ////            for (int aS1 : s1) {
        ////                System.out.print(aS1 + " ");
        ////            }
        ////            System.out.println();
        ////        }
//
    }
}
