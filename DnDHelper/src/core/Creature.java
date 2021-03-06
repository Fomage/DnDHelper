package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Creature extends Observable implements Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3972269434795657303L;
	
	//Attributes
	
	private Buffer buffer;
	private Inventory inventory;
	private Skills skills;
	private Stats stats;
	private String name;

	//constructors
	/**
	 * Default constructor... Use is not recommanded...
	 */
	public Creature() {
		buffer = new Buffer(this);
		buffer.addObserver(this);
		inventory = new Inventory(this);
		inventory.addObserver(this);
		stats = new Stats();
		stats.addObserver(this);
		setSkills(new Skills(stats));
		name="Default";
	}
	
	/**
	 *WARNING / UNTESTED (like the rest of the code...) \ 
	 *The Buffer is always initialised using the default constructor
	 * @param inventory an inventory. All the item-related buffs will be added to the buffer.
	 * @param stats stats.
	 * @param skills stats will be set as the corresponding statistics.
	 * @throws Exception possibly...
	 */
	public Creature(Inventory inventory, Stats stats, Skills skills, String name) throws Exception {
		buffer = new Buffer(this);
		buffer.addObserver(this);
		setInventory(inventory);
		this.stats=stats;
		stats.addObserver(this);
		setSkills(skills);
		this.name=name;
	}
	
	//Accessors
	
	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
		buffer.addObserver(this);
		setChanged();
		notifyObservers();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		inventory.addObserver(this);
		setChanged();
		notifyObservers();
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
		skills.setStats(getStats());
		skills.addObserver(this);
		setChanged();
		notifyObservers();
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
		skills.setStats(stats);
		stats.addObserver(this);
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

	//Observable
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
	
	public void restoreObservable(){
		getBuffer().addObserver(this);
		getBuffer().restoreObservable();
		getInventory().addObserver(this);
		getInventory().restoreObservable();
		getSkills().addObserver(this);
		getSkills().restoreObservable();
		getStats().addObserver(this);
	}

}
