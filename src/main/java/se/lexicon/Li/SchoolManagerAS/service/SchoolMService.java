package se.lexicon.Li.SchoolManagerAS.service;

import java.time.LocalDate;
import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;

public interface SchoolMService {
	
	Student registerNewStudent(String name, String email, String address);

	Course registerNewCourse(String courseName, LocalDate startDate, int weekDuration);
	
	boolean addStudent(Student student);
	
	boolean addCourse(Course course);
	
	boolean addStudentToCourse(Course course, Student student);
	
	void addStudentToCourse(Course course, Student...students);

	boolean removeStudent(Student student);

	boolean removeCourse(Course course);

	Student findStudentByEmail(String email);

	List<Student> findStudentByName(String name);

	Student findStudentById(int id);
	
	List<Student> findAllStudent();
	
	void removeAllStudent();
	
	Course findCourseById(int id);

	List<Course> findCourseByName(String name);

	List<Course> findCourseByDate(LocalDate date);

	List<Course> findAllCourse();
		
	List<Course> findCourseByStudent(Student student);

	void removeAllCourse();
	
}
