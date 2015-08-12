package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import core.Buff;
import core.Creature;
import core.Item;

public class NewItemWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtAssociatedBuffs;
	private JTextPane txtpnDescription;
	private JComboBox<String> comboItemType;
	private Item associatedItem;
	private JPanel buffPanel;
	private BuffPanel associatedBuffs;
	private boolean finished = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public NewItemWindow(Item item, Creature creature, MainWindow main) {
		this.associatedItem = item;
		NewItemWindow window = this;

		setTitle("Edit Item");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtItemName = new JTextField();
		txtItemName.setFocusCycleRoot(true);
		txtItemName.setToolTipText("Enter a name");
		txtItemName.setText("Item Name");
		txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtItemName.setColumns(10);
		txtItemName.setBounds(5, 11, 143, 32);
		contentPane.add(txtItemName);
		
		txtpnDescription = new JTextPane();
		txtpnDescription.setFocusCycleRoot(false);
		txtpnDescription.setText("Item Description");
		txtpnDescription.setBounds(5, 60, 419, 86);
		contentPane.add(txtpnDescription);

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finished = true;
				associatedItem.setDescription(txtpnDescription.getText());
				associatedItem.setType(comboItemType.getSelectedIndex());
				associatedItem.setName(txtItemName.getText());
				associatedBuffs.applyBuffsToItem();

				window.dispose(); // DONE : save the new item information into a
									// new item
			}
		});
		btnOK.setBounds(280, 200, 144, 51);
		contentPane.add(btnOK);

		

		comboItemType = new JComboBox<String>();
		comboItemType
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Amulet", "Armor", "Gem", "Ring", "Sword" }));
		comboItemType.setBounds(280, 157, 144, 32);
		contentPane.add(comboItemType);

		buffPanel = new JPanel();
		buffPanel.setBounds(5, 157, 254, 94);
		contentPane.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));

		txtAssociatedBuffs = new JTextField();
		txtAssociatedBuffs.setOpaque(false);
		txtAssociatedBuffs.setEditable(false);
		txtAssociatedBuffs.setText("Associated Buffs");
		buffPanel.add(txtAssociatedBuffs, BorderLayout.NORTH);
		txtAssociatedBuffs.setColumns(10);

		associatedBuffs = new BuffPanel(main, associatedItem);
		associatedItem.addObserver(associatedBuffs);
		associatedBuffs.setLayout(new BoxLayout(associatedBuffs, BoxLayout.LINE_AXIS));
		// buffPanel.add(associatedBuffs, BorderLayout.CENTER);

		JButton addBuff = new JButton("+");
		addBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewBuffWindow newbuff = new NewBuffWindow(null, item, main, associatedBuffs);
				newbuff.setVisible(true);

				newbuff.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							if (newbuff.isFinished()) {
								if (newbuff.isPublic()) {
									main.addPublicBuff(newbuff.getBuff());
								}

								associatedBuffs.addToBuffer(newbuff.getBuff());
								associatedBuffs.update();
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
		buffPanel.add(addBuff, BorderLayout.EAST);

		JScrollPane buffScroll = new JScrollPane(associatedBuffs);
		buffScroll.setBorder(null);
		buffScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buffScroll.setPreferredSize(new Dimension(160, 0));
		buffPanel.add(buffScroll, BorderLayout.WEST);

		JButton btnMoveToStash = new JButton("Move to Stash");
		btnMoveToStash.setFocusable(false);
		btnMoveToStash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					creature.getInventory().removeItem(associatedItem);
					main.addItemToStash(associatedItem);
					window.dispose();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
		btnMoveToStash.setBounds(293, 11, 131, 34);
		contentPane.add(btnMoveToStash);

		JComboBox<String> loadFromStash = new JComboBox<String>();
		List<String> names = new ArrayList<String>();
		for (Item stashedItem : main.getStash()) {
			names.add(stashedItem.getName());
		}
		loadFromStash.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadItem(main.getStash().get(loadFromStash.getSelectedIndex()));
				main.removeFromStash(main.getStash().get(loadFromStash.getSelectedIndex()));

			}

		});
		loadFromStash.setModel(new DefaultComboBoxModel<>(names.toArray(new String[names.size()])));
		loadFromStash.setBounds(158, 11, 125, 32);
		contentPane.add(loadFromStash);
		
		
		
		
		loadItem(associatedItem);
		
		
		
		this.getRootPane().setDefaultButton(btnOK);
		
		
		//txtItemName.setRequestFocusEnabled(true);
		//txtItemName.requestFocus();

	}

	private void loadItem(Item item) {

		associatedItem = item;
		associatedBuffs.setItem(associatedItem);
		for (Buff buff : associatedItem.getBuffs()) {
			associatedBuffs.addToBuffer(buff);
		}
		associatedBuffs.update();
		txtItemName.setText(item.getName());
		if(item.getDescription()!=null && !item.getDescription().equals("")){
			txtpnDescription.setText(item.getDescription());
		}
		
		
		comboItemType.setSelectedIndex(item.getType());

	}

	public Item getItem() {
		return associatedItem;
	}

	public boolean isFinished() {

		return finished;
	}
}
