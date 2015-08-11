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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Buff;
import core.Creature;
import core.Item;
import core.Serializer;
import java.awt.FlowLayout;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean onTopState = true;
	List<CreaturePanel> creaturePanels;
	JPanel creatures;
	List<Buff> publicBuffs = new ArrayList<Buff>();
	List<Item> stash = new ArrayList<Item>();

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
//		try {
//			@SuppressWarnings("unchecked")
//			List<Creature> creatureList = (List<Creature>) Serializer.load("./Session.ses");
//			for(Creature creat : creatureList){
//				creaturePanels.add(new CreaturePanel(this, creat));
//			}
//		} catch (Exception e1) {
//			
//			
//		}
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
		
		JPanel sessionPanel = new JPanel();
		bot.add(sessionPanel, BorderLayout.CENTER);
		sessionPanel.setLayout(null);
		
		JButton saveSessionButton = new JButton("Save");
		saveSessionButton.setBounds(0, 0, 89, 45);
		sessionPanel.add(saveSessionButton);
		saveSessionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Creature> session = new ArrayList<Creature>();
				for(CreaturePanel creaturePanel : creaturePanels){
					session.add(creaturePanel.getCreature());
				}
				
				JFileChooser chooser = new JFileChooser(".");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Session Files", "ses");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(main);
				
				try {
					if(chooser.getSelectedFile()!=null){
						Serializer.save((Serializable) session, chooser.getSelectedFile().getPath()+".ses");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JButton loadSessionButton = new JButton("Load");
		loadSessionButton.setBounds(89, 0, 89, 45);
		sessionPanel.add(loadSessionButton);
		loadSessionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser chooser = new JFileChooser(".");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Session Files", "ses");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(main);
				
				try {
					//System.out.println(chooser.getSelectedFile().getPath());
					
					if(chooser.getSelectedFile()!=null){
						@SuppressWarnings("unchecked")
						List<Creature> creatureList = (List<Creature>) Serializer.load(chooser.getSelectedFile().getPath());
						creaturePanels = new ArrayList<CreaturePanel>();
						for(Creature creat : creatureList){
							
							creaturePanels.add(new CreaturePanel(main, creat));
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
						main.pack();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		
		JPanel options = new JPanel();
		bot.add(options, BorderLayout.EAST);
		options.setLayout(new BorderLayout(0, 0));
		
		
		options.add(chckbxOnTop, BorderLayout.SOUTH);
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
		options.add(chckbxResizable, BorderLayout.NORTH);
		
		Component verticalGlue = Box.createVerticalGlue();
		options.add(verticalGlue, BorderLayout.CENTER);
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
		
		JFileChooser chooser = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Session Files", "ses");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(main);
		
		try {
			//System.out.println(chooser.getSelectedFile().getPath());
			
			if(chooser.getSelectedFile()!=null){
				@SuppressWarnings("unchecked")
				List<Creature> creatureList = (List<Creature>) Serializer.load(chooser.getSelectedFile().getPath());
				creaturePanels = new ArrayList<CreaturePanel>();
				for(Creature creat : creatureList){
					
					creaturePanels.add(new CreaturePanel(main, creat));
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
				main.pack();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}

	public boolean isOnTop() {
		// TODO Auto-generated method stub
		return onTopState;
	}
	
	public void removeCreature(CreaturePanel cPanel){
		creaturePanels.remove(cPanel);
		if(!this.isResizable()){
			this.pack();
		}
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

	public void addPublicBuff(Buff buff){
		if(buff == null){
			return;
		}
		boolean alreadyPublic = false;
		
		for(Buff publicBuff : publicBuffs){
			if(buff.getName().equals(publicBuff.getName())){
				alreadyPublic = true;
			}
		}
		if(!alreadyPublic){
			publicBuffs.add(buff);
		}
		
	}
	
	public List<Buff> getPublicBuffs(){
		return publicBuffs;
	}
	
	public void removePublicBuff(Buff buff){
		publicBuffs.remove(buff);
	}

	public void addItemToStash(Item item){
		if(item == null){
			return;
		}
		boolean alreadyInStash = false; //this should be impossible
		
		for(Buff publicBuff : publicBuffs){
			if(item.getName().equals(publicBuff.getName())){
				System.out.println("DUPLICATE ITEM");
				alreadyInStash = true;
			}
		}
		if(!alreadyInStash){
			stash.add(item);
		}
		
	}
	
	public List<Item> getStash(){
		return stash;
	}
	
	public void removeFromStash(Item item){
		stash.remove(item);
	}
	
	public boolean getOnTopState() {
		// TODO Auto-generated method stub
		return onTopState;
	}
	
	
		
	

}
