package se.lexicon.Li.SchoolManagerAS.data;

import java.util.List;
import java.util.ArrayList;

import se.lexicon.Li.SchoolManagerAS.models.Student;

public class StudentDaoList implements StudentDao {
	private static List<Student> students = new ArrayList<>();

	@Override
	public boolean addStudent(Student student) {
		if (student == null) {
			return false;
		}

		for (Student s : students) {
			if (s.getEmail().equals(student.getEmail())) {
				System.out.println("Email is already used.");
				return false;
			}
		}
		if (students.contains(student)) {
			return false;
		}
		return students.add(student);
	}

	@Override
	public boolean removeStudent(Student student) {
		if (student == null) {
			return false;
		}

		if (students.contains(student)) {
			return students.remove(student);
		}
		return false;
	}

	@Override
	public Student findByEmail(String email) {
		for (Student s : students) {
			if (s.getEmail().equals(email)) {
				return s;
			}
		}
		return null;

	}

	@Override
	public List<Student> findByName(String name) {
		List<Student> re = new ArrayList<>();

		for (Student s : students) {
			if (s.getName().toLowerCase().contains(name.toLowerCase())) {
				re.add(s);
			}
		}
		return re;
	}

	@Override
	public Student findById(int id) {
		for (Student e : students) {
			if (e.getID() == id) {
				return e;
			}
		}
		return null;
	}

	@Override
	public List<Student> findAll() {
		return students;
	}

	@Override
	public void removeAll() {
		students.clear();
	}

}
