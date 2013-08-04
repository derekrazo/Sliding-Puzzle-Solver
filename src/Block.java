
public class Block {

	public int topLeftX;
	public int topLeftY;
	public int bottomRightX;
	public int bottomRightY;
	
	public Block(int x1,int y1, int x2, int y2)
	{
		topLeftX = x1;
		topLeftY = y1;
		bottomRightX = x2;
		bottomRightY = y2;
	}
	
	public Block(Block previous)
	{
		topLeftX = previous.topLeftX;
		topLeftY = previous.topLeftY;
		bottomRightX = previous.bottomRightX;
		bottomRightY = previous.bottomRightY;
	}
	
	
}
