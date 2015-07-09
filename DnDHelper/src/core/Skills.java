package core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Skills extends Observable implements Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4563999116843397047L;
	
	//Attributes
	
	private List<Skill> skills;
	private Stats stats;
	private boolean ignoreNotify;
	
	//Constructors
	/**
	 * Default constructor. Bases itself on a default-generated Stats, and has no skills.
	 */
	public Skills() {
		skills = new LinkedList<Skill>();
		stats = new Stats();
		ignoreNotify=false;
	}
	
	/**
	 * Initially with no skills.
	 * @param stats bases itself on theses stats for rolls.
	 */
	public Skills(Stats stats){
		skills = new LinkedList<Skill>();
		this.stats = stats;
		ignoreNotify=false;
	}
	
	//Refresh
	
	/**
	 * Ensures all the skills rely on the same stats.
	 */
	public void refresh(){
		ignoreNotify=true;
		for(Skill s : skills){
			s.setStats(getStats());
		}
		ignoreNotify=false;
	}
	
	//Accessors

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
		refresh();
		notifyObservers();
	}
	
	public void addSkill(Skill s){
		skills.add(s);
		s.setStats(getStats());
		s.addObserver(this);
	}
	
	/**
	 * @param s a skill
	 * @return true if the skill was removed, false otherwise
	 */
	public boolean removeSkill(Skill s){
		if(containsSkill(s)){
			s.deleteObserver(this);
			return true;
		}
		else
			return false;
	}
	
	public boolean containsSkill(Skill s){
		return skills.contains(s);
	}

	/**
	 * MASSIVE WARNING : THERE IS NO CONST IN JAVA BUT DO NOT MODIFY ANYTHING THIS WAY !!!!!!!!!!!!!!!!!! I MEAN IT !
	 * @return a list containing the skills.... it's a LinkedList...
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	//Observable
	@Override
	public void update(Observable o, Object arg) {
		if(!ignoreNotify){
			notifyObservers();
		}
		//DOT NOT ADD ANYTHING HERE
	}
	
	

}

