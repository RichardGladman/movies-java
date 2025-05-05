package com.thefifthcontinent.movies.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.thefifthcontinent.movies.model.Actor;
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
			
		
	   	try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename, true))) {
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
}
