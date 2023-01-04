package plot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ObtainOutputValues {
	
	
	public static String[][] getOutputValues(String fileName, int numOutput){


		String line = "";  
		String splitBy = ",";  
		String arr[][] =new String[numOutput][4] ;
		try   
		{  

			String path = System.getProperty("user.dir");   
			System.out.println("Working Directory = " + path);
			BufferedReader br = new BufferedReader(new FileReader(fileName));  
			int i = 0;

			while ((line = br.readLine()) != null) //returns a Boolean value  
			{  

				String[] trainingset = line.split(splitBy); 
				for(int j=0;j<numOutput;j++) {
					arr[j][i] = trainingset[trainingset.length-(numOutput-j)];
				}
				// use comma as separator  
				i++;
				//System.out.println("output values "+trainingset[trainingset.length-1]);


			} 

		}   

		catch (IOException e)   
		{  
			e.printStackTrace();  
		}
		
		return  arr;
	} 
}


