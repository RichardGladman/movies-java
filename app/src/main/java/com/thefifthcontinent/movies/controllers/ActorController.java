package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;

public class ActorController extends Controller
{
	protected void add()
    {
    	view.header("Adding Actor");
    	
    	String name = view.getString("Enter Name", 2, 50);
    	
    	Actor actor = new Actor(name);
    	App.getActors().put(name.toLowerCase(), actor);
    	
    	view.success(name + " saved successfully");
    	dataChanged = true;
    }
    
    protected void edit()
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
    
    protected void delete()
    {
    	view.header("Delete Actor");
    	
    	String name = view.getString("Select Actor", 2, 50);
    	
    	Actor actor = App.getActors().get(name.toLowerCase());
    	if (actor == null) {
    		view.error("Actor not found");
    		return;
    	}
    	
    	App.getActors().remove(name.toLowerCase());
    	
    	view.success(actor.getName() + " deleted");
    	dataChanged = true;
    }
    
    protected void search()
    {
    	view.header("Search Actor");
    	
    	String criteria = view.getString("Enter Search Criteria (Enter For All)", 0, 50);
    	
    	view.printText("-".repeat(60), true);
    	view.printText("Actors matching: " + (criteria.equals("") ? "All" : criteria), true);
    	view.printText("-".repeat(60), true);
    	
    	App.getActors().values().stream()
    					.filter((v) -> v.getName().toLowerCase().startsWith(criteria))
    					.forEach(a -> view.printText(a.getName(), true));

    	view.printText("-".repeat(60), true);

    }
    
    protected Menu createMenu()
    {
    	Menu menu = new Menu("Manage Actors");
    	menu.addOption(new Option("Add New Actor", 'A', this::add));
    	menu.addOption(new Option("Edit Actor", 'E', this::edit));
    	menu.addOption(new Option("Delete Actor", 'D', this::delete));
    	menu.addOption(new Option("Search Actors", 'S', this::search));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
