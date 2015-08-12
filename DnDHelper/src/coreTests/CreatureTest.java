package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Buff;
import core.Creature;
import core.Serializer;
import core.StatBuff;
import core.Stats;

public class CreatureTest {

	@Test
	public void testCreature() throws Exception{
		fail("Not yet implemented");
	}

	@Test
	public void testCreatureInventoryStatsSkillsString() throws Exception{
		Creature bob = new Creature();
		bob.setName("bob");
		Buff b = new StatBuff(Stats.Fo,2,"+2 Fo",false,true);
		bob.getBuffer().addBuff(b);
		
		Serializer.save(bob, "test.t");
		
		Creature bob2 = (Creature)Serializer.load("test.t");
		bob2.restoreObservable();
		Buff b2 = bob2.getBuffer().getBuffs().get(0);
		Bugger bugBob = new Bugger();
		Bugger bugB = new Bugger();
		bob2.addObserver(bugBob);
		bob2.getBuffer().addObserver(bugB);
		
		b2.setName("SuperBuff");
		System.out.println(bugBob.count);
		System.out.println(bugB.count);
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
