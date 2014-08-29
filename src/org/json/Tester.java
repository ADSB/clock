package org.json;

/**
 * org.json.Tester class - contains some simple methods to test methods are returning the correct value
 * @author fowlerda Daniel Fowler. 
 *
 */
public class Tester {


	/**
	 * Compare two ints for equality, and return/print result 
	 * @param input input provided
	 * @param actual actual output
	 * @param expected desired output
	 * @return true if actualValue == expectedValue 
	 */
	public static boolean test(int input,int actual,int expected){
		System.err.print("Result:"+actual);
		if(actual==expected) {
			System.err.println("\t(OK)");
			return true;
		}
		System.err.println(" (BAD) Expected:\""+expected+"\" for input:\""+input+"\"");

		return false;
	}

	/**
	 * Compare two doubles , and return/print result .
	 * @param input input provided
	 * @param actual actual output
	 * @param expected desired output
	 * @return true if actualValue is within 0.01 of expectedValue 
	 */
	public static boolean test(double input, double actual,double expected){
		System.err.print("Result:"+actual);
		if(Math.abs(actual-expected)<0.01) {
			System.err.println("\t(OK)");
			return true;
		}
		System.err.println("(BAD) Expected:\""+expected+"\" for input:\""+input+"\"");

		return false;
	}

	/**
	 * Compare two Strings for equality, and return/print result 
	 * @param input input provided
	 * @param actual actual output
	 * @param expected desired output
	 * @return true if actualValue.equals(expectedValue)
	 */
	public static boolean test(String input,String actual,String expected){
		System.err.print("Output:\""+actual+"\"");
		if(actual.equals(expected)) {
			System.err.println(" (OK)");
			return true;
		}
		System.err.println("\t (BAD) Expected:\""+expected+"\" for input:\""+input+"\"");

		return false;
	}

	/**
	 * Compare two Strings for equality, and return/print result 
	 * @param input input provided
	 * @param actual actual output
	 * @param expected desired output
	 * @return true if actualValue==expectedValue
	 */
	public static boolean test(String input,boolean actual,boolean expected){
		System.err.print("Output:"+actual+"");
		if(actual==expected) {
			System.err.println(" (OK)");
			return true;
		}
		System.err.println("\t (BAD) Expected:"+expected+" for input:\""+input+"\"");

		return false;
	}


}
