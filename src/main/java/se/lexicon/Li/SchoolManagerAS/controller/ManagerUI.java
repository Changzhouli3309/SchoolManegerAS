package se.lexicon.Li.SchoolManagerAS.controller;

import java.time.LocalDate;
import java.util.List;

import se.lexicon.Li.SchoolManagerAS.models.Course;
import se.lexicon.Li.SchoolManagerAS.models.Student;
import se.lexicon.Li.SchoolManagerAS.service.SchoolMService;
import se.lexicon.Li.SchoolManagerAS.service.SchoolMServiceImp;

import static se.lexicon.Li.SchoolManagerAS.util.UserInput.*;
import static se.lexicon.Li.SchoolManagerAS.util.UtilNumber.*;

public class ManagerUI {
	private static SchoolMService sMS = new SchoolMServiceImp();

	/**
	 * Main Menu
	 * 
	 * @1-Pick_and_Edit Not active if no data in both list, if fail to search will
	 *                  back to main menu.
	 * @3-Remove_all_Students Not active if no data in list of students.
	 * @4-Remove_all_Courses Not active if no data in list of courses.
	 */
	public void mainMenu() {
		boolean running = true;
		pl("Welcome to School Maneger");
		while (running) {
			pl("---Existing Student: " + sMS.findAllStudent().size() + ", Course: " + sMS.findAllCourse().size()
					+ "\n---Main-Menu---");
			pl("1-Pick and Edit" + "\n2-Register New Student/Course" + "\n3-Remove all Students"
					+ "\n4-Remove all Courses" + "\n5(0)-Exit");
			switch (getIntFromLimit(5, 0)) {
			case 1:
				if (sMS.findAllStudent().size() != 0 || sMS.findAllCourse().size() != 0) {
					pickMenu();
				} else {
					pl("No Existing Data.\n");
				}
				break;
			case 2:
				registerMenu();
				break;
			case 3:
				if (sMS.findAllStudent().size() != 0) {
					if (yesOrNo("Are u sure to Remove all Students?")) {
						sMS.removeAllStudent();
						pl("All Students are removed.\n");
					}
				} else {
					pl("No Existing Student.\n");
				}
				break;
			case 4:
				if (sMS.findAllCourse().size() != 0) {
					if (yesOrNo("Are u sure to Remove all Courses?")) {
						sMS.removeAllCourse();
						pl("All Courses are removed.\n");
					}
				} else {
					pl("No Existing Course.\n");
				}
				break;
			default:
				running = false;
			}
		}
		pl("---Goodbye! Have a nice day---");
	}

	/**
	 * Choose to register a new student or course,
	 * 
	 * @Fail After fail to create, will go back to main menu
	 * @Success After success to create, can choose to add(add to) student(course)
	 */
	private static void registerMenu() {
		pl("---Register, New: 1-Student, 2-Course (0-Cansel)");
		switch (getIntFromLimit(2, 0)) {
		case 1:
			pl("---New Student");
			String[] inf = { "Student Name: ", "Email: ", "Adress: " };
			for (int i = 0; i < inf.length; i++) {
				pt(inf[i]);
				inf[i] = getNoEmptyString();
			}
			try {
				int newSID = sMS.registerNewStudent(inf[0], inf[1], inf[2]).getID();
				if (yesOrNo("Add to Existing Course?")) {
					do {
						sMS.addStudentToCourse(searchCourse(), sMS.findStudentById(newSID));
					} while (yesOrNo("Add to more?"));
				}
			} catch (NullPointerException e) {
			}
			break;
		case 2:
			pl("---New Course");
			pt("Course Name: ");
			String cn = getNoEmptyString();
			pt("Start Date,\n");
			LocalDate sd = getDate();
			pt("Duration(week): ");
			int wd = getInt();
			try {
				int newCID = sMS.registerNewCourse(cn, sd, wd).getID();
				if (yesOrNo("Add Existing Student to it?")) {
					do {
						sMS.addStudentToCourse(sMS.findCourseById(newCID), searchStundent());
					} while (yesOrNo("Add more?"));
				}
			} catch (NullPointerException e) {
			}
		default:
		}

	}

