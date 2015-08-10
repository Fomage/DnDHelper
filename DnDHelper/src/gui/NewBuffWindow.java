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
import core.SkillBuff;
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
	private Boolean finished = false;


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
		if(buff==null){
			txtName.setText("Buff Name");
		}
		else{
			txtName.setText(buff.getName());
		}
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JTextPane txtpnDescription = new JTextPane();
		if(buff == null){
			txtpnDescription.setText("Buff Description");
		}
		else{
			txtpnDescription.setText(buff.getDescription());
		}
		txtpnDescription.setBounds(5, 92, 419, 86);
		contentPane.add(txtpnDescription);
		
		JSpinner spinnerMod = new JSpinner();
		if(buff!=null){
			if(buff instanceof StatBuff){
				spinnerMod.setValue(((StatBuff) buff).getMod());
			}
			if(buff instanceof SkillBuff){
				spinnerMod.setValue(((SkillBuff) buff).getMod());
			}
			
		}
		spinnerMod.setBounds(223, 207, 47, 32);
		contentPane.add(spinnerMod);
		
		
		JComboBox<String> statorskillBox = new JComboBox<String>();
		statorskillBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Skill", "Stat"}));
		statorskillBox.setBounds(5, 207, 45, 32);
		contentPane.add(statorskillBox);
		if(buff!=null){
			if(buff instanceof StatBuff){
				statorskillBox.setSelectedIndex(1);
			}
		}
		
		
		JComboBox<String> skillComboBox = new JComboBox<String>();
		skillComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Skill 1", "Skill 2"}));
		skillComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(skillComboBox);
				

		JComboBox<String> statComboBox = new JComboBox<String>();
		statComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Force", "Dextérité","Constitution","Intelligence","Sagesse","Charisme"}));
		statComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(statComboBox);
		
		
		if(statorskillBox.getSelectedItem() == "Skill"){
			//TODO load the correct skill
			statComboBox.setVisible(false);
		}
		else{
			if(buff!=null){
				statComboBox.setSelectedIndex(((StatBuff)buff).getStat());
			}
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
		chckbxPositiveBuff.setBounds(184, 11, 124, 23);
		contentPane.add(chckbxPositiveBuff);
		if(buff!= null){
			chckbxPositiveBuff.setSelected(buff.isPositive());
		}
		
		JCheckBox chckbxHiddenBuff = new JCheckBox("Hidden Buff");
		chckbxHiddenBuff.setBounds(184, 51, 124, 23);
		contentPane.add(chckbxHiddenBuff);
		if(buff!= null){
			chckbxHiddenBuff.setSelected(buff.isHidden());
		}
		
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finished = true;
				//creaturePanel.setVisible(true);
				if(localBuff!=null){
					if(statorskillBox.getSelectedItem() == "Stats" && localBuff instanceof SkillBuff){
						
					}
					else if(statorskillBox.getSelectedItem() == "Skill" && localBuff instanceof StatBuff){
						
					}
					else{
						if(localBuff instanceof StatBuff){
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
								((StatBuff) localBuff).setStat(stat);
								((StatBuff) localBuff).setMod((int)spinnerMod.getValue());
								((StatBuff) localBuff).setName(txtName.getText());
								((StatBuff) localBuff).setHidden(chckbxHiddenBuff.isSelected());
								((StatBuff) localBuff).setPositive(chckbxPositiveBuff.isSelected());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							
						}
					}
				}
				else{
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
							String desc = txtpnDescription.getText();
							if(desc.equals("Buff Description")){
								desc = "";
							}
							localBuff = new StatBuff(stat,(int)spinnerMod.getValue(),txtName.getText(),chckbxHiddenBuff.isSelected(),chckbxPositiveBuff.isSelected());
							localBuff.setDescription(desc);
						}
							
						 catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						
					}
				}
				window.dispose(); // TODO : save the new buff information into a new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);
		
		JButton btnLoadExistingBuff = new JButton("Load Existing Buff");
		btnLoadExistingBuff.setBounds(5, 48, 143, 29);
		contentPane.add(btnLoadExistingBuff);
		if(localBuff!= null){
			JButton btnRemove = new JButton("REMOVE");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						creature.getBuffer().removeBuff(localBuff);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnRemove.setBounds(310, 11, 114, 62);
			contentPane.add(btnRemove);
		}
		
		
		
		
		
	}
	
	Buff getBuff(){
		return localBuff;
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
}
