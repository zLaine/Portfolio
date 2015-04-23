/*
A simulation of a robot sorting items onto shelves.  Done for CSC 201 at Northern Virginia Community College.


Written by: Zack Laine
Program: RobotSimulation
Date written: 11/23/2014
Date of Modification: 11/30/2014
Keywords:
	Variables:
		serialNum -- the serial number of the object the robot is holding
		atRow -- how far forward or backward the robot is in regards to rows of shelves
		mostSigDigit -- the most significant digit of the object held by the robot
		objectHeld -- if the robot is holding an item
		moveLeft -- tells whether the robot has moved to the left to place an object on a shelf.
		moveRight -- tells whether the robot has moved to the right to place an object on a shelf.
		^^^Variables for the Robot class
		
		shelf -- an array that holds the objects placed on the shelves
		^^^ Variables for Shelf class
	Segments:
		Robot goes to pickup station, picks up an item, checks the serial number and most significant digit to see
		whether it needs to go on a special shelf or just to be split by whether it is even or odd.  It then moves to the correct shelf
		and determines whether the shelf is full or not -- if not, it will place the item in the nearest open spot.  If it is full it will
		move to the next shelf on that side, unless it hits a special shelf (5 and 7 are reserved to hold cold and heavy items respectively.
		After the item has been placed, it returns to the pickup station to continue.  If it cannot place an item, the program stops.
	Program flow:
		(For 5/7) returnHome > moveRobot > addItem(Shelf class) > returnHome
		(All others) returnHome > moveRobot > fillShelf > addItem(Shelf class) > returnHome		
	Inputs:
		None
	Outputs:
		How the robot is moving, what shelf the item is being placed on, or an error that an item cannot be placed due to lack of shelves.

*/

package RobotSimulation;
import java.util.*;

public class Robot {
	
	int serialNum, atRow, mostSigDigit, weight;
	boolean objectHeld, moveLeft, moveRight;
	
	Robot()
	{
		serialNum = 0;
		atRow = 0;
		mostSigDigit = 0;
		weight = 0;
		objectHeld = false;
		moveLeft = false;
		moveRight = false;
	}
	