	/**
	 * Choose to pick a student or course,
	 * 
	 * @Fail After fail to pick one, will go back to main menu
	 * @Success After success to pick, enter the editing menu
	 */
	private static void pickMenu() {
		pl("\n---Pick, 1-Student, 2-Course (0-Cansel)");
		switch (getIntFromLimit(2, 0)) {
		case 1:
			editMenuSt(searchStundent());
			break;
		case 2:
			editMenuCo(searchCourse());
			break;
		default:
		}

	}

	private static Student searchStundent() {
		if (sMS.findAllStudent().size() == 0) {
			pl("No Existing Student.\n");
			return null;
		}
		pl("---Searching Student by:\n1-ID, 2-Name, 3-Email, 4-Show all (0-Cansel)");
		switch (getIntFromLimit(4, 0)) {
		case 1:
			pt("ID: ");
			return nullCheakSt(sMS.findStudentById(getInt()), "ID");
		case 2:
			pt("Name: ");
			return pickStInList(sMS.findStudentByName(getNoEmptyString()), "Name");
		case 3:
			pt("Email:");
			return nullCheakSt(sMS.findStudentByEmail(getNoEmptyString()), "Email");
		case 4:
			return pickStInList(sMS.findAllStudent(), "All");
		default:
			return null;
		}
	}

	/**
	 * Sent out error message
	 * 
	 * @param s
	 * @param msg
	 * @return
	 */
	private static Student nullCheakSt(Student s, String msg) {
		if (s == null) {
			pl("No matching Student found.(" + msg + ")\n");
			return null;
		}
		return s;
	}

	private static Student pickStInList(List<Student> list, String msg) {
		if (list.size() == 0) {
			pl("No matching Student found.(" + msg + ")\n");
			return null;
		}
		pl("---Student-List---");
		for (int i = 0; i < list.size(); i++) {
			pl((i + 1) + "-ID: " + addZero(list.get(i).getID(), 2) + ", Name: " + list.get(i).getName());
		}
		pl("------------------");
		pl("Enter Number to pick (0-Cansel)");
		int index = getIntFromLimit(list.size(), 0) - 1;
		if (index != -1) {
			return sMS.findStudentById(list.get(index).getID());
		} else {
			return null;
		}
	}

	private static void editMenuSt(Student s) {
		boolean picked = true;
		if (s == null) {
			picked = false;
		}
		while (picked) {
			pl("\n---Picked, ID: " + addZero(s.getID(), 2) + ", " + s.getName());
			pl("1-Detail, 2-Edit, 3-Add to Course, 4-Remove from Course, 5-Remove Student (0-Cansel)");
			switch (getIntFromLimit(5, 0)) {
			case 1:
				pl(s);
				break;
			case 2:
				changeInfoSt(s);
				break;
			case 3:
				do {
					sMS.addStudentToCourse(searchCourse(), s);
				} while (yesOrNo("Add to more?"));
				break;
			case 4:
				do {
					sMS.removeStudentFromCourse(searchCourse(), s);
				} while (yesOrNo("Remove from more?"));
				break;
			case 5:
				sMS.removeStudent(s);
			default:
				picked = false;
			}
		}
	}

	private static void changeInfoSt(Student s) {
		boolean changing = true;
		while (changing) {
			pl("---Change: 1-Name, 2-Email, 3-Adress (0-Cansel)");
			switch (getIntFromLimit(3, 0)) {
			case 1:
				pl("New Name: ");
				s.setName(getNoEmptyString());
				break;
			case 2:
				pl("New Email: ");
				String newMail = getNoEmptyString();
				boolean duplicate = false;
				for (Student st : sMS.findAllStudent()) {
					if (st.getEmail().equals(newMail)) {
						pl("This Email is already used.");
						duplicate = true;
						break;
					}
				}
				if (duplicate != true) {
					s.setEmail(newMail);
				}
				break;
			case 3:
				pl("New Adress: ");
				s.setAddress(getNoEmptyString());
				break;
			default:
				changing = false;
			}
		}
		for (Course c : sMS.findAllCourse()) {
			int index = c.getStudentList().indexOf(sMS.findStudentById(s.getID()));
			if (index != -1) {
				c.getStudentList().set(index, s);
			}
		}
		pl("Save Data:\n" + s);
	}

