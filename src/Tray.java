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
	
	
	
	//given input ArrayList, adds all viable moves to the ArrayList as trays
	public void getMoves(ArrayList<Tray> input)
	{
	for (int i = 0;i<myBlockList.length;i++){
		for (int j=1;j<=4;j++)
		{
			switch (j)
			{
			case 1: //up
				if (myBlockList[i].topLeftY-1<0)
				{
					break;
				}
				for(int k =myBlockList[i].topLeftX;k<=myBlockList[i].bottomRightX;k++)
				{
					if (myBoardState[k][myBlockList[i].topLeftY-1]!=-1)
					{
						break;
					}
				}
				input.add(this.move(i, j));
				break;
			case 2: //right
				if (myBlockList[i].bottomRightX+1>=myBoardState.length)
				{
					break;
				}
				for(int k =myBlockList[i].topLeftY;k<=myBlockList[i].bottomRightY;k++)
				{
					if (myBoardState[k][myBlockList[i].bottomRightX+1]!=-1)
					{
						break;
					}
				}
				input.add(this.move(i, j));
				break;
			case 3: //down
				if (myBlockList[i].bottomRightY+1<myBoardState[0].length)
				{
					break;
				}
				for(int k =myBlockList[i].topLeftX;k<=myBlockList[i].bottomRightX;k++)
				{
					if (myBoardState[k][myBlockList[i].bottomRightY+1]!=-1)
					{
						break;
					}
				}
				input.add(this.move(i, j));
				break;
			case 4: //right
				if (myBlockList[i].topLeftX-1<0)
				{
					break;
				}
				for(int k =myBlockList[i].topLeftY;k<=myBlockList[i].bottomRightY;k++)
				{
					if (myBoardState[k][myBlockList[i].topLeftX-1]!=-1)
					{
						break;
					}
				}
				input.add(this.move(i, j));
				break;
			}
			
		}
	}
	}

	
}
