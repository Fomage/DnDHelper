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
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class NewBuffWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319463507363235057L;
	private JPanel contentPane;
	private JTextField txtName;
	@SuppressWarnings("unused")
	private CreaturePanel creaturePanel;


	/**
	 * Create the frame.
	 */
	public NewBuffWindow(CreaturePanel creaturePanel) {
		this.creaturePanel = creaturePanel;
		NewBuffWindow window = this;
		setTitle("New Buff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setToolTipText("Enter a fucking name wtf are you retarded?");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setBounds(5, 5, 143, 32);
		txtName.setText("Buff Name");
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setText("Buff Description");
		txtpnDescription.setBounds(5, 60, 419, 86);
		contentPane.add(txtpnDescription);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(207, 189, 47, 32);
		contentPane.add(spinner);
		
		JToggleButton tglbtnPositiveBuff = new JToggleButton("Positive Buff");
		tglbtnPositiveBuff.setBounds(174, 11, 121, 23);
		contentPane.add(tglbtnPositiveBuff);
		
		JToggleButton tglbtnHiddenBuff = new JToggleButton("Hidden Buff");
		tglbtnHiddenBuff.setBounds(305, 11, 121, 23);
		contentPane.add(tglbtnHiddenBuff);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Skill 1", "Skill 2"}));
		comboBox.setBounds(5, 189, 195, 32);
		contentPane.add(comboBox);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//creaturePanel.setVisible(true);
				window.dispose(); // TODO : save the new buff information into a new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);
	}
}
