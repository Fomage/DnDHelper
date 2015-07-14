package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatsTest {

	@Test
	public void testConstructors() throws Exception{
		Stats bill = new Stats();
		for(int i=0;i<6;i++)
			assertEquals(10, bill.getStat(i));
		
	}
	
	

}
