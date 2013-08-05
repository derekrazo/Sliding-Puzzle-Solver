
public class PathFinderTester {
	public static void main(String[] args)
	{
		int [][] startBoard =
			{{-1,2,2,2},
			{-1,0,-1,-1},
			{-1,-1,-1,-1},
			{-1,-1,1,-1}};
		
		Block [] startBlocks = {new Block(1,1,1,1),new Block(3,2,3,2),new Block(0,1,0,3)};
		
		int [][] endBoard = 
			{{1,-1,-1,-1},
			{0,-1,-1,-1},
			{-1,-1,-1,-1},
			{2,2,2,-1}};

		Block [] endBlocks = {new Block(1,0,1,0),new Block(0,0,0,0),new Block(3,0,3,2)};

		Tray trayStart = new Tray(startBoard, startBlocks);
		Tray trayEnd = new Tray(endBoard, endBlocks);
		
		PathFinder testPath = new PathFinder(trayStart, trayEnd);
		
		String [] solution = testPath.solution();

		/*
		int [][] startBoard =
			{{-1,-1,-1,-1},
			{-1, 0, -1, -1},
			{1,2,-1,-1},
			{-1,-1,-1,-1}};
		
		Block [] startBlocks = {new Block(1,1,1,1),new Block(2,0,2,0),new Block(2,1,2,1)};
		
		int [][] endBoard = 
			{{-1,-1,-1,-1},
			{0, -1, -1, -1},
			{1,-1,-1,-1},
			{2,-1,-1,-1}};

		Block [] endBlocks = {new Block(1,0,1,0),new Block(2,0,2,0),new Block(3,0,3,0)};
		
		Tray trayStart = new Tray(startBoard, startBlocks);
		Tray trayEnd = new Tray(endBoard, endBlocks);
		
		PathFinder testPath = new PathFinder(trayStart, trayEnd);
		
		String [] solution = testPath.solution();
		*/
	}
}
