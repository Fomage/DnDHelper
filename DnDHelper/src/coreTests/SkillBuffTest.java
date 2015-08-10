package coreTests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Creature;
import core.Skill;
import core.SkillBuff;
import core.Stats;

public class SkillBuffTest {

	@Test
	public void testSkillBuff() throws Exception{
		Bugger bug = new Bugger();
		Creature jean = new Creature();
		jean.addObserver(bug);
		try{
			@SuppressWarnings("unused")
			SkillBuff a = new SkillBuff("Escalade",2,jean,"Bonus anneau d'escalade",false,true);
		}catch(Exception e){
			assertTrue(e.toString().equals("java.lang.Exception: Skill Escalade unfound in Skills::getSkill"));
		}
		jean.getSkills().addSkill(new Skill(0,Stats.Fo,new Stats(),"Escalade"));
		SkillBuff a = new SkillBuff("Escalade",2,jean,"Bonus anneau d'escalade",false,true);
		
		assertEquals(2,bug.count);
		assertEquals(2,jean.getSkills().getSkill("Escalade").getScore());
		assertTrue(a.getName().equals("Bonus anneau d'escalade"));
		
		try{a.apply();}catch(Exception e){
			assertTrue(e.toString().equals("java.lang.Exception: SkillBuff is already applied : Bonus anneau d'escalade on creature Default"));
		}
		a.unapply();
		assertEquals(0,jean.getSkills().getSkill("Escalade").getScore());
		assertEquals(3,bug.count);
	}

}
