package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Skill;
import core.Skills;
import core.Stats;

public class SkillsTest {

	@Test
	public void testConstructors() throws Exception{
		Skills a = new Skills();
		assertTrue(a.getSkills().isEmpty());
		for(int i=0;i<6;i++){
			assertEquals(0,a.getStats().getMod(i));
		}
		
		Stats stats = new Stats(new int[] {8,10,12,14,16,18});
		Skills b = new Skills(stats);
		assertTrue(b.getSkills().isEmpty());
		for(int i=0;i<6;i++){
			assertEquals(i-1,b.getStats().getMod(i));
		}
	}

	@Test
	public void testAccessors() throws Exception{
		Skills a = new Skills();
		Stats garbageStats = new Stats();
		Stats stats = new Stats(new int[] {10,12,14,16,18,20});
		Bugger bug = new Bugger();
		a.addObserver(bug);
		Skill skilla = new Skill(12,Stats.Cha,garbageStats,"Bluff");
		Skill skrillex = new Skill(5,Stats.Con,garbageStats,"Concentration");
		
		a.addSkill(skilla);
		a.addSkill(skrillex);
		
		assertEquals(2,a.getSkills().size());
		assertEquals(2,bug.count);
		assertEquals(12,skilla.getScore());
		
		a.setStats(stats);
		assertTrue(a.containsSkill(skrillex));
		assertTrue(a.removeSkill(skrillex));
		assertTrue(!a.containsSkill(skrillex));
		
		assertEquals(4,bug.count);
		assertEquals(17,skilla.getScore());
	}

}
