/*
 * A tray is a representation of a board state. 
 * Trays will be stored inside a graph.
 * Tray is a vertex in the graph of tray states.
 * 
 * Knows:
 * 
 * 1. Position of blocks
 * 2. position of spaces
 * 3. Weight which represents its closeness to another tray
 * 
 * Does:
 * 
 * 
 * 
 * 
 * */


public class Tray {
	
	private int[][] myBoardState;
	private Tray myPreviousTray;
	private Block[] myBlockList;

	public Tray(String[] config, String size)
	{
		
	}
	
	//direction is represented as clockwise positive integers from 1-4 inclusive
	public Tray(Tray previousTray, int moveBlockId, int direction)
	{
		myPreviousTray = previousTray;
		myBlockList = previousTray.myBlockList;
		myBoardState = previousTray.move(moveBlockId,direction).myBoardState;
	}
	
	public Tray(Tray previousTray)
	{
		myPreviousTray = previousTray;
	
		myBlockList = previousTray.myBlockList.clone();
		
		myBoardState = new int[previousTray.myBoardState.length][];
		
		for (int i = 0;i < previousTray.myBoardState.length;i++)
		{
			myBoardState[i] = previousTray.myBoardState[i].clone();
		}
	}
	
	//only used for testing right now
	public Tray(int [][] inBoardState)
	{
		myBoardState = inBoardState;
	}

	//assumes move is possible / legal
	public Tray move(int moveBlockId, int direction) {
		
		Tray clone = new Tray(this);
		
		switch(direction){
			case 1: clone.myBlockList[moveBlockId].topLeftY = clone.myBlockList[moveBlockId].topLeftY+1;
					clone.myBlockList[moveBlockId].bottomRightY = clone.myBlockList[moveBlockId].bottomRightY+1;
					break;
					
			case 2: clone.myBlockList[moveBlockId].topLeftX = clone.myBlockList[moveBlockId].topLeftX+1;
					clone.myBlockList[moveBlockId].bottomRightX = clone.myBlockList[moveBlockId].bottomRightX+1;
					break;
					
			case 3: clone.myBlockList[moveBlockId].topLeftY = clone.myBlockList[moveBlockId].topLeftY-1;
					clone.myBlockList[moveBlockId].bottomRightY = clone.myBlockList[moveBlockId].bottomRightY-1;
					break;
					
			case 4: clone.myBlockList[moveBlockId].topLeftX = clone.myBlockList[moveBlockId].topLeftX-1;
					clone.myBlockList[moveBlockId].bottomRightX = clone.myBlockList[moveBlockId].bottomRightX-1;
					break;
		}
		
		
		
		return null;
	}

	
}
