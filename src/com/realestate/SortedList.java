package com.realestate;

public class SortedList extends List
{
	
	public SortedList(int maxItems) // Instantiates and returns a reference to an empty list object // with room for maxItems elements 
	{
		super(maxItems); 
	}
	
	public SortedList() // Instantiates and returns a reference to an empty list object with room for 100 elements 
	{
		super(100); 
	}
	
	public boolean isThere (Listable item) // Returns true if an element with the same key as item is on this list; otherwise, returns false 
	{
		int compareResult;
		int midPoint; 
		int first = 0; 
		int last = numberOfItems - 1;
		boolean moreToSearch = (first <= last);
		boolean found = false;
		while (moreToSearch && !found) 
		{
			midPoint = (first + last) / 2; 
			compareResult = item.compareTo(list[midPoint]);
			if (compareResult == 0) 
				found = true;
			else if (compareResult < 0)  // item is less than element at location
			{
				last = midPoint - 1; 
				moreToSearch = (first <= last);
			}
	
			else                         // item is greater than element at location 
			{
			
				first = midPoint + 1; 
				moreToSearch = (first <= last);
			 
			}
		}
		return found;
	}
	
	public Listable retrieve (Listable item) // Returns a copy of the list element with the same key as item 
	{
		int compareResult;
		int first = 0; 
		int last = numberOfItems - 1;
		int midPoint = (first + last) / 2; 
		boolean found = false;
		
		while (!found)
		{
		
			midPoint = (first + last) / 2;
			compareResult = item.compareTo(list[midPoint]);
			if (compareResult == 0) 
				found = true;
		
			else if (compareResult < 0)// item is less than element at location 
				last = midPoint - 1;
			else                         // item is greater than element at location 
				first = midPoint + 1;
		}
		return list[midPoint].copy();
	}
	
	// Adds a copy of item to this list 
	public void insert (Listable item)
	{
		int location = 0; 
		boolean moreToSearch = (location < numberOfItems);
		while (moreToSearch) 
		{
			if (item.compareTo(list[location]) < 0)  
				moreToSearch = false;
			else 
			{
				location++;
				moreToSearch = (location < numberOfItems);
			}
		}
		
		for (int index = numberOfItems; index > location; index--) 
			list[index] = list[index - 1];
		
		list[location] = item.copy(); 
		numberOfItems++;
	}
	
	// Deletes the element that matches item from this list
	public void delete (Listable item)
	{
		
		int location = 0;
		
		while (item.compareTo(list[location]) != 0)
		{
			location++;
		}
		
		for (int index = location + 1; index < numberOfItems; index++)
		{
			list[index - 1] = list[index];
		}
		
		numberOfItems--;
		
	}
}
