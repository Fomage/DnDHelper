package gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import core.Buff;
import core.Creature;
import core.Item;
import gui.BuffButton;

public class BuffPanel extends JPanel implements Observer {

	private MainWindow main;
	private Creature associatedCreature;
	private Boolean isCreatureBound;
	private Item item;

	
	public BuffPanel(MainWindow main,CreaturePanel creaturePanel){
		super();
		this.associatedCreature=creaturePanel.getCreature();
		this.main = main;
		isCreatureBound = true;
	}
	
	public BuffPanel(MainWindow main, Item item){
		super();
		this.main = main;
		isCreatureBound = false;
		this.item = item;
	}
	private static final long serialVersionUID = 1L;

	public void update(Observable arg0, Object arg1) {
		update();
		
	}
	
	public void update(){
		List<Buff> buffs;
		if(isCreatureBound){
			buffs = associatedCreature.getBuffer().getBuffs();
			
		}
		else{
			buffs = item.getBuffs();
			//System.out.println(buffs.size());
		}
		this.removeAll();
		
		for(Buff buff : buffs){
			//System.out.println(buff.getName());
			BuffButton associatedButton = new BuffButton(buff,associatedCreature,main,this);
			this.add(associatedButton);
		}
		this.invalidate();
		this.validate();
		this.repaint();
	}

	public void setItem(Item associatedItem) {
		item = associatedItem;
		
	}

}
