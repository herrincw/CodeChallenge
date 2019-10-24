/**
 * Ownum Coding problem solution
 * @author Cameron Herring 
 */

package SortPassage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkSolution {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String words = readFile("passage.txt"); // create single string from reading file
		String[] sentences = readFile("passage.txt").split("\\."); // create an array of sentences from reading the file
		System.out.println("Word Count: "); // Run top words method
		String topword = topWords(words);

		System.out.println("Last Sentence: ");
		System.out.println(lastSentence(topword, sentences));

	}

	/**
	 * Method to read a text file and convert it to a single string.
	 * 
	 * @param filename
	 * @return returns returns file as a single string to compute word frequency.
	 * @throws IOException
	 */
	public static String readFile(String filename) throws IOException {

		List<String> words = new ArrayList<>();

		File f = new File(filename); // Creating file type for input
		FileReader fr = new FileReader(f); // Creating file Reader Object
		BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
		String s = null;
		String a = "";
		while ((s = br.readLine()) != null) // Reading the content line by line
		{
			if (s.length() > 0) { // Ignore Blank Lines
				s = s.replaceAll("-", " "); // removing - chars
				s = s.replaceAll("[^\\p{L}\\p{Z}\\.]", ""); // Regex here Leaving "." character to split by sentence
				words.add(s);
				a += s;

			}
		}
		fr.close();

		return a;
		// return words;

	}

	/**
	 * Method to remove characters from a string, and read them into a hashmap to
	 * count word frequency prints top 10 results and returns most frequent word.
	 * 
	 * @param passage passage read from file into a single string
	 * @return Most frequent word
	 */
	public static String topWords(String passage) {

		passage = passage.replace(".", "").toLowerCase(); // regex removing oeriods from the sentences convert string to
															// all lowercase.
		String[] words = passage.split(" "); // split each word into an array
		String TopWord = "";
		System.out.println(words.length);
		HashMap<String, Integer> wordCount = new HashMap<>(); // Hashmap to count word frequency

		for (int i = 0; i < words.length; i++) { // load hashmap
			if (wordCount.containsKey(words[i])) {
				int count = wordCount.get(words[i]); // if word exist in the map increment keys value.
				wordCount.put(words[i], count + 1);
			} else
				wordCount.put(words[i], 1); // else add word to hashmap
		}

		return sortedMap(wordCount); // Sorts Hashmap and prints top 10 results. Returns top word for final
										// computation.

	}

	/**
	 * Method to compute the sentence containing the most frequently used word.
	 * 
	 * @param key       most frequently used word
	 * @param sentences an array of strings from sentences split by a "."
	 * @return returns the last sentence containing the most used word or "Not
	 *         Found"
	 */

	/**
	 * Helper Method to sort the hashmap and returns sorted list top word.
	 * @param unsorted Unsorted Hashmap with loaded words and counts from th                passage
	 * @return Returns most frequently used word.
	 */
	@SuppressWarnings("unchecked")
	public static String sortedMap(HashMap<String, Integer> unsorted) {
		Object[] a; // create object array to sort names
		a = unsorted.entrySet().toArray(); // load Hmap Set to an object Array.
		Arrays.sort(a, new Comparator() { // Use Arrays sorting algorithm using a comparator.
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>) o2).getValue() // using the HMaps value Count and using
																	// comparableinterface to sort by word frequency
						.compareTo(((Map.Entry<String, Integer>) o1).getValue());
			}
		}

		);
		String sortedList = "Top 10 Words:" + "\n";
		// Print top 10 results from the Array
		int count = 1;
		for (int i = 0; i < 10; i++) {

			sortedList += count + ". (" + ((Map.Entry<String, Integer>) a[i]).getKey() + " | Count: "
					+ ((Map.Entry<String, Integer>) a[i]).getValue() + ") " + "\n";
			count++;
		}

		System.out.println(sortedList);
		return (((Map.Entry<String, Integer>) a[0]).getKey());

	}

	public static String lastSentence(String key, String[] sentences) {

		for (int i = (sentences.length - 1); i > 0; i--) { // starting at the end of the array which would be the end of
															// the passage,
			if (sentences[i].contains(key)) // loop to check if the sentence contains the most used word.
				return sentences[i]; // return sentence if it contains word.

		}

		return "Not Found";

	}

}