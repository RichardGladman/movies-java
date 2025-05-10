package com.thefifthcontinent.movies.model;

import java.util.ArrayList;
import java.util.List;

public class Movie
{
	private static int nextId = 1;
	
	private int id;
	private String title;
	private Category category;
	private String certificate;
	private int runningTime;
	private List<Director> directors;
	private List<Actor> stars;
	
	public Movie(String title, Category category, String certificate, int runningTime)
	{
		super();
		this.id = nextId++;
		this.title = title;
		this.category = category;
		this.certificate = certificate;
		this.runningTime = runningTime;
		this.directors = new ArrayList<>();
		this.stars = new ArrayList<>();
	}
	
	public void addDirector(Director director)
	{
		directors.add(director);
	}
	
	public void addStar(Actor actor)
	{
		stars.add(actor);
	}

	public static int getNextId()
	{
		return nextId;
	}

	public int getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public Category getCategory()
	{
		return category;
	}

	public String getCertificate()
	{
		return certificate;
	}

	public int getRunningTime()
	{
		return runningTime;
	}

	public List<Director> getDirectors()
	{
		return directors;
	}

	public List<Actor> getStars()
	{
		return stars;
	}
	
	@Override
	public String toString()
	{
		String data = "%-50s %-10s %-4s %d".formatted(title, category, certificate, runningTime);

		if (stars.size() > 0) {
			data += "\nActors:\n";
			for (Actor a: stars) {
				data += a.getName() + "\n";
			}
		}
		
		if (directors.size() > 0) {
			data += "Directors:\n";
			for (Director d: directors) {
				data += d.getName() + "\n";
			}
		}
		
		return data;
	}
}
