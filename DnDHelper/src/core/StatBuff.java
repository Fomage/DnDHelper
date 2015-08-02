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
	 * Applies the buff to the creature immediately. If you don't like it tell me.
	 * @param stat the stat on which this buff will apply
	 * @param mod the amount added to the stat
	 * @param creature the Creature on which this buff will be applied
	 * @param name the name of this
	 * @throws Exception if stat isn't a stat
	 */
	public StatBuff(int stat,int mod,Creature creature,String name) throws Exception{
		super(creature,name);
		setStat(stat);
		setMod(mod);
		apply();
	}

	//Buff Methods

	@Override
	public void apply() throws Exception{
		if(isApplied())
			throw new Exception("StatBuff is already applied : "+getName()+" on creature "+getCreature().getName());
		else{
			getCreature().getStats().setStat(
					getStat(),
					getCreature().getStats().getStat(getStat())+getMod());
			setApplied(true);
		}
	}

	@Override
	public void unapply() throws Exception{
		if(!isApplied())
			throw new Exception("StatBuff is not yet applied : "+getName()+" on creature "+getCreature().getName());
		else{
			getCreature().getStats().setStat(
					getStat(),
					getCreature().getStats().getStat(getStat())-getMod());
			setApplied(false);
		}
	}

	//Accessors

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) throws Exception{
		if(Stats.isAStat(stat)){
			this.stat = stat;
			setChanged();
			notifyObservers();
		}
		else
			throw new Exception("Invalid argument in StatBuffer::setStat : "+stat);
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
