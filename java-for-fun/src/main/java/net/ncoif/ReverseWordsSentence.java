package net.ncoif;

public class ReverseWordsSentence {

	/**
	 * Reverse the words in a string.
	 * 
	 * For example, "I love chocolate" should return "chocolate love I".
	 * 
	 * @param stringToReverse
	 * @return
	 */
	public static String reverseWordsString(String stringToReverse) {
		char[] input = stringToReverse.toCharArray();
		char[] output = reverseWordsString(input);
		return new String(output);
	}

	private static char[] reverseWordsString(char[] stringToReverse) {
		// first reverse the entire string
		char[] s = reverseString(stringToReverse, 0, stringToReverse.length - 1);

		// then reverse each of the words
		int lastestWordBegining = 0;
		for (int i = 0; i < s.length - 1; i++) {
			if (s[i] == ' ') {
				s = reverseString(s, lastestWordBegining, i - 1);
				lastestWordBegining = i + 1;
			}
		}
		if (lastestWordBegining < s.length) {
			s = reverseString(s, lastestWordBegining, s.length-1);
		}
		
		return stringToReverse;
	}

	private static char[] reverseString(char[] word, int start, int end) {
		char[] range = new char[end - start + 1];

		for (int i = 0; i <= (end - start); i++) {
			range[i] = word[end - i];
		}

		for (int i = 0; i <= (end - start); i++) {
			word[start + i] = range[i];
		}
		return word;
	}
}
