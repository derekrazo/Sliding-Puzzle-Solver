import java.util.ArrayList;
import java.util.LinkedList;


public class PathFinder {
  
	String myTrayCode;
	LinkedList<Tray> startTrays;
	LinkedList<Tray> endTrays;
	
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
				if(startTrays.peek().equals(endTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				//getMoves Returns iterator
				//startFringe.add(startTrays.pop().getMoves());
			}
			
	
			while(endTrays.size()!=0)
			{
				if(endTrays.peek().equals(startTrays.peek()))
				{
					return findPath(startTrays.pop(),endTrays.pop());
				}
				
				//getMoves Returns iterator
				//endFringe.add(endTrays.pop().getMoves());
			}
			
			startTrays = startFringe;
			endTrays = endFringe;
		}
		return null;
	}
	
	public String [] findPath(Tray toStart, Tray toFinish)
	{
		path.add(toStart);
		findPathBack(toStart);
		findPathForward(toFinish);
		
		String [] rtnPath = new String Array();
		
		//Somehow Convert the path into a String [] of the correct syntax.
		
		return rtnPath;
	}
	
	public void findPathBack(Tray t)
	{
		Tray prevT = t.prevTray;
		
		if(prevT != null)
		{
			path.add(0, prevT);
			findPathBack(prevT);
		}
	}
	
	public void findPathForward(Tray t)
	{
		Tray prevT = t.prevTray;
		
		if(prevT != null)
		{
			path.add(prevT);
			findPathBack(prevT);
		}
	}
}
