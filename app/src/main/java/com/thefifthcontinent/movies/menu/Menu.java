package com.thefifthcontinent.movies.menu;

import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;

public class Menu 
{
	private static Scanner scanner = new Scanner(System.in);

	private String header;
	private List<Option> options = new ArrayList<>();
	
	public Menu(String header)
	{
		this.header = header;
	}
	
	public void addOption(Option option)
	{
		options.add(option);
	}
	
	public void render() 
	{
		System.out.println(header);
		
		for (Option option: options) {
			option.render();
		}
	}
	
	public Option getChoice()
	{
		System.out.print("Enter your choice: ");
		
		char choice = scanner.nextLine().toUpperCase().charAt(0);
		
		for (Option option: options) {
			if (option.getOption() == choice) {
				return option;
			}
		}
		
		return null;
	}
}
