package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class NewItemWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtAssociatedBuffs;
	private CreaturePanel creaturePanel;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public NewItemWindow(CreaturePanel creaturePanel) {
		this.creaturePanel = creaturePanel;
		
		NewItemWindow window = this;
		setTitle("New Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextPane txtpnDescription = new JTextPane();
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Type 1 items", "Type 2 items"}));
		comboBox_1.setBounds(207, 11, 195, 32);
		contentPane.add(comboBox_1);
		
		JPanel buffPanel = new JPanel();
		buffPanel.setBounds(5, 157, 254, 94);
		contentPane.add(buffPanel);
		buffPanel.setLayout(new BorderLayout(0, 0));
		
		txtAssociatedBuffs = new JTextField();
		txtAssociatedBuffs.setEditable(false);
		txtAssociatedBuffs.setText("Associated Buffs");
		buffPanel.add(txtAssociatedBuffs, BorderLayout.NORTH);
		txtAssociatedBuffs.setColumns(10);
		
		JButton addBuff = new JButton("+");
		addBuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewBuffWindow newbuff = new NewBuffWindow(window.creaturePanel);
				newbuff.setVisible(true);
			}
		});
		buffPanel.add(addBuff, BorderLayout.EAST);
		
		JPanel associatedBuffs = new JPanel();
		buffPanel.add(associatedBuffs, BorderLayout.CENTER);}
}