	private static Course searchCourse() {
		if (sMS.findAllCourse().size() == 0) {
			pl("No Existing Course.");
			return null;
		}
		pl("---Searching Course by: 1-ID, 2-Name, 3-Start date, 4-Student, 5-Show all (0-Cansel)");
		switch (getIntFromLimit(5, 0)) {
		case 1:
			pt("ID: ");
			return nullCheakCo(sMS.findCourseById(getInt()), "ID");
		case 2:
			pt("Name: ");
			return pickCoInList(sMS.findCourseByName(getNoEmptyString()), "Name");
		case 3:
			pt("Start date,\n");
			return pickCoInList(sMS.findCourseByDate(getDate()), "Date");
		case 4:
			return pickCoInList(sMS.findCourseByStudent(searchStundent()), "Student");
		case 5:
			return pickCoInList(sMS.findAllCourse(), "All");
		default:
			return null;
		}
	}

	/**
	 * Sent out error message
	 * 
	 * @param s
	 * @param msg
	 * @return
	 */
	private static Course nullCheakCo(Course c, String msg) {
		if (c == null) {
			pl("No matching Student found.(" + msg + ")\n");
			return null;
		}
		return c;
	}

	private static Course pickCoInList(List<Course> list, String msg) {
		if (list.size() == 0) {
			pl("No matching Course found.(" + msg + ")\n");
			return null;
		}
		pl("---Course-List---");
		for (int i = 0; i < list.size(); i++) {
			pl((i + 1) + "-ID: " + addZero(list.get(i).getID(), 2) + ", Name: " + list.get(i).getCourseName());
		}
		pl("------------------");
		pl("Enter Number to pick (0-Cansel)");
		int index = getIntFromLimit(list.size(), 0) - 1;
		if (index != -1) {
			return sMS.findCourseById(list.get(index).getID());
		} else {
			return null;
		}
	}

	private static void editMenuCo(Course c) {
		boolean picked = true;
		if (c == null) {
			picked = false;
		}
		while (picked) {
			pl("\n---Picked, ID: " + addZero(c.getID(), 2) + ", Course: " + c.getCourseName());
			pl("1-Detail, 2-Edit, 3-Add Student, 4-Remove Student, 5-Remove Course (0-Cansel)");
			switch (getIntFromLimit(5, 0)) {
			case 1:
				pl(c);
				break;
			case 2:
				changeInfoCo(c);
				break;
			case 3:
				do {
					sMS.addStudentToCourse(c, searchStundent());
				} while (yesOrNo("Add more?"));
				break;
			case 4:
				do {
					sMS.removeStudentFromCourse(c, searchStundent());
				} while (yesOrNo("Remove more?"));
				break;
			case 5:
				sMS.removeCourse(c);
			default:
				picked = false;
			}
		}
	}

	private static void changeInfoCo(Course c) {
		boolean changing = true;
		while (changing) {
			pl("---Change: 1-Name, 2-StartDate, 3-Duration(week) (0-Cansel)");

			switch (getIntFromLimit(3, 0)) {
			case 1:
				pl("New Name: ");
				c.setCourseName(getNoEmptyString());
				break;
			case 2:
				pl("New StartDate: ");
				c.setStartDate(getDate());
				break;
			case 3:
				pl("New Duration(week): ");
				c.setWeekDuration(getInt());
			default:
				changing = false;
			}
		}
		pl("Save Data:\n" + c);
	}

	public static void pl() {
		System.out.println();
	}

	public static void pl(Object o) {
		System.out.println(o);
	}

	public static void pt(Object o) {
		System.out.print(o);
	}
}
