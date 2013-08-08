import java.util.ArrayList;

public class cloneTest {
  
	

	public cloneTest()
	{
		
		
	}
	
	public static void main(String[] args)
	{
		Timer t = new Timer();
		int n = 10000000;
		System.out.println("Time for iter: " + iterTime(t, n));
		t.reset();
		
		System.out.println("Time for clone: " + cloneTime(t, n));
		t.reset();
		
		System.out.println("Time for copy: " + copyTime(t, n));
		t.reset();
	}
	
	
	public static long iterTime(Timer t, int n)
	{
		int [] myInts = new int[n];
		
		for(int i = 0;i < n;i++)
		{
			myInts[i] = i;
		}
		
		t.start();
		
		int [] iterArray = new int[n];

		for(int i = 0;i < myInts.length;i++)
		{
			iterArray[i] = myInts[i];
		}
		
		t.stop();
		return t.elapsed();
	}
	
	public static long cloneTime(Timer t, int n)
	{
		int [] myInts = new int[n];
		
		for(int i = 0;i < n;i++)
		{
			myInts[i] = i;
		}
		
		t.start();
		
		int [] iterArray = myInts.clone();
		
		t.stop();
		return t.elapsed();
	}
	
	public static long copyTime(Timer t, int n)
	{
		int [] myInts = new int[n];
		
		for(int i = 0;i < n;i++)
		{
			myInts[i] = i;
		}
		
		t.start();
		
		int [] iterArray = new int[n];
		System.arraycopy(myInts, 0, iterArray, 0, n);
		
		t.stop();
		return t.elapsed();
	}
	
	
	
	
}
