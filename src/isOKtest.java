import static org.junit.Assert.*;

import org.junit.Test;


public class isOKtest {

	@Test
	public void test() {
		//test 1 block
		int [][] startBoard =
			   {{ 0, -1,-1,-1},
				{-1,-1,-1,-1},
				{-1,-1,- 1,-1},
				{-1,-1,-1,-1}};

		Block[] startBlocks = {new Block(0,0,0,0)};

		Tray trayStart = new Tray(startBoard, startBlocks);
		
		assertTrue( trayStart.isOK());
		//test empty board
		startBoard =new int[][]
			   {{ -1, -1,-1,-1},
				{-1,-1,-1,-1},
				{-1,-1, -1,-1},
				{-1,-1,-1,-1}};

		startBlocks = new Block[]{};

		trayStart = new Tray(startBoard, startBlocks);
		assertTrue(trayStart.isOK());
		//test misplaced block, single
		startBoard =new int[][]
				   {{ 0, -1,-1,-1},
					{-1,-1,-1,-1},
					{-1,-1, -1,-1},
					{-1,-1,-1,-1}};

			startBlocks = new Block[]{new Block(1,1,1,1)};

			trayStart = new Tray(startBoard, startBlocks);
			assertFalse(trayStart.isOK());
		//test extra block in state, single
			startBoard =new int[][]
					   {{ 0, -1,-1,1},
						{-1,-1,-1,-1},
						{-1,-1, -1,-1},
						{-1,-1,-1,-1}};

				startBlocks = new Block[]{new Block(0,0,0,0)};

				trayStart = new Tray(startBoard, startBlocks);
				assertFalse(trayStart.isOK());
		//test missing block in state, block 
				startBoard =new int[][]
						   {{ -1, -1,-1,-1},
							{-1,-1,-1,-1},
							{-1,-1, -1,-1},
							{-1,-1,-1,-1}};

					startBlocks = new Block[]{new Block(0,0,0,0)};

					trayStart = new Tray(startBoard, startBlocks);
					assertFalse(trayStart.isOK());
		//test missing block in list	block
					startBoard =new int[][]
							   {{ 0, -1,-1,-1},
								{-1,-1,-1,-1},
								{-1,-1, -1,-1},
								{-1,-1,-1,-1}};

						startBlocks = new Block[]{};

						trayStart = new Tray(startBoard, startBlocks);
						assertFalse(trayStart.isOK());
		//test extra block in list
						startBoard =new int[][]
								   {{ 0, -1,-1,-1},
									{-1,-1,-1,-1},
									{-1,-1, -1,-1},
									{-1,-1,-1,-1}};

							startBlocks = new Block[]{new Block(0,0,0,0),
														new Block(1,1,1,1)};

							trayStart = new Tray(startBoard, startBlocks);
							assertFalse(trayStart.isOK());
		//test impossible block list block
							startBoard =new int[][]
									   {{ 0, -1,-1,-1},
										{-1,-1,-1,-1},
										{-1,-1, -1,-1},
										{-1,-1,-1,-1}};

								startBlocks = new Block[]{new Block(0,0,0,4)};

								trayStart = new Tray(startBoard, startBlocks);
								assertFalse(trayStart.isOK());
		//test previous tray having diff size
								startBoard =new int[][]
										   {{ 0, -1,-1,-1},
											{-1,-1,-1,-1},
											{-1,-1, -1,-1},
											{-1,-1,-1,-1}};

									startBlocks = new Block[]{new Block(0,0,0,0)};

									trayStart = new Tray(startBoard, startBlocks);
									int[][] HARDYBoard=new int[][]
											   {{ 0, -1,-1,-1},
												{-1,-1,-1,-1},
												{-1,-1, -1,-1},
												{-1,-1,-1,-1},
												{-1,-1,-1,-1}};
									Block [] hardyBlocks=new Block[]{new Block(0,0,0,0)};
									Tray hardyHAR=new Tray(HARDYBoard,hardyBlocks);
									trayStart.myPreviousTray=hardyHAR;
									assertFalse(trayStart.isOK());
									//test previous tray being ok
									startBoard =new int[][]
											   {{ -1, -1,-1,-1},
												{-1,0,-1,-1},
												{-1,-1, -1,-1},
												{-1,-1,-1,-1}};

										startBlocks = new Block[]{new Block(1,1,1,1)};

										trayStart = new Tray(startBoard, startBlocks);
										Tray HARDYHAR=trayStart.move(0, 3);
										assertFalse(HARDYHAR.isOK());
		//test previous tray no moves
									startBoard =new int[][]
											   {{ 0, -1,-1,-1},
												{-1,-1,-1,-1},
												{-1,-1, -1,-1},
												{-1,-1,-1,-1}};

										startBlocks = new Block[]{new Block(0,0,0,0)};

										trayStart = new Tray(startBoard, startBlocks);
										trayStart.myPreviousTray=new Tray(trayStart);
										assertFalse(trayStart.isOK());
		//test previous tray 2 moves
										startBoard =new int[][]
												   {{ 0, -1,-1,-1},
													{-1,-1,-1,-1},
													{-1,-1, -1,-1},
													{-1,-1,-1,-1}};

											startBlocks = new Block[]{new Block(0,0,0,0)};

											trayStart = new Tray(startBoard, startBlocks);
											HARDYHAR=trayStart.move(0, 3);
											HARDYHAR=trayStart.move(0, 3);
											trayStart.myPreviousTray=HARDYHAR;
											assertFalse(trayStart.isOK());
		//test previous tray having extra blocks
											startBoard =new int[][]
													   {{ 0, -1,-1,-1},
														{-1,-1,-1,-1},
														{-1,-1, -1,-1},
														{-1,-1,-1,-1}};

												startBlocks = new Block[]{new Block(0,0,0,0)};

												trayStart = new Tray(startBoard, startBlocks);
											HARDYBoard=new int[][]
													   {{ 0, -1,-1,-1},
														{-1,1,-1,-1},
														{-1,-1, -1,-1},
														{-1,-1,-1,-1}};
											hardyBlocks=new Block[]{new Block(0,0,0,0),
																	new Block(1,1,1,1)};
											hardyHAR=new Tray(HARDYBoard,hardyBlocks);
											trayStart.myPreviousTray=hardyHAR;
											assertFalse(trayStart.isOK());
		//test previous tray having missing blocks
													startBoard =new int[][]
															   {{ 0, -1,-1,-1},
																{-1,-1,-1,-1},
																{-1,-1, -1,-1},
																{-1,-1,-1,-1}};

														startBlocks = new Block[]{new Block(0,0,0,0)};

														trayStart = new Tray(startBoard, startBlocks);
														HARDYBoard=new int[][]
																   {{ -1, -1,-1,-1},
																	{-1,-1,-1,-1},
																	{-1,-1, -1,-1},
																	{-1,-1,-1,-1}};
														hardyBlocks=new Block[]{};
														hardyHAR=new Tray(HARDYBoard,hardyBlocks);
														trayStart.myPreviousTray=hardyHAR;
														assertFalse(trayStart.isOK());
	}
	

}
