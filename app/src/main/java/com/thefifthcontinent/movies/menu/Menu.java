package com.thefifthcontinent.movies.menu;

import java.util.List;

import com.thefifthcontinent.movies.view.View;

import java.util.ArrayList;

public class Menu 
{
	private final View view = new View();
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
		view.header(header);;
		
		for (Option option: options) {
			option.render();
		}
	}
	
	public Option getChoice()
	{
		view.printText("Enter your choice: ");
		char choice = view.getChar();
		
		for (Option option: options) {
			if (option.getOption() == choice) {
				return option;
			}
		}
		
		return null;
	}
}
