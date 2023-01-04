package utility.file;
import java.io.*;  



import layout.NN;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

public class InputFromFile {

	public static void loadFromCSVFile(String filename, double input[][], double output[][]) {
		String line = "";  
		String splitBy = ",";  
		try   
		{  

			 String path = System.getProperty("user.dir");   
		     System.out.println("Working Directory = " + path);
			BufferedReader br = new BufferedReader(new FileReader(filename));  
			//System.out.println(filename);
			int j = 0;
			while ((line = br.readLine()) != null) //returns a Boolean value  
			{  

				String[] trainingset = line.split(splitBy);   // use comma as separator  
				int i =0;
				//System.out.println("output values "+trainingset[trainingset.length-1]);
				while(i<trainingset.length-NN.numOfOutput){

					input[j][i] =  Integer.parseInt(trainingset[i]);
					i++;

				}
				//System.out.println(Double.parseDouble(trainingset[trainingset.length-1]));

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
