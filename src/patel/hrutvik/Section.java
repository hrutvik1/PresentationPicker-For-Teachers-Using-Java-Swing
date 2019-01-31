package patel.hrutvik;

import java.util.*;

/**
 * This class contains methods that are called in the Panel which are used to manipulate the students ArrayList and markedStudents ArrayList
 * @author hrutvik
 *
 */
public class Section {
	private ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<Student> markedStudents = new ArrayList<Student>();
	private String fileName;
	private int randomIndex = 0;

	/**
	 * This method is used to add student objects to the students ArrayList 
	 * The parameter takes in properties of the student object (first/last name, id, content, delivery) 
	 * @param studentInformation
	 */
	public void addStudent(Student studentInformation) {
		students.add(studentInformation);
	}

	/**
	 * This method is used to add student objects that have been evaluated to the markedStudents ArrayList.
	 * The parameter takes in properties of the student object (first/last name, id, content, delivery) 
	 * @param markedStudentInformation
	 */
	public void addMarkedStudent(Student markedStudentInformation) {
		markedStudents.add(markedStudentInformation);
	}

	/**
	 * This method returns a student object at a random index of the markedStudents ArrayList
	 * The parameter it takes is a random index generated by the randomStudent method
	 * @param i
	 * @return
	 */
	public Student getMarkedStudent(int i) {
		return markedStudents.get(i);
	}

	/**
	 * This mutator method is used to set the fileName  
	 * @param filePath
	 */
	public void setFileAbsPath(String filePath) {

		fileName = filePath.substring(0, filePath.indexOf('.'));
	}

	/**
	 * This accessor method is used to return the fileName
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * This method is used to return the number of student objects in the students ArrayList
	 * @return
	 */
	public int numRemaining() {
		return students.size();
	}

	/**
	 * This method is used to return a Student object at a random index generated by the randomStudent method in the students ArrayList
	 * @param x
	 * @return
	 */
	public Student currentStudent(int x) {
		return students.get(x);
	}

	/**
	 * This method is used to generate a random number which will be used in various methods for retriving Student objects from ArrayLists
	 * @return
	 */
	public int randomStudent() { 
		randomIndex = (int) (Math.random() * (students.size()));
		return randomIndex;
	}

	/**
	 * This mehtod is used to remove a Student object at a random index generated by the randomStudent method in the students ArrayList
	 * @return
	 */
	public Student removeStudent() {
		return students.remove(randomIndex);
	}

}
