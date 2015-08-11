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
	
	

}
