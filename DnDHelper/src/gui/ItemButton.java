package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Creature;
import core.Item;

public class ItemButton extends JButton {

	ImageIcon amuletIcon = new ImageIcon(getClass().getResource("/images/amulet.png"));
	ImageIcon armorIcon = new ImageIcon(getClass().getResource("/images/armor.png"));
	ImageIcon gemIcon = new ImageIcon(getClass().getResource("/images/gem.png"));
	ImageIcon ringIcon = new ImageIcon(getClass().getResource("/images/ring.png"));
	ImageIcon swordIcon = new ImageIcon(getClass().getResource("/images/sword.png"));
	ImageIcon[] icons = { amuletIcon, armorIcon, gemIcon, ringIcon, swordIcon };
	ImageIcon image;

	private static final long serialVersionUID = 1L;
	private Item associatedItem;

	public ItemButton(Item item, Creature creature, MainWindow main, InventoryPanel currentInventoryPanel) {

		super();
		associatedItem = item;

		image = icons[item.getType()];

		this.setIcon(image);
		// DONE set the right icon and update accordingly
		this.setToolTipText("<html>" + associatedItem.getName() + "<br>" + associatedItem.getDescription() + "</html>");
		this.setItem(associatedItem);
		this.setBorder(null);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// DONE : 2 cases : item or creature dependent
				NewItemWindow newitem = new NewItemWindow(item, creature, main);
				newitem.setVisible(true);
				// panel.setVisible(false);
				main.setAlwaysOnTop(false);
				newitem.toFront();

				newitem.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						main.setAlwaysOnTop(main.getOnTopState());
						currentInventoryPanel.update();

						// DONE : RETURN NEW BUFF HERE with newbuff
						return;
					}
				});

			}
		});
	}

	public Item getItem() {
		return associatedItem;
	}

	public void setItem(Item item) {
		this.associatedItem = item;
	}
}
