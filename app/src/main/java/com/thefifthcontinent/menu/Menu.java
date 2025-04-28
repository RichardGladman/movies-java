package com.thefifthcontinent.menu;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu 
{
	Scanner scanner = new Scanner(System.in); 

	String header;
	List<Option> options = new ArrayList<>();
	
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
	
	public char getChoice()
	{
		System.out.print("Enter your choice: ");
		
		return scanner.nextLine().toUpperCase().charAt(0);
	}
}
