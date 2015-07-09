package core;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Item extends Observable implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2147826682088213346L;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
