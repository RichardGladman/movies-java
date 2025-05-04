package com.thefifthcontinent.movies;

import java.util.HashMap;
import java.util.Map;

import com.thefifthcontinent.movies.controllers.ActorController;
import com.thefifthcontinent.movies.controllers.MovieController;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.model.Movie;

public class App 
{
	private static final Map<String, Movie> movies = new HashMap<>();
	private static final Map<String, Actor> actors = new HashMap<>();
	
	public static Map<String, Movie> getMovies()
	{
		return movies;
	}
	
	public static Map<String, Actor> getActors()
	{
		return actors;
	}
	
    public static void main(String[] args) 
    {
    	new App().run();
    }
    
    private void run()
    {
    	Menu menu = createMenu();
    	Option choice = null;
    	
    	do {
			menu.render();
			choice = menu.getChoice();
			
			if (choice != null && choice.getAction() != null) {
				choice.getAction().run();
			}
			
		} while (choice.getOption() != 'Q');
    }
    
    private void manageMovies()
    {
    	new MovieController().run();
    }
    
    private void manageActors()
    {
    	new ActorController().run();
    }
     
    private Menu createMenu()
    {
    	Menu menu = new Menu("Main Menu");
    	menu.addOption(new Option("Manage Movies", 'M', this::manageMovies));
    	menu.addOption(new Option("Manage Actors", 'A', this::manageActors));
    	menu.addOption(new Option("Manage Directors", 'D', null));
    	menu.addOption(new Option("Quit", 'Q', null));

    	return menu;
    }
}
