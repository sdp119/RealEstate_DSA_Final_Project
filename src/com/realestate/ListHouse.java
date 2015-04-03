
package com.realestate;

public class ListHouse implements Listable{
    
    private int lotNumber;
    
    private String firstName;
    
    private String lastName;
    
    private int price;
    
    private int squareFeet;
    
    private int noOfBedRooms;
    
	/**
	 * @param lotNumber
	 * @param firstName
	 * @param lastName
	 * @param price
	 * @param squareFeet
	 * @param noOfBedRooms
	 */
	public ListHouse(int lotNumber, String firstName, String lastName,
			int price, int squareFeet, int noOfBedRooms) 
	{
		super();
		this.lotNumber = lotNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.price = price;
		this.squareFeet = squareFeet;
		this.noOfBedRooms = noOfBedRooms;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(int noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }
    
    @Override
    public int compareTo(Listable otherListHouse)
    {
    	
    	ListHouse other = (ListHouse)otherListHouse;
    	
    	return (this.lotNumber - other.lotNumber);
    	
    }

    @Override
    public Listable copy() 
    {
    	
    	ListHouse copyObject = new ListHouse(lotNumber, firstName, lastName, price, squareFeet, noOfBedRooms);
    	
    	return copyObject;
    	
    }
    
}
