package gui;

import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Buff;
import core.Creature;
import core.Item;
import core.Serializer;
import core.Skill;
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
	private Buff localBuff;
	private Boolean finished = false;
	private JTextPane txtpnDescription;
	private JSpinner spinnerMod;
	private JComboBox<String> statorskillBox;
	private JComboBox<String> skillComboBox;
	private JComboBox<String> statComboBox;
	private JCheckBox chckbxPositiveBuff;
	private JCheckBox chckbxHiddenBuff;
	private JCheckBox chckbxPublicBuff;
	private JComboBox<String> publicCombo;

	/**
	 * Create the frame.
	 */
	public NewBuffWindow(Buff buff, Creature creature, MainWindow main) {
		this.localBuff = buff;
		NewBuffWindow window = this;
		if (buff == null) {
			setTitle("New Buff");
		} else {

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
		txtName.setBounds(5, 5, 203, 32);

		contentPane.add(txtName);
		txtName.setColumns(10);

		txtpnDescription = new JTextPane();

		txtpnDescription.setBounds(5, 92, 419, 86);
		contentPane.add(txtpnDescription);

		spinnerMod = new JSpinner();
		spinnerMod.setBounds(223, 207, 47, 32);
		contentPane.add(spinnerMod);

		statorskillBox = new JComboBox<String>();
		statorskillBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Skill", "Stat" }));
		statorskillBox.setBounds(5, 207, 45, 32);
		contentPane.add(statorskillBox);

		skillComboBox = new JComboBox<String>();
		String[] skills = new String[creature.getSkills().getSkills().size()];
		int c = 0;
		for (Skill skill : creature.getSkills().getSkills()) {
			skills[c] = skill.getName();
			c++;
		}
		skillComboBox.setModel(new DefaultComboBoxModel<String>(skills));
		skillComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(skillComboBox);

		statComboBox = new JComboBox<String>();
		statComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Force", "Dextérité", "Constitution", "Intelligence", "Sagesse", "Charisme" }));
		statComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(statComboBox);

		if (statorskillBox.getSelectedItem() == "Skill") {
			// DONE load the correct skill
			statComboBox.setVisible(false);
		} else {
			skillComboBox.setVisible(false);
		}

		statorskillBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (statorskillBox.getSelectedItem() == "Stat") {
					statComboBox.setVisible(true);
					skillComboBox.setVisible(false);
				} else {
					statComboBox.setVisible(false);
					skillComboBox.setVisible(true);
				}

			}
		});

		chckbxPositiveBuff = new JCheckBox("Positive Buff");
		chckbxPositiveBuff.setBounds(214, 5, 94, 23);
		contentPane.add(chckbxPositiveBuff);

		chckbxHiddenBuff = new JCheckBox("Hidden Buff");
		chckbxHiddenBuff.setBounds(214, 31, 94, 23);
		contentPane.add(chckbxHiddenBuff);

		
		loadBuff(buff);
		JButton btnLoadSavedBuff = new JButton("Saved");
		btnLoadSavedBuff.setBounds(5, 48, 63, 29);
		btnLoadSavedBuff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(".");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Buff Files", "buf");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(main);

				try {
					// System.out.println(chooser.getSelectedFile().getPath());
					if (returnVal ==JFileChooser.APPROVE_OPTION && chooser.getSelectedFile() != null) {
						loadBuff((Buff) Serializer.load(chooser.getSelectedFile().getPath()));
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				// System.out.println(chooser.getSelectedFile().getName());
			}
		});
		contentPane.add(btnLoadSavedBuff);

		publicCombo = new JComboBox<String>();
		List<String> names = new ArrayList<String>();
		for (Buff publicbuff : main.getPublicBuffs()) {
			names.add(publicbuff.getName());
		}
		publicCombo.setModel(new DefaultComboBoxModel<String>(names.toArray(new String[names.size()])));
		publicCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBuff(main.getPublicBuffs().get(publicCombo.getSelectedIndex()));

			}
		});
		publicCombo.setBounds(85, 48, 123, 29);
		publicCombo.setEnabled(false);
		contentPane.add(publicCombo);

		chckbxPublicBuff = new JCheckBox("Public Buff");
		chckbxPublicBuff.setBounds(214, 57, 94, 23);
		if (localBuff != null && names.contains(localBuff.getName())) {
			chckbxPublicBuff.setSelected(true);
			publicCombo.setEnabled(true);
		}
		chckbxPublicBuff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				publicCombo.setEnabled(chckbxPublicBuff.isSelected());

			}
		});
		contentPane.add(chckbxPublicBuff);

		if (localBuff != null) {
			JButton btnRemove = new JButton("REMOVE");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						creature.getBuffer().removeBuff(localBuff);
						window.dispose();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}
			});
			btnRemove.setBounds(310, 11, 114, 62);
			contentPane.add(btnRemove);
		}
		
