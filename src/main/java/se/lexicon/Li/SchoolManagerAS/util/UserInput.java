package se.lexicon.Li.SchoolManagerAS.util;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Year;

public class UserInput {

	private static final Scanner scan = new Scanner(System.in);

	public static String getString() {
		return scan.nextLine();
	}

	/**
	 * Get String which is not just empty space
	 * 
	 * @return valid String
	 */
	public static String getNoEmptyString() {
		while (true) {
			String re = getString().trim();
			if (!re.equals("")) {
				return re;
			} else {
				System.out.println("Not a valid enter.");
			}
		}
	}

	/**
	 * Get a valid Date
	 * 
	 * @return LocalDate
	 */
	public static LocalDate getDate() {
		int year = 0, month = 0, dayOfMonth = 0;

		System.out.print("Year: ");
		year = getIntFromLimit(Year.MAX_VALUE, Year.MIN_VALUE);

		System.out.print("Month: ");
		month = getIntFromLimit(12, 1);

		System.out.print("Day: ");
		dayOfMonth = getIntFromLimit(31, 1);

		return LocalDate.of(year, month, dayOfMonth);
	}

	/**
	 * Keep asking until get a valid answer *Do not Add "help" as answer
	 * 
	 * @param strings :valid answers
	 * @return valid answer
	 */
	public static String getVaildAnswer(String... strings) {
		while (true) {
			String check = getString().toLowerCase();

			if (check.equals("help")) {
				System.out.print("Valid answer: ");
				for (int i = 0; i < strings.length; i++) {
					if (i != strings.length - 1) {
						System.out.print(strings[i] + ", ");
					} else {
						System.out.print(strings[i] + ".\n");
					}
				}

			} else {
				for (String s : strings) {
					if (check.equals(s.toLowerCase())) {
						return s;
					}
				}
				System.out.println("Not a valid enter.(enter \"Help\" to see all)");
			}
		}
	}

	/**
	 * Keep asking for answer yes or no , doesn't accept anything else.
	 * 
	 * @param askS :Question to ask for yes or no
	 * @return boolean
	 */
	public static boolean yesOrNo(String question) {
		System.out.println(question + "(y/n)");
		int tryN = 0;
		while (true) {
			String check = getString().toLowerCase();
			switch (check) {
			case "y":
				return true;
			case "n":
				return false;
			default:
				tryN++;
				if (tryN % 5 == 0) {
					System.out.println(question + "(y/n)");
				} else {
					System.out.println("Not a valid enter.(y/n)");
				}
			}
		}
	}

	/**
	 * Check the string is number with in range of Integer or not, until you get a
	 * valid number
	 * 
	 * @return valid number
	 */
	public static int getInt() {
		while (true) {
			try {
				int n = Integer.parseInt(getString());
				return n;
			} catch (NumberFormatException e) {
				System.out.println("Not valid enter.(int)");
			}
		}
	}

	/**
	 * keep asking for number until you put right number from limit
	 * 
	 * @param max
	 * @param min
	 * @return valid number
	 */
	public static int getIntFromLimit(int max, int min) {
		while (true) {
			int n = getInt();
			if (n >= min && n <= max) {
				return n;
			}
			System.out.println("Not valid enter.(int " + min + "-" + max + ")");
		}
	}

	/**
	 * keep asking for number until you put right number from limit
	 * 
	 * @param max
	 * @param min
	 * @param with decide max and min are Inclusive or not
	 * @return
	 */
	public static int getIntFromLimit(int max, int min, boolean with) {
		while (true) {
			int n = getInt();
			if (n >= min && n <= max) {
				if (!with && n == min || n == max) {
				} else {
					return n;
				}
			}
			System.out.println("Not valid enter.(int not with " + min + "-" + max + ")");
		}
	}

	public static int getIntFromLimit(int max, int min, boolean wmax, boolean wmin) {
		while (true) {
			int n = getInt();
			if (n >= min && n <= max) {
				if (!wmax && n == max) {
				} else if (!wmin && n == min) {
				} else {
					return n;
				}
			}
			String ms = "Not valid enter.(int";
			if (!wmin) {
				ms += " without " + min + " -";
			} else {
				ms += " with " + min + " -";
			}

			if (!wmax) {
				ms += " without " + max + ")";
			} else {
				ms += " with " + max + ")";
			}
			System.out.println(ms);
		}

	}

	/**
	 * Check the string is number with in range of Double or not, until you get a
	 * valid number
	 * 
	 * @return valid number
	 */
	public static double getDouble() {
		while (true) {
			try {
				double n = Double.parseDouble(getString());
				return n;
			} catch (NumberFormatException e) {
				System.out.println("Not valid enter.(double)");
			}
		}
	}

	/**
	 * keep asking for number until you put right number from limit
	 * 
	 * @param max
	 * @param min
	 * @return valid number
	 */
	public static double getDoubleFromLimit(double max, double min) {
		while (true) {
			double n = getDouble();
			if (n >= min && n <= max) {
				return n;
			}
			System.out.println("Not valid enter.(double " + min + "-" + max + ")");
		}
	}

	/**
	 * keep asking for number until you put right number from limit
	 * 
	 * @param max
	 * @param min
	 * @param with decide max and min are Inclusive or not
	 * @return
	 */
	public static double getDoubleFromLimit(double max, double min, boolean with) {
		while (true) {
			double n = getDouble();
			if (n >= min && n <= max) {
				if (!with && n == min || n == max) {
				} else {
					return n;
				}
			}
			System.out.println("Not valid enter.(double not with " + min + "-" + max + ")");
		}
	}

	public static double getDoubleFromLimit(double max, double min, boolean wmax, boolean wmin) {
		while (true) {
			double n = getDouble();
			if (n >= min && n <= max) {
				if (!wmax && n == max) {
				} else if (!wmin && n == min) {

				} else {
					return n;
				}
			}
			String ms = "Not valid enter.(double";
			if (!wmin) {
				ms += " without " + min + " -";
			} else {
				ms += " with " + min + " -";
			}

			if (!wmax) {
				ms += " without " + max + ")";
			} else {
				ms += " with " + max + ")";
			}
			System.out.println(ms);
		}
	}

}
