import java.io.*;
import java.util.*;

public class Solver 
{
  //knows whether it's debugging, 
	//knows initial tray configuration 
	//and final tray configuration
	
	public String[] isDebugging;
	private Tray initialTray;
	private Tray finalTray;
	
	public static void main(String[] args)
	{
		//can take either 2 args or 3 args
		if (args.length==0||args.length>3)
		{
			System.err.println("***Invalid Number of Arguments");
			System.exit(1);
		}
		//initialize Solver Object
		Solver mySolver = new Solver(args);
		PathFinder myPathFinder = new PathFinder(mySolver.initialTray,mySolver.finalTray);
		String [] output=null;
		try
		{
		output = myPathFinder.solution();
		}catch(NoAnswerException e){
			System.exit(1);
		}
		for(int i=0;i<output.length;i++)
		{
			System.out.println(output[i]);
		}
		
	}
	
	public Solver (String[] args)
	//takes in String Array of 2 or 3 args
	//Constructs trays and debugging array
	{
		InputSource initialString;
		InputSource finalString;
		if(args.length==1)
		{
			if(!args[0].equals("-ooptions")){
				System.out.println("***Invalid Args");
			}
			debugParser(args[0].toLowerCase().trim());
			System.exit(1);
			
		}
		if(args.length==2)
		{
			initialString=new InputSource(args[0]);
			finalString=new InputSource(args[1]);
			isDebugging = null;
		}else
		{
			initialString=new InputSource(args[1]);
			finalString=new InputSource(args[2]);
			isDebugging = debugParser(args[0].toLowerCase().trim());
		}
		String size = initialString.readLine();
		ArrayList<String> input1 = new ArrayList<String>();
		ArrayList<String> input2 = new ArrayList<String>();
		while (true)
		{
			String line1 = initialString.readLine();
			String line2 = finalString.readLine();
			if (line1!=null)
			{
				input1.add(line1);
			}
			if (line2!=null)
			{
				input2.add(line2);
			}
			if (line1==null&&line2==null)
			{
				break;
			}
		}
		String [] goodexample =null;
		initialTray=new Tray(input1.toArray(goodexample),size);
		finalTray=new Tray(input2.toArray(goodexample),size);
		
	}
	
	public String[] debugParser(String debugString)
	//takes in InputSource and returns debugging array
	{
		String [] output= new String [1];
		if (!debugString.substring(0, 2).equals("-o")){
			return null;
		}
		String args=debugString.substring(2);
		switch (args){
		case "options": //add in descriptions for debug options here
			System.out.println("options: prints all possible args");
			break;
		}
		
		output[0]="debug";
		return output;
	}
}
