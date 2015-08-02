package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Skill;
import core.Stats;

public class SkillTest {

	@Test
	public void testConstructors() throws Exception{
		Skill test = new Skill();
		assertEquals(-4,test.getMod());
		assertEquals(-4,test.getScore());
		assertEquals(Stats.Fo,test.getStat());
		assertEquals(0,test.getStats().getMod(test.getStat()));
		assertTrue(test.getName().equals("Default"));
		
		Stats myStats = new Stats(new int[] {10,12,14,16,18,20});
		Skill a = new Skill(2,Stats.Dex,myStats,"Crochetage");
		assertEquals(2,a.getMod());
		assertEquals(3,a.getScore());
		assertEquals(Stats.Dex,a.getStat());
		assertEquals(1,a.getStats().getMod(a.getStat()));
		assertTrue(a.getName().equals("Crochetage"));
	}

	@Test
	public void testAccessors() throws Exception{
		Bugger bug = new Bugger();
		Stats stats = new Stats();
		Skill a = new Skill();
		a.addObserver(bug);
		
		a.setMod(0);
		a.setName("Psychologie");
		a.setStat(Stats.Sag);
		a.setStats(stats);
		stats.setStat(Stats.Sag, 16);
		
		assertEquals(0,a.getMod());
		assertTrue(a.getName().equals("Psychologie"));
		assertEquals(Stats.Sag,a.getStat());
		assertEquals(stats,a.getStats());
		assertEquals(3, a.getScore());
		assertEquals(5,bug.count);
	}

}
