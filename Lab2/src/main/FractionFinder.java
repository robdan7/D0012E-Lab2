package main;

import misc.Tuple;

/**
 * Class for finding the maximum value {a<sub>i</sub>/a<sub>j</sub> | 0 < i < j < n} in an array.
 * @author Robin, Oskar
 *
 */
public class FractionFinder {
	
	/**
	 * Find the maximum value {a<sub>i</sub>/a<sub>j</sub> | 0 < i < j < n}.
	 * @param list - an array with two or more numbers.
	 * @return Maximum value.
	 */
	public static Number findMaxFraction(Number[] list) {
		
		Tuple<Number,Number>[] tuples = convertToTuples(list);
		if (list.length < 2) {
			throw new IllegalArgumentException("array size must be larger or equal to 2.");
		}
		Tuple<Number,Tuple<Number,Number>> result = fractionHelper(tuples, 0, tuples.length-1);
		//return result.y.doubleValue()/result.x.doubleValue();
		//return result.x.doubleValue() > result.y.y.doubleValue()/result.y.x.doubleValue() ? result.y.y.doubleValue()/result.y.x.doubleValue() : result.x;
		return result.x;
	}
	
	/**
	 * Helper method for {@link #findMaxFraction(Number[])}.
	 * @param list - an array.
	 * @param lowIndex -  lowest index.
	 * @param highIndex - highest index.
	 * @return The maximum value {a<sub>i</sub>/a<sub>j</sub> | 0 < i < j < n}.
	 */
	private static Tuple<Number, Tuple<Number,Number>> fractionHelper(Tuple<Number,Number>[] list, int lowIndex, int highIndex) {
		if (highIndex == lowIndex) {	// Base case.
			//System.out.println(list[lowIndex]);
			//return list[lowIndex];		// One pair equals two elements.
			Tuple<Number,Number> lowHigh;
			if (list[lowIndex].x.doubleValue() < list[lowIndex].y.doubleValue()) {
				lowHigh = new Tuple<Number,Number>(list[lowIndex].x, list[lowIndex].y);
			} else {
				lowHigh = new Tuple<Number,Number>(list[lowIndex].y, list[lowIndex].x);
			}
			
			return new Tuple<Number,Tuple<Number,Number>>(list[lowIndex].y.doubleValue()/list[lowIndex].x.doubleValue(),lowHigh);
		}
		
		// Recursion.
		Tuple<Number, Tuple<Number,Number>> tuple1 = fractionHelper(list, lowIndex,(highIndex-lowIndex)/2+lowIndex);
		Tuple<Number, Tuple<Number,Number>> tuple2 = fractionHelper(list, (highIndex-lowIndex)/2+lowIndex+1, highIndex);
		
<<<<<<< HEAD
		Number value = tuple1.x.doubleValue() > tuple2.x.doubleValue() ? tuple1.x.doubleValue() : tuple2.x.doubleValue();
		Number frac = tuple2.y.y.doubleValue() / tuple1.y.x.doubleValue();
		value = value.doubleValue() < frac.doubleValue() ? frac : value;
=======
		// Find the largest value and return it.
		return findLargestFraction(tuple1, tuple2);
	}
	
	/**
	 * Find the largest combined fraction.<br>
	 * <strong>Case 1:</strong> The largest fraction is a local y/x.<br>
	 * <strong>Case 2:</strong> The largest fraction is a combination; the largest fraction is 
	 * (largest y)/(smallest x).
	 * @param tuple1 - a pair.
	 * @param tuple2 - another pair.
	 * @return The largest combined fraction.
	 */
	private static Tuple<Number, Number> findLargestFraction(Tuple<Number,Number> tuple1, Tuple<Number,Number> tuple2) {
>>>>>>> branch 'master' of https://github.com/robdan7/D0012E-Lab2.git
		
		Number lowest = tuple1.y.x.doubleValue() < tuple2.y.x.doubleValue() ? tuple1.y.x : tuple2.y.x;
		Number highest = tuple1.y.y.doubleValue() > tuple2.y.y.doubleValue() ? tuple1.y.y :tuple2.y.y;
		
		Tuple<Number,Number> result = new Tuple<Number,Number>(lowest, highest);

		return new Tuple<Number,Tuple<Number,Number>>(value,result);
	}
	
	/**
	 * Create an array of pairs instead of numbers. Now we can compare pairs instead ;)
	 * @param array
	 * @return an array of pairs representing the input.
	 */
	private static Tuple<Number, Number>[] convertToTuples(Number[] array) {
		int isOdd = array.length/2 == array.length/2.0f ? 0 : 1;	// we need this if the list length is odd.
		
		
		@SuppressWarnings("unchecked")
		Tuple<Number,Number>[] tuples = (Tuple<Number, Number>[]) new Tuple[array.length/2 + isOdd];
		
		// copy all* elements.
		for (int i = 0; i < array.length/2; i++) {
			tuples[i] = new Tuple<Number,Number>(array[i*2],array[i*2+1]);
		}
		
		// When the list is odd we put the last element together with the second last.
		if (isOdd == 1) {
			tuples[tuples.length-1] = new Tuple<Number,Number>(array[array.length-2],array[array.length-1]);
		}
		return tuples;
		
		// * Don't copy the last element if the list is odd. This is done separate.
	}

}
