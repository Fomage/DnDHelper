package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Item extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2147826682088213346L;
	
	//attributes
	
	private String name,description;
	private List<Buff> buffs;
	private int type;

	//
	public Item() {
		name="Default";
		description="";
		buffs=new ArrayList<Buff>();
		type=0;
	}
	
	public Item(String name, String description, int type){
		this.name=name;
		this.description=description;
		buffs=new ArrayList<Buff>();
		this.type=type;
	}
	
	//getters setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		throw new Exception("Buff "+s+" not found in Item::searchBuff of item "+getName());
	}

	/**
	 * @param b the buff will be applied.
	 * @throws Exception if the buff has trouble being applied
	 */
	public void addBuff(Buff b) {
		if(!buffs.contains(b)){
			buffs.add(b);
			b.addObserver(this);
			setChanged();
			notifyObservers(b);
		}
	}
	
	/**
	 * @param b a buff
	 * @return true if the buff was found and removed, false otherwise
	 */
	public boolean removeBuff(Buff b) {
		if(buffs.contains(b)){
			buffs.remove(b);
			b.deleteObserver(this);
			setChanged();
			notifyObservers(b);
			return true;
		}
		else
			return false;
	}
	
	public void removeAllBuffs() throws Exception{
		while(!buffs.isEmpty()){
			removeBuff(buffs.get(0));
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		setChanged();
		notifyObservers();
	}

	//Observable
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}

	
}
