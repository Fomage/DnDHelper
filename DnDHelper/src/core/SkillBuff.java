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
	 * This will immediately apply the buff to creature. If you don't like it tell me
	 * @param skill the name of the skill on which to apply this buff
	 * @param mod the amount added to skill when this is applied
	 * @param creature the creature on which this buff is applied
	 * @param name the name of this buff
	 * @throws Exception if a problem happens during the apply. Cf apply.
	 */
	public SkillBuff(String skill,int mod,Creature creature, String name, boolean hidden, boolean positive) throws Exception{
		super(creature, name, hidden, positive);
		setSkill(skill);
		setMod(mod);
		apply();
	}

	
	//Buffer
	@Override
	public void apply() throws Exception {
		if(isApplied())
			throw new Exception("SkillBuff is already applied : "+getName()+" on creature "+getCreature().getName());
		else{
			getCreature().getSkills().getSkill(getSkill()).setMod(
					getCreature().getSkills().getSkill(getSkill()).getMod()
					+getMod());
			setApplied(true);
		}
	}

	@Override
	public void unapply() throws Exception {
		if(!isApplied())
			throw new Exception("SkillBuff is not yet applied : "+getName()+" on creature "+getCreature().getName());
		else{
			getCreature().getSkills().getSkill(getSkill()).setMod(
					getCreature().getSkills().getSkill(getSkill()).getMod()
					-getMod());
			setApplied(true);
		}
	}

	//Accessors
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
		setChanged();
		notifyObservers();
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
		setChanged();
		notifyObservers();
	}
	
	

}
