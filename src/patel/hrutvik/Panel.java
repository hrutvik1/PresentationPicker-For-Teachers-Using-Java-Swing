package patel.hrutvik;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

@SuppressWarnings("serial")
/**
 * This class is used to display the information to the user and retrieve user input (GUI)
 * @author hrutvik
 *
 */
public class Panel extends JPanel {
 
	private Section sect;
	private int randomStudent;
	private String content = "-1";
	private String delivery = "-1";
	private int resultContent;
	private int resultDelivery;
	private JButton btnFileOpener;
	private JButton btnSelectRandomStudent;
	private JButton btnMarkStudent;
	private JButton btnSaveFile;
	private JLabel lblFileName;
	private JLabel lblStudentNameID;
	private JLabel lblTeachersMarks;
	private JLabel lblStudentsRemaining;
	private	String skipHeaderLine;
	private	File file;
	private Scanner in;

	/**
	 * Constructor of the Panel class
	 */
	public Panel() {

		setBackground(Color.CYAN);
		setLayout(new GridLayout(4, 1, 0, 0));
		setPreferredSize(new Dimension(600, 600));

		btnFileOpener = new JButton("Choose file");
		btnFileOpener.addActionListener(new ClickListener());

		btnSelectRandomStudent = new JButton("Select (new) student to Evaluate");
		btnSelectRandomStudent.addActionListener(new ClickListener());
		btnSelectRandomStudent.setVisible(false);

		btnMarkStudent = new JButton("Update Mark");
		btnMarkStudent.addActionListener(new ClickListener());
		btnMarkStudent.setVisible(false);

		btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(new ClickListener());
		btnSaveFile.setVisible(false);

		lblFileName = new JLabel();
		lblStudentNameID = new JLabel();
		lblTeachersMarks = new JLabel();
		lblStudentsRemaining = new JLabel();

		lblFileName.setForeground(Color.RED);
		lblStudentsRemaining.setForeground(Color.MAGENTA);
		btnSaveFile.setForeground(Color.RED);

		add(btnFileOpener);
		add(lblFileName);
		add(btnSaveFile);
		add(btnSelectRandomStudent);
		add(btnMarkStudent);
		add(lblStudentNameID);
		add(lblTeachersMarks);
		add(lblStudentsRemaining);

	}

