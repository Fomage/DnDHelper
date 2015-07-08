package gui;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

public class CreaturePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CreaturePanel() {
		setBackground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		this.setPreferredSize(new Dimension(300, 100));
		//this.setMinimumSize(new Dimension(300, 100));
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 250, 100);
		add(MainPanel);
		MainPanel.setLayout(null);
		
		JTextPane txtpnCreaturename = new JTextPane();
		txtpnCreaturename.setBounds(10, 7, 117, 28);
		txtpnCreaturename.setEditable(false);
		MainPanel.add(txtpnCreaturename);
		txtpnCreaturename.setBackground(Color.LIGHT_GRAY);
		txtpnCreaturename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnCreaturename.setText("CreatureName");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(147, 12, 36, 23);
		MainPanel.add(btnEdit);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBounds(193, 12, 36, 23);
		MainPanel.add(btnInventory);
		
		JPanel BuffPanel = new JPanel();
		BuffPanel.setBounds(10, 46, 219, 46);
		MainPanel.add(BuffPanel);
		
		JPanel SelectPanel = new JPanel();
		SelectPanel.setBounds(250, 0, 50, 150);
		add(SelectPanel);
		SelectPanel.setLayout(null);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(0, 0, 50, 100);
		SelectPanel.add(btnSelect);
		
	}
}
