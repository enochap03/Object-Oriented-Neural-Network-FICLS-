package plot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;


public class SaveLoadPlotDetails {
	

	public static boolean storePlotDetailsToDesk(ProcessPlot plotdetails, String fileName)  {

		try {

			plotdetails.setDateTime(new Date());
			FileOutputStream fs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(plotdetails);
			
		}

		catch(IOException io){
				System.out.println("IO Exception "+io);
			
		}
		catch(Exception e){
			
			System.out.println("Other exception "+e);
		}
		return true;
	
	}
	public static ProcessPlot getPlotDetailsFromDesk(String fileName) {
		
		ProcessPlot potdetails = null;	
		try{
			
			FileInputStream fi = new FileInputStream(fileName);
		
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			potdetails = (ProcessPlot) oi.readObject();
			

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
		return potdetails;
		
	}

}
