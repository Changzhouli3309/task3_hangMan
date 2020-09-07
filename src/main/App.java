package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();

	public static void main(String[] args) {
		//image of the hang man and the length of arrays is max guess times
		String[] hangManP = { "\n ____", "\n | ", " |", "\n | ", " o", "\n | ", "/|\\", "\n | ",
				"/ \\", "\n | ", "\n/|\\ End\n" };
		// pool of answer
		String[] answers = { "java", "tea", "coffe", "game" };
		
		boolean playing = true;
		boolean isCorrect = false;
		// create Hang Man by pick random answer
		HangMan hm = new HangMan(answers[getRanInt(0, answers.length)]);
		
		System.out.println("Welcome to The Hangman game.");
		System.out.println("Enter a letter or a word as one guess.");

		while (playing) {
			//show game status
			printHangMan(hm.getGuessTimes(),hangManP);
			printAnser(hm.getShowText());
			printGuessed(hm.getGuessedPool());

			if (isCorrect || (hm.getGuessTimes() == hangManP.length)) {
				//when you get right answer or out of guessed times
				if (isCorrect) {
					System.out.println("You Win.");
				}
				
				System.out.println("\nPlay again? (Y/N)");
				if (getInput().toLowerCase().equals("y")) {
					//get a new hang man
					hm = new HangMan(answers[getRanInt(0, answers.length)]);
					isCorrect = false;
				} else {
					playing = false;
				}
				
			} else {
				System.out.print("Your guess: ");
				isCorrect = hm.checkAnser(getInput().toLowerCase());
			}
		}
	}

	/**
	 * get random number(integer) from a fixed limit
	 * @param min
	 * @param max
	 * @return int
	 */
	static int getRanInt(int min, int max) {
		return ran.nextInt(max - min) + min;
	}
	
	/**
	 * get string from scanner
	 */
	private static String getInput() {
		return scan.nextLine();
	}

	private static void printGuessed(ArrayList<Character> guessedPool) {
		if (!guessedPool.isEmpty()) {
			String s = "Gussed:[ ";
			for (char c : guessedPool) {
				s += c + " ";
			}
			System.out.println(s + "]");
		}
	}

	private static void printAnser(char[] showText) {
		String s = "[ ";
		for (char c : showText) {
			s += c + " ";
		}
		System.out.println(s + "]");
	}

	/**
	 * Print the hang man base on guessed times
	 * @param guessTimes 
	 * @param hangManP : hang man image as string array 
	 */
	private static void printHangMan(int guessTimes, String[] hangManP) {
		String s = "";
		if (guessTimes != 0) {
			for (int i = 0; i < guessTimes; i++) {
				s += hangManP[i];
			}
		}
		System.out.println(s);
	}

}
