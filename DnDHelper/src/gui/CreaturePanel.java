package gui;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;

public class CreaturePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364541950907930931L;
	private JTabbedPane tabbedPaneTest;

	/**
	 * Create the panel.
	 */
	public CreaturePanel(MainWindow main) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ImageIcon editIcon = new ImageIcon("src/gui/images/edit2.png");
		ImageIcon trashIcon = new ImageIcon("src/gui/images/trash2.png");
		ImageIcon creatureIcon = new ImageIcon("src/gui/images/person2.png");
		ImageIcon inventoryIcon = new ImageIcon("src/gui/images/inventory2.png");
		ImageIcon checkedIcon = new ImageIcon("src/gui/images/checked.png");
		ImageIcon uncheckedIcon = new ImageIcon("src/gui/images/unchecked.png");
		
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(303, 100));
		CreaturePanel panel = this;
		setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBounds(2, 2, 350, 108);
		add(contentPane);
		contentPane.setLayout(null);
		
		tabbedPaneTest = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPaneTest.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPaneTest.setFocusable(false);
		tabbedPaneTest.setBorder(null);
		tabbedPaneTest.setBounds(0, 0, 260, 100);
		
		
		contentPane.add(tabbedPaneTest);
		
		JPanel mainPanels = new JPanel();
		mainPanels.setBounds(0, 0, 250, 100);
		//contentPane.add(mainPanels);
		mainPanels.setLayout(new CardLayout(0, 0));
		//this.setMinimumSize(new Dimension(300, 100));
		
		JPanel mainPanelCreature = new JPanel();
		mainPanelCreature.setBorder(null);
		//mainPanels.add(mainPanelCreature, "name_309225081159039");
		tabbedPaneTest.addTab(null,creatureIcon,mainPanelCreature);
		mainPanelCreature.setLayout(null);
		
		JTextPane txtpnCreaturename = new JTextPane();
		txtpnCreaturename.setOpaque(false);
		txtpnCreaturename.setBounds(10, 7, 117, 28);
		txtpnCreaturename.setEditable(false);
		mainPanelCreature.add(txtpnCreaturename);
		txtpnCreaturename.setBackground(UIManager.getColor("Button.background"));
		txtpnCreaturename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnCreaturename.setText("CreatureName");
		
		JButton btnEdit = new JButton(editIcon);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO : edit launches NewCreatureWindow with default values
			}
		});
		btnEdit.setBorder(null);
		btnEdit.setBorderPainted(false);
		btnEdit.setFocusPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setToolTipText("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnEdit.setBounds(190, 12, 20, 20);
		mainPanelCreature.add(btnEdit);
		
		JButton btnRemove = new JButton(trashIcon);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.removeCreature(panel);
			}
		});
		btnRemove.setBorder(null);
		btnRemove.setBorderPainted(false);
		btnRemove.setFocusPainted(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setToolTipText("Remove");
		btnRemove.setBounds(155, 12, 20, 20);
		mainPanelCreature.add(btnRemove);
		
		JPanel buffPanel = new JPanel();
		buffPanel.setBounds(10, 46, 206, 46);
		mainPanelCreature.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddBuff = new JButton("+");
		btnAddBuff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAddBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewBuffWindow newbuff = new NewBuffWindow(panel);
				newbuff.setVisible(true);
				panel.setVisible(false);
				main.setAlwaysOnTop(false);
				newbuff.toFront();
				
				
				newbuff.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						// TODO : RETURN NEW BUFF HERE with newbuff
						
						
						main.setAlwaysOnTop(main.isOnTop());
						panel.setVisible(true);
						return;
					}
				});
			}
		});
		buffPanel.add(btnAddBuff, BorderLayout.EAST);
		
		JPanel currentBuffs = new JPanel();
		buffPanel.add(currentBuffs, BorderLayout.WEST);
		
		JPanel mainPanelInventory = new JPanel();
		mainPanelInventory.setBorder(null);
		//mainPanels.add(mainPanelInventory, "name_309312157043447");
		tabbedPaneTest.addTab(null,inventoryIcon,mainPanelInventory);
		mainPanelInventory.setLayout(new BorderLayout(0, 0));
		
		JPanel inventoryPanel = new JPanel();
		mainPanelInventory.add(inventoryPanel, BorderLayout.WEST);
		
		JPanel miscPanel = new JPanel();
		mainPanelInventory.add(miscPanel, BorderLayout.EAST);
		miscPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddItem = new JButton("+");
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAddItem.setFocusPainted(false);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewItemWindow newitem = new NewItemWindow(panel);
				newitem.setVisible(true);
				newitem.toFront();
				panel.setVisible(false);
				main.setAlwaysOnTop(false);
				
				
				newitem.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						// TODO : RETURN NEW ITEM HERE with newitem
						
						panel.setVisible(true);
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
		
		JTextPane textPane = new JTextPane();
		textPane.setToolTipText("Dernier jet pour cette cr\u00E9ature");
		textPane.setEditable(false);
		textPane.setText("0");
		selectPanel.add(textPane, BorderLayout.NORTH);
		
		JToggleButton btnSelect = new JToggleButton(uncheckedIcon);
		btnSelect.setSelectedIcon(checkedIcon);
		btnSelect.setBorder(null);
		btnSelect.setBorderPainted(false);
		btnSelect.setFocusPainted(false);
		btnSelect.setContentAreaFilled(false);
		selectPanel.add(btnSelect, BorderLayout.CENTER);
		
	}
}
