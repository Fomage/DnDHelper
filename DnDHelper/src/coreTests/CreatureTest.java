package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Creature;
import core.StatBuff;

public class CreatureTest {

	@Test
	public void testCreature() throws Exception{
		System.out.println("Gnarl");
		Creature tom = new Creature();
		System.out.println("Gnarl");
		tom.setName("tom");
		System.out.println("Gnarl");
		Bugger bug = new Bugger();
		System.out.println("Gnarl");
		tom.addObserver(bug);
		
		System.out.println("Gnarl");
		tom.getBuffer().addBuff(new StatBuff(0,10,"testeuh",false,true));
		System.out.println(bug.count);
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
