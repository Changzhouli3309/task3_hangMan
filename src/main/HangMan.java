package main;

import java.util.ArrayList;
import java.util.Arrays;

public class HangMan {

	private char[] answer;
	private char[] showText;
	private ArrayList<Character> guessedPool;
	private int guessTimes;

	/**
	 * save input answer as character array, create show text with same length as
	 * answer and fill with "_", create empty array list guess pool, set guess times
	 * to 0
	 * 
	 * @param answer : string
	 */
	public HangMan(String answer) {
		this.answer = answer.toCharArray();
		this.showText = new char[this.answer.length];
		Arrays.fill(showText, '_');
		this.guessedPool = new ArrayList<Character>();
		this.guessTimes = 0;
	}

	public boolean checkAnser(String toCheck) {

		if (toCheck.length() == 1) {
			// if you guess one letter
			char c = toCheck.charAt(0);
			return checkChar(c);
		} else {
			return checkString(toCheck);
		}
	}

	private boolean checkChar(char c) {
		boolean hasMatch = false;
		boolean isGuessed = false;

		for (char d : showText) {
			if (c == d) {
				isGuessed = true;
			}
		}

		for (int i = 0; i < answer.length; i++) {
			if (c == answer[i]) {
				showText[i] = c;
				hasMatch = true;
			}
		}

		if (!hasMatch || isGuessed) {
			if (!guessedPool.contains(c) && !isGuessed) {
				// add wrong guess to the guessed pool
				guessedPool.add(c);
				guessedPool.sort(null);
			}
			guessTimes++;
		}

		return Arrays.equals(answer, showText);
	}

	private boolean checkString(String toCheck) {
		boolean isMatch = Arrays.equals(answer, toCheck.toCharArray());

		if (!isMatch) {
			guessTimes++;
		} else {
			// show the answer when you guess whole word rightly
			showText = answer;
		}

		return isMatch;
	}

	public int getGuessTimes() {
		return guessTimes;
	}

	public char[] getShowText() {
		return showText;
	}

	public ArrayList<Character> getGuessedPool() {
		return guessedPool;
	}

}
