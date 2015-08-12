package core;

public class StatBuff extends Buff {

	/**
	 * 
	 */
	private static final long serialVersionUID = 270175245872857830L;

	//Attributes

	int stat,mod;

	//constructors
	/**
	 * Default constructor, stat=Fo, mod=0, creature default-generated, name="Default"
	 */
	public StatBuff() {
		super();
		stat=Stats.Fo;
		mod=0;
	}

	/**
	 * @param stat the stat on which this buff will apply
	 * @param mod the amount added to the stat
	 * @param name the name of this
	 * @throws Exception if stat isn't a stat
	 */
	public StatBuff(int stat,int mod,String name, boolean hidden, boolean positive) throws Exception{
		super(name,hidden,positive);
		setStat(stat);
		setMod(mod);
	}

	static StatBuff makeStatBuff(int stat,int mod,String name) throws Exception{
		if(mod>=0)
			return new StatBuff(stat,mod,name,false,true);
		else
			return new StatBuff(stat,mod,name,false,false);
	}

	//Buff Methods

	@Override
	public void apply(Creature creature) throws Exception{
		if(isApplied(creature))
			throw new Exception("StatBuff is already applied : "+getName()+" on creature "+creature.getName());
		else{
			creature.getStats().setStat(
					getStat(),
					creature.getStats().getStat(getStat())+getMod());
			setApplied(creature,true);
		}
	}

	@Override
	public void unapply(Creature creature) throws Exception{
		if(!isApplied(creature))
			throw new Exception("StatBuff is not yet applied : "+getName()+" on creature "+creature.getName());
		else{
			creature.getStats().setStat(
					getStat(),
					creature.getStats().getStat(getStat())-getMod());
			setApplied(creature,false);
		}
	}

	protected void applyAll() throws Exception{
		for(Creature c : getApplied()){
			c.getStats().setStat(
					getStat(),
					c.getStats().getStat(getStat())+getMod());
		}
	}

	protected void unapplyAll() throws Exception{
		for(Creature c : getApplied()){
			c.getStats().setStat(
					getStat(),
					c.getStats().getStat(getStat())-getMod());
		}
	}

	//Accessors

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) throws Exception{
		if(Stats.isAStat(stat)){
			unapplyAll();
			this.stat = stat;
			applyAll();
			setChanged();
			notifyObservers();
		}
		else
			throw new Exception("Invalid argument in StatBuffer::setStat : "+stat);
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
	 * Unapplies this buff to all the creatures it was applied to before, then creates a new SkillBuff
	 * and applies it to the sames creatures it was applied before. Needs extensive testing.
	 * @param skill the skill modified by the new buff
	 * @param mod the modification added by the new buff to stat
	 * @return a StatBuff
	 * @throws Exception
	 */
	public SkillBuff toSkillBuff(String skill, int mod) throws Exception{
		SkillBuff res = new SkillBuff(skill,mod,getName(),isHidden(),isPositive());
		for(Creature c : getApplied()){
			res.apply(c);
		}
		while(!getApplied().isEmpty())
			unapply(getApplied().get(0));
		return res;
	}

}
