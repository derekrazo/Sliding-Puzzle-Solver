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

}
