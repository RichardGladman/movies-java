package com.thefifthcontinent.movies.menu;

import java.util.function.Function;

public class Option
{
	String text;
	char option;
	Function<Void, Void> action;
	
	public Option(String text, char option, Function<Void, Void> action)
	{
		this.text = text;
		this.option = option;
		this.action = action;
	}
	
	public void render()
	{
		System.out.println(option + ": " + text);
	}
}
