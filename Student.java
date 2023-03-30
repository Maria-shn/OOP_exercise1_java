/** 
 * Represents a student.
 */
public class Student {
	
	private int sid;                             
	private String name;                         
	private LinkedList<CourseTaken> courseList;
	
	/** 
	 * Constructs a new student with the given sid and name, and an empty course list.
	 * @param sid  the student's id
	 * @param name the student's name
	 */
	public Student(int sid, String name) {
		this.sid = sid;
		this.name = name;
		this.courseList = new LinkedList<CourseTaken>();
	}
	
	/** 
	 * Returns the id of this student.
	 * @return the sid of this student.
	 */
	public int getSid() {
		return this.sid;
	}
	
	/** 
	 * Returns the name of this student.
	 * @return the name of this student.
	 */
	public String getName() {
		return this.name;
	}
	
	/** 
	 * Adds the given course and grade to the course list of this student.
	 * @param c     the course to add
	 * @param grade the grade in the added course 
	 */
	public void addCourse(Course c, int grade) {
		// Put your code here.
		if(!tookCourse(c)){
		CourseTaken courseTaken = new CourseTaken (c, grade);
		courseList.add(courseTaken);
		} else {
			ListIterator<CourseTaken> listIterator = courseList.iterator();
		    CourseTaken courseTaken = new CourseTaken (c, grade);
		  while(listIterator.hasNext()){
			CourseTaken current = listIterator.next();
			if(current.getCourse().equals(courseTaken.getCourse())){
				int index = courseList.indexOf(current);
				courseList.remove(current);
				courseList.add(courseTaken, index);
			}
			
		}
		}
	}
	
	/** 
	 * Returns the grade that this student got in the given course, 
	 *  or -1 if the course was not taken by this student.
	 * @param c - the returned grade will be the grade in this course.
	 * @return the grade that this student got in the given course
	 */
	public int gradeInCourse(Course c) {
		// Replace the following statement with your code.
		if(this.tookCourse(c)){
			ListIterator<CourseTaken> listIterator = courseList.iterator();
		CourseTaken current = courseList.getFirst();
		CourseTaken toFind = new CourseTaken(c, 0);
		for(int i= 0; i<courseList.size(); i++){
			if(current.getCourse().equals(toFind.getCourse())){
				return current.getGrade();
			} else if(listIterator.hasNext()){
				current = listIterator.next();
			}
			
		}
		}
		return -1;
	}
	
	/** 
	 * Returns true if this student took the given course, false otherwise.
	 * @param c  the course we want to know whether or not the student took.
	 * @return true if this student took the given course, false otherwise.
	 */
	public boolean tookCourse(Course c) {
		// Replace the following statement with your code.
		ListIterator<CourseTaken> listIterator = courseList.iterator();
		CourseTaken toFind = new CourseTaken(c, 0);
		while(listIterator.hasNext()){
			CourseTaken current = listIterator.next();
			if(current.getCourse().equals(toFind.getCourse())){
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * Returns a string which contains
	 * 1) this student
	 * 2) all the courses that s/he took
	 * 3) the grade point average
	 */
	public String studentReport() {
		// Put your code here.
		String stringCourseList =  "";
		double gpa = 0.0;
		ListIterator<CourseTaken> listIterator = courseList.iterator();
		if(courseList.size() >0){
		while(listIterator.hasNext()){
			    CourseTaken courseTaken = listIterator.next();
				stringCourseList = stringCourseList + "\n" + courseTaken;
				gpa = gpa + courseTaken.getGrade();
			}
		if(gpa!=0){
		gpa = gpa/(courseList.size()*1.0);
		}
	return this.toString() + ":" +  stringCourseList + "\n" + "\n" + "Average: " + gpa + "\n" ;
	}else {
		return this.toString() +  "\n" + "()" +  "\n" + "Average: -1.0";
}

	}

	
	/** 
	 * Two students will be consdired equals iff they have the same sid
	 * @param other the other Student
	 * @return true/false
	 */
	public boolean equals(Student other){
	if(this.getSid() == other.getSid()){
		return true;
	}
		return false;
	}
	/**
	 * Textual representation of this student.
	 */
	public String toString() {
		return "Student " + this.sid + ": " + this.name;
	}
}