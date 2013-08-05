import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;


public class TrayTest {

	public void cloneTestStringConstruct(){
		String [] config = new String[1];
		config[0]="0 0 0 0";
		Tray start = new Tray(config,"1 2");
		config [0]= "1 0 1 0";
		Tray finish = new Tray(config,"1 2");
		
		LinkedList test = new LinkedList();
		start.getMoves(test);
		Object moved = test.getFirst();
		assertEquals(start,new Tray(config,"1 2"));
		assertEquals(finish,new Tray(config,"1 2"));
		assertEquals(moved,finish);

	}
	
	@Test
	public void test() {
		
		int [][] startBoard =
		   {{ 0,-1,-1,-1},
			{-1,-1,-1,-1},
			{-1,-1,-1,-1},
			{-1,-1,-1,-1}};

		Block [] startBlocks = {new Block(0,0,0,0)};

		int [][] endBoard = 
			{{-1, 0,-1,-1},
			 {-1,-1,-1,-1},
			 {-1,-1,-1,-1},
			 {-1,-1,-1,-1}};

		Block [] endBlocks = {new Block(0,1,0,1)};
		
		int [][] leftBoard = 
			{{-1,-1,-1,-1},
			 { 0,-1,-1,-1},
			 {-1,-1,-1,-1},
			 {-1,-1,-1,-1}};

		Block [] leftBlocks = {new Block(1,0,1,0)};
		

		Tray trayStart = new Tray(startBoard, startBlocks);
		Tray trayStartCopy = new Tray(startBoard, startBlocks);
		
		Tray trayLeft = new Tray(leftBoard, leftBlocks);
		
		Tray trayEnd = new Tray(endBoard, endBlocks);
		
		//Tests equals method
		assertTrue(trayStart.equals(trayStartCopy));
		assertFalse(trayStart.equals(trayEnd));

		//test down
		Tray moveTray = new Tray(trayStart.move(0, 3));
		assertTrue(trayEnd.equals(moveTray));
		assertFalse(moveTray.equals(trayStart));

		//test up
		moveTray = moveTray.move(0, 1);
		assertTrue(trayStart.equals(moveTray));
		assertFalse(moveTray.equals(trayEnd));

		//test left
		moveTray = moveTray.move(0, 4);
		assertTrue(moveTray.equals(trayLeft));
		
		//test right
		moveTray = moveTray.move(0, 2);
		assertTrue(moveTray.equals(trayStart));
		
	}
	
		@Test
	public void testBig() {
		
		int [][] startBoard =
		   {{ 0, 0,-1,-1},
			{ 0, 0,-1,-1},
			{-1,-1,-1,-1},
			{-1,-1,-1,-1}};

		Block [] startBlocks = {new Block(0,0,1,1)};

		int [][] endBoard = 
			{{-1, 0,0,-1},
			 {-1, 0,0,-1},
			 {-1,-1,-1,-1},
			 {-1,-1,-1,-1}};

		Block [] endBlocks = {new Block(0,1,1,2)};
		
		int [][] leftBoard = 
			{{-1,-1,-1,-1},
			 { 0,0,-1,-1},
			 { 0,0,-1,-1},
			 {-1,-1,-1,-1}};

		Block [] leftBlocks = {new Block(1,0,2,1)};
		

		Tray trayStart = new Tray(startBoard, startBlocks);
		Tray trayStartCopy = new Tray(startBoard, startBlocks);
		
		Tray trayLeft = new Tray(leftBoard, leftBlocks);
		
		Tray trayEnd = new Tray(endBoard, endBlocks);
		
		//Tests equals method
		assertTrue(trayStart.equals(trayStartCopy));
		assertFalse(trayStart.equals(trayEnd));

		//test down
		Tray moveTray = new Tray(trayStart.move(0, 3));
		assertTrue(trayEnd.equals(moveTray));
		assertTrue(moveTray.equals(trayEnd));
		assertFalse(trayStart.equals(trayEnd));
		assertFalse(moveTray.equals(trayStart));
		

		//test up
		moveTray = moveTray.move(0, 1);
		assertTrue(trayStart.equals(moveTray));
		assertFalse(moveTray.equals(trayEnd));

		//test left
		moveTray = moveTray.move(0, 4);
		assertTrue(moveTray.equals(trayLeft));
		
		//test right
		moveTray = moveTray.move(0, 2);
		assertTrue(moveTray.equals(trayStart));
		
	}
	
	@Test
	public void firstTrayConstructorTest() {
		String[] config = new String[1];
		config[0] = "0 0 0 0";
		Tray firstTest = new Tray(config, "1, 2");
		System.out.println(firstTest);
		assertEquals(firstTest, new Tray(config, "1, 2"));
		// see if a new block has been made
		// see if block has correct coordinates
		
		// code taken from Tray constructor just for testing creation of Blocks in myTestBlockList
		// getting around myTestBlockList's private setting
		String testSize = "1, 2";
		Block[] myTestBlockList = new Block[config.length];
		String[] holder = testSize.split(" ");
		int[][] myTestBoardState = 
				new int[Integer.parseInt(holder[1])][Integer.parseInt(holder[0])];
		for (int i=0;i<myTestBlockList.length;i++){
			holder =config[i].split(" ");
			myTestBlockList[i]=new Block (Integer.parseInt(holder[0]),Integer.parseInt(holder[1]),
					Integer.parseInt(holder[2]),Integer.parseInt(holder[3]));
			for (int j = myTestBlockList[i].topLeftX;j<myTestBlockList[i].bottomRightX;j++){
				Arrays.fill(myTestBoardState[j],myTestBlockList[i].topLeftY,
						myTestBlockList[i].bottomRightY, i);
			}
		}
		// test
		assertNotNull(myTestBlockList);
		assertTrue(myTestBlockList[0] = new Block(0, 0, 0, 0););
	}
}
}
