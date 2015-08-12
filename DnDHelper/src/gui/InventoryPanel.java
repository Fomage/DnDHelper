package gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import core.Creature;
import core.Item;

public class InventoryPanel extends JPanel implements Observer {

	private MainWindow main;
	private Creature associatedCreature;

	public InventoryPanel(MainWindow main, CreaturePanel creaturePanel) {
		super();
		this.associatedCreature = creaturePanel.getCreature();
		this.main = main;
		this.associatedCreature.addObserver(this);

	}

	private static final long serialVersionUID = 1L;

	public void update(Observable arg0, Object arg1) {
		update();

	}

	public void update() {
		List<Item> items = associatedCreature.getInventory().getItems();

		this.removeAll();

		for (Item item : items) {
			// System.out.println(buff.getName());
			ItemButton associatedButton = new ItemButton(item, associatedCreature, main, this);
			this.add(associatedButton);
		}
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
