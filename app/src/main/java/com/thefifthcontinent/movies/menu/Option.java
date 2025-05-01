package com.thefifthcontinent.movies.menu;

public class Option
{
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
		System.out.println(option + ": " + text);
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
