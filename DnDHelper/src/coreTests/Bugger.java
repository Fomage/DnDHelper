package coreTests;

import java.util.Observable;
import java.util.Observer;

public class Bugger implements Observer {
	
	public int count;

	public Bugger() {
		count=0;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		count++;
		System.out.println("Ping "+count);
	}
	
	

}
