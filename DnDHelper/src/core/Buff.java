package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public abstract class Buff extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2063353591841926763L;
	
	//attributes
	
	private Creature creature;
	private String name;
	private boolean applied,hidden,positive;

	//constructors
	/**
	 * Default constructor, based on a default-generated creature...................that's evil. name="Default"
	 */
	public Buff() {
		creature=new Creature();
		name="Default";
		applied=false;
		hidden=false;
		positive=true;
	}
	
	public Buff(Creature creature, String name, boolean hidden, boolean positive) throws Exception{
		this.creature=creature;
		this.name=name;
		applied=false;
		this.hidden=hidden;
		this.positive=positive;
		apply();
		creature.getBuffer().addBuff(this);
	}
	
	//Methods
	
	/**
	 * Applies the buff to the creature, modifying it.
	 * @throws Exception if it is already applied when called, or if some serious shit happened elsewhere.
	 */
	abstract public void apply() throws Exception;
	/**
	 * Removes the effects of the buffs that were applied by using apply()
	 * @throws Exception if it is not yet applied when called, or if some serious shit happened elsewhere.
	 */
	abstract public void unapply() throws Exception;
	
	//Accessors
	
	public Creature getCreature() {
		return creature;
	}

	/**
	 * This immediately applies the buff to the creature. If you don't like it tell me. It also adds this to
	 * the new creature's buffer.
	 * @param creature the new creature on which the buff is applied
	 */
	public void setCreature(Creature creature) throws Exception{
		if(this.creature!=creature){
			if(applied)
				unapply();
			this.creature = creature;
			apply();
			creature.getBuffer().addBuff(this);
			setChanged();
			notifyObservers();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}
	
	public boolean isApplied() {
		return applied;
	}
	
	protected void setApplied(boolean b) {
		applied=b;
		setChanged();
		notifyObservers();
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		setChanged();
		notifyObservers();
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
		setChanged();
		notifyObservers();
	}

	//Observer
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
	
}
