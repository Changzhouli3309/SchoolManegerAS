package se.lexicon.Li.SchoolManagerAS.data;

import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Student;

public interface StudentDao {

	boolean addStudent(Student student);

	boolean removeStudent(Student student);

	Student findByEmail(String email);

	List<Student> findByName(String name);

	Student findById(int id);

	List<Student> findAll();

	void removeAll();

}
