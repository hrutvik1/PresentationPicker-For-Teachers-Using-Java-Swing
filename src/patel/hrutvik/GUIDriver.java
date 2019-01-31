package patel.hrutvik;

import javax.swing.JFrame;

/**
 * This program can be used to evaluate students for their presentation content and delivery.
 * The user is required to have a CSV file containing information of the student (first name, last name, id, initial content mark, initial delivery mark).
 * The program is user friendly and will let the user mark the students on random.
 * After the user is done marking all the students there will be a button that will let them save the marks into a new CSV file.
 * The user can save the file whenever they like as long as they have 1 student marked.
 * If anything goes wrong in the way there will be a message indicating what went wrong.
 * Once the file is saved there will be a dialogue saying where the saved file is located. 
 * @author hrutvik
 * 
 */
public class GUIDriver {

	/**
	 * This is the main method where the frame is made and panel is embedded in the frame
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Teacher's frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Panel()); 
		frame.pack();
		frame.setFocusable(true);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}
