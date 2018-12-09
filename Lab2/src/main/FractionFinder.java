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
		
		//Tuple<Number,Number>[] tuples = convertToTuples(list);
		if (list.length < 2) {
			throw new IllegalArgumentException("array size must be larger or equal to 2.");
		}
		
		int maxIndex = list.length-1;
		if ((list.length>>1)<<1 != list.length) {
			maxIndex--;
		}
		
		Tuple<Number,Tuple<Number,Number>> result = fractionHelper(list, 0, maxIndex);
		Number frac = result.x;
		if (list[list.length-1].doubleValue()/result.y.x.doubleValue() > frac.doubleValue()) {
			frac = list[list.length-1].doubleValue()/result.y.x.doubleValue();
		}
		//return result.y.doubleValue()/result.x.doubleValue();
		//return result.x.doubleValue() > result.y.y.doubleValue()/result.y.x.doubleValue() ? result.y.y.doubleValue()/result.y.x.doubleValue() : result.x;
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
			//System.out.println(list[lowIndex]);
			//return list[lowIndex];		// One pair equals two elements.
			Tuple<Number,Number> lowHigh;
			if (list[lowIndex].doubleValue() < list[highIndex].doubleValue()) {
				lowHigh = new Tuple<Number,Number>(list[lowIndex], list[highIndex]);
			} else {
				lowHigh = new Tuple<Number,Number>(list[highIndex], list[lowIndex]);
			}
			
			return new Tuple<Number,Tuple<Number,Number>>(list[highIndex].doubleValue()/list[lowIndex].doubleValue(),lowHigh);
		}
		
		// Recursion.
		Tuple<Number, Tuple<Number,Number>> tuple1 = fractionHelper(list, lowIndex,(((highIndex-lowIndex+1)>>2)<<1)-1+lowIndex);
		Tuple<Number, Tuple<Number,Number>> tuple2 = fractionHelper(list, (((highIndex-lowIndex+1)>>2)<<1)+lowIndex, highIndex);
		
		Number value = tuple1.x.doubleValue() > tuple2.x.doubleValue() ? tuple1.x.doubleValue() : tuple2.x.doubleValue();
		Number frac = tuple2.y.y.doubleValue() / tuple1.y.x.doubleValue();
		value = value.doubleValue() < frac.doubleValue() ? frac : value;
		
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
	@Deprecated
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