//		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
//			
//			@Override
//			public boolean dispatchKeyEvent(KeyEvent e) {
//				
//				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
//					window.dispose();
//				}
//				else if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					btnOK.getActionListeners()[0].actionPerformed(null);
//				}
//					
//				
//				return false;
//				
//			}
//		});
		final Action closeWindow = new AbstractAction("closeWindow"){

			/**
			 * 
			 */
			private static final long serialVersionUID = -3485420914415913052L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				window.dispose();
				
			}
			
		};
		closeWindow.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		JButton escape = new JButton(closeWindow);
		
		escape.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"closeWindow");
		escape.getActionMap().put("closeWindow", closeWindow);
		escape.setFocusable(false);
		contentPane.add(escape);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finished = true;
				// creaturePanel.setVisible(true);
				if (localBuff != null) {
					if (statorskillBox.getSelectedIndex()==1  && localBuff instanceof SkillBuff) {
						// TODO : swap Class of Buff
						//System.out.println("Skill to Stat");
						try {
							
							localBuff = ((SkillBuff)localBuff).toStatBuff(0, 0);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					} else if (statorskillBox.getSelectedIndex()==2  && localBuff instanceof StatBuff) {
						try {
							localBuff = ((StatBuff)localBuff).toSkillBuff(creature.getSkills().getSkills().get(0).getName(), 0);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					}
					
					if (localBuff instanceof StatBuff) {
						int stat = Stats.Fo;
						switch ((String) statComboBox.getSelectedItem()) {
						case "Force":
							stat = Stats.Fo;
							break;
						case "Dextérité":
							stat = Stats.Dex;
							break;
						case "Constitution":
							stat = Stats.Con;
							break;
						case "Intelligence":
							stat = Stats.Int;
							break;
						case "Sagesse":
							stat = Stats.Sag;
							break;
						case "Charisme":
							stat = Stats.Cha;
							break;
						}
						try {
							((StatBuff) localBuff).setStat(stat);
							((StatBuff) localBuff).setMod((int) spinnerMod.getValue());
							((StatBuff) localBuff).setName(txtName.getText());
							((StatBuff) localBuff).setHidden(chckbxHiddenBuff.isSelected());
							((StatBuff) localBuff).setPositive(chckbxPositiveBuff.isSelected());
						} catch (Exception e) {

							e.printStackTrace();
						}
					} else {
						try {
							((SkillBuff) localBuff).setSkill((String) skillComboBox.getSelectedItem());
							((SkillBuff) localBuff).setMod((int) spinnerMod.getValue());
							((SkillBuff) localBuff).setName(txtName.getText());
							((SkillBuff) localBuff).setHidden(chckbxHiddenBuff.isSelected());
							((SkillBuff) localBuff).setPositive(chckbxPositiveBuff.isSelected());
						} catch (Exception e) {

							e.printStackTrace();
						}
					
					}
				} else {
					if (statorskillBox.getSelectedItem() == "Stat") {
						int stat = Stats.Fo;
						switch ((String) statComboBox.getSelectedItem()) {
						case "Force":
							stat = Stats.Fo;
							break;
						case "Dextérité":
							stat = Stats.Dex;
							break;
						case "Constitution":
							stat = Stats.Con;
							break;
						case "Intelligence":
							stat = Stats.Int;
							break;
						case "Sagesse":
							stat = Stats.Sag;
							break;
						case "Charisme":
							stat = Stats.Cha;
							break;
						}
						try {
							String desc = txtpnDescription.getText();
							if (desc.equals("Buff Description")) {
								desc = "";
							}
							localBuff = new StatBuff(stat, (int) spinnerMod.getValue(), txtName.getText(),
									chckbxHiddenBuff.isSelected(), chckbxPositiveBuff.isSelected());
							localBuff.setDescription(desc);
							String end = ".buf";
							if(localBuff.getName().endsWith(".buf")){
								end = "";
							}
							Serializer.save(localBuff, "./" + localBuff.getName() + end);
						}

						catch (Exception e) {

							e.printStackTrace();
						}
					} else {
						try {
							String desc = txtpnDescription.getText();
							if (desc.equals("Buff Description")) {
								desc = "";
							}
							localBuff = new SkillBuff((String) skillComboBox.getSelectedItem(),
									(int) spinnerMod.getValue(), txtName.getText(), chckbxHiddenBuff.isSelected(),
									chckbxPositiveBuff.isSelected());
							localBuff.setDescription(desc);
							Serializer.save(localBuff, "./" + localBuff.getName() + ".buf");
						}

						catch (Exception e) {

							e.printStackTrace();
						}
					}
				}
				if (localBuff == null) {
					System.out.println("wtf");
				}
				window.dispose(); // DONE : save the new buff information into a
									// new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);
		
		this.getRootPane().setDefaultButton(btnOK);
		btnOK.requestFocus();

	}

	public NewBuffWindow(Buff buff, Item item, MainWindow main, BuffPanel buffPanel) {
		this.localBuff = buff;
		NewBuffWindow window = this;
		if (buff == null) {
			setTitle("New Buff");
		} else {

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
		txtName.setBounds(5, 5, 203, 32);

		contentPane.add(txtName);
		txtName.setColumns(10);

		txtpnDescription = new JTextPane();

		txtpnDescription.setBounds(5, 92, 419, 86);
		contentPane.add(txtpnDescription);

		spinnerMod = new JSpinner();
		spinnerMod.setBounds(223, 207, 47, 32);
		contentPane.add(spinnerMod);

		statorskillBox = new JComboBox<String>();
		statorskillBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Skill", "Stat" }));
		statorskillBox.setBounds(5, 207, 45, 32);
		contentPane.add(statorskillBox);

		skillComboBox = new JComboBox<String>();
		
		Creature creature = new Creature();
		String[] skills = new String[creature.getSkills().getSkills().size()];
		int c = 0;
		for (Skill skill : creature.getSkills().getSkills()) {
			skills[c] = skill.getName();
			c++;
		}
		skillComboBox.setModel(new DefaultComboBoxModel<String>(skills));
		skillComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(skillComboBox);

		statComboBox = new JComboBox<String>();
		statComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Force", "Dextérité", "Constitution", "Intelligence", "Sagesse", "Charisme" }));
		statComboBox.setBounds(60, 207, 153, 32);
		contentPane.add(statComboBox);

		if (statorskillBox.getSelectedItem() == "Skill") {
			// DONE load the correct skill
			statComboBox.setVisible(false);
		} else {
			skillComboBox.setVisible(false);
		}

		statorskillBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (statorskillBox.getSelectedItem() == "Stat") {
					statComboBox.setVisible(true);
					skillComboBox.setVisible(false);
				} else {
					statComboBox.setVisible(false);
					skillComboBox.setVisible(true);
				}

			}
		});

		chckbxPositiveBuff = new JCheckBox("Positive Buff");
		chckbxPositiveBuff.setBounds(214, 5, 94, 23);
		contentPane.add(chckbxPositiveBuff);

		chckbxHiddenBuff = new JCheckBox("Hidden Buff");
		chckbxHiddenBuff.setBounds(214, 31, 94, 23);
		contentPane.add(chckbxHiddenBuff);

		loadBuff(buff);
		JButton btnLoadSavedBuff = new JButton("Saved");
		btnLoadSavedBuff.setBounds(5, 48, 63, 29);
		btnLoadSavedBuff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(".");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Buff Files", "buf");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(main);

				try {
					// System.out.println(chooser.getSelectedFile().getPath());

					if (returnVal ==JFileChooser.APPROVE_OPTION && chooser.getSelectedFile() != null) {
						loadBuff((Buff) Serializer.load(chooser.getSelectedFile().getPath()));
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}

				// System.out.println(chooser.getSelectedFile().getName());
			}
		});
		contentPane.add(btnLoadSavedBuff);

		publicCombo = new JComboBox<String>();
		List<String> names = new ArrayList<String>();
		for (Buff publicbuff : main.getPublicBuffs()) {
			names.add(publicbuff.getName());
		}
		publicCombo.setModel(new DefaultComboBoxModel<String>(names.toArray(new String[names.size()])));
		publicCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadBuff(main.getPublicBuffs().get(publicCombo.getSelectedIndex()));

			}
		});
		publicCombo.setBounds(85, 48, 123, 29);
		publicCombo.setEnabled(false);
		contentPane.add(publicCombo);

		// chckbxPublicBuff = new JCheckBox("Public Buff");
		// chckbxPublicBuff.setBounds(214, 57, 94, 23);
		// if(localBuff!=null && names.contains(localBuff.getName())){
		// chckbxPublicBuff.setSelected(true);
		// publicCombo.setEnabled(true);
		// }
		// chckbxPublicBuff.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// publicCombo.setEnabled(chckbxPublicBuff.isSelected());
		//
		// }
		// });
		// contentPane.add(chckbxPublicBuff);

		if (localBuff != null) {
			JButton btnRemove = new JButton("REMOVE");
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						buffPanel.removeFromBuffer(localBuff);
						buffPanel.update();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					window.dispose();
				}
			});
			btnRemove.setBounds(310, 11, 114, 62);
			contentPane.add(btnRemove);
		}
		
		final Action closeWindow = new AbstractAction("closeWindow"){

			/**
			 * 
			 */
			private static final long serialVersionUID = -3485420914415913052L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				window.dispose();
				
			}
			
		};
		closeWindow.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		JButton escape = new JButton(closeWindow);
		
		escape.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"closeWindow");
		escape.getActionMap().put("closeWindow", closeWindow);
		escape.setFocusable(false);
		contentPane.add(escape);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finished = true;
				// creaturePanel.setVisible(true);
				if (localBuff != null) {
					if (statorskillBox.getSelectedIndex()==1  && localBuff instanceof SkillBuff) {
						// TODO : swap Class of Buff
						//System.out.println("Skill to Stat");
						try {
							
							localBuff = ((SkillBuff)localBuff).toStatBuff(0, 0);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					} else if (statorskillBox.getSelectedIndex()==2  && localBuff instanceof StatBuff) {
						try {
							localBuff = ((StatBuff)localBuff).toSkillBuff(creature.getSkills().getSkills().get(0).getName(), 0);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					}
					
					if (localBuff instanceof StatBuff) {
						int stat = Stats.Fo;
						switch ((String) statComboBox.getSelectedItem()) {
						case "Force":
							stat = Stats.Fo;
							break;
						case "Dextérité":
							stat = Stats.Dex;
							break;
						case "Constitution":
							stat = Stats.Con;
							break;
						case "Intelligence":
							stat = Stats.Int;
							break;
						case "Sagesse":
							stat = Stats.Sag;
							break;
						case "Charisme":
							stat = Stats.Cha;
							break;
						}
						try {
							((StatBuff) localBuff).setStat(stat);
							((StatBuff) localBuff).setMod((int) spinnerMod.getValue());
							((StatBuff) localBuff).setName(txtName.getText());
							((StatBuff) localBuff).setHidden(chckbxHiddenBuff.isSelected());
							((StatBuff) localBuff).setPositive(chckbxPositiveBuff.isSelected());
						} catch (Exception e) {

							e.printStackTrace();
						}
					} else {
						try {
							((SkillBuff) localBuff).setSkill((String) skillComboBox.getSelectedItem());
							((SkillBuff) localBuff).setMod((int) spinnerMod.getValue());
							((SkillBuff) localBuff).setName(txtName.getText());
							((SkillBuff) localBuff).setHidden(chckbxHiddenBuff.isSelected());
							((SkillBuff) localBuff).setPositive(chckbxPositiveBuff.isSelected());
						} catch (Exception e) {

							e.printStackTrace();
						}
					
					}
				} else {
					if (statorskillBox.getSelectedItem() == "Stat") {
						int stat = Stats.Fo;
						switch ((String) statComboBox.getSelectedItem()) {
						case "Force":
							stat = Stats.Fo;
							break;
						case "Dextérité":
							stat = Stats.Dex;
							break;
						case "Constitution":
							stat = Stats.Con;
							break;
						case "Intelligence":
							stat = Stats.Int;
							break;
						case "Sagesse":
							stat = Stats.Sag;
							break;
						case "Charisme":
							stat = Stats.Cha;
							break;
						}
						try {
							String desc = txtpnDescription.getText();
							if (desc.equals("Buff Description")) {
								desc = "";
							}
							localBuff = new StatBuff(stat, (int) spinnerMod.getValue(), txtName.getText(),
									chckbxHiddenBuff.isSelected(), chckbxPositiveBuff.isSelected());
							localBuff.setDescription(desc);
							String end = ".buf";
							if(localBuff.getName().endsWith(".buf")){
								end = "";
							}
							Serializer.save(localBuff, "./" + localBuff.getName() + end);
						}

						catch (Exception e) {

							e.printStackTrace();
						}
					} else {
						try {
							String desc = txtpnDescription.getText();
							if (desc.equals("Buff Description")) {
								desc = "";
							}
							localBuff = new SkillBuff((String) skillComboBox.getSelectedItem(),
									(int) spinnerMod.getValue(), txtName.getText(), chckbxHiddenBuff.isSelected(),
									chckbxPositiveBuff.isSelected());
							localBuff.setDescription(desc);
							Serializer.save(localBuff, "./" + localBuff.getName() + ".buf");
						}

						catch (Exception e) {

							e.printStackTrace();
						}
					}
				}
				if (localBuff == null) {
					System.out.println("wtf");
				}
				window.dispose(); // DONE : save the new buff information into a
									// new buff
			}
		});
		btnOK.setBounds(280, 189, 144, 62);
		contentPane.add(btnOK);
		
		this.getRootPane().setDefaultButton(btnOK);
		btnOK.requestFocus();
		
		
		
		
		
		
		
