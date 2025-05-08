package com.thefifthcontinent.movies.controllers;

import com.thefifthcontinent.movies.menu.Menu;
import com.thefifthcontinent.movies.menu.Option;
import com.thefifthcontinent.movies.view.View;

public abstract class Controller
{
	protected final View view = new View();
	protected boolean dataChanged;
    
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
    
    public boolean getDataChanged()
    {
    	return dataChanged;
    }
    
    protected abstract void add();
    protected abstract void edit();
    protected abstract void delete();
    protected abstract void search();
    protected abstract Menu createMenu();
}
