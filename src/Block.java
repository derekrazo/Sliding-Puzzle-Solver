
public class Block {

	public int leftCol;
	public int topRow;
	public int rightCol;
	public int bottomRow;
	public int myLength;
	public int myHeight;
	
	public Block(int x1,int y1, int x2, int y2)
	{
		leftCol = x1;
		topRow = y1;
		rightCol = x2;
		bottomRow = y2;
		myLength = (rightCol - leftCol) + 1;
		myHeight = (bottomRow - topRow) + 1;
	}
	
	public Block(Block previous)
	{
		leftCol = previous.leftCol;
		topRow = previous.topRow;
		rightCol = previous.rightCol;
		bottomRow = previous.bottomRow;
		myLength = previous.myLength;
		myHeight = previous.myHeight;
	}
	
	public boolean equals(Block b)
	{
		if ((leftCol == b.leftCol) && 
			(topRow == b.topRow) &&
			(rightCol == b.rightCol) &&
			(bottomRow == b.bottomRow) &&
			(myLength == b.myLength) &&
			(myHeight == b.myHeight))
		{
			return true;
		}
		
		return false;
	}
	
	
}

