package com.realestate;

public abstract class List 
{
	
	protected Listable[] list; /*Array to hold this list’s elements*/	 
	
	int numberOfItems;             /* Number of elements on this list*/
	
	protected int currentPosition;   /* Current position for iteration*/
	
	/*Instantiates and returns a reference to an empty list*/
	public List(int maxItems)  
	{
		
		numberOfItems = 0; list = new Listable[maxItems];
		
	}
	
	/*Returns whether this list is full*/
	public boolean isFull()  
	{
		
		return (list.length == numberOfItems);
		
	}
	
	/*Returns the number of elements on this list*/
	public int lengthIs()  
	{
		return numberOfItems; 
	}
	
	/*
	 * Returns true if an element with the same key as item is on this list; 
	 * otherwise, returns false
	 */
	public abstract boolean isThere (Listable item);
	
	/*Returns a copy of the list element with the same key as item*/
	public abstract Listable retrieve(Listable item); 
	
	/*Adds a copy of item to this list*/
	public abstract void insert (Listable item);  
	
	/*Deletes the element with the same key as item from this list*/
	public abstract void delete (Listable item);  
	
	/*Initializes current position for an iteration through this list*/
	public void reset() 
	{
		currentPosition = 0;
	}
	
	/*Returns copy of the next element on this list*/
	public Listable getNextItem() 
	{
	
		Listable next = list[currentPosition]; 
		if (currentPosition == numberOfItems-1)
		{
			currentPosition = 0;
		}
		else
		{
			currentPosition++;
		}
		
		return next.copy();
		
	} 
}
