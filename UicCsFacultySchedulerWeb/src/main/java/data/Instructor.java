package data;

/**
 * Data object to represent a CS instructor.
 * 
 * @author bryan
 *
 */
public class Instructor implements Comparable<Instructor>{

	/**
	 * The full name of this instructor.
	 */
	private String name;
	/**
	 * The first name of this instructor.
	 */
	private String firstName;
	/**
	 * The last name of this instructor.
	 */
	private String lastName;
	/**
	 * The title of this instructor.
	 */
	private String title;
	/**
	 * This instructor's professional background.
	 */
	private String background;
	/**
	 * This instructor's email address.
	 */
	private String email;
	/**
	 * The name of this instructor's degree.
	 */
	private String degName;
	/**
	 * The year this instructor's degree was completed.
	 */
	private int degYear;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course1 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course2 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course3 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course4 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course5 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course6 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course7 = 99;
	/**
	 * A course slot to store a course this instructor teaches. If the "slot" is
	 * empty, 99 is used as a placeholder.
	 */
	private int course8 = 99;

	/**
	 * Just a regular constructor.
	 */
	public Instructor() {
		
	}

	/**
	 * Standard getter.
	 * 
	 * @return This instructor's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Determines whether or not this instructor teaches the given course.
	 * 
	 * @param c
	 *            The course to check.
	 * @return A boolean indicating whether or not this instructor teaches the
	 *         given course.
	 */
	public boolean doesTeachCourse(Course c) {
		boolean result = false;
		int num = c.getNumber();

		if (num == course1 || num == course2 || num == course3 || num == course4 || num == course5 || num == course6 || num == course7
				|| num == course8) {
			result = true;
		}
		return result;
	}

	/**
	 * Standard setter. Also sets the first and last name fields.
	 * 
	 * @param name
	 *            The name to be assigned to this instructor.
	 */
	public void setName(String name) {
		String[] split = name.split(" "); 
		
		this.name = name;
		this.firstName = split[0];
		this.lastName = split[split.length-1];
	}
	
	/**
	 * Creates a nice, readable plaintext representation of this object's
	 * attributes.
	 * 
	 * @return A string of plaintext containing this object's attributes.
	 */
	public String fileBlock(){
		StringBuilder s = new StringBuilder();
		s.append("name\t\t" + name + "\n");
		s.append("title\t\t" + title + "\n");
		s.append("email\t\t" + email + "\n");
		s.append("degName\t\t" + degName + "\n");
		s.append("degYear\t\t" + degYear + "\n");
		s.append("background\t" + background + "\n");
		s.append("course1\t\t" + course1 + "\n");
		s.append("course2\t\t" + course2 + "\n");
		s.append("course3\t\t" + course3 + "\n");
		s.append("course4\t\t" + course4 + "\n");
		s.append("course5\t\t" + course5 + "\n");
		s.append("course6\t\t" + course6 + "\n");
		s.append("course7\t\t" + course7 + "\n");
		s.append("course8\t\t" + course8 + "\n\n");
		
		return s.toString();
	}
	
	/**
	 * Creates a single-line TSV style representation of this object's data.
	 * 
	 * @return A single TSV line.
	 */
	public String tsvLine(){
		StringBuilder s = new StringBuilder();
		s.append(name + "\t");
		s.append(title + "\t");
		s.append(email + "\t");
		s.append(degName + "\t");
		s.append(degYear + "\t");
		s.append(background + "\t");
		s.append(course1 + "\t");
		s.append(course2 + "\t");
		s.append(course3 + "\t");
		s.append(course4 + "\t");
		s.append(course5 + "\t");
		s.append(course6 + "\t");
		s.append(course7 + "\t");
		s.append(course8);
		
		return s.toString();
	}
	
	/**
	 * Standard toString method.
	 * 
	 * @return The name of this instructor.
	 */
	public String toString(){
		return name;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Standard setter.
	 * 
	 * @param email
	 *            The value to assign to this field.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getDegName() {
		return degName;
	}

	/**
	 * Standard setter.
	 * 
	 * @param degName
	 *            The value to assign to this field.
	 */
	public void setDegName(String degName) {
		this.degName = degName;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getDegYear() {
		return degYear;
	}

	/**
	 * Standard setter.
	 * 
	 * @param degYear
	 *            The value to assign to this field.
	 */
	public void setDegYear(int degYear) {
		this.degYear = degYear;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Standard setter.
	 * 
	 * @param title
	 *            The value to assign to this field.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Standard setter.
	 * 
	 * @param background
	 *            The value to assign to this field.
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Standard setter.
	 * 
	 * @param firstName
	 *            The value to assign to this field.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Standard setter.
	 * 
	 * @param lastName
	 *            The value to assign to this field.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse1() {
		return course1;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course1
	 *            The value to assign to this field.
	 */
	public void setCourse1(int course1) {
		this.course1 = course1;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse2() {
		return course2;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course2
	 *            The value to assign to this field.
	 */
	public void setCourse2(int course2) {
		this.course2 = course2;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse3() {
		return course3;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course3
	 *            The value to assign to this field.
	 */
	public void setCourse3(int course3) {
		this.course3 = course3;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse4() {
		return course4;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course4
	 *            The value to assign to this field.
	 */
	public void setCourse4(int course4) {
		this.course4 = course4;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse5() {
		return course5;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course5
	 *            The value to assign to this field.
	 */
	public void setCourse5(int course5) {
		this.course5 = course5;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse6() {
		return course6;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course6
	 *            The value to assign to this field.
	 */
	public void setCourse6(int course6) {
		this.course6 = course6;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse7() {
		return course7;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course7
	 *            The value to assign to this field.
	 */
	public void setCourse7(int course7) {
		this.course7 = course7;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The referenced field.
	 */
	public int getCourse8() {
		return course8;
	}

	/**
	 * Standard setter.
	 * 
	 * @param course8
	 *            The value to assign to this field.
	 */
	public void setCourse8(int course8) {
		this.course8 = course8;
	}
	
	/**
	 * For sorting an ArrayList of instructors by their last name, then their
	 * first name, then their degree year.
	 */
	@Override
	public int compareTo(Instructor dude) {
		
		if(!this.lastName.equalsIgnoreCase(dude.getLastName())){
			return this.lastName.compareTo(dude.getLastName());
		}
		if(!this.firstName.equalsIgnoreCase(dude.getFirstName())){
			return this.firstName.compareTo(dude.getFirstName());
		}
		return this.degYear - dude.getDegYear();
	}
}
