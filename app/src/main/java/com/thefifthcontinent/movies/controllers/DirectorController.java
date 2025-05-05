package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Director;
import com.thefifthcontinent.movies.view.View;

public class DirectorController
{
	private final View view = new View();
	private boolean dataChanged;
    
    public void run()
    {
    	Menu menu = createMenu();
    	Option choice = null;
    	
    	do {
			menu.render();
			choice = menu.getChoice();
			
			if (choice != null && choice.getAction() != null) {
				choice.getAction().run();
			}
			
		} while (choice.getOption() != 'B');
    }
    
    public boolean getDataChanged()
    {
    	return dataChanged;
    }
   
    private void add()
    {
    	view.header("Adding Director");
    	
    	String name = view.getString("Enter Name", 2, 50);
    	
    	Director director = new Director(name);
    	App.getDirectors().put(name.toLowerCase(), director);
    	
    	view.success(name + " saved successfully");
    	dataChanged = true;
    }
    
    private Menu createMenu()
    {
    	Menu menu = new Menu("Manage Directors");
    	menu.addOption(new Option("Add New Director", 'A', this::add));
    	menu.addOption(new Option("Edit Director", 'E', null));
    	menu.addOption(new Option("Delete Director", 'D', null));
    	menu.addOption(new Option("Search Directors", 'S', null));
    	menu.addOption(new Option("View Director", 'V', null));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
