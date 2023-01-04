package corelationcofficient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import layout.NN;

public class LoadArray {

	public static double[] loadFromCSVFile(String name) {
		String line = "";  
		String splitBy = ",";  
		 double[] arr = null;

		List<Double> array = new ArrayList<Double>();
		try   
		{  

			 String path = System.getProperty("user.dir");   
		     System.out.println("Working Directory = " + path);
			BufferedReader br = new BufferedReader(new FileReader(name));  
			int j = 0;
			while ((line = br.readLine()) != null) //returns a Boolean value  
			{  

				String[] trainingset = line.split(splitBy);   // use comma as separator  
				//System.out.println(trainingset);
				int i =0;
				while(i<trainingset.length){
					array.add(Double.parseDouble(trainingset[i]));
					i++;

				}
				
			} 
			arr = new double[array.size()];
			// ArrayList to Array Conversion
			for (int i = 0; i < array.size(); i++)
				arr[i] = array.get(i);

		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}
		return arr;
	}  
}
