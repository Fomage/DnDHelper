package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Inventory extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -823149932436168298L;
	
	//attributes
	
	private List<Item> items;
	private Creature creature;
	
	//constructors
	public Inventory() {
		creature=new Creature();
		items=new ArrayList<Item>();
	}
	
	public Inventory(Creature creature){
		this.creature=creature;
		items=new ArrayList<Item>();
	}
	
	//getters setters
	
	public Creature getCreature() {
		return creature;
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
		setChanged();
		notifyObservers();
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	public void addItem(Item i) throws Exception{
		if(!items.contains(i)){
			items.add(i);
			i.addObserver(this);
			for(Buff b : i.getBuffs()){
				creature.getBuffer().addBuff(b);
			}
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeItem(Item i) throws Exception{
		if(items.contains(i)){
			items.remove(i);
			i.deleteObserver(this);
			for(Buff b : i.getBuffs()){
				if(b.isApplied(creature))
					b.unapply(creature);
			}
			setChanged();
			notifyObservers();
		}
	}
	
	//Observable
	@Override
	public void update(Observable o, Object arg) {
		if((arg instanceof Buff)&&(o instanceof Item)){
			Buff b = (Buff)arg;
			Item i = (Item)o;
			if(i.getBuffs().contains(b)){
				try{creature.getBuffer().addBuff(b);}
				catch(Exception e){
					System.out.println("Buff appliance failed when adding buff "+b.getName()
							+" to item "+i.getName()
							+" to creature "+creature.getName()
							+" with exception "+e.toString());
				}
			}
			else{
				try{creature.getBuffer().removeBuff(b);}
				catch(Exception e){
					System.out.println("Buff removal failed when removing buff "+b.getName()
							+" from item "+i.getName()
							+" from creature "+creature.getName()
							+" with exception "+e.toString());
				}
			}
		}
		setChanged();
		notifyObservers();
	}

}
