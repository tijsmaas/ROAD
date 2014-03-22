package utils;

/**
 * Faulty user input throws many exceptions in Java.
 * To keep the codebase clean, these conversion functions can be utilised.
 * @param input
 */
public class TypeConversion
{
	public static Integer toInteger(String input) {
		try {
			return Integer.valueOf(input);
		} catch(NumberFormatException nfe) {
			return null;
		}
	}
	
	public static Float toFloat(String input) {
		try {
			// beware: this accepts more inputs than specified by the SUMO specification.
			// see javadoc for more info
			return Float.valueOf(input);
		} catch(NullPointerException npe) {
			return null;
		} catch(NumberFormatException nfe) {
			return null;
		}
	}
	
	
}
