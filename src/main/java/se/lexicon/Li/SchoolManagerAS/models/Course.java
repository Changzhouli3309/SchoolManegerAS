package se.lexicon.Li.SchoolManagerAS.models;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import static se.lexicon.Li.SchoolManagerAS.util.UtilNumber.addZero;

public class Course {

	private static int sequencer = 0;
	private final int ID;
	private String courseName;
	private LocalDate startDate;
	private int weekDuration;
	private List<Student> studentList;

	@Override
	public String toString() {
		String re = "";
		if (studentList.size() != 0) {
			re += "---Student-List---\n";
			for (int i = 0; i < studentList.size(); i++) {
				re += i + "-ID: " + addZero(studentList.get(i).getID(), 2) + ", " 
			+ "Name: "+studentList.get(i).getName() + "\n";
			}
			re += "------------------";
		} else {
			re += "No student in course.";
		}

		return "Course, ID: " + addZero(ID, 2) + ", Name: " + courseName + "\nStartDate: " + startDate
				+ ", WeekDuration: " + weekDuration + "\n" + re;
	}

	/**
	 * 
	 * @param courseName
	 * @param startDate    :LocalDate.now()
	 * @param weekDuration
	 */
	public Course(String courseName, LocalDate startDate, int weekDuration) {
		ID = sequencer++;
		this.courseName = courseName;
		this.startDate = startDate;
		this.weekDuration = weekDuration;
		studentList = new ArrayList<>();
	}

	public int getID() {
		return ID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getWeekDuration() {
		return weekDuration;
	}

	public void setWeekDuration(int weekDuration) {
		this.weekDuration = weekDuration;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

}