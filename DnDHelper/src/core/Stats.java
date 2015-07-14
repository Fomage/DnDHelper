package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Stats extends Observable implements Serializable, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5505892327683784291L;
	
	//private stuff
	
	private static boolean isStats(int arg[]) throws Exception{
		if(arg.length==6){
			for(int i=0;i<6;i++){
				if(arg[i]<0)
					throw new Exception("Fck u m8 ur array has negative values in Stats Constructor");
			}
		}
		else{throw new Exception("Fck u m8 ur array is not of length 6 in Stats Constructor");}
		return true;
	}
	
	//Attributes
	private int[] stats;
	
	//Public static stuff
	
	/**
	 * Fo=0
	 * Dex=1
	 * Con=2
	 * Int=3
	 * Sag=4
	 * Cha=5
	 */
	public static final int Fo=0;
	public static final int Dex=1;
	public static final int Con=2;
	public static final int Int=3;
	public static final int Sag=4;
	public static final int Cha=5;
	
	/**
	 * @param stat a String that contains the name of a stat (ex : "Fo" or "Dex" etc)
	 * @return the corresponding integer (between 0 and 5)
	 * @throws Exception if the parameter is fucked up.
	 */
	public static int statToInt(String stat) throws Exception{
		switch(stat){
		case "Fo":
			return Fo;
		case "Dex":
			return Dex;
		case "Con":
			return Con;
		case "Int":
			return Int;
		case "Sag":
			return Sag;
		case "Cha":
			return Cha;		
		default:
			throw new Exception("Invalid arg in Stats::statToInt : "+stat);
		}
	}
	
	/**
	 * @param arg an integer between 0 and 5.
	 * @return the corresponding String (ex: "Fo")
	 * @throws Exception if arg is fucked up.
	 */
	public static String statToString(int arg) throws Exception{
		switch(arg){
		case 0:
			return "Fo";
		case 1:
			return "Dex";
		case 2:
			return "Con";
		case 3:
			return "Int";
		case 4:
			return "Sag";
		case 5:
			return "Cha";
		default:
			throw new Exception("Invalid arg in Stats::statToInt : "+arg);
		}
	}
	
	/**
	 * @param arg an int supposedly representing a stat.
	 * @return true if the int represents a stat, false otherwise.
	 */
	public static boolean isAStat(int arg){
		return ((arg>=0) && (arg<6));
	}

	//Constructors
	/**
	 * The basic constructor. Initializes all the stats at 10.
	 */
	public Stats() {
		stats = new int[6];
		for(int i=0;i<6;i++)
			stats[i]=10;
	}
	
	/**
	 * @param arg an array of length 6 containing the values of the stats, in the order : Fo/Dex/Con/Int/Sag/Cha
	 * @throws Exception if arg is fucked up, I'm not happy.
	 */
	public Stats(int[] arg) throws Exception{
		if(isStats(arg)){
			stats=arg.clone();
		}
	}
	
	//Accesseurs
	
	/**
	 * DO NOT USE THIS METHOD I'll delete it later
	 * @return a fucking array ! With the stats inside in the canonical order.
	 */
	public int[] getStats() {
		return stats;
	}

	/**
	 * @param stats : an array of length 6 with positive integer values, representing the stats in the canonical order
	 * @throws Exception if the argument is bad.
	 */
	public void setStats(int[] stats) throws Exception{
		if(isStats(stats))
			this.stats = stats;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @param i integer between 0 and 5
	 * @return the value of the corresponding stat.
	 * @throws Exception if the argument is fucked up.
	 */
	public int getStat(int i) throws Exception{
		if((i>=0) && (i<6)) {
			return stats[i];
		}
		else
			throw new Exception("Invalid arg in Stats::getStat : "+i);
	}
	
	/**
	 * A simple overload of the integer-based accessor.
	 */
	public int getStat(String s) throws Exception{
		return getStat(statToInt(s));
	}
	
	/**
	 * @param stat  the stat u want to change
	 * @param value the new value of the stat
	 * @throws Exception if the arguments are invalid
	 */
	public void setStat(int stat, int value) throws Exception{
		if((stat>=0) && (stat<6)) {
			if(value>=0){
				stats[stat]=value;
				setChanged();
				notifyObservers();
			}
			else
				throw new Exception("Negative argument in Stats::setStat : "+value);
		}
		else
			throw new Exception("Invalid stat argument in Stats::setStat : "+stat);
	}
	
	/**
	 * @param i the stat from which you want to obtain the modifier
	 * @return the modifier of the stat (0 if 10-11, +1 if 12-13,...)
	 * @throws Exception if an argument is invalid
	 */
	public int getMod(int i) throws Exception{
		if(isAStat(i))
			return (getStat(i)-10)/2;
		else
			throw new Exception("Invalid argument in Stats::getMod : "+i);
	}
	
	//Observer
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	


}
