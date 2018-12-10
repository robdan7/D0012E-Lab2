package main;

import misc.Tuple;

/**
 * Class for finding the maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n} in an array.
 * @author Robin, Oskar
 *
 */
public class FractionFinder {
	
	/**
	 * Find the maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n}.
	 * @param list - an array with two or more numbers.
	 * @return Maximum value.
	 */
	public static Number findMaxFraction(Number[] list) {
		if (list.length < 2) {
			throw new IllegalArgumentException("array size must be larger or equal to 2.");
		}
		
		// If the array length is uneven: "Remove" the last element from the algorithm.
		int maxIndex = list.length-1;
		boolean unevenFlag = false;
		if (list.length%2 == 1) { // if the length is uneven.
			maxIndex--;
			unevenFlag = true;
		}
		
		Tuple<Number,Tuple<Number,Number>> result = fractionHelper(list, 0, maxIndex);
		Number frac = result.x;
		
		// Code for when the array length is uneven.
		if (unevenFlag && (list[list.length-1].doubleValue()/result.y.x.doubleValue() > frac.doubleValue())) {
			frac = list[list.length-1].doubleValue()/result.y.x.doubleValue();
		}
		
		return frac;
	}
	
	/**
	 * Helper method for {@link #findMaxFraction(Number[])}.
	 * @param list - an array.
	 * @param lowIndex -  lowest index.
	 * @param highIndex - highest index.
	 * @return The maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n}.
	 */
	private static Tuple<Number, Tuple<Number,Number>> fractionHelper(Number[] list, int lowIndex, int highIndex) {
		if (highIndex == (lowIndex+1)) {	// Base case.
			// Calculate lowest and largest numbers and put them in a tuple.
			Tuple<Number,Number> lowHigh;
			if (list[lowIndex].doubleValue() < list[highIndex].doubleValue()) {
				lowHigh = new Tuple<Number,Number>(list[lowIndex], list[highIndex]);
			} else {
				lowHigh = new Tuple<Number,Number>(list[highIndex], list[lowIndex]);
			}
			
			// Calculate the fraction.
			Number fraction = list[highIndex].doubleValue()/list[lowIndex].doubleValue();
			
			return new Tuple<Number,Tuple<Number,Number>>(fraction,lowHigh);
		}
		
		// Recursion.
		Tuple<Number, Tuple<Number,Number>> tuple1 = fractionHelper(list, lowIndex,(((highIndex-lowIndex+1)>>2)<<1)-1+lowIndex);
		Tuple<Number, Tuple<Number,Number>> tuple2 = fractionHelper(list, (((highIndex-lowIndex+1)>>2)<<1)+lowIndex, highIndex);
		
		// Get the largest local fraction
		Number maxFraction = tuple1.x.doubleValue() > tuple2.x.doubleValue() ? tuple1.x.doubleValue() : tuple2.x.doubleValue();
		
		// Get the largest/smallest fraction.
		Number tempFrac = tuple2.y.y.doubleValue() / tuple1.y.x.doubleValue();
		
		// Calculate the new global max fraction.
		maxFraction = maxFraction.doubleValue() < tempFrac.doubleValue() ? tempFrac : maxFraction;
		
		// Calculate global max and min numbers.
		Number lowest = tuple1.y.x.doubleValue() < tuple2.y.x.doubleValue() ? tuple1.y.x : tuple2.y.x;
		Number highest = tuple1.y.y.doubleValue() > tuple2.y.y.doubleValue() ? tuple1.y.y :tuple2.y.y;
		
		// Create a pair with global min and max numbers.
		Tuple<Number,Number> result = new Tuple<Number,Number>(lowest, highest);

		return new Tuple<Number,Tuple<Number,Number>>(maxFraction,result);
	}
}
