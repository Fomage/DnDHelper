package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import core.Item;
import core.StatBuff;

public class NewItemWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtAssociatedBuffs;
	private JTextPane txtpnDescription;
	private CreaturePanel creaturePanel;
	private JComboBox<String> comboItemType;
	private Item associatedItem;
	private JPanel buffPanel;
	private BuffPanel associatedBuffs;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public NewItemWindow(Item item,CreaturePanel creaturePanel,MainWindow main) {
		this.creaturePanel = creaturePanel;
		this.associatedItem = item;
		NewItemWindow window = this;
		if(item == null){
			setTitle("New Item");
		}
		else{
			setTitle("Edit Item");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtpnDescription = new JTextPane();
		txtpnDescription.setText("Item Description");
		txtpnDescription.setBounds(5, 60, 419, 86);
		contentPane.add(txtpnDescription);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.dispose(); // TODO : save the new buff information into a new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);	
		
		txtItemName = new JTextField();
		txtItemName.setToolTipText("Enter a fucking name wtf are you retarded?");
		txtItemName.setText("Item Name");
		txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtItemName.setColumns(10);
		txtItemName.setBounds(5, 11, 143, 32);
		contentPane.add(txtItemName);
		
		comboItemType = new JComboBox<String>();
		comboItemType.setModel(new DefaultComboBoxModel<String>(new String[] {"Amulet","Armor","Gem","Ring","Sword"}));
		comboItemType.setBounds(207, 11, 195, 32);
		contentPane.add(comboItemType);
		
		buffPanel = new JPanel();
		buffPanel.setBounds(5, 157, 254, 94);
		contentPane.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));
		
		txtAssociatedBuffs = new JTextField();
		txtAssociatedBuffs.setOpaque(false);
		txtAssociatedBuffs.setEditable(false);
		txtAssociatedBuffs.setText("Associated Buffs");
		buffPanel.add(txtAssociatedBuffs, BorderLayout.NORTH);
		txtAssociatedBuffs.setColumns(10);
		
		
		associatedBuffs = new BuffPanel(main, associatedItem);
		associatedItem.addObserver(associatedBuffs);
		associatedBuffs.setLayout(new BoxLayout(associatedBuffs, BoxLayout.LINE_AXIS));
		//buffPanel.add(associatedBuffs, BorderLayout.CENTER);
		
		loadItem(item);
		
		JButton addBuff = new JButton("+");
		addBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewBuffWindow newbuff = new NewBuffWindow(null,item,main);
				newbuff.setVisible(true);
				
				
				newbuff.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						try {
							if(newbuff.isFinished()){
								if(newbuff.isPublic()){
									main.addPublicBuff(newbuff.getBuff());
								}
								
								
								associatedItem.addBuff(newbuff.getBuff());
								associatedBuffs.setItem(associatedItem);
								associatedBuffs.update();
							}
							
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
						//updateBuffPanel(currentBuffs);
						
						// TODO : RETURN NEW BUFF HERE with newbuff
						return;
					}
				});
			}
		});
		buffPanel.add(addBuff, BorderLayout.EAST);
		
		JScrollPane buffScroll = new JScrollPane(associatedBuffs);
		buffScroll.setBorder(null);
		buffScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buffScroll.setPreferredSize(new Dimension(160, 0));
		buffPanel.add(buffScroll, BorderLayout.WEST);
		
		
	}
	
	
	private void loadItem(Item item){
		if(item != null){
			associatedItem = item;
			txtpnDescription.setText(item.getDescription());
			txtItemName.setText(item.getName());
			
		}
		else{
			
			loadItem(new Item());
		}
	}
	
	public Item getItem(){
		return associatedItem;
	}
}
