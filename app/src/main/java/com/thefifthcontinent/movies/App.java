package com.thefifthcontinent.movies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    private void saveData()
    {
    	if (!movieController.getDataChanged() && !actorController.getDataChanged() && !directorController.getDataChanged()) {
    		return;
    	}
    	
    	String directoryName = System.getProperty("user.home") + "/Documents/movies";
    	String fileName = directoryName + "/movies.txt";
    	
    	try {
    		Files.createDirectories(Paths.get(directoryName));
    	} catch (IOException e) {
    		view.error("Failed to create directories");
    		return;
    	}
    	
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
    		for (Actor actor: actors.values()) {
    			String data = "ACTOR::" + actor.getName() + "\n";
    			writer.write(data);
    		}

    		for (Director director: directors.values()) {
    			String data = "DIRECTOR::" + director.getName() + "\n";
    			writer.write(data);
    		}

    		for (Movie movie: movies.values()) {
    			String data = "MOVIE::" + movie.getId() + "," +
    									movie.getTitle() + "," +
    									movie.getCategory() + "," +
    									movie.getCertificate() + "," +
    									movie.getRunningTime() + "\n";
    			writer.write(data);
    			
        		for (Actor actor: movie.getStars()) {
        			data = "MOVIEACTOR::" + actor.getName() + "\n";
        			writer.write(data);
        		}

        		for (Director director: movie.getDirectors()) {
        			data = "MOVIEDIRECTOR::" + director.getName() + "\n";
        			writer.write(data);
        		}
    		}
} catch (IOException e) {
    		view.error("Failed to write data");
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
