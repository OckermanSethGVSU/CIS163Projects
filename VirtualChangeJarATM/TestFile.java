package ChangeJarPro;;

import java.io.*;
import java.util.Scanner;

public class TestFile {

	/**
	 *
	 */

	public void sampleReadData(){
		String city;
		int population;
		
		try{
			// open the data file
			Scanner fileReader = new Scanner(new File("/testit")); 
			Scanner lineReader;

			// read one String in of data and an int
			city = fileReader.next();
			population = fileReader.nextInt();
			System.out.println (city + " " + population);
		}

		// could not find file
	catch(FileNotFoundException error) {
		System.out.println("File not found ");
	}

		// problem reading the fil
	catch(IOException error){
		System.out.println("Oops!  Something went wrong.");
	}
}

	public void sampleWriteData () {
			PrintWriter out = null;
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter("/testit")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String s = "ANCHORAGE";
			out.println(s + " " +  "256000");
			out.close();
			 
	}

public static void main(String[] args) {

	TestFile f = new TestFile();
	f.sampleWriteData();
	f.sampleReadData();

}

}
