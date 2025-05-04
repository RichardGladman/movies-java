package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.model.Category;
import com.thefifthcontinent.movies.model.Movie;
import com.thefifthcontinent.movies.view.View;

public class MovieController 
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
    
    private void addMovie()
    {
    	view.header("Adding Movie");
    	
    	String title = view.getString("Enter Title", 2, 50);
    	Category category = view.<Category>getValue("Enter Category", Category.class);
    	String certificate = view.getString("Enter Certificate", 1, 5);
    	int runningTime = view.getInteger("Enter Running Time", 0, 0);
    	String directorName = view.getString("Enter Director", 2, 50);
    	
    	Movie movie = new Movie(title, category, certificate, runningTime);
    	movie.addDirector(directorName);
    	addStars(movie);
    	
    	App.getMovies().put(title, movie);
    	
    	view.success(title + " added successfully");
    	dataChanged = true;
    }
    
    private void editMovie()
    {
    	view.header("Editing Movie");
    }
    
    private void deleteMovie()
    {
    	view.header("Deleting Movie");
    }
    
    private void searchMovies()
    {
    	view.header("Searching Movies");
    }
    
    private void viewMovie()
    {
    	view.header("View Movie");
    }
    
    private void addStars(Movie movie)
    {
    	String input = null;
    	
    	do {
    		input = view.getString("Enter Actor (Blank to finish)");
    		
    		if (input != "") {
    			Actor actor = App.getActors().get(input.toLowerCase());
    			if (actor == null) {
    				view.error("That actor does not exist");
    			} else {
    				movie.addStar(actor);
    			}
    		}
    	} while (input != "");
    }
    
    private Menu createMenu()
    {
    	Menu menu = new Menu("Manage Movies");
    	menu.addOption(new Option("Add New Movie", 'A', this::addMovie));
    	menu.addOption(new Option("Edit Movie", 'E', this::editMovie));
    	menu.addOption(new Option("Delete Movie", 'D', this::deleteMovie));
    	menu.addOption(new Option("Search Movies", 'S', this::searchMovies));
    	menu.addOption(new Option("View Movie", 'V', this::viewMovie));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
