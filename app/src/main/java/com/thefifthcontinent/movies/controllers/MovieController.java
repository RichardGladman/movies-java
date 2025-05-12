package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.App;
import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.model.Category;
import com.thefifthcontinent.movies.model.Director;
import com.thefifthcontinent.movies.model.Movie;

public class MovieController extends Controller
{
	protected void add()
    {
    	view.header("Adding Movie");
    	
    	String title = view.getString("Enter Title", 2, 50);
    	Category category = view.<Category>getValue("Enter Category", Category.class);
    	String certificate = view.getString("Enter Certificate", 1, 5);
    	int runningTime = view.getInteger("Enter Running Time", 0, 0);
    	
    	Movie movie = new Movie(title, category, certificate, runningTime);
    	addDirectors(movie);
    	addStars(movie);
    	
    	App.getMovies().put(title.toLowerCase(), movie);
    	
    	view.success(title + " added successfully");
    	dataChanged = true;
    }
    
	protected void edit()
    {
    	view.header("Editing Movie");

    	String title = view.getString("Select Movie", 2, 50);
    	
    	Movie movie = App.getMovies().get(title.toLowerCase());
    	if (movie == null) {
    		view.error("Movie not found");
    		return;
    	}

    	title = view.getString("Enter Title", 2, 50, movie.getTitle());
    	Category category = view.<Category>getValue("Enter Category", movie.getCategory(), Category.class);
    	String certificate = view.getString("Enter Certificate", 1, 5, movie.getCertificate());
    	int runningTime = view.getInteger("Enter Running Time", 0, 0, movie.getRunningTime());
    	
    	movie.setTitle(title);
    	movie.setCategory(category);
    	movie.setCertificate(certificate);
    	movie.setRunningTime(runningTime);
    	
    	
    }
    
	protected void delete()
    {
    	view.header("Deleting Movie");
    	
    	String title = view.getString("Select Movie", 2, 50);
    	
    	Movie movie = App.getMovies().get(title.toLowerCase());
    	if (movie == null) {
    		view.error("Movie not found");
    		return;
    	}
    	
    	App.getMovies().remove(title.toLowerCase());
    	
    	view.success(movie.getTitle() + " deleted");
    	dataChanged = true;
    }
    
	protected void search()
    {
    	view.header("Searching Movies");
    	
    	String criteria = view.getString("Enter Search Criteria (Enter For All)", 0, 50);
    	
    	view.printText("-".repeat(80), true);
    	view.printText("Movies matching: " + (criteria.equals("") ? "All" : criteria), true);
    	view.printText("-".repeat(80), true);
    	
    	App.getMovies().values().stream()
    					.filter((v) -> v.getTitle().toLowerCase().startsWith(criteria))
    					.forEach(m -> view.printText(m.toString(), true));

    	view.printText("-".repeat(80), true);
    }
    
    private void addDirectors(Movie movie)
    {
    	String input = null;
    	
    	do {
    		input = view.getString("Enter Director (Blank to finish)");
    		
    		if (input != "") {
    			Director director = App.getDirectors().get(input.toLowerCase());
    			if (director == null) {
    				view.error("That director does not exist");
    			} else {
    				movie.addDirector(director);
    			}
    		}
    	} while (input != "");
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
    
    protected Menu createMenu()
    {
    	Menu menu = new Menu("Manage Movies");
    	menu.addOption(new Option("Add New Movie", 'A', this::add));
    	menu.addOption(new Option("Edit Movie", 'E', this::edit));
    	menu.addOption(new Option("Delete Movie", 'D', this::delete));
    	menu.addOption(new Option("Search Movies", 'S', this::search));
    	menu.addOption(new Option("Back to Main", 'B', null));

    	return menu;
    }
}
