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
import javax.swing.JLabel;

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
		setResizable(false);
		setTitle("DnDHelper");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow main = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel creatures = new JPanel();
		contentPane.add(creatures, BorderLayout.NORTH);
		creatures.setLayout(new BoxLayout(creatures, BoxLayout.PAGE_AXIS));
		
		JPanel test1 = new CreaturePanel();
		creatures.add(test1);
		
		JPanel test2 = new CreaturePanel();
		creatures.add(test2);
		
		JPanel bot = new JPanel();
		bot.setPreferredSize(new Dimension(300, 40));
		contentPane.add(bot,BorderLayout.SOUTH);
		bot.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddcreature = new JButton("Add Creature");
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
				}
			}
		});
		bot.add(chckbxResizable,BorderLayout.EAST);
		
		contentPane.validate();
		this.pack();
	}

}
