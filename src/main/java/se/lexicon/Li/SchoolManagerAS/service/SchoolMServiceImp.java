package se.lexicon.Li.SchoolManagerAS.service;

import java.time.LocalDate;
import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;
import se.lexicon.Li.SchoolManagerAS.util.NullChecker;
import se.lexicon.Li.SchoolManagerAS.data.*;

public class SchoolMServiceImp implements SchoolMService {

	private static CourseDao courseDao = new CourseDaoList();
	private static StudentDao studentDao = new StudentDaoList();

	@Override
	public Student registerNewStudent(String name, String email, String address) {
		if (NullChecker.nullCheck(name, email, address))
			throw new NullPointerException("NullPointerException in " + this.getClass());

		Student newStudent = new Student(name, email, address);
		if (addStudent(newStudent)) {
			return newStudent;
		} else {
			System.out.println("Could not create Student.");
			return null;
		}
	}

	@Override
	public Course registerNewCourse(String courseName, LocalDate startDate, int weekDuration) {

		if (NullChecker.nullCheck(courseName, startDate, weekDuration))
			throw new NullPointerException("NullPointerException in " + this.getClass());

		Course newCourse = new Course(courseName, startDate, weekDuration);
		if (addCourse(newCourse)) {
			return newCourse;
		} else {
			System.out.println("Could not create Course.");
			return null;
		}

	}
	
	@Override
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
	}
	
	@Override
	public boolean addCourse(Course course) {
		return courseDao.addCourse(course);
	}

	@Override
	public void addStudentToCourse(Course course, Student... students) {
		if (NullChecker.nullCheck(course, students))
			throw new NullPointerException("NullPointerException in " + this.getClass());
		for (Student s : students) {
			addStudentToCourse(course, s);
		}
	}

	@Override
	public boolean addStudentToCourse(Course course, Student student) {
		if (NullChecker.nullCheck(student, course))
			throw new NullPointerException("NullPointerException in " + this.getClass());

		if (!course.getStudentList().contains(student)) {
			course.getStudentList().add(student);
			return true;
		} else {
			System.out.println("Could not add Student " + student.getName() + " to \n"
					+ "Course "+course.getCourseName()+", duplicate found.");
			return false;
		}
	}

	@Override
	public boolean removeStudent(Student student) {
		for (Course c : courseDao.findAll()) {
			c.getStudentList().remove(student);
		}
		return studentDao.removeStudent(student);
	}

	@Override
	public boolean removeCourse(Course course) {
		return courseDao.removeCourse(course);
	}

	@Override
	public Student findStudentByEmail(String email) {
		return studentDao.findByEmail(email);
	}

	@Override
	public List<Student> findStudentByName(String name) {
		return studentDao.findByName(name);
	}

	@Override
	public Student findStudentById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public List<Student> findAllStudent() {
		return studentDao.findAll();
	}

	@Override
	public void removeAllStudent() {
		for (Student s : studentDao.findAll()) {
			for (Course c : courseDao.findAll()) {
				c.getStudentList().remove(s);
			}
		}
		studentDao.removeAll();
	}

	@Override
	public Course findCourseById(int id) {
		return courseDao.findById(id);
	}

	@Override
	public List<Course> findCourseByName(String name) {
		return courseDao.findByName(name);
	}

	@Override
	public List<Course> findCourseByDate(LocalDate date) {
		return courseDao.findByDate(date);
	}

	@Override
	public List<Course> findAllCourse() {
		return courseDao.findAll();
	}

	@Override
	public List<Course> findCourseByStudent(Student student) {
		return courseDao.findByStudent(student);
	}

	@Override
	public void removeAllCourse() {
		courseDao.removeAll();

	}

}
