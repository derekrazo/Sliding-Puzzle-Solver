
public class Block {

	public int topLeftX;
	public int topLeftY;
	public int bottomRightX;
	public int bottomRightY;
	public int myLength;
	public int myHeight;
	
	public Block(int x1,int y1, int x2, int y2)
	{
		topLeftX = x1;
		topLeftY = y1;
		bottomRightX = x2;
		bottomRightY = y2;
		myLength = (bottomRightX - topLeftX) + 1;
		myHeight = (bottomRightY - topLeftY) + 1;
	}
	
	public Block(Block previous)
	{
		topLeftX = previous.topLeftX;
		topLeftY = previous.topLeftY;
		bottomRightX = previous.bottomRightX;
		bottomRightY = previous.bottomRightY;
		myLength = previous.myLength;
		myHeight = previous.myHeight;
	}
	
	public boolean equals(Block b)
	{
		if ((topLeftX == b.topLeftX) && 
			(topLeftY == b.topLeftY) &&
			(bottomRightX == b.bottomRightX) &&
			(bottomRightY == b.bottomRightY) &&
			(myLength == b.myLength) &&
			(myHeight == b.myHeight))
		{
			return true;
		}
		
		return false;
	}
	
	
}
