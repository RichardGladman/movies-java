package com.thefifthcontinent.movies.view;

import java.util.Scanner;

public class View
{
	private Scanner scanner = new Scanner(System.in);
	
	public void header(String text)
	{
		System.out.println(ANSICodes.BLUE + text + ANSICodes.RESET);
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
}
