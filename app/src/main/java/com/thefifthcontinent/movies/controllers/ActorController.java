package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.view.View;

public class ActorController
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
    
    private void addActor()
    {
    	view.header("Adding Actor");
    	
    	String name = view.getString("Enter Name", 2, 50);
    	
    	Actor actor = new Actor(name);
    	App.getActors().put(name.toLowerCase(), actor);
    	
    	view.success(name + " saved successfully");
    	dataChanged = true;
    }
    
    private void editActor()
    {
    	view.header("Edit Actor");
    	
    	String name = view.getString("Select Actor", 2, 50);
    	
    	Actor actor = App.getActors().get(name.toLowerCase());
    	if (actor == null) {
    		view.error("Actor not found");
    		return;
    	}
    	
    	App.getActors().remove(name.toLowerCase());
    	
    	actor.setName(view.getString("Enter Name", 0, 50, actor.getName()));
    	
    	App.getActors().put(actor.getName().toLowerCase(), actor);
    	
    	view.success(actor.getName() + " saved successfully");
    	dataChanged = true;
    }
    
    private Menu createMenu()
    {
    	Menu menu = new Menu("Manage Actors");
    	menu.addOption(new Option("Add New Actor", 'A', this::addActor));
    	menu.addOption(new Option("Edit Actor", 'E', this::editActor));
    	menu.addOption(new Option("Delete Actor", 'D', null));
    	menu.addOption(new Option("Search Actors", 'S', null));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
