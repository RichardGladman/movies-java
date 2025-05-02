package com.thefifthcontinent.movies.menu;

import com.thefifthcontinent.movies.view.View;

public class Option
{
	private final View view = new View();
	
	String text;
	char option;
	Runnable action;
	
	public Option(String text, char option, Runnable action)
	{
		this.text = text;
		this.option = option;
		this.action = action;
	}
	
	public void render()
	{
		view.printText(option + ": " + text, true);
	}

	public String getText()
	{
		return text;
	}

	public char getOption()
	{
		return option;
	}

	public Runnable getAction()
	{
		return action;
	}
	
	
}
