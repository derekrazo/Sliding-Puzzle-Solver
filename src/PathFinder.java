import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class PathFinder {
	
	String myTrayCode;
	LinkedList<Tray> startTrays;
	LinkedList<Tray> endTrays;
	
	HashSet<Tray> prevStartTrays;
	HashSet<Tray> prevEndTrays;
	
	ArrayList<Tray> path;
	
	public PathFinder(Tray startTray, Tray endTray)
	{
		startTrays = new LinkedList<Tray>();
		startTrays.add(startTray);
		
		endTrays = new LinkedList<Tray>();
		endTrays.add(endTray);
		
		prevStartTrays = new HashSet<Tray>();
		prevEndTrays = new HashSet<Tray>();
		
		path = new ArrayList<Tray>();
	}
	
	public String [] solution()
	{
		while(true)
		{
			//Debugging
			System.out.println("Running");
			System.out.println(startTrays.size());
			System.out.println(endTrays.size());
			
			//Inefficient as FUCK!
			for(Tray startTray : prevStartTrays)
			{
				for(Tray endTray : prevEndTrays)
				{
					if(startTray.equals(endTray))
					{
						System.out.print("Found");
						return findPath(startTray,endTray);
					}
				}
			}
			
			LinkedList<Tray> startFringe = new LinkedList<Tray>();
			LinkedList<Tray> endFringe = new LinkedList<Tray>();
		
			while(startTrays.size()!=0)
			{
				prevStartTrays.add(startTrays.peek());
				
				/*
				if(startTrays.peek().equals(endTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				if(endTrays.contains(startTrays.peek()))
				{
					System.out.print("Found!!!");
					return findPath(startTrays.pop(),endTrays.pop());
				}
				if(startTrays.contains(endTrays.peek()))
				{
					System.out.print("Found!!!");
					return findPath(startTrays.pop(),endTrays.pop());
				}
				*/
				
				//getMoves Returns iterator
				startTrays.pop().getMoves(startFringe);
			}
	
			while(endTrays.size()!=0)
			{
				prevEndTrays.add(endTrays.peek());
				
				/*
				if(endTrays.peek().equals(startTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				if(startTrays.contains(endTrays.peek()))
				{
					System.out.print("Found!!!");
					return findPath(startTrays.pop(),endTrays.pop());
				}
				if(endTrays.contains(startTrays.peek()))
				{
					System.out.print("Found!!!");
					return findPath(startTrays.pop(),endTrays.pop());
				}
				*/
				
				//getMoves Returns iterator
				endTrays.pop().getMoves(endFringe);
			}

			//Can make this more efficient
			startFringe.removeAll(prevStartTrays);
			
			startTrays = startFringe;
			
			//Can make this more efficient
			endFringe.removeAll(prevEndTrays);
			
			endTrays = endFringe;
		}
	}

	public String [] findPath(Tray toStart, Tray toFinish)
	{
		path.add(toStart);
		findPathBack(toStart);
		findPathForward(toFinish);
		
		String [] rtnPath = new String[path.size()];
		
		for(int i = 0; i < path.size()-1; i++)
		{
			rtnPath[i] = path.get(i).moveMade(path.get(i+1));
		}
		
		return rtnPath;
	}
	
	public void findPathBack(Tray t)
	{
		Tray prevT = t.myPreviousTray;
		
		if(prevT != null)
		{
			path.add(0, prevT);
			findPathBack(prevT);
		}
	}
	
	public void findPathForward(Tray t)
	{
		Tray prevT = t.myPreviousTray;
		
		if(prevT != null)
		{
			path.add(prevT);
			findPathBack(prevT);
		}
	}
}
