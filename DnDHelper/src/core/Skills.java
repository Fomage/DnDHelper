package core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Collections;

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
	 * Initially with basic skills.
	 * @param stats bases itself on theses stats for rolls.
	 */
	public Skills(Stats stats){
		skills = new LinkedList<Skill>();
		this.stats = stats;
		ignoreNotify=false;
		try{buildBasicSkills();}catch(Exception e){}
	}
	
	//buildBasicSKills
	
	/**
	 * Add the following skills with a default value of -4 :
	 * Art de la magie
	 * Bluff
	 * Déplacement silencieux
	 * Détection
	 * Diplomatie
	 * Discrétion
	 * Equilibre
	 * Fouille
	 * Intimidation
	 * Perception auditive
	 * Psychologie
	 * Surive
	 */
	public void buildBasicSkills() throws Exception{
		Stats bidon = new Stats();
		
		addSkill(new Skill(-4,Stats.Int,bidon,"Art de la magie"));
		addSkill(new Skill(-4,Stats.Cha,bidon,"Bluff"));
		addSkill(new Skill(-4,Stats.Dex,bidon,"Déplacement silencieux"));
		addSkill(new Skill(-4,Stats.Sag,bidon,"Détection"));
		addSkill(new Skill(-4,Stats.Cha,bidon,"Diplomatie"));
		addSkill(new Skill(-4,Stats.Dex,bidon,"Discretion"));
		addSkill(new Skill(-4,Stats.Dex,bidon,"Equilibre"));
		addSkill(new Skill(-4,Stats.Dex,bidon,"Fouille"));
		addSkill(new Skill(-4,Stats.Cha,bidon,"Intimidation"));
		addSkill(new Skill(-4,Stats.Sag,bidon,"Perception auditive"));
		addSkill(new Skill(-4,Stats.Sag,bidon,"Psychologie"));
		addSkill(new Skill(-4,Stats.Sag,bidon,"Survie"));
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
		setChanged();
		notifyObservers();
	}
	
	public void addSkill(Skill s){
		skills.add(s);
		s.setStats(getStats());
		s.addObserver(this);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @param s a skill
	 * @return true if the skill was removed, false otherwise
	 */
	public boolean removeSkill(Skill s){
		if(containsSkill(s)){
			skills.remove(s);
			s.deleteObserver(this);
			setChanged();
			notifyObservers();
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
		return Collections.unmodifiableList(skills);
	}
	
	public Skill getSkill(String s) throws Exception{
		for(int i=0;i<skills.size();i++){
			if(skills.get(i).getName().equals(s))
				return skills.get(i);
		}
		throw new Exception("Skill "+s+" unfound in Skills::getSkill");
	}

	//Observable
	@Override
	public void update(Observable o, Object arg) {
		if(!ignoreNotify){
			setChanged();
			notifyObservers();
		}
		//DOT NOT ADD ANYTHING HERE
	}
	
	public void restoreObservable(){
		for(Skill s : skills){
			s.addObserver(this);
			s.restoreObservable();
		}
		stats.addObserver(this);
	}

}

