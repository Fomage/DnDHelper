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

public class CreaturePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364541950907930931L;
	private JTabbedPane tabbedPaneTest;

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
		
		tabbedPaneTest = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPaneTest.setBounds(0, 0, 250, 100);
		
		
		contentPane.add(tabbedPaneTest);
		
		JPanel mainPanels = new JPanel();
		mainPanels.setBounds(0, 0, 250, 100);
		//contentPane.add(mainPanels);
		mainPanels.setLayout(new CardLayout(0, 0));
		//this.setMinimumSize(new Dimension(300, 100));
		
		JPanel mainPanelCreature = new JPanel();
		//mainPanels.add(mainPanelCreature, "name_309225081159039");
		tabbedPaneTest.addTab("C",null,mainPanelCreature);
		mainPanelCreature.setLayout(null);
		
		JTextPane txtpnCreaturename = new JTextPane();
		txtpnCreaturename.setBounds(10, 7, 117, 28);
		txtpnCreaturename.setEditable(false);
		mainPanelCreature.add(txtpnCreaturename);
		txtpnCreaturename.setBackground(UIManager.getColor("Button.background"));
		txtpnCreaturename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnCreaturename.setText("CreatureName");
		
		JButton btnEdit = new JButton("ED");
		btnEdit.setToolTipText("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnEdit.setBounds(175, 11, 41, 24);
		mainPanelCreature.add(btnEdit);
		
		JPanel buffPanel = new JPanel();
		buffPanel.setBounds(10, 46, 206, 46);
		mainPanelCreature.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddBuff = new JButton("+");
		btnAddBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewBuffWindow newbuff = new NewBuffWindow(panel);
				newbuff.setVisible(true);
				panel.setVisible(false);
				
				
				newbuff.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						// TODO : RETURN NEW BUFF HERE with newbuff
						
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
		//mainPanels.add(mainPanelInventory, "name_309312157043447");
		tabbedPaneTest.addTab("I",null,mainPanelInventory);
		mainPanelInventory.setLayout(new BorderLayout(0, 0));
		
		JPanel inventoryPanel = new JPanel();
		mainPanelInventory.add(inventoryPanel, BorderLayout.WEST);
		
		JPanel miscPanel = new JPanel();
		mainPanelInventory.add(miscPanel, BorderLayout.EAST);
		miscPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddItem = new JButton("+");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewItemWindow newitem = new NewItemWindow(panel);
				newitem.setVisible(true);
				panel.setVisible(false);
				
				
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
		selectPanel.setBounds(250, 0, 50, 100);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		
		JToggleButton btnSelect = new JToggleButton("S");
		btnSelect.setBounds(0, 0, 50, 100);
		selectPanel.add(btnSelect);
		
	}
}
