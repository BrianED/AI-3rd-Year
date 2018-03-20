import javax.swing.*;

public class MadLib
{
	public static void main(String[] args)
	{
		boolean exit = false;
		
		while(!exit)
		{
			JTextField field1 = new JTextField(5);
			JTextField field2 = new JTextField(5);
			JTextField field3 = new JTextField(5);
			JTextField field4 = new JTextField(5);
			JTextField field5 = new JTextField(5);
			JTextField field6 = new JTextField(5);
			
			JPanel myPanel = new JPanel();
			//myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			myPanel.add(new JLabel("noun:"));
			myPanel.add(field1);
			//myPanel.add(Box.createHorizontalStrut(5));
			myPanel.add(new JLabel("plural noun:"));
			myPanel.add(field2);
			//myPanel.add(Box.createHorizontalStrut(5));
			myPanel.add(new JLabel("noun:"));
			myPanel.add(field3);
			//myPanel.add(Box.createHorizontalStrut(5));
			myPanel.add(new JLabel("place:"));
			myPanel.add(field4);
			//myPanel.add(Box.createHorizontalStrut(5));
			myPanel.add(new JLabel("adjective:"));
			myPanel.add(field5);
			//myPanel.add(Box.createHorizontalStrut(5));
			myPanel.add(new JLabel("noun:"));
			myPanel.add(field6);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter the following words:", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION)
			{
				System.out.println("Be kind to your " + field1.getText() + "-footed " + field2.getText());
				System.out.println("For a duck may be somebody`s " + field3.getText() + ",");
				System.out.println("Be kind to your " + field2.getText() + " in " + field4.getText());
				System.out.println("Where the weather is always " + field5.getText() + ".\n");
				System.out.println("You may think that this is the " + field6.getText() + ",");
				System.out.println("Well it is.");
			}
			
			result = JOptionPane.showConfirmDialog(null, "Would you like to play the game again?", "Play again?", JOptionPane.YES_NO_OPTION);
			
			if ((result == JOptionPane.NO_OPTION))
			{
				exit = true;
			}
		}
	}
}