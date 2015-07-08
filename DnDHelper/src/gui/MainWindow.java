package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame  {
	public MainWindow(){
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null); // MODIFIER LE LAYOUT
		this.setContentPane(mainPanel);
		
		
		
	}
}
