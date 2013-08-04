import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class PathFinder {
	
	String myTrayCode;
	LinkedList<Tray> startTrays;
	LinkedList<Tray> endTrays;
	
	HashSet<Tray> prevTrays;
	
	ArrayList<Tray> path;
	
	public PathFinder(Tray startTray, Tray endTray)
	{
		startTrays = new LinkedList<Tray>();
		startTrays.add(startTray);
		
		endTrays = new LinkedList<Tray>();
		endTrays.add(endTray);
		
		path = new ArrayList<Tray>();
	}
	
	public String [] solution()
	{
		while(true)
		{
			LinkedList<Tray> startFringe = new LinkedList<Tray>();
			LinkedList<Tray> endFringe = new LinkedList<Tray>();
		
			while(startTrays.size()!=0)
			{
				prevTrays.add(startTrays.peek());
				
				if(startTrays.peek().equals(endTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				//getMoves Returns iterator
				startTrays.pop().getMoves(startFringe);
			}
			
	
			while(endTrays.size()!=0)
			{
				prevTrays.add(endTrays.peek());
				
				if(endTrays.peek().equals(startTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				//getMoves Returns iterator
				endTrays.pop().getMoves(endFringe);
			}
			
			startTrays = startFringe;
			endTrays = endFringe;
		}
	}
	
	public String [] findPath(Tray toStart, Tray toFinish)
	{
		path.add(toStart);
		findPathBack(toStart);
		findPathForward(toFinish);
		
		String [] rtnPath = new String[path.size()];
		
		//Somehow Convert the path into a String [] of the correct syntax.
		//Tray Needs a get move method
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
