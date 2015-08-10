package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean onTopState = true;
	List<CreaturePanel> creaturePanels;
	JPanel creatures;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
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
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		creaturePanels = new ArrayList<CreaturePanel>(); //TODO : Load creatures
		
		setResizable(false);
		setTitle("DnDHelper");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10,10,317,145);
		MainWindow main = this;
		
		
		
		ImageIcon diceicon = new ImageIcon("src/gui/images/dice2.png");
		Image icon =  diceicon.getImage();
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
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	
		
//		JPanel scrollCreatures = new JPanel();
//		scrollCreatures.setAlignmentY(Component.TOP_ALIGNMENT);
//		scrollCreatures.setAlignmentX(Component.LEFT_ALIGNMENT);
//		scrollCreatures.setBorder(null);
		
		//contentPane.add(scrollCreatures, BorderLayout.EAST);
		creatures = new JPanel();
		creatures.setAlignmentY(Component.TOP_ALIGNMENT);
		creatures.setAlignmentX(Component.LEFT_ALIGNMENT);
		creatures.setBorder(null);
//		scrollCreatures.add(creatures);
		//creatures.setPreferredSize(new Dimension(300,700));
		creatures.setLayout(new BoxLayout(creatures, BoxLayout.PAGE_AXIS));
		
		
		JScrollPane scrollPane = new JScrollPane(creatures);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.setWheelScrollingEnabled(true);
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		setIconImage(icon);
		
		for(CreaturePanel creaturepan : creaturePanels){
			creatures.add(creaturepan);
		}
		
		JPanel bot = new JPanel();
		bot.setPreferredSize(new Dimension(300, 80));
		contentPane.add(bot,BorderLayout.SOUTH);
		
		JCheckBox chckbxOnTop = new JCheckBox("On Top");
		
		JButton btnAddcreature = new JButton("+");
		btnAddcreature.setFocusPainted(false);
		btnAddcreature.setFont(new Font("SansSerif", Font.PLAIN, 35));
		btnAddcreature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCreatureWindow newcreature = new NewCreatureWindow();
				newcreature.setVisible(true);
				
				main.setAlwaysOnTop(false);
				newcreature.toFront();
				
				
				
				
				newcreature.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						if(newcreature.isFinished()){
							creaturePanels.add(new CreaturePanel(main , newcreature.getCreature()));
						}
						// TODO : RETURN NEW CREATURE HERE with <code> newcreature </code> DONE
						main.setAlwaysOnTop(onTopState);
						
						
						if(creaturePanels.size()>=6){
							scrollPane.setPreferredSize(new Dimension(325,600));
						}
						creatures.removeAll();
						for(CreaturePanel cPanel : creaturePanels){
							creatures.add(cPanel);
						}
						main.getContentPane().validate();
						if(!main.isResizable()){
							main.pack();
						}
						
					}
				});

				
			}
		});
		bot.setLayout(new BorderLayout(0, 0));
		
		JPanel options = new JPanel();
		bot.add(options, BorderLayout.EAST);
		
		
		options.add(chckbxOnTop);
		chckbxOnTop.setSelected(true);
		chckbxOnTop.addChangeListener(new ChangeListener(){

			
			public void stateChanged(ChangeEvent arg0) {
				if(onTopState){
					onTopState = false;
				}
				else{
					onTopState = true;
				}
				
			}
			
		});
		
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Type 1 Roll", "Type 2 Roll", "w jkebfwebfwjfw ejfwlmfwme bfw "}));
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

	public boolean isOnTop() {
		// TODO Auto-generated method stub
		return onTopState;
	}
	
	public void removeCreature(CreaturePanel cPanel){
		creaturePanels.remove(cPanel);
		check();
	}
	
	public void check(){
		creatures.removeAll();
		for(CreaturePanel cPanel : creaturePanels){
			creatures.add(cPanel);
		}
		this.getContentPane().validate();
		if(!this.isAutoRequestFocus()){
			this.pack();
		}
	}

	

	public boolean getOnTopState() {
		// TODO Auto-generated method stub
		return onTopState;
	}
	
	
		
	

}
