package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

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
		MainWindow main = this;
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	
		
		JPanel scrollCreatures = new JPanel();
		//contentPane.add(scrollCreatures, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(scrollCreatures);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(300,600));
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		JPanel creatures = new JPanel();
		scrollCreatures.add(creatures);
		//creatures.setPreferredSize(new Dimension(300,700));
		creatures.setLayout(new BoxLayout(creatures, BoxLayout.PAGE_AXIS));
		
		for(CreaturePanel creaturepan : creaturePanels){
			creatures.add(creaturepan);
		}
		
		JPanel bot = new JPanel();
		bot.setPreferredSize(new Dimension(300, 40));
		contentPane.add(bot,BorderLayout.SOUTH);
		bot.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddcreature = new JButton("Add Creature");
		btnAddcreature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaturePanels.add(new CreaturePanel());
				for(CreaturePanel creaturepan : creaturePanels){
					creatures.add(creaturepan);
				}
				main.getContentPane().validate();
				main.pack();
			}
		});
		bot.add(btnAddcreature,BorderLayout.WEST);
		
		JCheckBox chckbxResizable = new JCheckBox("Resizable");
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
		bot.add(chckbxResizable,BorderLayout.EAST);
		
		contentPane.validate();
		this.pack();
	}

}
