package se.lexicon.Li.SchoolManagerAS.data;

import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;

import java.time.LocalDate;
import java.util.ArrayList;

public class CourseDaoList implements CourseDao {

	private static List<Course> courses=new ArrayList<>();

	@Override
	public boolean addCourse(Course course) {
		if(course == null) {
			return false;
		}
		
		for (Course c:courses) {
			if (c.getCourseName().equals(course.getCourseName())) {
				System.out.println("Course name is used.");
				return false;
			}
		}
		if(courses.contains(course)) {
			return false;
		}
		return courses.add(course);
	}

	@Override
	public boolean removeCourse(Course course) {
		if (course == null) {
			return false;
		}

		if (courses.contains(course)) {
			return courses.remove(course);
		}
		return false;
	}

	@Override
	public Course findById(int id) {
		for (Course c : courses) {
			if (c.getID() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Course> findByName(String name) {
		List<Course> re = new ArrayList<>();

		for (Course c : courses) {
			if (c.getCourseName().toLowerCase().contains(name.toLowerCase())) {
				re.add(c);
			}
		}
		return re;
	}

	@Override
	public List<Course> findByDate(LocalDate date) {
		List<Course> re = new ArrayList<>();

		for (Course c : courses) {
			if (c.getStartDate().equals(date)) {
				re.add(c);
			}
		}
		return re;
	}

	@Override
	public List<Course> findAll() {
		return courses;
	}

	@Override
	public List<Course> findByStudent(Student student) {
		List<Course> re = new ArrayList<>();

		for (Course c : courses) {
			if (c.getStudentList().contains(student)) {
				re.add(c);
			}
		}
		return re;
	}
	
	@Override
	public void removeAll() {
		courses.clear();
	}


}
