package se.lexicon.Li.SchoolManagerAS.dataTest;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lexicon.Li.SchoolManagerAS.data.StudentDao;
import se.lexicon.Li.SchoolManagerAS.data.StudentDaoList;
import se.lexicon.Li.SchoolManagerAS.models.Student;

import static org.junit.Assert.*;

public class StudentDaoListTest {

	private StudentDao underTest = new StudentDaoList();

	private static Student testStudent;
	private static int studentID;
	private static String studentEmail;

	@Before
	public void init() {
		testStudent = new Student("Test1", "n1@email", "P1");
		underTest.addStudent(testStudent);
		studentID = testStudent.getID();
		studentEmail = testStudent.getEmail();
		underTest.addStudent(new Student("Test2", "n2@email", "P2"));
	}

	@After
	public void tear_down() {
		underTest.removeAll();
	}

	@Test
	public void test_add_new_student() {
		Student expected = new Student("Test3", "n3@email", "P3");
		assertTrue(underTest.addStudent(expected));

		assertFalse(underTest.addStudent(testStudent));
	}

	@Test
	public void test_find_student_by_ID() {
		assertEquals(testStudent, underTest.findById(studentID));

		assertNull(underTest.findById(-1));
	}

	@Test
	public void test_find_student_by_name() {
		String param = "Test";

		List<Student> result = underTest.findByName(param);
		for (Student s : result) {
			assertTrue(s.getName().contains(param));
		}
	}

	@Test
	public void test_find_student_by_name_with_lambda() {
		String param = "Test";

		List<Student> result = underTest.findByName(param);
		assertTrue(result.stream().allMatch(student -> student.getName().contains(param)));
	}

	@Test 
	public void test_find_student_by_email() {
		assertEquals(testStudent, underTest.findByEmail(studentEmail));
		
		assertNull(underTest.findByEmail("123@email"));
	}
	
	@Test
	public void test_delete_student() {
		assertFalse(underTest.removeStudent(null));

		assertTrue(underTest.removeStudent(testStudent));

		assertFalse(underTest.removeStudent(new Student("Test4", "n4@email", "P4")));

	}
}
