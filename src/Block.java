public class Block {
	//holds the index of:
	//leftmost column, rightmost colum, top row, bottom row
	//also contains the length (row) and the Height(column)

	public int leftCol; //upper left x position
	public int topRow;  //upper left y position
	public int rightCol; //upper right x position
	public int bottomRow; //upper right y position
	public int myLength; 
	public int myHeight;
	public double myWeight;
	public int similarStartBlocks;
	public Block[] similarEndBlocks;
	
	public Block(int x1,int y1, int x2, int y2)
	{
		//takes in (x,y) of top left corner, then (x,y) of bottom right corner
		leftCol = x1;
		topRow = y1;
		rightCol = x2;
		bottomRow = y2;
		myLength = (rightCol - leftCol) + 1;
		myHeight = (bottomRow - topRow) + 1;

	}
	
	public Block(int x1,int y1, int x2, int y2, Block[] endBlocks)
	{
		//takes in (x,y) of top left corner, then (x,y) of bottom right corner, array of similar endblocks
		leftCol = x1;
		topRow = y1;
		rightCol = x2;
		bottomRow = y2;
		myLength = (rightCol - leftCol) + 1;
		myHeight = (bottomRow - topRow) + 1;
		
		this.similarEndBlocks = endBlocks; 
		this.calibrateWeight();
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
		similarStartBlocks=previous.similarStartBlocks;
		similarEndBlocks=previous.similarEndBlocks;
	}
	

	public boolean equals(Block b)
	{
		//compares two blocks, returns true if all position variables are equal
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
	
	public double distanceFromClosestEndBlock() {
		double leastDistance = 100000000;
		
		for (Block b: this.similarEndBlocks) 
		{
			double cur = Math.sqrt((Math.abs((this.leftCol-b.leftCol)*(this.leftCol-b.leftCol)) + 
					Math.abs(((this.topRow-b.topRow)*(this.topRow-b.topRow)))));
			if (cur < leastDistance)
			{
				leastDistance = cur;
			}
			
		}
		
		//System.out.println("Least Distance : " + leastDistance);
		return leastDistance;
	}
	
	public void calibrateWeight() {
		/*
		 *calibrates the weight variable
		 * uses the following conditions:
		 *  - number of similar end blocks
		 *  - size of block
		 *  - distance to closest end block
		 *  - number of similar blocks in the tray
		 * */
		if (similarEndBlocks != null && similarEndBlocks.length != 0 ){
			
			myWeight = ((similarEndBlocks.length + ((this.myHeight*this.myLength) / 
				  							     ((this.distanceFromClosestEndBlock()+1))))
				  	 / (similarStartBlocks+1)); 
		}
		else{
			myWeight = 0;
		}
	}

}
