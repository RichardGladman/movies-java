package com.thefifthcontinent.movies.view;

import java.util.Scanner;

public class View
{
	private Scanner scanner = new Scanner(System.in);
	
	public void header(String text)
	{
		System.out.println(ANSICodes.BLUE + "\n" + text + "\n" + ANSICodes.RESET);
	}
	
	public void printText(String text)
	{
		printText(text, false);
	}
	
	public void printText(String text, boolean newLine)
	{
		if (newLine) {
			System.out.println(text);
		} else {
			System.out.print(text);
		}
	}
	
	public char getChar()
	{
		return scanner.nextLine().toUpperCase().charAt(0);
	}
	
	public String getString(String prompt)
	{
		return getString(prompt, 0, 0);
	}
	
	public String getString(String prompt, int minLen, int maxLen)
	{
		boolean valid;
		String input;
		
		do {
			System.out.print(prompt + ": ");
			input = scanner.nextLine();
			
			if (minLen > 0 && input.length() < minLen) {
				System.out.println(ANSICodes.RED + "Error, input must be at least " + minLen + " characters long" + ANSICodes.RESET);
				valid = false;
			} else if (maxLen > 0 && input.length() > maxLen) {
				System.out.println(ANSICodes.RED + "Error, input must be no more than " + maxLen + " characters long" + ANSICodes.RESET);
				valid = false;
			} else {
				valid = true;
			}
		} while (!valid);
		
		return input;
	}
	
	public int getInteger(String prompt, int min, int max)
	{
		int value = 0;
		boolean valid = false;
		
		do {
			String input = getString(prompt);
			
			try {
				value = Integer.parseInt(input);
				
				if (min > 0 && value < min) {
					System.out.println(ANSICodes.RED + "Value must be greater than " + min + ANSICodes.RESET);
				} else if (max > 0 && value > max) {
					System.out.println(ANSICodes.RED + "Value must be less than " + max + ANSICodes.RESET);
				} else {
					valid = true;
				}
				
			} catch (NumberFormatException e) {
				System.out.println(ANSICodes.RED + "Not a valid integer" + ANSICodes.RESET);
			}
		} while (!valid);
		
		return value;
	}
	
	public <T extends Enum<T>> T getValue(String prompt, Class<T> enumType)
	{
		T value = null;
		boolean valid = false;
		
		do {
			String input = getString(prompt, 1, 0);
			input = input.replaceAll(" ", "_").toUpperCase();
			
			try {
				value = Enum.valueOf(enumType, input);
				valid = true;
			} catch(IllegalArgumentException e) {
				System.out.println(ANSICodes.RED + "Invalid value entered" + ANSICodes.RESET);
			}
		} while(!valid);
		
		return value;
	}
}
