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
	private Director director;
	private List<String> stars;
	
	public Movie(String title, Category category, String certificate, int runningTime)
	{
		super();
		this.id = nextId++;
		this.title = title;
		this.category = category;
		this.certificate = certificate;
		this.runningTime = runningTime;
		this.director = null;
		this.stars = new ArrayList<>();
	}
	
	public void addDirector(String name)
	{
		this.director = new Director(name);
	}
	
	public void addStar(String star)
	{
		for (String s: stars)
		{
			if (s.equalsIgnoreCase(star)) {
				return;
			}
		}
		
		stars.add(star);
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

	public Director getDirector()
	{
		return director;
	}

	public List<String> getStars()
	{
		return stars;
	}
}
