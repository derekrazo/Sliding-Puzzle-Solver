import static org.junit.Assert.*;

import org.junit.Test;


public class TrayTest {

	@Test
	public void test() {
		int[][] store = new int[2][2];
		/*
		 * This grid is weird, its based on the input
		 * which makes the positive y direction point down
		 * 
		 * Board:
		 *       b-
		 *       --
		 * 
		 * */
		
		store[0] = new int[] {1,-1};
		store[1] = new int[] {-1,-1};
		
		Tray t = new Tray(store);
		
		//t.print();
		
		t.move(1,2);
		
		//t.print();
		
	}
	
	@Test
	public void cloneTest(){
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

}
