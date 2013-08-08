public class Block {
	//holds the index of:
	//leftmost column, rightmost colum, top row, bottom row
	//also contains the length (row) and the Height(column)

	public int leftCol;
	public int topRow;
	public int rightCol;
	public int bottomRow;
	public int myLength;
	public int myHeight;
	public int myWeight;
	
	public Block(int x1,int y1, int x2, int y2)
	{
		//takes in (x,y) of top left corner, then (x,y) of bottom right corner
		leftCol = x1;
		topRow = y1;
		rightCol = x2;
		bottomRow = y2;
		myLength = (rightCol - leftCol) + 1;
		myHeight = (bottomRow - topRow) + 1;
		// myWeight = (size x proximity)
		//	    = myWeight;
	}
	
	public Block(Block previous)
	{
		//takes in a block, makes a clone of it
		leftCol = previous.leftCol;
		topRow = previous.topRow;
		rightCol = previous.rightCol;
		bottomRow = previous.bottomRow;
		myLength = previous.myLength;
		myHeight = previous.myHeight;
		myWeight = previous.myWeight;
	}
	
	public boolean equals(Block b)
	{
		//compares two blocks, returns true if all variables are equal
		if ((leftCol == b.leftCol) && 
			(topRow == b.topRow) &&
			(rightCol == b.rightCol) &&
			(bottomRow == b.bottomRow) &&
			(myLength == b.myLength) &&
			(myHeight == b.myHeight) &&
			(myWeight == b.myWeight))
		{
			return true;
		}
		
		return false;
	}
	
	
}

