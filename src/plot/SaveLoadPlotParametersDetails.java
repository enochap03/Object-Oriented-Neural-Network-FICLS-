package plot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// persistent  class for graphs details
public class SaveLoadPlotParametersDetails {
	

	public static boolean storePlotDetailsToDesk(TreeMap<String,ArrayList<Double>> parameters, String fileName)  {

		try {

			
			FileOutputStream fs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(parameters);
			
		}

		catch(IOException io){
				System.out.println("IO Exception "+io);
			
		}
		catch(Exception e){
			
			System.out.println("Other exception "+e);
		}
		return true;
	
	}
	public static TreeMap<String,ArrayList<Double>> getPlotDetailsFromDesk(String fileName) {
		
		TreeMap<String,ArrayList<Double>> parameters = null;	
		try{
			
			FileInputStream fi = new FileInputStream(fileName);
		
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			parameters = (TreeMap <String,ArrayList<Double>>) oi.readObject();
			

		}
		catch (ClassNotFoundException cn) {
			
			System.out.println("Class not found "+cn);
		}

		catch(IOException io){
			
			System.out.println("IO Exception"+ io);
		}
		
		catch (Exception e) {
			
			System.out.println("Other Exception "+e);
		}
		return parameters;
		
	}

}
