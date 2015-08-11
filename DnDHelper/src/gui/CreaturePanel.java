package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import core.Creature;
import core.Item;

public class CreaturePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364541950907930931L;
	private JTabbedPane tabbedPane;
	private Creature creature;
	private BuffPanel currentBuffs;
	private JTextPane resultPane;

	/**
	 * Create the panel.
	 */
	public CreaturePanel(MainWindow main) {
		this(main, new Creature());
	}

	/**
	 * @wbp.parser.constructor
	 */

	public CreaturePanel(MainWindow main, Creature creature) {

		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ImageIcon editIcon = new ImageIcon("src/gui/images/edit2.png");
		ImageIcon editIconPressed = new ImageIcon("src/gui/images/edit2pressed.png");
		ImageIcon trashIcon = new ImageIcon("src/gui/images/trash2.png");
		ImageIcon trashIconPressed = new ImageIcon("src/gui/images/trash2pressed.png");
		ImageIcon creatureIcon = new ImageIcon("src/gui/images/person2.png");
		ImageIcon inventoryIcon = new ImageIcon("src/gui/images/inventory2.png");
		ImageIcon checkedIcon = new ImageIcon("src/gui/images/checked.png");
		ImageIcon uncheckedIcon = new ImageIcon("src/gui/images/unchecked.png");

		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(303, 100));
		this.creature = creature;
		CreaturePanel panel = this;
		setLayout(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBounds(2, 2, 350, 108);
		add(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPane.setFocusable(false);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 260, 100);

		contentPane.add(tabbedPane);

		JPanel mainPanels = new JPanel();
		mainPanels.setBounds(0, 0, 250, 100);
		// contentPane.add(mainPanels);
		mainPanels.setLayout(new CardLayout(0, 0));
		// this.setMinimumSize(new Dimension(300, 100));

		JPanel mainPanelCreature = new JPanel();
		mainPanelCreature.setBorder(null);
		// mainPanels.add(mainPanelCreature, "name_309225081159039");
		tabbedPane.addTab(null, creatureIcon, mainPanelCreature);
		mainPanelCreature.setLayout(null);

		class CreatureNamePanel extends JTextPane implements Observer {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void update(Observable o, Object arg) {

				this.setText(((Creature) o).getName());
			}
		}

		CreatureNamePanel txtpnCreaturename = new CreatureNamePanel();
		this.creature.addObserver(txtpnCreaturename);

		txtpnCreaturename.setOpaque(false);
		txtpnCreaturename.setBounds(10, 0, 117, 28);
		txtpnCreaturename.setEditable(false);
		mainPanelCreature.add(txtpnCreaturename);
		txtpnCreaturename.setBackground(UIManager.getColor("Button.background"));

		txtpnCreaturename
				.setFont(new Font(txtpnCreaturename.getFont().getName(), txtpnCreaturename.getFont().getStyle(), 18));
		txtpnCreaturename.setText(creature.getName());

		JButton btnEdit = new JButton(editIcon);
		btnEdit.setPressedIcon(editIconPressed);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCreatureWindow editCreature = new NewCreatureWindow(creature);
				editCreature.setVisible(true);

				main.setAlwaysOnTop(false);
				editCreature.toFront();

				editCreature.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if (editCreature.isFinished()) {
							// System.out.println(editCreature.creature.getName());

							// DONE : refresh in some way (Obs)
						}

						main.setAlwaysOnTop(main.getOnTopState());

					}
				});

				// DONE : edit launches NewCreatureWindow with creature values

			}
		});
		btnEdit.setBorder(null);
		btnEdit.setBorderPainted(false);
		btnEdit.setFocusPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setToolTipText("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnEdit.setBounds(190, 5, 20, 20);
		mainPanelCreature.add(btnEdit);

		JButton btnRemove = new JButton(trashIcon);
		btnRemove.setPressedIcon(trashIconPressed);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.removeCreature(panel);
				if (!main.isResizable()) {
					main.pack();
				}
			}
		});
		btnRemove.setBorder(null);
		btnRemove.setBorderPainted(false);
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setToolTipText("Remove");
		btnRemove.setBounds(155, 5, 20, 20);
		mainPanelCreature.add(btnRemove);

		JPanel buffPanel = new JPanel();
		buffPanel.setBounds(10, 32, 210, 60);
		mainPanelCreature.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));

		currentBuffs = new BuffPanel(main, this);
		creature.addObserver(currentBuffs);
		currentBuffs.setLayout(new BoxLayout(currentBuffs, BoxLayout.LINE_AXIS));

		JButton btnAddBuff = new JButton("+");
		btnAddBuff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAddBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewBuffWindow newbuff = new NewBuffWindow(null, creature, main);
				newbuff.setVisible(true);
				// panel.setVisible(false);
				main.setAlwaysOnTop(false);
				newbuff.toFront();

				newbuff.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							if (newbuff.isFinished()) {

								if (newbuff.isPublic()) {
									main.addPublicBuff(newbuff.getBuff());
								}

								creature.getBuffer().addBuff(newbuff.getBuff());
							}

						} catch (Exception e1) {

							e1.printStackTrace();
						}
						// updateBuffPanel(currentBuffs);

						// DONE : RETURN NEW BUFF HERE with newbuff
						return;
					}
				});
			}
		});
		buffPanel.add(btnAddBuff, BorderLayout.EAST);

		JScrollPane buffScroll = new JScrollPane(currentBuffs);
		buffScroll.setBorder(null);
		buffScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buffScroll.setPreferredSize(new Dimension(160, 0));
		buffPanel.add(buffScroll, BorderLayout.WEST);

		JPanel mainPanelInventory = new JPanel();
		mainPanelInventory.setBorder(null);
		// mainPanels.add(mainPanelInventory, "name_309312157043447");
		tabbedPane.addTab(null, inventoryIcon, mainPanelInventory);
		mainPanelInventory.setLayout(new BorderLayout(0, 0));

		JPanel inventoryPanel = new JPanel();
		mainPanelInventory.add(inventoryPanel, BorderLayout.CENTER);
		inventoryPanel.setLayout(new BorderLayout(0, 0));

		InventoryPanel currentInventory = new InventoryPanel(main, panel);
		creature.addObserver(currentInventory);
		currentInventory.setLayout(new BoxLayout(currentInventory, BoxLayout.LINE_AXIS));

		JScrollPane invScroll = new JScrollPane(currentInventory);
		invScroll.setBorder(null);
		invScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		invScroll.setPreferredSize(new Dimension(175, 0));
		inventoryPanel.add(invScroll, BorderLayout.WEST);

		// JButton sampleItem = new JButton(swordIcon);
		// sampleItem.setBorder(null);
		// sampleItem.setOpaque(false);
		// sampleItem.setBorderPainted(false);
		// sampleItem.setFocusPainted(false);
		// sampleItem.setContentAreaFilled(false);
		// currentInventory.add(sampleItem);

		JPanel miscPanel = new JPanel();
		inventoryPanel.add(miscPanel);
		miscPanel.setLayout(new BorderLayout(0, 0));

		JButton btnAddItem = new JButton("+");
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAddItem.setFocusPainted(false);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewItemWindow newitem = new NewItemWindow(new Item(), panel.getCreature(), main);
				newitem.setVisible(true);
				newitem.toFront();
				// panel.setVisible(false);
				main.setAlwaysOnTop(false);

				newitem.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						// DONE : RETURN NEW ITEM HERE with newitem
						try {
							if (newitem.isFinished()) {
								creature.getInventory().addItem(newitem.getItem());
								// System.out.println(creature.getInventory().getItems().size());
								// System.out.println(newitem.getItem().getBuffs().size());
								// System.out.println(creature.getBuffer().getBuffs().size());
							}
							currentBuffs.update();

						} catch (Exception e1) {

							e1.printStackTrace();
						}
						// panel.setVisible(true);
						return;
					}
				});
			}
		});
		miscPanel.add(btnAddItem, BorderLayout.EAST);

		JPanel selectPanel = new JPanel();
		selectPanel.setBorder(null);
		selectPanel.setBounds(260, 0, 40, 100);
		contentPane.add(selectPanel);
		selectPanel.setLayout(new BorderLayout(0, 0));

		resultPane = new JTextPane();
		resultPane.setToolTipText("Dernier jet pour cette créature");
		resultPane.setEditable(false);
		resultPane.setText("0");
		selectPanel.add(resultPane, BorderLayout.NORTH);

		JToggleButton btnSelect = new JToggleButton(uncheckedIcon);
		btnSelect.setSelectedIcon(checkedIcon);
		btnSelect.setBorder(null);
		btnSelect.setBorderPainted(false);
		btnSelect.setFocusPainted(false);
		btnSelect.setContentAreaFilled(false);
		selectPanel.add(btnSelect, BorderLayout.CENTER);

	}

	// public void updateBuffPanel (BuffPanel currentBuffs,MainWindow main){
	// //System.out.println("updating buff panel");
	//
	// List<Buff> buffs = this.creature.getBuffer().getBuffs();
	// currentBuffs.removeAll();
	// for(Buff buff : buffs){
	// //System.out.println(buff.getName());
	// BuffButton associatedButton = new BuffButton(buff,this,main);
	// currentBuffs.add(associatedButton);
	// }
	// currentBuffs.invalidate();
	// currentBuffs.validate();
	// currentBuffs.repaint();
	// //System.out.println("updated buff panel");
	// //DONE synchronize BuffPanel and this.creature Buffer
	//
	////
	// }
	// public void updateInventoryPanel (InventoryPanel inventoryPanel){
	// //DONE synchronize InventoryPanel and this.creature Inventory
	// }

	// class BuffButton extends JButton{
	/**
	 * 
	 */
	// ImageIcon goodvisBuffIcon = new ImageIcon("src/gui/images/goodvis.png");
	// ImageIcon goodinvisBuffIcon = new
	// ImageIcon("src/gui/images/goodinvis.png");
	// ImageIcon badvisBuffIcon = new ImageIcon("src/gui/images/badvis.png");
	// ImageIcon badinvisBuffIcon = new
	// ImageIcon("src/gui/images/badinvis.png");
	// ImageIcon image;
	//
	// private static final long serialVersionUID = 1L;
	// private Buff associatedBuff;
	//
	// public BuffButton (Buff buff,CreaturePanel parent,MainWindow main){
	//
	// super();
	// associatedBuff=buff;
	//
	// image = badvisBuffIcon;
	// if(buff.isHidden()){
	// if(buff.isPositive()){
	// image = goodinvisBuffIcon;
	// }
	// else{
	// image = badinvisBuffIcon;
	// }
	// }
	// else{
	// if(buff.isPositive()){
	// image = goodvisBuffIcon;
	// }
	// }
	// this.setIcon(image);
	// //DONE set the right icon and update accordingly
	// this.setToolTipText("<html>"+associatedBuff.getName()+"<br>"+associatedBuff.getDescription()+"</html>");
	// this.setBuff(associatedBuff);
	// this.setBorder(null);
	// this.setOpaque(false);
	// this.setBorderPainted(false);
	// this.setFocusPainted(false);
	// this.setContentAreaFilled(false);
	// this.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// NewBuffWindow newbuff = new NewBuffWindow(buff,creature,main);
	// newbuff.setVisible(true);
	// //panel.setVisible(false);
	//// main.setAlwaysOnTop(false);
	// newbuff.toFront();
	//
	//
	// newbuff.addWindowListener(new WindowAdapter(){
	// public void windowClosed(WindowEvent e){
	//
	// if(newbuff.isFinished() && newbuff.isPublic()){
	// main.addPublicBuff(newbuff.getBuff());
	// }
	// parent.updateBuffPanel(parent.currentBuffs,main);
	//
	// // DONE : RETURN NEW BUFF HERE with newbuff
	// return;
	// }
	// });
	//
	// }
	// });
	// }
	//
	//
	//
	// public Buff getBuff() {
	// return associatedBuff;
	// }
	//
	// public void setBuff(Buff buff) {
	// this.associatedBuff = buff;
	// }

	// }

	// class BuffPanel extends JPanel implements Observer{
	//
	// /**
	// *
	// */
	//
	// private MainWindow main;
	//
	// public BuffPanel(MainWindow main){
	// super();
	// this.main = main;
	// }
	// private static final long serialVersionUID = 1L;
	//
	// public void update(Observable arg0, Object arg1) {
	//
	// updateBuffPanel(this,main);
	// }
	//
	// }

	// class InventoryPanel extends JPanel implements Observer{
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// public void update(Observable arg0, Object arg1) {
	//
	// updateInventoryPanel(this);
	// }
	//
	// }

	public Creature getCreature() {

		return creature;
	}

	public BuffPanel getCurrentBuffs() {

		return currentBuffs;
	}

	public void showResult(String result) {
		resultPane.setText(result);
	}

	public String getLastResult() {
		return resultPane.getText();
	}

}
