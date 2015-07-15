package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.Creature;
import core.Stats;

@SuppressWarnings("serial")
public class NewCreatureWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtCreatureName;
	private boolean finished = false;
	private JPanel[] statPanels;
	private int[] tempStats;
	Creature creature;
	
	public NewCreatureWindow(){
		
		this(new Creature());
		setTitle("New Creature");
	}
	/**
	 * Create the frame.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public NewCreatureWindow(Creature creature) {
		NewCreatureWindow window = this;
		this.creature = creature;
		setTitle("Edit Creature");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 449, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tempStats=creature.getStats().getStats();
		
		
		txtCreatureName = new JTextField();
		txtCreatureName.setToolTipText("Enter a fucking name wtf are you retarded?");
		txtCreatureName.setText(creature.getName());
		txtCreatureName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCreatureName.setColumns(10);
		txtCreatureName.setBounds(20, 11, 143, 32);
//		txtCreatureName.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				window.getCreature().setName(((JTextField)(arg0.getSource())).getText());
//			}
//		});
		contentPane.add(txtCreatureName);
		
		
		JPanel creatureInfo = new JPanel();
		creatureInfo.setBounds(15, 54, 408, 323);
		contentPane.add(creatureInfo);
		creatureInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel creatureStats = new JPanel();
		creatureInfo.add(creatureStats, BorderLayout.WEST);
		creatureStats.setLayout(new BoxLayout(creatureStats, BoxLayout.Y_AXIS));
		
		//DONE creature statpanels
		this.statPanels = createStatPanels();
		for(JPanel statPanel : statPanels){
			creatureStats.add(statPanel);
		}
		
		
		
		JPanel creatureSkills = new JPanel();
		creatureInfo.add(creatureSkills, BorderLayout.EAST);
		creatureSkills.setLayout(new BoxLayout(creatureSkills, BoxLayout.Y_AXIS));
		
		JPanel sampleSkill = new JPanel();
		creatureSkills.add(sampleSkill);
		
		JTextPane txtpnSampleskillname = new JTextPane();
		txtpnSampleskillname.setEditable(false);
		txtpnSampleskillname.setOpaque(false);
		txtpnSampleskillname.setText("sampleSkillName");
		sampleSkill.add(txtpnSampleskillname);
		
		JSpinner skillRankSpinner = new JSpinner();
		sampleSkill.add(skillRankSpinner);
		
		JTextPane txtpnSampleskilltotvalue = new JTextPane();
		txtpnSampleskilltotvalue.setOpaque(false);
		txtpnSampleskilltotvalue.setEditable(false);
		txtpnSampleskilltotvalue.setText("total");
		sampleSkill.add(txtpnSampleskilltotvalue);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		creatureInfo.add(horizontalGlue, BorderLayout.CENTER);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NewCreatureWindow.this.creature.setName(NewCreatureWindow.this.txtCreatureName.getText());
				for(int i = 0; i<6 ; i++){
					try {
						NewCreatureWindow.this.creature.getStats().setStat(i, NewCreatureWindow.this.tempStats[i]);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				finished = true;
				window.dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(279, 401, 144, 62);
		contentPane.add(button);
		
		JTextPane txtpnCharacterDescirption = new JTextPane();
		txtpnCharacterDescirption.setText("Character Description");
		txtpnCharacterDescirption.setBounds(25, 401, 219, 62);
		contentPane.add(txtpnCharacterDescirption);
		
		JButton btnLoadExistingCreature = new JButton("Load Existing Creature");
		btnLoadExistingCreature.setBounds(250, 15, 143, 26);
		contentPane.add(btnLoadExistingCreature);
		}
	
	public boolean isFinished(){
		return finished;
	}
	
	public Creature getCreature(){
		return creature;
	}
	
	
	public JPanel[] createStatPanels(){
		JPanel[] res = new JPanel[6];
		for(int i = 0 ; i <6 ; i++){
		
			JPanel statPanel = new JPanel();
			
			statPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JTextPane txtpnStatName = new JTextPane();
			txtpnStatName.setOpaque(false);
			txtpnStatName.setEditable(false);
			
			try {
				txtpnStatName.setText(Stats.statToString(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fuck Nico (statToString)");
			}
			
			statPanel.add(txtpnStatName);
			
			JTextPane txtpnSamplemodvalue = new JTextPane();
			txtpnSamplemodvalue.setOpaque(false);
			
			class statSpinner extends JSpinner{
				private int stat;
				public statSpinner(int stat){
					super();
					this.stat = stat;
				}
				public int getStat(){
					return stat;
				}
			}
			
			JSpinner statValueSpinner = new statSpinner(i);
			
			try {
				statValueSpinner.setValue(creature.getStats().getStat(i));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			statValueSpinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if((int)statValueSpinner.getValue()<0){
						statValueSpinner.setValue(0);
					}
					try {
						NewCreatureWindow.this.tempStats[((statSpinner) arg0.getSource()).getStat()]=(int) statValueSpinner.getValue();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					try {
						txtpnSamplemodvalue.setText(""+((int)((JSpinner) arg0.getSource()).getValue()-10)/2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			statPanel.add(statValueSpinner);
			try {
				txtpnSamplemodvalue.setText(""+creature.getStats().getMod(((statSpinner)statValueSpinner).getStat()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statPanel.add(txtpnSamplemodvalue);
			res[i] = statPanel;
			
		}
		return res;
	}
	
}