	//moveRobot
	//Sets the most significant digit for the item the robot is holding, and if it needs to be refrigerated or placed on the shelf for heavy items,
	//it moves the robot and places them, otherwise it calls fillShelf to put the item on the odd or even shelves.  Calls addItem or fillShelves.
	public void check5And7(Shelf shelf0, Shelf shelf1, Shelf shelf2, Shelf shelf3, Shelf shelf4, Shelf shelf5, Shelf shelf6, Shelf shelf7)
	{	
		if(this.objectHeld = true)
		{
			if(this.mostSigDigit == 5)
			{
				serialNum = shelf5.addItem(serialNum, shelf5, 5);
				if(serialNum == 0)
				{
					this.atRow = 2;
					this.moveLeft = true;
					System.out.println("Moved to row 2!");
					System.out.println("Moved left!");
					System.out.println("Put the item on shelf 5!");
				}
				
				this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
			}
			if(this.weight >= 700)
			{
				serialNum = shelf7.addItem(serialNum, shelf7, 7);
				if(serialNum == 0)
				{
					this.atRow = 3;
					this.moveLeft = true;
					System.out.println("Moved to row 3!");
					System.out.println("Moved left!");
					System.out.println("Put the item on shelf 7!");
				}
				
				this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
			}
			else
			{
				if(serialNum % 2 == 0)
				{
					fillShelf(0, serialNum, shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
				}
				else
				{
					fillShelf(1, serialNum, shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
				}
			}
		}
	}
	
	//fillShelf
	//Fills the shelves for even and odd items that do not have special placement needs, calls addItem then returnHome.
	public void fillShelf(int shelfNum, int serialNum, Shelf shelf0, Shelf shelf1, Shelf shelf2, Shelf shelf3, Shelf shelf4, Shelf shelf5, Shelf shelf6, Shelf shelf7)
	{
		//runs through the shelves for the even serial numbers
		switch(shelfNum)
		{
			case 0:
				if(shelf0.shelf[7] == 0)
				{
					serialNum = shelf0.addItem(serialNum, shelf0, 0);
				}
				if(serialNum == 0)
				{
					this.moveRight = true;
					System.out.println("Moved right!");
					System.out.println("Put the item on shelf 0!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
			case 2:
				if(shelf2.shelf[7] == 0)
				{
					serialNum = shelf2.addItem(serialNum, shelf2, 2);
				}
				if(serialNum == 0)
				{
					this.atRow = 1;
					this.moveRight = true;
					System.out.println("Moved to row 1!");
					System.out.println("Moved right!");
					System.out.println("Put the item on shelf 2!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
			case 4:
				if(shelf4.shelf[7] == 0)
				{
					serialNum = shelf4.addItem(serialNum, shelf4, 4);
				}
				if(serialNum == 0)
				{
					this.atRow = 2;
					this.moveRight = true;
					System.out.println("Moved to row 2!");
					System.out.println("Moved right!");
					System.out.println("Put the item on shelf 4!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
			case 6:
				if(shelf6.shelf[7] == 0)
				{
					serialNum = shelf6.addItem(serialNum, shelf6, 6);
				}
				if(serialNum == 0)
				{
					this.atRow = 3;
					this.moveRight = true;
					System.out.println("Moved to row 3!");
					System.out.println("Moved right!");
					System.out.println("Put the item on shelf 6!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
				else
				{
					System.out.println("All even-side shelves are full!  Item number " + serialNum + " not placed.");
					break;
				}
		}
		
		//runs through the shelves for the odd serial numbers
		switch(shelfNum)
		{
			case 1:
				if(shelf1.shelf[7] == 0)
				{
					serialNum = shelf1.addItem(serialNum, shelf1, 1);
				}
				if(serialNum == 0)
				{
					this.moveLeft = true;
					System.out.println("Moved left!");
					System.out.println("Put the item on shelf 1!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
			case 3:
				if(shelf3.shelf[7] == 0)
				{
					serialNum = shelf3.addItem(serialNum, shelf3, 3);
				}
				if(serialNum == 0)
				{
					this.atRow = 1;
					this.moveLeft = true;
					System.out.println("Moved to row 1!");
					System.out.println("Moved left!");
					System.out.println("Put the item on shelf 3!");
					this.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
					break;
				}
				else
				{
					System.out.println("All nonspecialty odd-side shelves are full!  Item number " + serialNum + " not placed.");
					break;
				}
		}		
	}
	
	//checkSigDig
	//finds the most significant digit for the serial number, used for sorting whether an item should go into shelf 5 or 7.
	public int checkSigDigit(int serialNum)
	{
		int sigDigit;
		sigDigit = serialNum/10000;
		
		return sigDigit;
	}
	
	//returnHome
	//Brings the robot back to the pickup station and has it pick up a new item to shelve.
	public void returnHome(Shelf shelf0, Shelf shelf1, Shelf shelf2, Shelf shelf3, Shelf shelf4, Shelf shelf5, Shelf shelf6, Shelf shelf7)
	{
		if(this.moveLeft == true)
		{
			this.moveLeft = false;
			System.out.println("Moved right!");
		}
		
		if(this.moveRight == true)
		{
			this.moveRight = false;
			System.out.println("Moved left!");
		}
		
		if(this.atRow != 0)
		{
			this.atRow = 0;
			System.out.println("Robot moved to row 0!");
		}
		
		Random rand = new Random();
		this.serialNum = (rand.nextInt(79998) + 1);
		this.mostSigDigit = this.checkSigDigit(serialNum);
		this.objectHeld = true;
		this.weight = (rand.nextInt(798) + 1);
		
		//insures no items that need to be refrigerated are over 700lbs
		while(this.weight >= 700 && this.mostSigDigit == 5)
		{
			this.weight = (rand.nextInt(798) + 1);
		}
		
		this.check5And7(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
	}
	
	public static void main(String[] args) 
	{
		Robot robot = new Robot();
		Shelf shelf0 = new Shelf(0);
		Shelf shelf1 = new Shelf(1);
		Shelf shelf2 = new Shelf(2);
		Shelf shelf3 = new Shelf(3);
		Shelf shelf4 = new Shelf(4);
		Shelf shelf5 = new Shelf(5);
		Shelf shelf6 = new Shelf(6);
		Shelf shelf7 = new Shelf(7);
		
		if(shelf0.shelf[0] == 0)
		{
			robot.returnHome(shelf0, shelf1, shelf2, shelf3, shelf4, shelf5, shelf6, shelf7);
		}
		
		shelf0.displayShelf();
		shelf1.displayShelf();
		shelf2.displayShelf();
		shelf3.displayShelf();
		shelf4.displayShelf();
		shelf5.displayShelf();
		shelf6.displayShelf();
		shelf7.displayShelf();
	}

}
