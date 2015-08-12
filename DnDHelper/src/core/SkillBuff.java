package core;

public class SkillBuff extends Buff {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4486984128658079996L;
	
	//attributes
	
	private String skill;
	private int mod;

	//Constructors	
	/**
	 * The default constructor. default-generated Creature, name="Default",skill="Default",mod=0
	 */
	public SkillBuff() {
		super();
		skill="Default";
		mod=0;
	}

	/**
	 * @param skill the name of the skill on which to apply this buff
	 * @param mod the amount added to skill when this is applied
	 * @param name the name of this buff
	 * @throws Exception if a problem happens during the apply. Cf apply.
	 */
	public SkillBuff(String skill,int mod, String name, boolean hidden, boolean positive) {
		super(name, hidden, positive);
		this.skill=skill;
		this.mod=mod;
	}
	
	static SkillBuff makeSkillBuff(String skill,int mod,String name) {
		if(mod>=0)
			return new SkillBuff(skill,mod,name,false,true);
		else
			return new SkillBuff(skill,mod,name,false,false);
	}

	
	//Buffer
	@Override
	public void apply(Creature creature) throws Exception {
		if(isApplied(creature))
			throw new Exception("SkillBuff is already applied : "+getName()+" on creature "+creature.getName());
		else{
			creature.getSkills().getSkill(getSkill()).setMod(
					creature.getSkills().getSkill(getSkill()).getMod()
					+getMod());
			setApplied(creature,true);
		}
	}

	@Override
	public void unapply(Creature creature) throws Exception {
		if(!isApplied(creature))
			throw new Exception("SkillBuff is not yet applied : "+getName()+" on creature "+creature.getName());
		else{
			creature.getSkills().getSkill(getSkill()).setMod(
					creature.getSkills().getSkill(getSkill()).getMod()
					-getMod());
			setApplied(creature,false);
		}
	}
	
	protected void applyAll() throws Exception{
		for(Creature c : getApplied()){
			c.getSkills().getSkill(getSkill()).setMod(
					c.getSkills().getSkill(getSkill()).getMod()
					+getMod());
		}
	}
	
	protected void unapplyAll() throws Exception{
		for(Creature c : getApplied()){
			c.getSkills().getSkill(getSkill()).setMod(
					c.getSkills().getSkill(getSkill()).getMod()
					-getMod());
		}
	}

	//Accessors
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) throws Exception{
		unapplyAll();
		this.skill = skill;
		applyAll();
		setChanged();
		notifyObservers();
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) throws Exception{
		unapplyAll();
		this.mod = mod;
		applyAll();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Unapplies this buff to all the creatures it was applied to before, then creates a new StatBuff
	 * and applies it to the sames creatures it was applied before. Needs extensive testing.
	 * @param stat the stat modified by the new buff
	 * @param mod the modification added by the new buff to stat
	 * @return a StatBuff
	 * @throws Exception
	 */
	public StatBuff toStatBuff(int stat, int mod) throws Exception{
		StatBuff res = new StatBuff(stat,mod,getName(),isHidden(),isPositive());
		for(Creature c : getApplied()){
			res.apply(c);
		}
		while(!getApplied().isEmpty())
			unapply(getApplied().get(0));
		return res;
	}

}
