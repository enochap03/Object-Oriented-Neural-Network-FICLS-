package utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapUtilityFunctions {

	
public static void removeZeros(TreeMap<String, ArrayList<Double>> parameters) {
		
		String  [] keys = new String[25];
		int eindex = -1;
		Iterator hmIterator = parameters.entrySet().iterator(); 
		while (hmIterator.hasNext()) { 
			Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
			String key = (String)mapElement.getKey();
			ArrayList<Double> value = (ArrayList<Double>)mapElement.getValue();
			if(!checkNonzero(value)) {
				eindex++;
				keys[eindex] = key;
				//parameters.remove(key);
				
			}
			//System.out.println("Key :"+key+" value: "+value);	
		} 
		
		for(int i =0;i<=eindex;i++) {
			parameters.remove(keys[i]);
			
		}
		
	}
	
	static boolean checkNonzero(ArrayList<Double> values) {
		//boolean nonZero=false;
		for(int i=0;i<values.size();i++) {
			if(values.get(i)>0||values.get(i)<0)
				return true;

		}

		return false;
	}
}
