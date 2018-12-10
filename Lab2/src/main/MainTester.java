package main;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import list.NodeList;
import misc.Tuple;

/**
 * Testing class for lab2 part 1.
 * @author Robin, Oskar
 *
 */
public class MainTester {
	private int intervalSize, iterations;
	private NodeList<Tuple<Integer, Long>> algorithmResults;
	
	/**
	 * Class used for testing the implementation.
	 * @author Robin, Oskar
	 */
	public MainTester(int intervalSize, int iterations) {
		this.algorithmResults = new NodeList<Tuple<Integer,Long>>();
		this.intervalSize = intervalSize;
		this.iterations = iterations;
	}
	

	public static void main(String[] args) {
		/* Code for testing the functionality of the algorithm.
		Integer[] array = new Integer[9];
		Random r = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(array.length+1)+1;
		}
		
		for (int i = 0; i < array.length ; i++) {
			System.out.print(array[i] + ", ");
		}
		
		System.out.println("\nresult: " + FractionFinder.findMaxFraction(array));
		*/
		
		Scanner consoleScanner = new Scanner(System.in);

		System.out.print("Enter sorting interval size:");
		int interval = Integer.parseInt(consoleScanner.nextLine());	// The scanner does weird things if we input integers directly. dunno why.
		
		System.out.print("Enter iterations:");
		int iterations = Integer.parseInt(consoleScanner.nextLine());
		
		System.out.print("Enter Excel export file:");
		String expoFile = consoleScanner.nextLine();
		
		consoleScanner.close();
		
		new MainTester(interval, iterations).analyzeListAndExport(expoFile);
	}
	
	
	/**
	 * First test the algorithm and then export the data to an .xls file.
	 * @param exportFile
	 */
	public void analyzeListAndExport(String exportFile) {
		System.out.println("Sorting...");
		this.analyzeInterval();

		try {
			this.exportToExcel(exportFile);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}

	
	public void analyzeInterval() {
		Random r = new Random();
		java.lang.Number[] array = null;
		
		for (int i = 0, j = this.intervalSize; i < this.iterations; i++, j+=this.intervalSize) {
			
			array = new java.lang.Number[j];
			
			for (int k = 0; k < array.length; k++) {
				array[k] = r.nextInt()+1;
			}
			
			Tuple<Integer, Long> test = new Tuple<Integer,Long>(j,this.analyzeSingular(array));
			this.algorithmResults.appendEnd(test);
		}
	}
	
	private long analyzeSingular(java.lang.Number[] array) {
		long newTime = 0;
		long oldTime = System.currentTimeMillis();
		FractionFinder.findMaxFraction(array);
		newTime = System.currentTimeMillis();
		
		
		return newTime - oldTime;
	}
	
	public void exportToExcel(String filename) throws IOException, RowsExceededException, WriteException {
		int row, column;
		
		
		WritableWorkbook book = Workbook.createWorkbook(new File(filename + ".xls"));
		WritableSheet sheet = book.createSheet("sheet1", 0);
		
		// This is just some basic stuff we have yet not automated.
		row = 0;
		column = 0;
		Label label = new Label(column,row,"sizes");
		sheet.addCell(label);
		
		Number number;

		// for every pivot: add all data.
		for (Tuple<Integer, Long> tuple : this.algorithmResults) {
			row ++;
			number = new Number(column, row, tuple.x);
			sheet.addCell(number);
				
			number = new Number(column+1, row, tuple.y);
			sheet.addCell(number);
		}
		
		// Note to self: Remember to write and close.
		book.write();
		book.close();
	}
}
