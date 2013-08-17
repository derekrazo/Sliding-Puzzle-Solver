import java.lang.management.ManagementFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;



public class PathFinder {
	// works backwards and forwards to find a path between endtray and start tray
	// has two linkedlists to store moves possible
	// has two hashsets to keep track of what moves have already been examined
	// has a path arrayList for constructing the solution

	String myTrayCode;
	LinkedList<Tray> startTrays;
	LinkedList<Tray> endTrays;
	
	Tray origin;
	Tray destination;

	HashSet<Tray> prevStartTrays;
	HashSet<Tray> prevEndTrays;

	ArrayList<Tray> path;

	//Weighting Queue
	Comparator<Tray> comparator;
	PriorityQueue<Tray> weightQueue;
	Timer timer = new Timer();
	
	//TwoSided
	//startQueue;

	public PathFinder(Tray startTray, Tray endTray)
	{
		// takes in Tray representing initial state, tray representing goal
		// adds startTray to Linklist startTrays, endTray to LinkList endTrays
		// initializes the rest of the instance variables
		startTrays = new LinkedList<Tray>();
		startTrays.add(startTray);

		endTrays = new LinkedList<Tray>();
		endTrays.add(endTray);

		prevStartTrays = new HashSet<Tray>();
		prevEndTrays = new HashSet<Tray>();

		comparator = new WeightComparator();
		weightQueue = new PriorityQueue<Tray>(10, comparator);
		weightQueue.add(startTray);
		prevStartTrays.add(startTray);

		path = new ArrayList<Tray>();
		
		destination = endTray;
		origin = startTray;
	}

	public class WeightComparator implements Comparator<Tray>
	{
	    @Override
	    public int compare(Tray x, Tray y)
	    {
	        if (x.myWeight > y.myWeight)
	        {
	            return -1;
	        }
	        if (x.myWeight < y.myWeight)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}

	

	public String [] solution() throws NoAnswerException
	{
		int count = 0;
		
		while(true){
			timer.start();

			while (timer.elapsed() < 20000){
			//while(count < 10)
			//{
		
				//Debugging
				if(Solver.debug().equals("debug")||
						Solver.debug().equals("solution")||
								Solver.debug().equals("stepbystep")){
				System.out.println("***Running");
				}
				
	
			
				for(Tray startTray : prevStartTrays)
				{
					if(endTrays.peek().equals(startTray))
					{
						if(Solver.debug().equals("debug")||
								Solver.debug().equals("solution")||
										Solver.debug().equals("stepbystep")){
						System.out.println("Found");
						}
						return findPath(startTray,endTrays.peek());
					}
				}
				
				
				
	
				LinkedList<Tray> possibleMoves = new LinkedList<Tray>();
				if(Solver.debug().equals("debug")||
								Solver.debug().equals("stepbystep")){
					weightQueue.peek().getMoves(possibleMoves,prevStartTrays);
				}
				weightQueue.poll().getMoves(possibleMoves, prevStartTrays);
				
				weightQueue.addAll(possibleMoves);
				prevStartTrays.addAll(possibleMoves);
				if(Solver.debug().equals("debug")||
						Solver.debug().equals("solution")||
								Solver.debug().equals("stepbystep")){
			    System.out.println("Free Heap memory (bytes): " + 
			    		(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() - 
			    		 ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()));
				}
				/*
			    if ((ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() - 
			    	 ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()) < 1000000)
			    {
			    	//prevStartTrays.clear();
					double ave = 0; 
					
				    System.out.println("CLEANING:"); 

			    	for(Tray t : weightQueue)
			    	{
						//ave += t.myWeight; 
					    //System.out.println("t. weight : " + t.myWeight); 
			    	}
			    	
			    	//ave = ave / weightQueue.size();
			    	
				    //System.out.println("AVE:" + ave); 

			    	for(Tray t : weightQueue)
			    	{
						if (t.myWeight < ave)
						{
							weightQueue.remove(t);
						} 
			    	}
			    	
			    	
			    	//gc();
			    }
				*/
				if(Solver.debug().equals("debug")||
						Solver.debug().equals("solution")||
								Solver.debug().equals("stepbystep")){
			    System.out.println("Cycles :" + count); 
			    System.out.println("weightQueue size :" + weightQueue.size()); 
				}
				count ++;

			}
			
			Tray curTray = new Tray(weightQueue.poll());
			LinkedList<Tray> fringe = new LinkedList<Tray>();
			Random randMove = new Random();
			
			double closestWeight = curTray.myWeight;
			
			Tray largestRandTrayWeight;
			
			//prevStartTrays.clear();

			//timer.reset();
			
			while (true)
			{
				if(Solver.debug().equals("debug")||
						Solver.debug().equals("solution")||
								Solver.debug().equals("stepbystep")){
				System.out.println("time:" + timer.elapsed());
				}
				
				largestRandTrayWeight = new Tray(origin);
				
				for(int i = 0; i < 100; i++)
				{
					timer.start();
					prevStartTrays.clear();
					fringe.clear();
					//System.out.println("time:" + timer.elapsed());
					
					while (timer.elapsed() < 100)
					{
						//System.out.println("time:" + timer.elapsed());
						fringe.clear();
						curTray.getMoves(fringe, prevStartTrays);
						
						prevStartTrays.addAll(fringe);
						
						if(Solver.debug().equals("debug")||
								Solver.debug().equals("solution")||
										Solver.debug().equals("stepbystep")){
						System.out.println("Fringe Size is: "+ (fringe.size()));
						}
						
						if(fringe.size()==0)
						{
							if(Solver.debug().equals("debug")||
									Solver.debug().equals("solution")||
											Solver.debug().equals("stepbystep")){
							System.out.println("***Fringe is Empty");
							}
							break;
						}
						
						curTray = fringe.get(randMove.nextInt(fringe.size()));
						
						
						for(Tray prevTray : fringe)
						{
							if(destination.equals(prevTray))
							{
								if(Solver.debug().equals("debug")||
										Solver.debug().equals("solution")||
												Solver.debug().equals("stepbystep")){
								System.out.println("***Found");
								}
								printPath(prevTray);
								String [] rtn = new String[0];
								return null;
							}
						}
					}
					
					timer.stop();
					timer.reset();
					
					if(largestRandTrayWeight.myWeight<curTray.myWeight)
					{
						largestRandTrayWeight = curTray;
					}
				}
				
				if(closestWeight<largestRandTrayWeight.myWeight)
				{
					closestWeight=largestRandTrayWeight.myWeight;
				}
				//timer.reset();
				
				//if(weightQueue.size() <= 1)
				//{
				//	curTray = new Tray(origin);
				//}
				//else
				//{
				if(Solver.debug().equals("debug")||
						Solver.debug().equals("solution")||
								Solver.debug().equals("stepbystep")){
				System.out.println(closestWeight);
				}
				
				weightQueue.add(largestRandTrayWeight);
				curTray = new Tray(weightQueue.poll());
			}
		}
	}
	
