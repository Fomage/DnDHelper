package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Buffer extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139151447209018073L;

	//Attributes
	private List<Buff> buffs;
	private Creature creature;

	//Constructors
	/**
	 * Default constructor, no buffs, default-generated creature
	 */
	public Buffer() {
		buffs=new ArrayList<Buff>();
		creature=new Creature();
		//this.addObserver(creature);
	}

	/**
	 * No buffs generated.
	 * @param creature the creature on which every buff added to this buffer will be applied.
	 */
	public Buffer(Creature creature) {
		buffs=new ArrayList<Buff>();
		this.creature=creature;
		//this.addObserver(creature);
	}

	//getters setters

	public Creature getCreature() {
		return creature;
	}

	/**
	 * Unapply all the buffs to the old Creature and applies all the buffs to the new Creature.
	 * @param creature the new Creature.
	 * @throws Exception if there is a problem applying or unapplying the buffs.
	 */
	public void setCreature(Creature creature) throws Exception{
		for(Buff b : buffs){
			if(b.isApplied(this.creature))
				b.unapply(this.creature);
		}
		this.deleteObserver(this.creature);
		this.creature = creature;
		this.addObserver(creature);
		for(Buff b : buffs){
			if(!b.isApplied(creature))
				b.apply(creature);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * @return an unmodifiable list of Buffs.
	 */
	public List<Buff> getBuffs() {
		return Collections.unmodifiableList(buffs);
	}

	/**
	 * @param s a String containing the name of the searched buff
	 * @return the first buff found with the matching name
	 * @throws Exception if no buff with that name was found
	 */
	public Buff searchBuff(String s) throws Exception{
		for(Buff b : buffs){
			if(b.getName().equals(s))
				return b;
		}
		throw new Exception("Buff "+s+" not found in Buffer:searchBuff of creature "+creature.getName());
	}

	/**
	 * @param b the buff will be immediately applied.
	 * @throws Exception if the buff has trouble being applied
	 */
	public void addBuff(Buff b) throws Exception{
		if(!buffs.contains(b)){
			buffs.add(b);
			if(!b.isApplied(creature))
				b.apply(creature);
			b.addObserver(this);
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * @param b a buff
	 * @return true if the buff was found and removed, false otherwise
	 * @throws Exception if something went wrong unapplying the buff
	 */
	public boolean removeBuff(Buff b) throws Exception{
		if(buffs.contains(b)){
			if(b.isApplied(creature))
				b.unapply(creature);
			buffs.remove(b);
			b.deleteObserver(this);
			setChanged();
			notifyObservers();
			return true;
		}
		else
			return false;
	}


	//Observable
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
	
	public void restoreObservable(){
		//creature.addObserver(this);
		for(Buff b : buffs)
			b.addObserver(this);
	}

}
