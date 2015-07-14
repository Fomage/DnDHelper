package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Stats;

public class StatsTest {

	@SuppressWarnings("unused")
	@Test
	public void testConstructors() throws Exception{
		Stats bill = new Stats();
		for(int i=0;i<6;i++)
			assertEquals(10, bill.getStat(i));
		int[] a={10,10,10,10,10,10,10};
		int[] b={10,10,10,10,10,9};
		int[] c={10,10,10,10,10,-9};
		try{Stats billy = new Stats(a);
		fail("No exception thrown");}catch(Exception e){}
		Stats jack = new Stats(b);
		b[5]=12;
		assertEquals(9,jack.getStats()[5]);
		try{Stats billy = new Stats(c);
		fail("No exception thrown");}catch(Exception e){}
	}
	
	@Test
	public void testStatics() throws Exception{
		for(int i=0;i<6;i++){
			assertEquals(i,Stats.statToInt(Stats.statToString(i)));
		}
		try{Stats.statToInt("Patate");
		fail("No exception raised");}catch(Exception e){}
		try{Stats.statToString(-1);
		fail("No exception raised");}catch(Exception e){}
		try{Stats.statToString(6);
		fail("No exception raised");}catch(Exception e){}
	}

}
