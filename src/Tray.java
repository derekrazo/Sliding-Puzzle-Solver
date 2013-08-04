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

	private int[][] boardState;
	private Tray previousTray;
	private int[] blockSizes;

	public Tray(String[] config, String size)
	{
		
	}
	
	//direction is represented as clockwise positive integers from 1-4 inclusive
	public Tray(Tray previousTray, int moveBlockId, int direction)
	{
		myPreviousTray = previousTray;
		blockSizes = previousTray.blockSizes;
		this.boardState = previousTray.move(moveBlockId,direction);
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
