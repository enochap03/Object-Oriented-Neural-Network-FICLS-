package utility.file;
import java.io.*;  



import layout.NN;


// class parses input file for extracting input, output set for training
public class InputFromFile {

	public static void loadFromCSVFile(String filename, double input[][], double output[][]) {
		String eachLine = "";  
		String splitByCom = ",";  
		try   
		{  

			 String fullPath = System.getProperty("user.dir");   
		     System.out.println("Current Path = " + fullPath);
			BufferedReader buffRead = new BufferedReader(new FileReader(filename));  
			int jIndex = 0;
			while ((eachLine = buffRead.readLine()) != null) //check for empty string  
			{  

				String[] trainingset = eachLine.split(splitByCom);   // uses comma as a delimiter  
				int iIndex =0;
				// input part separation   
				while(iIndex<trainingset.length-NN.numOfOutput){

					input[jIndex][iIndex] =  Integer.parseInt(trainingset[iIndex]);
					iIndex++;

				}
				int hIndex = 0;
				//output part separation
				while(iIndex<trainingset.length){

					output[jIndex][hIndex] = Integer.parseInt(trainingset[iIndex]);
					hIndex++;
					iIndex++;
				}
				jIndex= jIndex+1;

			} 
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
	}  


}
