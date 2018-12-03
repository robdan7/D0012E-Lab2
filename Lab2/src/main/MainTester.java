package main;

public class MainTester {

	/**
	 * Class used for testing the implementation.
	 * @author Robin, Oskar
	 */
	public MainTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Number[] array = new Float[4];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = (i+1)/2.0f;
		}
		
		for (int i = 0; i < array.length ; i++) {
			System.out.println(array[i]);
		}
		
		System.out.println(FractionFinder.findMaxFraction(array));
	}

}
