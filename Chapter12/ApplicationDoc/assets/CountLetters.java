package ApplicationDoc.assets;

/*
 * CountLetters.java
 * Chapter 10, CountLetters review
 * Lawrenceville Press
 * June 24, 2005
 */
 
 import java.util.Scanner;
 
 /**
  * This javadoc comment is for exercise 11 in chapter 12
  */
  
 /**
  * The occurences of letters in a phrase are counted.
  */
 public class CountLetters {

	public static void main(String[] args) {
		final int LOW = 'A';		//smallest possible value
		final int HIGH = 'Z';		//highest possible value
		int[] letterCounts = new int[HIGH - LOW + 1];
		Scanner input = new Scanner(System.in);		
		String phrase;
		char[] phraseLetters;
		int offset;		//array index
		
		/** prompt user for a phrase */
		System.out.print("Enter a phrase: ");
		phrase = input.nextLine();
		
		/** convert phrase to char array and count letter occurrences */
		phrase = phrase.toUpperCase();
		phraseLetters = phrase.toCharArray();
		for (int letter = 0; letter < phraseLetters.length; letter++) {
			offset = phraseLetters[letter] - LOW;
			if (offset >= 0 && offset < (HIGH - LOW + 1)) {	//be sure character is a letter
				letterCounts[offset] += 1;
			}
		}
		
		/** show letter occurrences */
		for (int i = LOW; i <= HIGH; i++) {
			System.out.println((char)i + ": " + letterCounts[i - LOW]);
		}

        input.close();
	}
}