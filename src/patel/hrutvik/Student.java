package patel.hrutvik;

/**
 * This class contains methods that are called in the Panel class which are used to modify different ArrayLists and retrive information about Student objects 
 * @author hrutvik
 *
 */
public class Student {

	/**
	 * Each Student object has 5 properties (first/last Name, ID, content mark, delivery mark)
	 */
	private int id, content, delivery;
	private String firstName, lastName;

	/**
	 * This constructor is used to create/initialize a Student object
	 * @param first
	 * @param last
	 * @param id
	 * @param content
	 * @param delivery
	 */
	public Student(String first, String last, int id, int content, int delivery) { 
																				
		this.id = id;
		this.content = content;
		this.delivery = delivery;
		this.firstName = first;
		this.lastName = last;
	}

	/**
	 * This constructor is an overloaded constructor (called with no parameters)
	 */
	public Student() { 
					
	}

	/**
	 * This accessor method is used to return the firstName of the Student object
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This accessor method is used to return the lastName of the Student object
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This accessor method is used to return the ID of the Student object
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * This accessor method is called in the panel when using the label lblStudentNameID
	 * The label displays the student being evaluated for the user
	 * @return
	 */
	public String getIdentification() {

		String identification = firstName + ", " + lastName;
		return identification;
	}

	/**
	 * This accessor method is used to return the information/properties of a Student object that has been evaluated
	 * @return
	 */
	public String getMarkedStudentInfo() {
		return firstName + "," + lastName + "," + String.valueOf(id) + "," + String.valueOf(content) + ","
				+ String.valueOf(delivery);
	}

}
