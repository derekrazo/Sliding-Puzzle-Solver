public class Block {
	//holds the index of:
	//leftmost column, rightmost colum, top row, bottom row
	//also contains the length (row) and the Height(column)

	public int leftCol; //upper left x position
	public int topRow;  //upper left y position
	public int rightCol;
	public int bottomRow;
	public int myLength;
	public int myHeight;
	public double myWeight;
	public int similarStartBlocks;
	//public Block[] simmilarEndBlocks = new Block[3]; for tests
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
		//takes in (x,y) of top left corner, then (x,y) of bottom right corner
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
		//compares two blocks, returns true if all variables are equal
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
		 * Will add more considerations:
		 * 
		 * 1. distance from goal
		 * 2. how many similar blocks are in the start and end?
		 * 		-equal number in both doesnt scale at all
		 * 		-more in start = lower weight 
		 * 		-more in end = 
		 * 		-add number of end position / number of start positions
		 * 3.
		 * 4.
		 * 5.
		 * 6.
		 * 7.
		 * 8.
		 * 
		 * */
		if (similarEndBlocks != null && similarEndBlocks.length != 0 ){
			
			//System.out.println("got here");
			
			/*
			myWeight = ((similarEndBlocks.length + ((this.myHeight*this.myLength) / 
				  							     (this.distanceFromClosestEndBlock()+1)))
				  	 / similarStartBlocks); 
			*/
			
			//Random r = new Random(10);
			
			myWeight = ((similarEndBlocks.length + ((this.myHeight*this.myLength) / 
				  							     ((this.distanceFromClosestEndBlock()+1))))
				  	 / (similarStartBlocks+1)); 
			
			
			//myWeight = ((similarEndBlocks.length + ((this.myHeight*this.myLength) / (this.distanceFromClosestEndBlock()+1)))/ similarStartBlocks);
			/*
			System.out.println("Sim block len: " + similarEndBlocks.length);
			System.out.println("height times weight: " + (this.myHeight*this.myLength));
			System.out.println("distance from closest end + 1: " + this.distanceFromClosestEndBlock()+1);
			System.out.println("size divided by distance: " + ((this.myHeight*this.myLength) / (this.distanceFromClosestEndBlock()+1)));
			System.out.println("# simmiliar start blocks: " + similarStartBlocks);
			
			//myWeight = 1.0/(this.distanceFromClosestEndBlock()+1);
			System.out.println("Block calibrated at : " + myWeight);
			*/
		}
		else{
			myWeight = 0;
		}
	}

}
