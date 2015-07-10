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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;

public class NewCreatureWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemName;
	private boolean finished = false;

	
	/**
	 * Create the frame.
	 */
	public NewCreatureWindow() {
		NewCreatureWindow window = this;
		setTitle("Creature");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 449, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtItemName = new JTextField();
		txtItemName.setToolTipText("Enter a fucking name wtf are you retarded?");
		txtItemName.setText("Creature Name");
		txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtItemName.setColumns(10);
		txtItemName.setBounds(20, 11, 143, 32);
		contentPane.add(txtItemName);
		
		JPanel creatureInfo = new JPanel();
		creatureInfo.setBounds(15, 54, 408, 323);
		contentPane.add(creatureInfo);
		creatureInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel creatureStats = new JPanel();
		creatureInfo.add(creatureStats, BorderLayout.WEST);
		creatureStats.setLayout(new BoxLayout(creatureStats, BoxLayout.Y_AXIS));
		
		JPanel sampleStat = new JPanel();
		creatureStats.add(sampleStat);
		sampleStat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextPane txtpnSamplestatname = new JTextPane();
		txtpnSamplestatname.setOpaque(false);
		txtpnSamplestatname.setEditable(false);
		txtpnSamplestatname.setText("sampleStatName");
		sampleStat.add(txtpnSamplestatname);
		
		JSpinner statValueSpinner = new JSpinner();
		sampleStat.add(statValueSpinner);
		
		JTextPane txtpnSamplemodvalue = new JTextPane();
		txtpnSamplemodvalue.setOpaque(false);
		txtpnSamplemodvalue.setText("mod");
		sampleStat.add(txtpnSamplemodvalue);
		
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
	}


