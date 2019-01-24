package se.lexicon.Li.SchoolManagerAS.util;

import java.util.Random;

public class UtilNumber {

	private static Random ran = new Random();

	/**
	 * Get a random integer with range between max and min
	 * 
	 * @param max highest number (include)
	 * @param min lowest number (include)
	 * @return a random number
	 */
	public static int randomInt(int max, int min) {
		return ran.nextInt(max - min) + min;
	}

	/**
	 * Get a random number with range between max and min
	 * 
	 * @param max highest number (include)
	 * @param min lowest number (include)
	 * @return a random number
	 */
	public static double randomDouble(double max, double min) {
		return ran.nextDouble() * (max - min) + min;
	}

	/**
	 * Print integer part for double which has no value at decimal
	 * 
	 * @param n
	 * @return string
	 */
	public static String printN(double n) {
		String s = "" + (int) n;
		if (n - (int) n > 0) {
			s = "" + n;
		}
		return s;
	}

	/**
	 * Add 0 before the number if it is too short
	 * 
	 * @param n
	 * @param max
	 * @return
	 */
	public static String addZero(int n, int di) {
		String s = "" + n;
		String re = "";
		for (int i = s.length(); i < di; i++) {
			re += "0";
		}
		return re + s;
	}

	/**
	 * Add space after the number if it is too short
	 * 
	 * @param n
	 * @param max
	 * @return
	 */
	public static String addSpaceBefore(int n, int di) {
		String s = "" + n;
		String re = "";
		for (int i = s.length(); i < di; i++) {
			re += " ";
		}
		return re + s;
	}

	/**
	 * Add space after the number if it is too short
	 * 
	 * @param n
	 * @param di :amount of length that you want
	 * @return String n+space
	 */
	public static String addSpaceBehind(int n, int di) {
		String s = "" + n;
		String re = "";
		for (int i = s.length(); i < di; i++) {
			re += " ";
		}
		return s + re;
	}

	// Print decimal with better looking
	public static String printD(double n, int de, int di) {
		String sD = Double.toString(rounding(n, de));
		String re = "", inP = addZero((int) n, di);
		for (int i = sD.indexOf("."); i <= sD.indexOf(".") + de; i++) {
			re += sD.charAt(i);
		}

		if (de == 0) {
			return inP;
		} else {
			return inP + re;
		}
	}

	public static String printD(double n, int de) {
		String sD = Double.toString(rounding(n, de));
		String re = "", inP = Integer.toString((int) n);
		for (int i = sD.indexOf("."); i <= sD.indexOf(".") + de; i++) {
			re += sD.charAt(i);
		}

		if (de == 0) {
			return inP;
		} else {
			return inP + re;
		}

	}

	public static double getRoundingDouble(double n, int de) {
		double re = Double.parseDouble(printD(n, de));
		return re;

	}

	public static double rounding(double n, int de) {
		double re = 5 * Math.pow(0.1, (de + 1)) + n;
		return re;

	}

}
