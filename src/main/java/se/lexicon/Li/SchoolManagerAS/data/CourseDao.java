package se.lexicon.Li.SchoolManagerAS.data;

import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;

import java.time.LocalDate;

public interface CourseDao {

	boolean addCourse(Course course);

	boolean removeCourse(Course course);

	Course findById(int id);

	List<Course> findByName(String name);

	List<Course> findByDate(LocalDate date);

	List<Course> findAll();

	List<Course> findByStudent(Student student);

	void removeAll();

}