	/**
	 * This method is used to get the CSV file to be evaluated using JFileChooser
	 * @return
	 */
	private File getFile() {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inFile = new File(chooser.getSelectedFile().toString());
			return inFile;
		}
		return null;

	}

	/**
	 * This method is used to load and parse the CSV file 
	 * @param fn
	 * @param sect
	 */
	private void loadStudents(File fn, Section sect) {
		int MAX_COLS = 5;

		try {
			in = new Scanner(fn);
			String line = "";
			String[] studentInfo;
			skipHeaderLine = in.nextLine();
			sect.setFileAbsPath(fn.getAbsolutePath());
			while (in.hasNextLine()) {
				line = in.nextLine();
				studentInfo = line.split(",");
				if (studentInfo.length == MAX_COLS) {
					sect.addStudent(new Student(studentInfo[0], studentInfo[1], Integer.valueOf(studentInfo[2]), 
							Integer.valueOf(studentInfo[3]), Integer.valueOf(studentInfo[4]))); 

				} else {
					throw new Exception(
							"Invalid file format. \nExpected: Firstname, Lastname, Id, Content mark, Delivery mark");
				}
			}
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * This class is used to get user input through buttons and input dialogs 
	 * @author hrutvik
	 *
	 */
	private class ClickListener implements ActionListener {

		/**
		 * This method is used to invoke an ActionEvent on a Button (used to get input from buttons)
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == btnFileOpener) {

				try {
					file = getFile();

					sect = new Section(); // creates section object called sect
					new Student();

					loadStudents(file, sect);

					remove(btnFileOpener);
					lblFileName.setText("File you are currently evaluating: " + file.getName());
					lblFileName.setVisible(true);
					btnSelectRandomStudent.setVisible(true);
				} catch (Exception e) {
					e.getMessage();
					JOptionPane.showMessageDialog(null,
							"you have exited the program, re run if you want to try evaluating agian");
					System.exit(0);
				}

			}

			if (event.getSource() == btnSelectRandomStudent) {
				randomStudent = sect.randomStudent();
				lblStudentNameID
						.setText("currently evaluating: " + sect.currentStudent(randomStudent).getIdentification());
				lblStudentNameID.setVisible(true);
				btnMarkStudent.setVisible(true);
				lblTeachersMarks.setVisible(false);
			}

			if (event.getSource() == btnMarkStudent) { // update mark
				try {
					content = JOptionPane.showInputDialog(null, "Content mark (out of 100)? ");
					resultContent = JOptionPane.showConfirmDialog(null, "Confirm mark of " + content + " (Yes/No)?");

					delivery = JOptionPane.showInputDialog(null, "delivery mark (out of 100)? ");
					resultDelivery = JOptionPane.showConfirmDialog(null, "Confirm mark of " + delivery + " (Yes/No)?");

					if ((Integer.valueOf(content) >= 0 && Integer.valueOf(content) <= 100)
							&& (Integer.valueOf(delivery) >= 0 && Integer.valueOf(delivery) <= 100)) {
						if (resultContent == JOptionPane.YES_OPTION && resultDelivery == JOptionPane.YES_OPTION) {

							if (Integer.valueOf(content) > 100 || Integer.valueOf(delivery) < 0) {

								JOptionPane.showMessageDialog(null, "INVALID INPUT");
								lblStudentNameID.setVisible(false);
								lblTeachersMarks.setVisible(false);
								btnMarkStudent.setVisible(false);
							}

							else {
								if (lblTeachersMarks.equals(null)) {
									lblTeachersMarks.setText("content mark: " + "delivery mark: ");
								} else {
									lblTeachersMarks
											.setText("content mark: " + content + " delivery mark: " + delivery);
									lblTeachersMarks.setVisible(true);
								}
								
								sect.addMarkedStudent(new Student(sect.currentStudent(randomStudent).getFirstName(),
										sect.currentStudent(randomStudent).getLastName(),
										sect.currentStudent(randomStudent).getID(), Integer.valueOf(content),
										Integer.valueOf(delivery)));
								sect.removeStudent(); 
														
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"NO MARK WILL BE ENTERED FOR NOW BECAUSE YOU EITHER SAID NO TO DELIVERY OR NO TO CONTENT");
						}
						btnMarkStudent.setVisible(false);

						btnSaveFile.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "INVALID INPUT");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "an error occured, please enter LEGAL marks");
				}

			}

			int count = sect.numRemaining() ;
			lblStudentsRemaining.setText("There are " + count + " students remaining to mark.");
			lblStudentsRemaining.setVisible(true);

			if (count == 0) {
				lblStudentsRemaining.setText("You're done marking, click save");
			}

			if (event.getSource() == btnSaveFile) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?");
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null,"The file was saved in the follwoing directory: "+ file.getParent());
					writeToFile();
					lblTeachersMarks.setVisible(false);
				}
				if (count == 0) {
					System.exit(0);
				}
			}
			if (sect.numRemaining() == 0) {
				btnSelectRandomStudent.setVisible(false);
				lblTeachersMarks.setVisible(false);
				lblStudentNameID.setVisible(false);
			}

		}
 
		/**
		 * This method is used to create a new file using a BufferedWriter and to write to the file 
		 */
		private void writeToFile() {
			try { // exception handling
				File outFile = new File(sect.getFileName() + "-FINAL.csv");
				FileWriter out = new FileWriter(outFile); // FileWriter object
				BufferedWriter writeFile = new BufferedWriter(out);

				writeFile.write(skipHeaderLine);
				writeFile.newLine();
				for (int i = 0; i < sect.markedStudents.size(); i++) {
					writeFile.write(sect.getMarkedStudent(i).getMarkedStudentInfo());
					writeFile.newLine();
				}
				writeFile.close();
				out.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "an error occured!!");
			}
		}
	}
}
