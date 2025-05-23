package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Director;

public class DirectorController extends Controller
{
	protected void add()
    {
    	view.header("Adding Director");
    	
    	String name = view.getString("Enter Name", 2, 50);
    	
    	Director director = new Director(name);
    	App.getDirectors().put(name.toLowerCase(), director);
    	
    	view.success(name + " saved successfully");
    	dataChanged = true;
    }
    
	protected void edit()
	{
		view.header("Edit Director");

		String name = view.getString("Select Director", 2, 50);
		
		Director director = App.getDirectors().get(name.toLowerCase());
		if (director == null) {
			view.error("Director not found");
			return;
		}
		
		App.getDirectors().remove(name.toLowerCase());
		
		director.setName(view.getString("Enter Name", 0, 50, director.getName()));
		
		App.getDirectors().put(director.getName().toLowerCase(), director);
		
		view.success(director.getName() + "Saved Successfully");
   		dataChanged = true;
	}
	
	protected void delete()
	{
		view.header("Deleting Director");

		String name = view.getString("Select Director", 2, 50);
		
		Director director = App.getDirectors().get(name.toLowerCase());
		if (director == null) {
			view.error("Director not found");
			return;
		}
		
		App.getDirectors().remove(name.toLowerCase());
		
		view.success(director.getName() + " deleted");
		dataChanged = true;
	}
	
	protected void search()
	{
		view.header("Searching Directors");
    	
    	String criteria = view.getString("Enter Search Criteria (Enter For All)", 0, 50);
    	
    	view.printText("-".repeat(60), true);
    	view.printText("Directors matching: " + (criteria.equals("") ? "All" : criteria), true);
    	view.printText("-".repeat(60), true);
    	
    	App.getDirectors().values().stream()
    					.filter((v) -> v.getName().toLowerCase().startsWith(criteria))
    					.forEach(d -> view.printText(d.getName(), true));

    	view.printText("-".repeat(60), true);
	}
   
	protected Menu createMenu()
    {
    	Menu menu = new Menu("Manage Directors");
    	menu.addOption(new Option("Add New Director", 'A', this::add));
    	menu.addOption(new Option("Edit Director", 'E', this::edit));
    	menu.addOption(new Option("Delete Director", 'D', this::delete));
    	menu.addOption(new Option("Search Directors", 'S', this::search));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
