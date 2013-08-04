import java.util.LinkedList;

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
	public Tray myPreviousTray;
	private Block[] myBlockList;

	public Tray(String[] config, String size)
	{
		boardState = new int[size.charAt(0)][size.charAt(2)];
		//blockSizes = new int[config.length];
		myBlockList = null;
		previousTray = null;
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
	public Tray(int [][] inBoardState, Block[] inBlockList)
	{
		myBoardState = inBoardState;
		myBlockList = inBlockList;
		myPreviousTray = null;
	}

	//assumes move is possible / legal
	public Tray move(int moveBlockId, int direction) {
		
		Tray clone = new Tray(this);
		
		//change the block representation in new tray
		switch(direction){
			case 1: 
					for (int i = 0;i < myBlockList[moveBlockId].myLength;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX+i]
								[clone.myBlockList[moveBlockId].topLeftY+1] = moveBlockId;
						
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX+i]
								[clone.myBlockList[moveBlockId].bottomRightY] = -1;
					}
					
					clone.myBlockList[moveBlockId].topLeftY = clone.myBlockList[moveBlockId].topLeftY+1;
					clone.myBlockList[moveBlockId].bottomRightY = clone.myBlockList[moveBlockId].bottomRightY+1;
					
					break;
					
			case 2: 
					for (int i = 0;i < myBlockList[moveBlockId].myHeight;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].bottomRightX+1]
								[clone.myBlockList[moveBlockId].topLeftY+i] = moveBlockId;
						
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX]
								[clone.myBlockList[moveBlockId].topLeftY+i] = -1;
					}
				
					clone.myBlockList[moveBlockId].topLeftX = clone.myBlockList[moveBlockId].topLeftX+1;
					clone.myBlockList[moveBlockId].bottomRightX = clone.myBlockList[moveBlockId].bottomRightX+1;
					break;
					
			case 3: 
					for (int i = 0;i < myBlockList[moveBlockId].myLength;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX+i]
								[clone.myBlockList[moveBlockId].bottomRightY-1] = moveBlockId;
						
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX+i]
								[clone.myBlockList[moveBlockId].topLeftY] = -1;
					}
				
					clone.myBlockList[moveBlockId].topLeftY = clone.myBlockList[moveBlockId].topLeftY-1;
					clone.myBlockList[moveBlockId].bottomRightY = clone.myBlockList[moveBlockId].bottomRightY-1;
					break;
					
			case 4: 
					for (int i = 0;i < myBlockList[moveBlockId].myHeight;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].topLeftX-1]
								[clone.myBlockList[moveBlockId].topLeftY+i] = moveBlockId;
						
						clone.myBoardState[clone.myBlockList[moveBlockId].bottomRightX]
								[clone.myBlockList[moveBlockId].topLeftY+i] = -1;
					}
				
					clone.myBlockList[moveBlockId].topLeftX = clone.myBlockList[moveBlockId].topLeftX-1;
					clone.myBlockList[moveBlockId].bottomRightX = clone.myBlockList[moveBlockId].bottomRightX-1;
					break;
		}
		
		return clone;
	}
	
	
	//given input ArrayList, adds all viable moves to the ArrayList as trays
	public void getMoves(LinkedList<Tray> fringe)
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
				fringe.add(this.move(i, j));
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
				fringe.add(this.move(i, j));
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
				fringe.add(this.move(i, j));
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
				fringe.add(this.move(i, j));
				break;
			}
			}
		}
	}

	public String moveMade(Tray next)
	{
		String prevPos = null;
		String nextPos = null;

		boolean prevPosFound = false;
		boolean nextPosFound = false;

		for(int col = 0; col < myBoardState[0].length; col++)
		{
			for(int row = 0; row < myBoardState.length; row++)
			{
				if(this.myBoardState[row][col] != next.myBoardState[row][col])
				{
					if(this.myBoardState[row][col]!=0&&!prevPosFound)
					{
						prevPos = col + " " + row;
						prevPosFound = true;
					}
					if(next.myBoardState[row][col]!=0&&!nextPosFound)
					{
						nextPos = col + " " + row;
						nextPosFound = true;
					}
				}
			}
		}

		return prevPos + " " + nextPos;
	}

}
