package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Buff;
import core.Creature;



public class BuffButton extends JButton {
	ImageIcon goodvisBuffIcon = new ImageIcon("src/gui/images/goodvis.png");
	ImageIcon goodinvisBuffIcon = new ImageIcon("src/gui/images/goodinvis.png");
	ImageIcon badvisBuffIcon = new ImageIcon("src/gui/images/badvis.png");
	ImageIcon badinvisBuffIcon = new ImageIcon("src/gui/images/badinvis.png");
	ImageIcon image;
	
	private static final long serialVersionUID = 1L;
	private Buff associatedBuff;
	
	public BuffButton (Buff buff,Creature creature,MainWindow main,BuffPanel currentBuffPanel){
		
		super();
		associatedBuff=buff;
		
		image = badvisBuffIcon;
		if(buff.isHidden()){
			if(buff.isPositive()){
				image = goodinvisBuffIcon;
			}
			else{
				image = badinvisBuffIcon;
			}
		}
		else{
			if(buff.isPositive()){
				image = goodvisBuffIcon;
			}
		}
		this.setIcon(image);
		//TODO set the right icon and update accordingly
		this.setToolTipText("<html>"+associatedBuff.getName()+"<br>"+associatedBuff.getDescription()+"</html>");
		this.setBuff(associatedBuff);
		this.setBorder(null);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO : 2 cases : item or creature dependent
				NewBuffWindow newbuff = new NewBuffWindow(buff,creature,main);
				newbuff.setVisible(true);
				//panel.setVisible(false);
//				main.setAlwaysOnTop(false);
				newbuff.toFront();
				
				
				newbuff.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e){
						
						if(newbuff.isFinished() && newbuff.isPublic()){
							main.addPublicBuff(newbuff.getBuff());
						}
						currentBuffPanel.update();
						
						// TODO : RETURN NEW BUFF HERE with newbuff
						return;
					}
				});
				
			}
		});
	}
	
	

	public Buff getBuff() {
		return associatedBuff;
	}

	public void setBuff(Buff buff) {
		this.associatedBuff = buff;
	}
}