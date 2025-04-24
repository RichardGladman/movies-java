package com.thefifthcontinent.menu;

import java.util.List;
import java.util.ArrayList;

public class Menu 
{
	String header;
	List<Option> options = new ArrayList<>();
	
	public Menu(String header)
	{
		this.header = header;
	}
	
	public void render() 
	{
		System.out.println(header);
		
		for (Option option: options) {
			option.render();
		}
	}
}
