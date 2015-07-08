package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NewBuffWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBuffWindow frame = new NewBuffWindow();
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
	public NewBuffWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		spinner.setBounds(333, 189, 47, 32);
		contentPane.add(spinner);
		
		JToggleButton tglbtnPositiveBuff = new JToggleButton("Positive Buff");
		tglbtnPositiveBuff.setBounds(174, 11, 121, 23);
		contentPane.add(tglbtnPositiveBuff);
		
		JToggleButton tglbtnHiddenBuff = new JToggleButton("Hidden Buff");
		tglbtnHiddenBuff.setBounds(305, 11, 121, 23);
		contentPane.add(tglbtnHiddenBuff);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Skill 1", "Skill 2"}));
		comboBox.setBounds(54, 189, 195, 32);
		contentPane.add(comboBox);
	}
}