	/**
	    * This method guarantees that garbage collection is
	    * done unlike <code>{@link System#gc()}</code>
	    */
	   public static void gc() {
	     Object obj = new Object();
	     WeakReference ref = new WeakReference<Object>(obj);
	     obj = null;
	     while(ref.get() != null) {
	       System.gc();
	     }
	   }
	
	public void printPath(Tray toStart)
	{
		//recursively uses myPreviousTray to follows trays back to origin
		Tray curTray = toStart;

		while(curTray.myPreviousTray!=null)
		{
			curTray.myPreviousTray.myNextTray = curTray;
			curTray = curTray.myPreviousTray;
		}
		if(Solver.debug().equals("debug")||
				Solver.debug().equals("printpath")){
			System.out.println("***First Traversal Success");
		}
		while(curTray.myNextTray!=null)
		{
			if(!curTray.equals(curTray.myNextTray))
			{
				System.out.println(curTray.moveMade(curTray.myNextTray));
			}
			curTray = curTray.myNextTray;
		}
	}
	   
	public String[] findPath(Tray toStart, Tray toFinish) {

		path.add(toStart);
		findPathBack(toStart);
		findPathForward(toFinish);
		// creates string array with cell for each tray in the path
		// store string returned by Tray.movemade of each move made
		String[] rtnPath = new String[path.size()];

		for (int i = 0; i < path.size()-1; i++) {
			rtnPath[i] = path.get(i).moveMade(path.get(i + 1));

			if(Solver.debug().equals("debug")||
					Solver.debug().equals("printpath")){
			System.out.println("***Tray");
			int [][] board = path.get(i).myBoardState;
			for(int[] array : board)
			{
				for(int interger : array)
				{
					System.out.print(interger);
				}
				System.out.println();
			}
			System.out.println();
			}

		}

		return rtnPath;
	}

	public void findPathBack(Tray t) {
		//recursively uses myPreviousTray to follows trays back to origin
		Tray prevT = t.myPreviousTray;

		if (prevT != null) {
			path.add(0, prevT);
			findPathBack(prevT);
		}
	}

	public void findPathForward(Tray t) {
		//recursively uses myPreviousTray to follows trays back to origin
		Tray prevT = t.myPreviousTray;

		if (prevT != null) {
			path.add(prevT);
			findPathForward(prevT);
		}
	}
}
