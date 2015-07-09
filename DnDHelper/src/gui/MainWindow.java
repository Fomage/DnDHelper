package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import java.awt.FlowLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		List<CreaturePanel> creaturePanels = new ArrayList<CreaturePanel>();
		
		setResizable(false);
		setTitle("DnDHelper");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10,10,317,145);
		MainWindow main = this;
		
		
		
		ImageIcon diceicon = new ImageIcon("src/gui/images/dice2.png");
		ImageIcon dicepressedicon = new ImageIcon("src/gui/images/dice2pressed.png");
//		URL url = getClass().getResource("/gui/images/dice.png");
//		Image diceimg;
//		try {
//			diceimg = ImageIO
//					.read(url);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		contentPane = new JPanel();
		
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	
		
		JPanel scrollCreatures = new JPanel();
		scrollCreatures.setBorder(null);
		//contentPane.add(scrollCreatures, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(scrollCreatures);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setWheelScrollingEnabled(true);
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		JPanel creatures = new JPanel();
		creatures.setBorder(null);
		scrollCreatures.add(creatures);
		//creatures.setPreferredSize(new Dimension(300,700));
		creatures.setLayout(new BoxLayout(creatures, BoxLayout.PAGE_AXIS));
		
		for(CreaturePanel creaturepan : creaturePanels){
			creatures.add(creaturepan);
		}
		
		JPanel bot = new JPanel();
		bot.setPreferredSize(new Dimension(300, 80));
		contentPane.add(bot,BorderLayout.SOUTH);
		
		JButton btnAddcreature = new JButton("+");
		btnAddcreature.setFocusPainted(false);
		btnAddcreature.setFont(new Font("SansSerif", Font.PLAIN, 35));
		btnAddcreature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaturePanels.add(new CreaturePanel());
				if(creaturePanels.size()>=6){
					scrollPane.setPreferredSize(new Dimension(325,600));
				}
				for(CreaturePanel creaturepan : creaturePanels){
					creatures.add(creaturepan);
				}
				main.getContentPane().validate();
				main.pack();
			}
		});
		bot.setLayout(new BorderLayout(0, 0));
		
		JPanel options = new JPanel();
		bot.add(options, BorderLayout.EAST);
		
		JCheckBox chckbxOnTop = new JCheckBox("On Top");
		options.add(chckbxOnTop);
		chckbxOnTop.setSelected(true);
		
		JCheckBox chckbxResizable = new JCheckBox("Resizable");
		options.add(chckbxResizable);
		chckbxResizable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.isResizable()){
					
					main.setResizable(false);
					contentPane.validate();
					main.pack();
				}
				else{
					main.setResizable(true);
					contentPane.validate();
					main.pack();
				}
			}
		});
		chckbxOnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.isAlwaysOnTop()){
					
					main.setAlwaysOnTop(false);
					contentPane.validate();
					main.pack();
				}
				else{
					main.setAlwaysOnTop(true);
					contentPane.validate();
					main.pack();
				}
			}
		});
		bot.add(btnAddcreature, BorderLayout.WEST);
		
		JPanel diceRollPanel = new JPanel();
		bot.add(diceRollPanel, BorderLayout.SOUTH);
		diceRollPanel.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Type 1 Roll", "Type 2 Roll", "w jkebfwebfwjfw ejfwlmfwme bfw "}));
		diceRollPanel.add(comboBox, BorderLayout.WEST);
		
		JPanel rollPanel = new JPanel();
		diceRollPanel.add(rollPanel, BorderLayout.EAST);
		rollPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnRoll = new JButton(diceicon);
		rollPanel.add(btnRoll, BorderLayout.WEST);
		btnRoll.setPressedIcon(dicepressedicon);
		btnRoll.setBorder(null);
		btnRoll.setBorderPainted(false);
		btnRoll.setFocusPainted(false);
		btnRoll.setContentAreaFilled(false);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		rollPanel.add(horizontalStrut, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 5));
		diceRollPanel.add(verticalStrut, BorderLayout.SOUTH);
		
		
		contentPane.validate();
		this.pack();
	}
	
	
	
		
	

}
