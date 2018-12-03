package main;

import misc.Tuple;

public class MaxDivision {

	public MaxDivision() {
		
	}
	
	public float findMaxFraction() {
		return 0;
	}
	
	private Tuple<Integer, Integer> fractionHelper(int[] list, int lowIndex, int highIndex) {
		if (highIndex - lowIndex == 1) {
			return new Tuple<Integer, Integer>(list[0],list[1]);
		}
		Tuple<Integer, Integer> tup1 = this.fractionHelper(list, lowIndex,(highIndex-lowIndex)/2+lowIndex);
		Tuple<Integer, Integer> tup2 = this.fractionHelper(list, (highIndex-lowIndex)/2+lowIndex+1, highIndex);
		
		float frac1 = tup1.y/tup1.x;
		float frac2 = tup2.y/tup2.x;
		
		
		
		
		
		return null;
	}

}
