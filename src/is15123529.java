import javax.swing.*;

public class is15123529 {
    public static void main(String[] args) {

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

        int tempCourse, t2;
        String userInput;
        String pattern = "[0-9]+";
        boolean valid = true;
        for (int i = 0; i < values.length; i++){
            valid = false;
            while (!valid) {
                userInput = JOptionPane.showInputDialog(null, dialogues[i]);
                if (!userInput.matches(pattern))
                    JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                else {
                    // Check specific cases first
                    if (i == 3) {
                        while (true) { // if Total modules > course modules t1 >= Integer.parseInt(userInput)
                            tempCourse = Integer.parseInt(JOptionPane.showInputDialog(null, "Course Modules")); // course modules
                            if (tempCourse <= Integer.parseInt(userInput)) {
                                i++;
                                break;
                            }
                            JOptionPane.showMessageDialog(null, errors[i], "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        i++;
//                    } else if (i == 4 && ((values[3] + values[7]) < Integer.parseInt(userInput))) {
                    } else {
                        values[i] = Integer.parseInt(userInput);
                        valid = true; // Break out of while loop
                    }
                }
            }
        }
System.exit(1);


        // ed is the ceil of e/d
        //System.out.println((double)e / d);
        ed = Math.ceil((double)e / d);
        //System.out.println(ed);

        // 2-D array Student Schedule (ss)
        int[][] ss = new int[s][c];
        for(int row = 0; row < s; row++) {
            for(int col = 0; col < c; col++) {
                ss[row][col] = (int)(Math.random() * e + 1);
            }
        }
        for (int[] s1 : ss) {
            for (int aS1 : s1) {
                System.out.print(aS1 + " ");
            }
            System.out.println();
        }
    }
}
