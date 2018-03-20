import javax.swing.*;

public class OldWorking {
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


        int g = 0; // Generations
        int p = 0; // Population
        int s = 0; // Students
        int e = 0; // Total Modules
        int c = 0; // Course modules
        int d = 0; // Exam sessions/day
        int cr = 0; // Crossover probability
        int mu = 0; // Mutation probability
        double ed = 0; // Exams per session
        int re = 0; // Reproduction probabilty
        String str = "Total modules must be greater than course modules" +
                "and both must be greater than 0";

        while (true) { // Generations
            g = Integer.parseInt(JOptionPane.showInputDialog("Generations: "));
            if (g > 0)
                break;
            JOptionPane.showMessageDialog(null, "Generations must be positive.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        while (true) { // Population
            p = Integer.parseInt(JOptionPane.showInputDialog("Population: "));
            if (p > 0)
                break;
            JOptionPane.showMessageDialog(null, "Population must be positive.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        while (true) { // Students
            s = Integer.parseInt(JOptionPane.showInputDialog("Students: "));
            if (s > 0)
                break;
            JOptionPane.showMessageDialog(null, "Students must be positive.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        while (true) { // Total modules and course modules
            e = Integer.parseInt(JOptionPane.showInputDialog("Total modules: "));
            c = Integer.parseInt(JOptionPane.showInputDialog("Course modules: "));
            if (e > 0 && c > 0 && e >= c)
                break;
            JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
        }
        while (true) { // Exam sessions per day
            d = Integer.parseInt(JOptionPane.showInputDialog("Exam sessions/day: "));
            if (d > 0)
                break;
            JOptionPane.showMessageDialog(null, "Exam sessions/day must be positive.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        while (true) {
            while (true) { // Crossover probability
                cr = Integer.parseInt(JOptionPane.showInputDialog("Crossover probability: "));
                if (cr > 0 && cr < 100)
                    break;
                JOptionPane.showMessageDialog(null, "Exam sessions/day must be positive.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            while (true) { // Mutation probability
                mu = Integer.parseInt(JOptionPane.showInputDialog("Mutation probability: "));
                if (mu > 0 && mu < 100)
                    break;
                JOptionPane.showMessageDialog(null, "Exam sessions/day must be positive.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            re = (100 - (cr + mu));
//            System.out.println("Re: " + re + "\n");
//            System.out.println("Sum of Re Cr Mu: " + (re + cr + mu) + "\n");
            if ((re + cr + mu) == 100) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Sum must be equal to 100",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
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
//        for(int i = 0; i < ss.length; i++) {
//            for(int j = 0; j < ss[i].length; j++) {
//                System.out.print(ss[i][j] + " ");
//            }
//            System.out.println();
//        }





        //crap
//        do {
//            g = Integer.parseInt(JOptionPane.showInputDialog("Generations: "));
//        } while(g < 0);
//        do {
//            p = Integer.parseInt(JOptionPane.showInputDialog("Population: "));
//        } while(p < 0);
//        do {
//            s = Integer.parseInt(JOptionPane.showInputDialog("Students: "));
//        } while(s < 0);
//        do {
//            do {
//                e = Integer.parseInt(JOptionPane.showInputDialog("Total modules: "));
//            } while(e < 0);
//            do {
//                c = Integer.parseInt(JOptionPane.showInputDialog("Course modules: "));
//            } while(c < 0);
//        } while(e < c);
//        do {
//            d = Integer.parseInt(JOptionPane.showInputDialog("Exam sessions/day: "));
//        } while(d < 0);
        /*
        input for Cr and Mu
         */
//        ed = Math.ceil();
    }
}