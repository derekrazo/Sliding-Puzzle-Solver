import java.util.LinkedList;
import java.util.Arrays;

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
	//myBoardState is a doubleArray showing position of blocks
	//myBlockList contains all block objects on the tray
	//myPreviousTray points back to the Tray preceding this one
	
	public int[][] myBoardState;
	public Tray myPreviousTray;
	public Block[] myBlockList;

	public Tray(String[] config, String size) {
		//takes in a String Array 
		// of format [x y x y,x y x y, etc] where each cell is the points for one block
		// as well as a string size of format "x y" 
		myPreviousTray = null;
		myBlockList = new Block[config.length];
		String[] holder = size.split(" ");
		myBoardState = new int[Integer.parseInt(holder[0])][Integer
				.parseInt(holder[1])];
		for (int n = 0; n < myBoardState.length; n++) {
			Arrays.fill(myBoardState[n], -1);
		}
		for (int i = 0; i < myBlockList.length; i++) {
			String[] block = config[i].split(" ");
			myBlockList[i] = new Block(Integer.parseInt(block[0]),
					Integer.parseInt(block[1]), Integer.parseInt(block[2]),
					Integer.parseInt(block[3]));
			for (int j = myBlockList[i].leftCol; j <= myBlockList[i].rightCol; j++) {
				Arrays.fill(myBoardState[j], myBlockList[i].topRow,
						myBlockList[i].bottomRow + 1, i);
			}
		}
	}
	
	//direction is represented as clockwise positive integers from 1-4 inclusive
	public Tray(Tray previousTray, int moveBlockId, int direction)
	{
		//makes new tray off of playing one move on previous Tray
		myPreviousTray = previousTray;
		myBlockList = previousTray.myBlockList;
		myBoardState = previousTray.move(moveBlockId,direction).myBoardState;
	}

	public Tray(Tray previousTray)
	{
		//clones previous tray
		myPreviousTray = previousTray;

		myBlockList = new Block[previousTray.myBlockList.length];
		
		for (int b = 0;b < previousTray.myBlockList.length;b++)
		{
			myBlockList[b] = new Block( previousTray.myBlockList[b].leftCol,
										previousTray.myBlockList[b].topRow,
										previousTray.myBlockList[b].rightCol,
										previousTray.myBlockList[b].bottomRow
									  );
							
		}
		
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
						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol+i]
								[clone.myBlockList[moveBlockId].topRow-1] = moveBlockId;

						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol+i]
								[clone.myBlockList[moveBlockId].bottomRow] = -1;
					}

					clone.myBlockList[moveBlockId].topRow = clone.myBlockList[moveBlockId].topRow-1;
					clone.myBlockList[moveBlockId].bottomRow = clone.myBlockList[moveBlockId].bottomRow-1;

					break;

			case 2: 
					for (int i = 0;i < myBlockList[moveBlockId].myHeight;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].rightCol+1]
								[clone.myBlockList[moveBlockId].topRow+i] = moveBlockId;

						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol]
								[clone.myBlockList[moveBlockId].topRow+i] = -1;
					}

					clone.myBlockList[moveBlockId].leftCol = clone.myBlockList[moveBlockId].leftCol+1;
					clone.myBlockList[moveBlockId].rightCol = clone.myBlockList[moveBlockId].rightCol+1;
					break;

			case 3: 
					for (int i = 0;i < myBlockList[moveBlockId].myLength;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol+i]
								[clone.myBlockList[moveBlockId].bottomRow+1] = moveBlockId;

						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol+i]
								[clone.myBlockList[moveBlockId].topRow] = -1;
					}

					clone.myBlockList[moveBlockId].topRow = clone.myBlockList[moveBlockId].topRow+1;
					clone.myBlockList[moveBlockId].bottomRow = clone.myBlockList[moveBlockId].bottomRow+1;
					break;

			case 4: 
					for (int i = 0;i < myBlockList[moveBlockId].myHeight;i++)
					{
						clone.myBoardState[clone.myBlockList[moveBlockId].leftCol-1]
								[clone.myBlockList[moveBlockId].topRow+i] = moveBlockId;

						clone.myBoardState[clone.myBlockList[moveBlockId].rightCol]
								[clone.myBlockList[moveBlockId].topRow+i] = -1;
					}

					clone.myBlockList[moveBlockId].leftCol = clone.myBlockList[moveBlockId].leftCol-1;
					clone.myBlockList[moveBlockId].rightCol = clone.myBlockList[moveBlockId].rightCol-1;
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
			Label1:
			switch (j)
			{
			
			case 1: //up
				if (myBlockList[i].topRow-1<0)
				{
					break;
				}
				for(int k =myBlockList[i].leftCol;k<=myBlockList[i].rightCol;k++)
				{
					if (myBoardState[k][myBlockList[i].topRow-1]!=-1)
					{
						break Label1;
					}
				}
				fringe.add(this.move(i, j));
				break;
			case 2: //right
				if (myBlockList[i].rightCol+1>=myBoardState.length)
				{
					break;
				}
				for(int k =myBlockList[i].topRow;k<=myBlockList[i].bottomRow;k++)
				{
					if (myBoardState[myBlockList[i].rightCol+1][k]!=-1)
					{
						break Label1;
					}
				}
				fringe.add(this.move(i, j));
				break;
			case 3: //down
				if (myBlockList[i].bottomRow+1>=myBoardState[0].length)
				{
					break;
				}
				for(int k =myBlockList[i].leftCol;k<=myBlockList[i].rightCol;k++)
				{
					if (myBoardState[k][myBlockList[i].bottomRow+1]!=-1)
					{
						break Label1;
					}
				}
				fringe.add(this.move(i, j));
				break;
			case 4: //right
				if (myBlockList[i].leftCol-1<0)
				{
					break;
				}
				for(int k =myBlockList[i].topRow;k<=myBlockList[i].bottomRow;k++)
				{
					if (myBoardState[myBlockList[i].leftCol-1][k]!=-1)
					{
						break Label1;
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
				int thisBlockID = this.myBoardState[row][col];
				int nextBlockID = next.myBoardState[row][col];
				
				if(nextBlockID==-1&&!prevPosFound&&thisBlockID!=-1)
				{
					prevPos = this.myBlockList[thisBlockID].leftCol + " " + this.myBlockList[thisBlockID].topRow;
					prevPosFound = true;
				}

				if(thisBlockID==-1&&!nextPosFound&&nextBlockID!=-1)
				{
					nextPos = next.myBlockList[nextBlockID].leftCol + " " + next.myBlockList[nextBlockID].topRow;
					nextPosFound = true;
				}
			}
		}

		return prevPos + " " + nextPos;
	}
	
	public boolean equals(Tray other)
	{
		for(Block block : this.myBlockList)
		{
			if(other.myBoardState[block.leftCol][block.topRow]==-1)
			{
				return false;
			}
			if(!block.equals(other.myBlockList[other.myBoardState[block.leftCol][block.topRow]]))
			{
				return false;
			}
		}
		return true;
	}
	
	
	
	// fixing
	
	public void addWeight(Tray t) {
		int myProximity;
		int mySize;
		Block[] alreadySeen = findDuplicates(t);
		for (Block b: t.myBlockList) {
			for (int i = 0; i < alreadySeen.length; i++) {
				if (b != alreadySeen[i]) {
					b.myProximity = b.findProximity(t);
					b.myWeight = b.myProximity * b.mySize;
				} else {
					b.myWeight = b.myWeight - 1;
				}
			}
		}

		// overload this
	}
	
	public Block[] findDuplicates(Tray t) {
		Block[] alreadySeen = new Block[myBlockList.length];
		for (int i = 0; i < t.myBlockList.length; i++) {
			Block a = this.myBlockList[i];
			for (int j = i - 1; j < myBlockList.length; j++) {
				Block b = this.myBlockList[j];
				if (b.equals(a)) {
					b = alreadySeen[i];
				}
			}
		}
		return alreadySeen;
	}
		public boolean isOK(){
		boolean annie = false;
		boolean[][] visited = new boolean[this.myBoardState.length][this.myBoardState[0].length];
		for (int i=0;i<this.myBlockList.length;i++){
			int x1 = this.myBlockList[i].leftCol;
			int x2 = this.myBlockList[i].rightCol;
			int y1 = this.myBlockList[i].topRow;
			int y2 = this.myBlockList[i].bottomRow;
			for (int j=x1;j<=x2;j++)
			{
				for (int k=y1;k<=y2;j++)
				{
					if (this.myBoardState[j][k]!=i || visited[j][k]==true){
						return annie;
					}
					visited[j][k]=true;
				}
			}
			for (int x=0;x<visited.length;x++){
				for (int y=0;y<visited[0].length;y++){
					if(visited[x][y]==false && this.myBoardState[x][y]!=-1){
						return annie;
					}
				}
			}
		}
		if (this.myPreviousTray!=null)
		{
			if (this.myPreviousTray.myBlockList.length!=this.myPreviousTray.myBlockList.length ||
					this.myPreviousTray.myBoardState.length!=this.myBoardState.length ||
					this.myPreviousTray.myBoardState[0].length!=this.myBoardState[0].length)
			{
				return annie;
			}
			int countdiff=0;
			for(int l =0; l<this.myBlockList.length;l++){
				if (!this.myBlockList[l].equals(this.myPreviousTray.myBlockList[l])){
					if ((this.myBlockList[l].leftCol==this.myPreviousTray.myBlockList[l].leftCol &&
							this.myBlockList[l].rightCol==this.myPreviousTray.myBlockList[l].rightCol)||
							this.myBlockList[l].topRow==this.myPreviousTray.myBlockList[l].topRow &&
							this.myBlockList[l].bottomRow==this.myPreviousTray.myBlockList[l].bottomRow)
					{
						countdiff++;
					}else{
						return annie;
					}
				}
			}
			if (countdiff!= 1||countdiff!=0){
				return annie;
			}
		}
		
		//check that IDs on board state match (this also checks for overlapping blocks) (done)
		//check there are no block ID's where they shouldn't be (done)
		//check that previousTray's size is same (done)
		//check that all blocks but one match 
		annie =true;
		return annie;
	}


}
