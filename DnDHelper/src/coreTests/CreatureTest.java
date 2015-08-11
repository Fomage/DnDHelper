package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Buff;
import core.Creature;
import core.Item;
import core.StatBuff;
import core.Stats;

public class CreatureTest {

	@Test
	public void testCreature() throws Exception{
		Creature bob= new Creature();
		bob.setName("bob");
		Bugger bug = new Bugger();
		bob.addObserver(bug);
		
		Item machin = new Item("Machin","Un machin",0);
		bob.getInventory().addItem(machin);
		assertEquals(0,bob.getStats().getMod(Stats.Fo));
		Buff buffDeMachin = new StatBuff(Stats.Fo,2,"Buff de machin",false,true);
		machin.addBuff(buffDeMachin);
		assertEquals(1,bob.getStats().getMod(Stats.Fo));
		System.out.println(bob.getBuffer().getBuffs().size());
		
	}

	@Test
	public void testCreatureInventoryStatsSkillsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBuffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBuffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInventory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetInventory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSkills() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSkills() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStats() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStats() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
