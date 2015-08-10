package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Buff extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2063353591841926763L;
	
	//attributes

	private String name;
	private boolean hidden,positive;
	private List<Creature> applied;

	//constructors
	/**
	 * Default constructor...................that's evil. name="Default"
	 */
	public Buff() {
		name="Default";
		applied=new ArrayList<Creature>();
		hidden=false;
		positive=true;
	}
	
	public Buff(String name, boolean hidden, boolean positive){
		this.name=name;
		applied=new ArrayList<Creature>();
		this.hidden=hidden;
		this.positive=positive;
	}
	
	//Methods
	
	/**
	 * Applies the buff to the creature, modifying it.
	 * @throws Exception if it is already applied when called, or if some serious shit happened elsewhere.
	 */
	abstract public void apply(Creature creature) throws Exception;
	/**
	 * Removes the effects of the buffs that were applied by using apply()
	 * @throws Exception if it is not yet applied when called, or if some serious shit happened elsewhere.
	 */
	abstract public void unapply(Creature creature) throws Exception;
	
	//Accessors
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}
	
	public boolean isApplied(Creature c) {
		return applied.contains(c);
	}
	
	protected void setApplied(Creature c, boolean b) {
		if((!applied.contains(c))&&b){
			applied.add(c);
			setChanged();
			notifyObservers();
		}
		else if ((applied.contains(c))&&(!b) ){
			applied.remove(c);
			setChanged();
			notifyObservers();
		}
	}
	
	public List<Creature> getApplied() {
		return Collections.unmodifiableList(applied);
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
