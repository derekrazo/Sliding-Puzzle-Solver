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
	

}
