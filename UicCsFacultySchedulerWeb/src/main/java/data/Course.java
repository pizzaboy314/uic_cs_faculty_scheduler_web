package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Data object to represent a CS course.
 * 
 * @author bryan
 *
 */
public class Course implements Comparable<Course>{
	
	/**
	 * Official title of course.
	 */
	private String name;
	/**
	 * Course number.
	 */
	private int number;
	/**
	 * The number of undergrad hours this course is worth.
	 */
	private int underGradHours;
	/**
	 * The number of grad hours this course is worth.
	 */
	private int gradHours;
	/**
	 * An ArrayList of instructors that teach this course.
	 */
	private List<Instructor> instructors;
	
	/**
	 * Just a regular old constructor.
	 */
	public Course(){
		instructors = new ArrayList<Instructor>();
	}
	
	/**
	 * Determines a random instructor to teach this course, chosen from the
	 * ArrayList instructors field.
	 * 
	 * @return An instructor object.
	 */
	public Instructor chooseInstructor() {
		Instructor dude;
		if (instructors.size() > 0) {
			Random r = new Random(System.currentTimeMillis());
			int i = r.nextInt(instructors.size());
			dude = instructors.get(i);
		} else {
			dude = new Instructor();
			dude.setName("N/A");
		}
		return dude;
	}

	/**
	 * Adds an instructor to this course's list of instructors.
	 * 
	 * @param dude
	 *            The instructor to add to this course.
	 */
	public void addInstructor(Instructor dude) {
		if (hasInstructor(dude) == false) {
			instructors.add(dude);
		}
	}

	/**
	 * Determines whether or not the given instructor is already in the list.
	 * This way, he/she can't be added twice.
	 * 
	 * @param dude
	 *            The instructor to check.
	 * @return A boolean indicating whether or not the list contains the given
	 *         instructor.
	 */
	public boolean hasInstructor(Instructor dude) {
		boolean b = false;
		for (Instructor i : instructors) {
			if (i.getName().equals(dude.getName())) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * Creates a nice, readable plaintext representation of this object's
	 * attributes.
	 * 
	 * @return A string of plaintext containing this object's attributes.
	 */
	public String fileBlock(){
		StringBuilder s = new StringBuilder();
		s.append("CS " + number + ": ");
		s.append(name + "\n");
		if(gradHours == 0){
			s.append("hours: " + underGradHours + "\n");
		} else {
			s.append("undergrad hours: " + underGradHours + "\n");
			s.append("grad hours: " + gradHours + "\n");
		}
		
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
		s.append(number + "\t");
		s.append(underGradHours + "\t");
		s.append(gradHours + "\t");
		
		return s.toString();
	}
	
	/**
	 * Standard toString method.
	 */
	public String toString(){
		if(number > 99){
			return "" + number;
		} else {
			return name;
		}
	}

	/**
	 * Standard getter.
	 * 
	 * @return The name of this course.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Standard setter.
	 * 
	 * @param name
	 *            A string to set as this course's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The number of this course.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Standard setter.
	 * 
	 * @param number
	 *            An int to set as this course's number.
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The number of undergrad hours this course has.
	 */
	public int getUnderGradHours() {
		return underGradHours;
	}

	/**
	 * Standard setter.
	 * 
	 * @param underGradHours
	 *            An int to set as this course's number of undergrad hours.
	 */
	public void setUnderGradHours(int underGradHours) {
		this.underGradHours = underGradHours;
	}

	/**
	 * Standard getter.
	 * 
	 * @return The number of grad hours this course has.
	 */
	public int getGradHours() {
		return gradHours;
	}

	/**
	 * Standard setter.
	 * 
	 * @param gradHours
	 *            An int to set as this course's number of grad hours.
	 */
	public void setGradHours(int gradHours) {
		this.gradHours = gradHours;
	}

	/**
	 * For sorting an ArrayList of courses by their course number.
	 */
	@Override
	public int compareTo(Course c) {
		return number - c.getNumber();
	}
}
