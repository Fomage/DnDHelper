package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Buff;
import core.Buffer;
import core.Creature;
import core.StatBuff;
import core.Stats;

public class BufferTest {

	@Test
	public void testBuffer() throws Exception{
		Bugger bug = new Bugger();
		Creature jean = new Creature();
		jean.setName("jean");
		jean.addObserver(bug);
		assertEquals(0,jean.getBuffer().getBuffs().size());
		
		jean.getBuffer().addBuff(new StatBuff(Stats.Fo,5,jean,"gné",false,true));
		Buff a = new StatBuff(Stats.Dex,2,jean,"FO+2",false,true);
		
		assertEquals(1,jean.getStats().getMod(Stats.Dex));
		assertTrue(null!=jean.getBuffer().searchBuff("gné"));
		assertTrue(jean.getBuffer().getBuffs().contains(a));
		
	}

	@Test
	public void testBufferCreature() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreature() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCreature() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBuffs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchBuff() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBuff() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBuff() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
