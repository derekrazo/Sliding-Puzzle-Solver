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
		/*
		System.out.println( );
		System.out.println("trayStart : bottom rightX : " + trayStart.myBlockList[0].rightCol);
		System.out.println("trayStart : bottom rightY : " + trayStart.myBlockList[0].bottomRow);
		System.out.println("trayStart : top Left X : " + trayStart.myBlockList[0].leftCol);
		System.out.println("trayStart : top Left Y : " + trayStart.myBlockList[0].topRow);
		
		trayStart = trayStart.move(0, 3);

		System.out.println( );
		System.out.println("trayStart : bottom rightX : " + trayStart.myBlockList[0].rightCol);
		System.out.println("trayStart : bottom rightY : " + trayStart.myBlockList[0].bottomRow);
		System.out.println("trayStart : top Left X : " + trayStart.myBlockList[0].leftCol);
		System.out.println("trayStart : top Left Y : " + trayStart.myBlockList[0].topRow);
		
		trayStart = trayStart.move(0, 2);

		System.out.println( );
		System.out.println("trayStart : bottom rightX : " + trayStart.myBlockList[0].rightCol);
		System.out.println("trayStart : bottom rightY : " + trayStart.myBlockList[0].bottomRow);
		System.out.println("trayStart : top Left X : " + trayStart.myBlockList[0].leftCol);
		System.out.println("trayStart : top Left Y : " + trayStart.myBlockList[0].topRow);
		
		trayStart = trayStart.move(0, 4);

		System.out.println( );
		System.out.println("trayStart : bottom rightX : " + trayStart.myBlockList[0].rightCol);
		System.out.println("trayStart : bottom rightY : " + trayStart.myBlockList[0].bottomRow);
		System.out.println("trayStart : top Left X : " + trayStart.myBlockList[0].leftCol);
		System.out.println("trayStart : top Left Y : " + trayStart.myBlockList[0].topRow);
		*/
		
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

		//test right
		moveTray = moveTray.move(0, 2);
		assertTrue(moveTray.equals(trayLeft));
		
		//test left
		moveTray = moveTray.move(0, 4);
		assertTrue(moveTray.equals(trayStart));
		
	}
	
	@Test
	public void testGetMoves() 
	{
		LinkedList<Tray> fringe = new LinkedList<Tray>(); 
		
		int [][] startBoard =
			   {{-1,-1,-1,-1},
				{-1, 0,-1,-1},
				{-1, 1,-1,-1},
				{-1,-1,-1,-1}};

		Block [] startBlocks = {new Block(1,1,1,1),new Block(2,1,2,1)};
		
		Tray trayStart = new Tray(startBoard, startBlocks);
		
		System.out.println( );
		System.out.println("startTray : top Left X : " + trayStart.myBlockList[0].leftCol);
		System.out.println("startTray : top Left Y : " + trayStart.myBlockList[0].topRow);
		System.out.println("startTray : bottom rightX : " + trayStart.myBlockList[0].rightCol);
		System.out.println("startTray : bottom rightY : " + trayStart.myBlockList[0].bottomRow);

		trayStart.getMoves(fringe);
		Tray myTray = fringe.pop();
		
		System.out.println( );
		System.out.println("fringe : top Left X : " + myTray.myBlockList[0].leftCol);
		System.out.println("fringe : top Left Y : " + myTray.myBlockList[0].topRow);
		System.out.println("fringe : bottom rightX : " + myTray.myBlockList[0].rightCol);
		System.out.println("fringe : bottom rightY : " + myTray.myBlockList[0].bottomRow);
		
		myTray = fringe.pop();

		System.out.println( );
		System.out.println("fringe : top Left X : " + myTray.myBlockList[0].leftCol);
		System.out.println("fringe : top Left Y : " + myTray.myBlockList[0].topRow);
		System.out.println("fringe : bottom rightX : " + myTray.myBlockList[0].rightCol);
		System.out.println("fringe : bottom rightY : " + myTray.myBlockList[0].bottomRow);
		
		myTray = fringe.pop();

		System.out.println( );
		System.out.println("fringe : top Left X : " + myTray.myBlockList[0].leftCol);
		System.out.println("fringe : top Left Y : " + myTray.myBlockList[0].topRow);
		System.out.println("fringe : bottom rightX : " + myTray.myBlockList[0].rightCol);
		System.out.println("fringe : bottom rightY : " + myTray.myBlockList[0].bottomRow);
		
		myTray = fringe.pop();

		System.out.println( );
		System.out.println("fringe : top Left X : " + myTray.myBlockList[0].leftCol);
		System.out.println("fringe : top Left Y : " + myTray.myBlockList[0].topRow);
		System.out.println("fringe : bottom rightX : " + myTray.myBlockList[0].rightCol);
		System.out.println("fringe : bottom rightY : " + myTray.myBlockList[0].bottomRow);
			
	}

}
}
