package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Skill extends Observable implements Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574032111771117724L;
	
	//Attributes
	
	private int mod;
	private int stat;
	private Stats stats;
	private String name;

	//Constructors
	/**
	 * Default Constructor : -4 mod, Strength-related, name "Default", based on default-generated stats
	 */
	public Skill() {
		mod=-4;
		stat=Stats.Fo;
		name="Default";
	}
	
	/**
	 * @param mod the modificator of the skill (mastery+various mods... does NOT include stat mod)
	 * @param stat the stat from which the mod will be applied
	 * @param stats the stats used to get the scores
	 * @param name the name of the skill
	 * @throws Exception if stat isn't a stat (if it isn't between 0 and 5)
	 */
	public Skill(int mod, int stat, Stats stats, String name) throws Exception {
		if(!Stats.isAStat(stat))
			throw new Exception("Invalid stat arg in Skill Constructor : "+stat);
		this.mod=mod;
		this.stat=stat;
		this.stats=stats;
		stats.addObserver(this);
		this.name=name;
	}
	
	//Accessors

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
		setChanged();
		notifyObservers();
	}

	public int getStat() {
		return stat;
	}

	/**
	 * @param arg a stat
	 * @throws Exception if arg isn't a stat
	 */
	public void setStat(int arg) throws Exception{
		if(!Stats.isAStat(arg))
			throw new Exception("Invalid arg in Skill::setStat : "+arg);
		this.stat=arg;
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}
	
	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats.deleteObserver(this);
		this.stats = stats;
		stats.addObserver(this);
		setChanged();
		notifyObservers();
	}

	/**
	 * Use this for rolls
	 * @return mod+stats mod.
	 * @throws Exception if something went VERY WRONG in my code. Notify me if u get this.
	 */
	public int getScore() throws Exception{
		return getMod()+getStats().getMod(getStat());
	}

	//Observer
	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

}
