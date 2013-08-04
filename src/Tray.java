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
		//boardState = new int[size.charAt(0)][size.charAt(2)];
		//blockSizes = new int[config.length];
		//previousTray = null;
	}
	
	//direction is represented as clockwise positive integers from 1-4 inclusive
	public Tray(Tray previousTray, int moveBlockId, int direction)
	{
		myPreviousTray = previousTray;
		myBlockList = previousTray.myBlockList;
		myBoardState = previousTray.move(moveBlockId,direction).myBoardState;
	}

	private int[][] move(int moveBlockId, int direction) {
		
		
		return null;
	}
	
	public boolean equals(Tray match)
	//compares tray to match tray, returns true if equal
	{
		return (this.mapArray.equals(match.mapArray)&&this.sizeList.equals(match.sizeList));
	}
	
}
