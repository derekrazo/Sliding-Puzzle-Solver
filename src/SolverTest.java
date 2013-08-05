import static org.junit.Assert.*;

import org.junit.Test;


public class SolverTest {

  @Test
	public void test() {
		//test that the constructors run
		String [] args =new String[2];
		args[0]="1x1";
		args[1]="1x1.goal";
		Solver.main(args);
		
		//test debugParser
		Solver test= new Solver (args);
		String [] haha=new String[1];
		haha[0]="debug";
		assertEquals(test.debugParser("-ooptions")[0],haha[0]);
		String[] debug = new String[3];
		debug[0]="-ooptions";
		debug[1]="1x1";
		debug[2]="1x1.goal";
		Solver.main(debug);
	}

}
