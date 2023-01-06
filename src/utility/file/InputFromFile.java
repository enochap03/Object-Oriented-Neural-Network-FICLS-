package utility.file;
import java.io.*;  



import layout.NN;


// class parses input file for extracting input, output set for training
public class InputFromFile {

	public static void loadFromCSVFile(String filename, double input[][], double output[][]) {
		String line = "";  
		String splitBy = ",";  
		try   
		{  

			 String path = System.getProperty("user.dir");   
		     System.out.println("Working Directory = " + path);
			BufferedReader br = new BufferedReader(new FileReader(filename));  
			int j = 0;
			while ((line = br.readLine()) != null) //returns a Boolean value  
			{  

				String[] trainingset = line.split(splitBy);   // use comma as separator  
				int i =0;
				while(i<trainingset.length-NN.numOfOutput){

					input[j][i] =  Integer.parseInt(trainingset[i]);
					i++;

				}
				int h = 0;

				while(i<trainingset.length){

					output[j][h] = Integer.parseInt(trainingset[i]);
					h++;
					i++;
				}
				j= j+1;

			} 
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
	}  


}
