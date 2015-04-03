package com.realestate;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class RealEstate {

	/* The list of house information */
	private static SortedList list = new SortedList();

	/* Text fields */
	private static JTextField txtLotNumber;
	private static JTextField txtFirstName;
	private static JTextField txtLastName;
	private static JTextField txtPrice;
	private static JTextField txtSquareFeet;
	private static JTextField txtNoOfBedRooms;

	/* Status Label */
	private static JLabel statusLabel;

	/* Display information about parameter house on screen */
	private static void showHouse(ListHouse house) 
	{
		try 
		{
			txtLotNumber.setText(Integer.toString(house.getLotNumber()));
			txtFirstName.setText(house.getFirstName());
			txtLastName.setText(house.getLastName());
			txtPrice.setText(Integer.toString(house.getPrice()));
			txtSquareFeet.setText(Integer.toString(house.getSquareFeet()));
			txtNoOfBedRooms.setText(Integer.toString(house.getNoOfBedRooms()));
			
		} 
		catch (Exception exception) 
		{

			exception.printStackTrace();

		}

	}

	/* Returns current screen information as a ListHouse */
	private static ListHouse getHouse() {
		String lastName;
		String firstName;
		int lotNumber;
		int price;
		int squareFeet;
		int noOfBedRooms;
		ListHouse house = null;
		try 
		{
			lotNumber = Integer.parseInt(txtLotNumber.getText());
			firstName = txtFirstName.getText();
			lastName = txtLastName.getText();
			price = Integer.parseInt(txtPrice.getText());
			squareFeet = Integer.parseInt(txtSquareFeet.getText());
			noOfBedRooms = Integer.parseInt(txtNoOfBedRooms.getText());
	
			house = new ListHouse(lotNumber, firstName, lastName, price,
					squareFeet, noOfBedRooms);
		
		} 
		catch (Exception exception) 
		{
	
			exception.printStackTrace();
	
		}
		
		return house;
		
	}

	/* Clears house information from screen */
	private static void clearHouse() {
		txtLotNumber.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPrice.setText("");
		txtSquareFeet.setText("");
		txtNoOfBedRooms.setText("");
	}

	/* Define a button listener*/
	
	private static class ActionHandler implements ActionListener 
	{
		/* Listener for the button events */
		
		public void actionPerformed(ActionEvent event) 
		{
			ListHouse house;
			
			/* Handles Reset event */
			if (event.getActionCommand().equals("Reset"))
			{
				list.reset();
				if (list.lengthIs() == 0)
				{
					clearHouse();
				}
				else 
				{
					house = (ListHouse) list.getNextItem();
					showHouse(house);
				}
				statusLabel.setText("List reset");

			}
			/* Handles Next event */
			else if (event.getActionCommand().equals("Next"))
			{
				if (list.lengthIs() == 0)
				{
					statusLabel.setText("list is empty!");
				}
				else
				{
					house = (ListHouse) list.getNextItem();
					showHouse(house);
					statusLabel.setText("Next house displayed");
				}
			}
			/* Handles Add event */
			else if (event.getActionCommand().equals("Add"))
			{
																	
				try {
					house = getHouse();
					if (list.isThere(house))
					{
						statusLabel.setText("Lot number already in use");
					}
					else
					{
						list.insert(house);
						statusLabel.setText("House added to list");
					}
				} 
				catch (NumberFormatException badHouseData) 
				{
					// Text field info incorrectly formated
					statusLabel.setText("Number? " + badHouseData.getMessage());

				}
			}
			/* Handles Delete event */
			else if (event.getActionCommand().equals("Delete")) 
			{ 
																	
				try 
				{
					house = getHouse();
					if (list.isThere(house)) 
					{
						list.delete(house);
						statusLabel.setText("House deleted");
					} else
					{
						statusLabel.setText("Lot number not on list");
					}
				}
				catch (NumberFormatException badHouseData) 
				{
					/* Text field info incorrectly formated*/
					statusLabel.setText("Number? " + badHouseData.getMessage());
				}
			}
			/* Handles Clear event */
			else if (event.getActionCommand().equals("Clear")) 
			{
				clearHouse();
				statusLabel.setText(list.lengthIs() + " houses on list");

			}
			/* Handles Find event */
			else if (event.getActionCommand().equals("Find"))
			{
				int lotNumber;
				try 
				{
					lotNumber = Integer.parseInt(txtLotNumber.getText());
					house = new ListHouse(lotNumber, "", "", 0, 0, 0);
					if (list.isThere(house))
					{
						house = (ListHouse) list.retrieve(house);
						showHouse(house);
						statusLabel.setText("House found");
					}
					else
					{
						statusLabel.setText("House not found");
					}

				} catch (NumberFormatException badHouseData) {
					/* Text field info incorrectly formated */
					statusLabel.setText("Number? " + badHouseData.getMessage());
				}
			}
		}

	}

	public static void main(String args[]) throws IOException {
		ListHouse house;

		JLabel lblBlank;
		JLabel lblLot; 
		JLabel lblFirstName;
		JLabel lblLastName;
		JLabel lblPrice;
		JLabel lblSquareFeet;
		JLabel lblNoOfBedRooms;
		
		JButton btnReset; 
		JButton btnNext; 
		JButton btnAdd; 
		JButton btnDelete; 
		JButton btnClear; 
		JButton btnFind; 
		ActionHandler action; // Declare Action listener

		/* Declare,Instantiate,Initialize display frame*/
		final JFrame displayFrame = new JFrame();
		displayFrame.setTitle("Real Estate Program");
		displayFrame.setSize(350, 400);
		displayFrame.addWindowListener(new WindowAdapter()

		{
			public void windowClosing(WindowEvent event) {

				ListHouse house;
				displayFrame.dispose();
				// Close window
				try {
					// Store info from list into house file
					HouseFile.rewrite();
					list.reset();
					int length = list.lengthIs();
					for (int counter = 1; counter <= length; counter++) {
						house = (ListHouse) list.getNextItem();
						HouseFile.putToFile(house);
					}
					HouseFile.close();

				} catch (Exception fileCloseProblem) {
					System.out
							.println("Exception raised concerning the house info file "
									+ "upon program termination");
				}
				System.exit(0);
			}
		});

		// Instantiate content pane and information panel
		Container contentPane = displayFrame.getContentPane();
		JPanel infoPanel = new JPanel();
		// Instantiate/initialize labels, and text fields
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setBorder(new LineBorder(Color.red));
		lblBlank = new JLabel("");
		lblLot = new JLabel("Lot Number:  ", JLabel.RIGHT);
		txtLotNumber = new JTextField("", 15);
		lblFirstName = new JLabel("First Name:  ", JLabel.RIGHT);
		txtFirstName = new JTextField("", 15);

		lblLastName = new JLabel("Last Name:  ", JLabel.RIGHT);
		txtLastName = new JTextField("", 15);
		lblPrice = new JLabel("Price:  ", JLabel.RIGHT);
		txtPrice = new JTextField("", 15);
		lblSquareFeet = new JLabel("Square Feet:  ", JLabel.RIGHT);
		txtSquareFeet = new JTextField("", 15);
		lblNoOfBedRooms = new JLabel("Number of Bedrooms:  ", JLabel.RIGHT);
		txtNoOfBedRooms = new JTextField("", 15);

		// Instantiate/register buttons
		btnReset = new JButton("Reset");
		btnNext = new JButton("Next");
		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear");
		btnFind = new JButton("Find");

		// Instantiate/register button listeners
		action = new ActionHandler();
		btnReset.addActionListener(action);
		btnNext.addActionListener(action);
		btnAdd.addActionListener(action);
		btnDelete.addActionListener(action);
		btnClear.addActionListener(action);
		btnFind.addActionListener(action);

		// Load info from house file into list
		HouseFile.reset();
		while (HouseFile.moreHouses()) {
			house = HouseFile.getNextHouse();
			list.insert(house);
		}

		list.reset();
		if (list.lengthIs() != 0) {

			house = (ListHouse) list.getNextItem();
			showHouse(house);
		}
		// Update status
		statusLabel
				.setText(list.lengthIs() + " houses on list                ");

		// Add components to frame
		infoPanel.setLayout(new GridLayout(10, 2));
		infoPanel.add(statusLabel);
		infoPanel.add(lblBlank);
		infoPanel.add(lblLot);
		infoPanel.add(txtLotNumber);
		infoPanel.add(lblFirstName);
		infoPanel.add(txtFirstName);
		infoPanel.add(lblLastName);
		infoPanel.add(txtLastName);
		infoPanel.add(lblPrice);
		infoPanel.add(txtPrice);
		infoPanel.add(lblSquareFeet);
		infoPanel.add(txtSquareFeet);
		infoPanel.add(lblNoOfBedRooms);
		infoPanel.add(txtNoOfBedRooms);
		infoPanel.add(btnReset);
		infoPanel.add(btnNext);
		infoPanel.add(btnAdd);
		infoPanel.add(btnDelete);
		infoPanel.add(btnClear);
		infoPanel.add(btnFind);

		contentPane.add(infoPanel);
		displayFrame.show();

	}
}
