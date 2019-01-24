package se.lexicon.Li.SchoolManagerAS.serviceTest;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;
import se.lexicon.Li.SchoolManagerAS.service.SchoolMService;
import se.lexicon.Li.SchoolManagerAS.service.SchoolMServiceImp;

public class SchoolMServiceImpTest {

	private SchoolMService underTest = new SchoolMServiceImp();

	private static Student testStudent1;
	private static Student testStudent2;
	private static Course testCourse;

	@Before
	public void init() {
		testStudent1 = new Student("Test1", "n1@email", "P1");
		underTest.addStudent(testStudent1);
		testStudent2 = new Student("Test2", "n2@email", "P2");
		underTest.addStudent(testStudent2);
		underTest.registerNewStudent("Test3", "n3@email", "P3");
		
		testCourse= new Course("T_course1", LocalDate.now(), 4);
		underTest.addCourse(testCourse);
		underTest.registerNewCourse("T_course2", LocalDate.now(), 3);
		
		underTest.addStudentToCourse(underTest.findAllCourse().get(0), underTest.findAllStudent().get(0),
				underTest.findAllStudent().get(1));
		underTest.addStudentToCourse(underTest.findAllCourse().get(1), underTest.findAllStudent().get(1),
				underTest.findAllStudent().get(2));
	}
	
	@After
	public void tearDown() {
		underTest.removeAllStudent();
		underTest.removeAllCourse();
	}
	
	@Test 
	public void test_register_with_duplicate_parameter() {
		assertNull(underTest.registerNewStudent("Test123", "n1@email", "P123"));
		assertNull(underTest.registerNewCourse("T_course2", LocalDate.now() , 3));
	}
	
	@Test(expected = NullPointerException.class)
	public void test_register_student_with_null_throw_NullPointerException() {
		underTest.registerNewStudent("Lee", null, "P1243");
	}
	
	@Test(expected = NullPointerException.class)
	public void test_register_course_with_null_throw_NullPointerException() {
		underTest.registerNewCourse(null, LocalDate.now() , 3);
	}
	
	@Test
	public void test_add_student_to_course() {
		//testStundent is added to T_course1
		assertTrue(underTest.findAllCourse().get(0).getStudentList().contains(testStudent1));
		assertFalse(underTest.findAllCourse().get(1).getStudentList().contains(testStudent1));
	}
	
	@Test
	public void test_check_list_in_course_after_remove_studnet() {
		underTest.removeStudent(testStudent1);
		
		assertFalse(underTest.findAllStudent().contains(testStudent1));
		assertFalse(underTest.findAllCourse().get(0).getStudentList().contains(testStudent1));
		assertTrue(underTest.findAllCourse().get(1).getStudentList().contains(testStudent2));
	}
}
