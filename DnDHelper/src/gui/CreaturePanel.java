package gui;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class CreaturePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CreaturePanel() {
		setBackground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setPreferredSize(new Dimension(300, 100));
		CreaturePanel panel = this;
		setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(2, 2, 296, 96);
		add(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanels = new JPanel();
		mainPanels.setBounds(0, 0, 250, 100);
		contentPane.add(mainPanels);
		mainPanels.setLayout(new CardLayout(0, 0));
		//this.setMinimumSize(new Dimension(300, 100));
		
		JPanel mainPanelCreature = new JPanel();
		mainPanels.add(mainPanelCreature, "name_309225081159039");
		mainPanelCreature.setLayout(null);
		
		JTextPane txtpnCreaturename = new JTextPane();
		txtpnCreaturename.setBounds(10, 7, 117, 28);
		txtpnCreaturename.setEditable(false);
		mainPanelCreature.add(txtpnCreaturename);
		txtpnCreaturename.setBackground(UIManager.getColor("Button.background"));
		txtpnCreaturename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnCreaturename.setText("CreatureName");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setToolTipText("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnEdit.setBounds(147, 12, 36, 23);
		mainPanelCreature.add(btnEdit);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setToolTipText("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainPanels.getLayout();
				cl.next(mainPanels);
			}
		});
		btnInventory.setBounds(193, 12, 36, 23);
		mainPanelCreature.add(btnInventory);
		
		JPanel BuffPanel = new JPanel();
		BuffPanel.setBounds(10, 46, 219, 46);
		mainPanelCreature.add(BuffPanel);
		
		JPanel mainPanelInventory = new JPanel();
		mainPanels.add(mainPanelInventory, "name_309312157043447");
		mainPanelInventory.setLayout(new BorderLayout(0, 0));
		
		JPanel inventoryPanel = new JPanel();
		mainPanelInventory.add(inventoryPanel, BorderLayout.WEST);
		
		JPanel miscPanel = new JPanel();
		mainPanelInventory.add(miscPanel, BorderLayout.EAST);
		miscPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setVerticalAlignment(SwingConstants.TOP);
		miscPanel.add(btnAddItem, BorderLayout.NORTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) mainPanels.getLayout();
				cl.next(mainPanels);
			}
		});
		miscPanel.add(btnBack, BorderLayout.SOUTH);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(250, 0, 50, 100);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(0, 0, 50, 100);
		selectPanel.add(btnSelect);
		
	}
}
