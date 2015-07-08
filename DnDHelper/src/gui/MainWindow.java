package gui;



import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2740437090361841747L;

	public MainWindow(){
//		JPanel mainPanel = new JPanel();
//		mainPanel.setBackground(Color.white);
//		mainPanel.setLayout(null); //TODO : MODIFIER LE LAYOUT
//		this.setContentPane(mainPanel);
		
		this.setTitle("DnDHelper");
		this.setSize(new Dimension(300,800));
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		
		//this.pack();
		this.setVisible(true);
	}
}
