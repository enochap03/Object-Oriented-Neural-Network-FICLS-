package transfer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import layout.HoldLayoutDetails;


//persistent  class for neural network details
public class SaveLoadWeights {


		public static boolean storeWeighToDesk(HoldLayoutDetails laytdetails, String fileName)  {

			try {

				FileOutputStream fOs = new FileOutputStream(fileName);
				ObjectOutputStream oOs = new ObjectOutputStream(fOs);
				oOs.writeObject(laytdetails);
				
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
				
				FileInputStream fIs = new FileInputStream(fileName);
				ObjectInputStream oIs = new ObjectInputStream(fIs);
				laytdetails = (HoldLayoutDetails) oIs.readObject();
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
