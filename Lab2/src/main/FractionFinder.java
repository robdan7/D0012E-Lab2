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
	 * @param list - the array
	 * @return Maximum value.
	 */
	public static Number findMaxFraction(Number[] list) {
		Tuple<Number,Number> result = fractionHelper(list, 0, list.length-1);
		return result.y.doubleValue()/result.x.doubleValue();
	}
	
	/**
	 * Helper method for {@link #findMaxFraction(Number[])}.
	 * @param list - the array.
	 * @param lowIndex -  lowest index.
	 * @param highIndex - highest index.
	 * @return The maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n}.
	 */
	private static Tuple<Number, Number> fractionHelper(Number[] list, int lowIndex, int highIndex) {
		if ((highIndex - lowIndex) == 1) {
			return new Tuple<Number, Number>(list[lowIndex],list[highIndex]);
		}
		Tuple<Number, Number> tuple1 = fractionHelper(list, lowIndex,(highIndex-lowIndex)/2+lowIndex);
		Tuple<Number, Number> tuple2 = fractionHelper(list, (highIndex-lowIndex)/2+lowIndex+1, highIndex);
		return findLargestFraction(tuple1, tuple2);
	}
	
	/**
	 * Find the largest combined fraction.<br>
	 * <strong>Case 1:</strong> The largest fraction is a local y/x.<br>
	 * <strong>Case 2:</strong> The largest fraction is a combination; the largest fraction is 
	 * (largest y)/(largest x).
	 * @param tuple1 - a pair.
	 * @param tuple2 - another pair.
	 * @return The largest combined fraction.
	 */
	private static Tuple<Number, Number> findLargestFraction(Tuple<Number,Number> tuple1, Tuple<Number,Number> tuple2) {
		double fraction1 = tuple1.y.doubleValue()/tuple1.x.doubleValue();
		double fraction2 = tuple2.y.doubleValue()/tuple2.x.doubleValue();
		
		Tuple<Number,Number> result = tuple1;
		double highestFraction = fraction1;
		
		// Find the largest local fraction.
		if (fraction1 < fraction2) {
			highestFraction = fraction2;
			result = tuple2;
		}
		
		// Find the lowest value in the first pair and the highest in the other.
		Number tuple1LowestValue = tuple1.x.doubleValue() < tuple1.y.doubleValue() ? tuple1.x.doubleValue() : tuple1.y.doubleValue();
		Number tuple2HighestValue = tuple2.x.doubleValue() > tuple2.y.doubleValue() ? tuple2.x.doubleValue() : tuple2.y.doubleValue();

		
		// If the largest fraction is a combination: Change the result.
		if ((tuple2HighestValue.doubleValue()/tuple1LowestValue.doubleValue()) > highestFraction) {
			result = new Tuple<Number,Number>(tuple1LowestValue, tuple2HighestValue);
		}
		
		return result;
	}

}
