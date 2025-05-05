package com.thefifthcontinent.movies;

import java.util.HashMap;
import java.util.Map;

import com.thefifthcontinent.movies.controllers.ActorController;
import com.thefifthcontinent.movies.controllers.DirectorController;
import com.thefifthcontinent.movies.controllers.MovieController;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.model.Director;
import com.thefifthcontinent.movies.model.Movie;
import com.thefifthcontinent.movies.util.FileHandler;
import com.thefifthcontinent.movies.view.View;

public class App 
{
	private static final Map<String, Movie> movies = new HashMap<>();
	private static final Map<String, Actor> actors = new HashMap<>();
	private static final Map<String, Director> directors = new HashMap<>();
	
	private final View view = new View();
	
	private ActorController actorController = new ActorController();
	private DirectorController directorController = new DirectorController();
	private MovieController movieController = new MovieController();
	
	public static Map<String, Movie> getMovies()
	{
		return movies;
	}
	
	public static Map<String, Actor> getActors()
	{
		return actors;
	}
	
	public static Map<String, Director> getDirectors()
	{
		return directors;
	}
	
    public static void main(String[] args) 
    {
    	new App().run();
    }
    
    private void run()
    {
    	loadData();
    	
    	Menu menu = createMenu();
    	Option choice = null;
    	
    	do {
			menu.render();
			choice = menu.getChoice();
			
			if (choice != null && choice.getAction() != null) {
				choice.getAction().run();
			}
			
		} while (choice.getOption() != 'Q');
    	
    	saveData();
    	
    }
    
    private void manageMovies()
    {
    	movieController.run();
    }
    
    private void manageActors()
    {
    	actorController.run();
    }
    
    private void manageDirectors()
    {
    	directorController.run();
    }
    
    private boolean loadData()
    {
    	FileHandler fHandler = new FileHandler(System.getProperty("user.home") + "/Documents/movies/", "movies.txt");
    	
    	try {
    		fHandler.loadData(actors, directors, movies);
    		return true;
    	} catch(RuntimeException e) {
    		view.error("Failed to load data");
    		return false;
    	}
    }
    
    private void saveData()
    {
    	if (!movieController.getDataChanged() && !actorController.getDataChanged() && !directorController.getDataChanged()) {
    		return;
    	}
    	
    	FileHandler fHandler = new FileHandler(System.getProperty("user.home") + "/Documents/movies/", "movies.txt");
    	
    	try {
    		fHandler.saveData(actors, directors, movies);
    		view.success("Data saved successfully");
    	} catch (RuntimeException e) {
    		view.error("Failed to save data " + e.getMessage());
    	}
    }
     
    private Menu createMenu()
    {
    	Menu menu = new Menu("Main Menu");
    	menu.addOption(new Option("Manage Movies", 'M', this::manageMovies));
    	menu.addOption(new Option("Manage Actors", 'A', this::manageActors));
    	menu.addOption(new Option("Manage Directors", 'D', this::manageDirectors));
    	menu.addOption(new Option("Quit", 'Q', null));

    	return menu;
    }
}
