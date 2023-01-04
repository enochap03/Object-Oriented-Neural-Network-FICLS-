package transfer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import layout.HoldLayoutDetails;



/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

public class SaveLoadWeights {


		public static boolean storeWeighToDesk(HoldLayoutDetails laytdetails, String fileName)  {

			try {

				FileOutputStream fs = new FileOutputStream(fileName);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(laytdetails);
				
			}

			catch(IOException io){
					System.out.println("IO Exception "+io);
				
			}
			catch(Exception e){
				
				System.out.println("Other exception "+e);
			}
			return true;
		
		}
		public static HoldLayoutDetails getWeighFromDesk(String fileName) {
			
			HoldLayoutDetails laytdetails = new HoldLayoutDetails();
			try{
				
				FileInputStream fi = new FileInputStream(fileName);
				ObjectInputStream oi = new ObjectInputStream(fi);
				laytdetails = (HoldLayoutDetails) oi.readObject();
				return laytdetails;
	
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
			return laytdetails;
			
		}
		
}
