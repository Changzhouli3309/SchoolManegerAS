package se.lexicon.Li.SchoolManagerAS.dataTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lexicon.Li.SchoolManagerAS.data.CourseDao;
import se.lexicon.Li.SchoolManagerAS.data.CourseDaoList;
import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;;

public class CourseDaoListTest {
	private CourseDao underTest = new CourseDaoList();

	private static Student testStudent;
	private static LocalDate startDate;
	private static Course testCourse;
	private static int courseID; 

	@Before
	public void init() {
		testStudent = new Student("Test1", "n1@email", "P1");
		startDate=LocalDate.now();
		testCourse= new Course("T_course", LocalDate.now(), 4);
		courseID=testCourse.getID();
		testCourse.getStudentList().add(testStudent);
		
		underTest.addCourse(testCourse);
		underTest.addCourse(new Course("T_course2", LocalDate.now(), 3));
		
		underTest.findAll().get(1).getStudentList().add(new Student("Test2","n2@email","P2"));
	}

	@After
	public void tear_down() {
		underTest.removeAll();
	}

	@Test
	public void test_add_new_course() {
		Course expected = new Course("T_course3", LocalDate.now(), 1);
		assertTrue(underTest.addCourse(expected));

		assertFalse(underTest.addCourse(testCourse));
	}

	@Test
	public void test_find_course_by_ID() {
		assertEquals(testCourse, underTest.findById(courseID));

		assertNull(underTest.findById(-1));
	}

	@Test
	public void test_find_course_by_name() {
		String param = "Test";

		List<Course> result = underTest.findByName(param);
		for (Course s : result) {
			assertTrue(s.getCourseName().contains(param));
		}
	}

	@Test
	public void test_find_course_by_name_with_lambda() {
		String param = "Test";

		List<Course> result = underTest.findByName(param);
		assertTrue(result.stream().allMatch(student -> student.getCourseName().contains(param)));
	}

	@Test 
	public void test_find_course_by_start_date() {
		List<Course> result = underTest.findByDate(startDate);
		for (Course s : result) {
			assertTrue(s.getStartDate().equals(startDate));
		}
	}
	
	@Test
	public void test_delete_course() {
		assertFalse(underTest.removeCourse(null));

		assertTrue(underTest.removeCourse(testCourse));

		assertFalse(underTest.removeCourse(new Course("T_course4", LocalDate.now(), 1)));

	}
	
	@Test 
	public void test_find_course_by_student() {
		List<Course> result = underTest.findByStudent(testStudent);
		for (Course s : result) {
			assertTrue(s.getStudentList().contains(testStudent));
		}
		
	}
	
}
