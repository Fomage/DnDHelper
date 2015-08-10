package gui;

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
import javax.swing.border.EmptyBorder;

import core.Buff;
import core.Creature;
import core.StatBuff;
import core.Stats;

import javax.swing.JCheckBox;

public class NewBuffWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319463507363235057L;
	private JPanel contentPane;
	private JTextField txtName;
	@SuppressWarnings("unused")
	private Buff localBuff;


	/**
	 * Create the frame.
	 */
	public NewBuffWindow(Buff buff,Creature creature) {
		this.localBuff = buff;
		NewBuffWindow window = this;
		if(buff == null){
			setTitle("New Buff");
		}
		else{
			setTitle("Edit Buff");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setToolTipText("Enter a name");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setBounds(5, 5, 143, 32);
		txtName.setText("Buff Name");
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setText("Buff Description");
		txtpnDescription.setBounds(5, 92, 419, 86);
		contentPane.add(txtpnDescription);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(223, 207, 47, 32);
		contentPane.add(spinner);
		
		
		JComboBox<String> statorskillBox = new JComboBox<String>();
		statorskillBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Skill", "Stat"}));
		statorskillBox.setBounds(5, 207, 45, 32);
		contentPane.add(statorskillBox);
		
		
		JComboBox<String> skillComboBox = new JComboBox<String>();
		skillComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Skill 1", "Skill 2"}));
		skillComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(skillComboBox);
				

		JComboBox<String> statComboBox = new JComboBox<String>();
		statComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Force", "Dextérité","Constitution","Intelligence","Sagesse","Charisme"}));
		statComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(statComboBox);
		if(statorskillBox.getSelectedItem() == "Skill"){
			statComboBox.setVisible(false);
		}
		else{
			skillComboBox.setVisible(false);
		}
		
		
		
		
		statorskillBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(statorskillBox.getSelectedItem()=="Stat"){
					statComboBox.setVisible(true);
					skillComboBox.setVisible(false);
				}
				else{
					statComboBox.setVisible(false);
					skillComboBox.setVisible(true);
				}
				
			}
		});
		
		JCheckBox chckbxPositiveBuff = new JCheckBox("Positive Buff");
		chckbxPositiveBuff.setBounds(271, 11, 124, 23);
		contentPane.add(chckbxPositiveBuff);
		
		JCheckBox chckbxHiddenBuff = new JCheckBox("Hidden Buff");
		chckbxHiddenBuff.setBounds(271, 51, 124, 23);
		contentPane.add(chckbxHiddenBuff);
		
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//creaturePanel.setVisible(true);
				
				if(statorskillBox.getSelectedItem()=="Stat"){
					int stat = Stats.Fo;
					switch((String)statComboBox.getSelectedItem()){
					case "Force": stat= Stats.Fo;
						break;
					case "Dextérité": stat= Stats.Dex;
						break;
					case "Constitution": stat= Stats.Con;
						break;
					case "Intelligence": stat= Stats.Int;
						break;	
					case "Sagesse": stat= Stats.Sag;
						break;	
					case "Charisme": stat= Stats.Cha;
						break;	
					}
					try {
						localBuff = new StatBuff(stat,(int)spinner.getValue(),txtName.getText(),chckbxHiddenBuff.isSelected(),chckbxPositiveBuff.isSelected());
						localBuff.setDescription(txtpnDescription.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					
				}
				window.dispose(); // TODO : save the new buff information into a new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);
		
		JButton btnLoadExistingBuff = new JButton("Load Existing Buff");
		btnLoadExistingBuff.setBounds(5, 48, 143, 29);
		contentPane.add(btnLoadExistingBuff);
		
		
		
		
	}
	
	Buff getBuff(){
		return localBuff;
	}
}
