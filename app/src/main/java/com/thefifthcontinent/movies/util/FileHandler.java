package com.thefifthcontinent.movies.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.thefifthcontinent.movies.model.Actor;
import com.thefifthcontinent.movies.model.Category;
import com.thefifthcontinent.movies.model.Director;
import com.thefifthcontinent.movies.model.Movie;

public class FileHandler
{
	private final String directory;
	private final String filename;
	
	public FileHandler(String directory, String filename)
	{
		this.directory = directory;
		this.filename = filename;
	}
	
	public void saveData(Map<String, Actor> actors, Map<String, Director> directors, Map<String, Movie> movies)
	{
		try {
			Files.createDirectories(Paths.get(directory));
	   	} catch(IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}
			
		
	   	try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename, false))) {
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
	   	} catch(IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}

	}

	public void loadData(Map<String, Actor> actors, Map<String, Director> directors, Map<String, Movie> movies)
	{
	   	try (BufferedReader reader = new BufferedReader(new FileReader(directory + filename))) {
	   		String line;
	   		Movie movie = null;
	        while ((line = reader.readLine()) != null) {
	        	String[] parts = line.split("::");
	        	if (parts[0].equals("ACTOR")) {
	        		Actor actor = new Actor(parts[1]);
	        		actors.put(parts[1].toLowerCase(), actor);
	        	} else if (parts[0].equals("DIRECTOR")) {
	        		Director director = new Director(parts[1]);
	        		directors.put(parts[1].toLowerCase(), director);
	        	} else if (parts[0] .equals("MOVIE")) {
	        		String[] fields = parts[1].split(",");
	        		movie = new Movie(fields[1], 
	        				Enum.valueOf(Category.class, fields[2]), 
	        				fields[3], Integer.parseInt(fields[4]));
	        		movies.put(fields[1].toLowerCase(), movie);
	        	} else if (parts[0].equals("MOVIEACTOR")) {
	        		movie.addStar(actors.get(parts[1].toLowerCase()));
	        	} else if (parts[0].equals("MOVIEDIRECTOR")) {
	        		movie.addDirector(directors.get(parts[1].toLowerCase()));
	        	}
	        }
	   	} catch (IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}
	
	}
}