//		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
//			
//			@Override
//			public boolean dispatchKeyEvent(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
//					window.dispose();
//				}
//				else if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					btnOK.getActionListeners()[0].actionPerformed(null);
//				}
//				return false;
//			}
//		});
	}

	private void loadBuff(Buff buff) {
		this.localBuff = buff;
		if (buff == null) {
			txtName.setText("Buff Name");
			txtpnDescription.setText("Buff Description");
		} else {
			txtName.setText(buff.getName());
			txtpnDescription.setText(buff.getDescription());
			if (buff instanceof StatBuff) {
				statComboBox.setSelectedIndex(((StatBuff) buff).getStat());
				spinnerMod.setValue(((StatBuff) buff).getMod());
				statorskillBox.setSelectedIndex(1);
			}
			if (buff instanceof SkillBuff) {
				skillComboBox.setSelectedItem(((SkillBuff) buff).getSkill());
				spinnerMod.setValue(((SkillBuff) buff).getMod());
			}
			chckbxPositiveBuff.setSelected(buff.isPositive());
			chckbxHiddenBuff.setSelected(buff.isHidden());

		}

	}

	public Boolean isPublic() {

		return chckbxPublicBuff != null && chckbxPublicBuff.isSelected();
	}

	public Buff getBuff() {
		return localBuff;
	}

	public boolean isFinished() {

		return finished;
	}
}
