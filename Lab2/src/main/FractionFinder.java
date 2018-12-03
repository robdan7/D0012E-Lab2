package main;

import misc.Tuple;

/**
 * Class for finding the maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n} in an array.
 * @author Robin
 *
 */
public class FractionFinder {
	
	/**
	 * find the maximum value {a<sub>i</sub>/a<sub>j</sub> | 1 < i < j < n}.
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
		if (highIndex - lowIndex == 1) {
			return new Tuple<Number, Number>(list[lowIndex],list[highIndex]);
		}
		Tuple<Number, Number> tup1 = fractionHelper(list, lowIndex,(highIndex-lowIndex)/2+lowIndex);
		Tuple<Number, Number> tup2 = fractionHelper(list, (highIndex-lowIndex)/2+lowIndex+1, highIndex);
		return findLargestFrac(tup1, tup2);
	}
	
	/**
	 * Find the largest combined fraction.<br>
	 * <strong>Case 1:</strong> The largest fraction is a local y/x.<br>
	 * <strong>Case 2:</strong> The largest fraction is a combination; the largest fraction is 
	 * (largest y)/(largest x).
	 * @param tup1 - a pair.
	 * @param tup2 - another pair.
	 * @return The largest combined fraction.
	 */
	private static Tuple<Number, Number> findLargestFrac(Tuple<Number,Number> tup1, Tuple<Number,Number> tup2) {
		double frac1 = tup1.y.doubleValue()/tup1.x.doubleValue();
		double frac2 = tup2.y.doubleValue()/tup2.x.doubleValue();
		
		Tuple<Number,Number> result = tup1;
		double highestFrac = frac1;
		
		// Find the largest local fraction.
		if (frac1 < frac2) {
			highestFrac = frac2;
			result = tup2;
		}
		
		// find the lowest value in the first pair and the highest in the other.
		Number tup1Lowest = tup1.x.doubleValue() < tup1.y.doubleValue() ? tup1.x.doubleValue() : tup1.y.doubleValue();
		Number tup2highest = tup2.x.doubleValue() > tup2.y.doubleValue() ? tup2.x.doubleValue() : tup2.y.doubleValue();

		
		// If the largest fraction is a combination: Change the result.
		if ((tup2highest.doubleValue()/tup1Lowest.doubleValue()) > highestFrac) {
			result = new Tuple<Number,Number>(tup1Lowest, tup2highest);
		}
		
		return result;
	}

}
