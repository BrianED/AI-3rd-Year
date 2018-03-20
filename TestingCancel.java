import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class TestingCancel
{
	public static void main(String[] args)
	{
        int s = 5; // Students
        int e = 17; // Total Modules
        int c = 4; // Course modules
		String str = "Total modules must be greater than course modules" +
                "and both must be greater than 0";
		
		Random rand = new Random();
		
/* 		int prevNum = 0;
		while(true) {
			int num = (rand.nextInt(max) + 1);
			if(!(Arrays.asList(array).contains(num)) && num > prevNum && num < max - 4) {       
				 array[rows][cols] = num;
				 prevNum = num;
				 break;
			}
		} */

        // 2-D array Student Schedule (ss)
        int[][] ss = new int[s][c];
        for(int i = 0; i < s; i++) {
            for(int j = 0; j < c; j++) {
				int prevNum = 0;
				while(true) {
					int num = (rand.nextInt(e) + 1);
					if(!(Arrays.asList(ss).contains(num)) && num > prevNum && num < e - 4) {       
						 ss[i][j] = num;
						 prevNum = num;
						 break;
					}
				}
			}
        }
		for(int i = 0; i < ss.length; i++) {
            for(int j = 0; j < ss[i].length; j++) {
				System.out.print(ss[i][j] + " ");
			}
			System.out.println();
        }
	}
}

/*
for(int i = 0; i < s; i++) {
            for(int j = 0; j < c; j++) {
				ss[i][j] = (int)(Math.random() * e + 1);
			}
        }
*/

