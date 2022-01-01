package fullyConnectedNetwork;
/**
 * Various helper methods for neural networks.
 * @author Bryan Wang
 *
 */
public class NetworkTools {
	
	/**
	 * Creates a double array filled with the initial value.
	 * @param size Size of the array created.
	 * @param initValue Initial value of the array values.
	 * @return Returns a double array filled with the initial value.
	 */
	public static double[]createArray(int size, double initValue){
		if(size<1) return null;
		double[] arr = new double[size];
		for(int i =0;i<size;i++) {
			arr[i]=initValue;
		}
		return arr;
	}
	
	/**
	 * Creates an array of set size filled with randomized values between two bounds.
	 * @param size Size of the array.
	 * @param lowerBound Lower bound for the randomized array values.
	 * @param upperBound Upper bound for the randomized array values.
	 * @return Returns an double array of set size filled with randomized values between two bounds.
	 */
	public static double[] createRandomArray(int size, double lowerBound, double upperBound) {
		if(size<1) return null;
		double[] arr = new double[size];
		for(int i =0;i<size;i++) {
			arr[i]=randomValue(lowerBound, upperBound);
		}
		return arr;
	}
	
	/**
	 * Create a set of integers (there are no repeating values)
	 * @param size Size of the array.
	 * @param lowerBound Lower bound for the randomized array values.
	 * @param upperBound Upper bound for the randomized array values.
	 * @return Returns an Integer array of set size filled with randomized values between two bounds 
	 * 		   (inclusive below, exclusive above), with no repeats.
	 */
	public static Integer[] createIntegerSet(int size, int lowerBound, int upperBound) {
		if(size<1) return null;
		Integer[] arr = new Integer[size];
		for(int i = 0; i<size; i++) {
			int n = (int) randomValue(lowerBound, upperBound);
			while(containsValue(arr,n)) {
				n = (int) randomValue(lowerBound, upperBound);
			}
			arr[i] = n;
		}
		return arr;
	}
	
	/**
	 * Creates a 2D rectangular array of set size filled with randomized values between two bounds.
	 * @param numRows Number of rows in the array.
	 * @param numCols Number of columns in the array.
	 * @param lowerBound Lower bound for the randomized array values.
	 * @param upperBound Upper bound for the randomized array values.
	 * @return Returns a 2D double array of set size filled with randomized values between two bounds.
	 */
	public static double[][] createRandomArray(int numRows, int numCols, double lowerBound, double upperBound) {
		if(numRows<1||numCols<1) return null;
		double[][] arr = new double[numRows][numCols];
		for(int i =0;i<numRows;i++) {
			arr[i]=createRandomArray(numCols, lowerBound, upperBound);
		}
		return arr;
	}
	
	/**
	 * Generates a random value
	 * @param lowerBound Lower bound for the randomized value.
	 * @param upperBound Upper bound for the randomized value.
	 * @return Returns a random double within given bounds.
	 */
	public static double randomValue(double lowerBound, double upperBound) {
		return Math.random()*(upperBound-lowerBound)+lowerBound;
	}
	
	/**
	 * Checks if an array contains a certain value at least once.
	 * @param <T>
	 * @param array The array of values being searched.
	 * @param value The value being searched for.
	 * @return Returns true if the given value occurs at least once in the array and false if not.
	 */
	public static <T extends Comparable<T>> boolean containsValue(T[] array, T value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (value.compareTo(array[i]) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Finds the index of the maximum value of a double array.
	 * @param values Double array o9f values.
	 * @return Returns the index of the max value in a double array.
	 */
	public static int indexOfMax(double[] values) {
		double max = 0;
		int index=0;
		for(int i =0;i<values.length;i++) {
			if(values[i]>max) {
				max=values[i];
				index=i;
			}
		}
		return index;
	}
	
	
}
