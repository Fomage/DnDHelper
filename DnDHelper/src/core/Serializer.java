package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public abstract class Serializer {

	static void save(Serializable o,String path) throws Exception{

		OutputStream file = new FileOutputStream(path);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);

		output.writeObject(o);

		output.close();
		buffer.close();
		file.close();
	}

	static Serializable load(String path) throws Exception{
		InputStream file = new FileInputStream("quarks.ser");
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);

		Serializable o = (Serializable)input.readObject();
		
		input.close();
		buffer.close();
		file.close();
		
		return o;
	}

}
