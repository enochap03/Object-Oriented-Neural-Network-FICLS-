package corelationcofficient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class GraphDetails {
	
	
	
	private String firstDeltaString;
	
	TreeMap<String,ArrayList<Double>> parameters;
	private int deltaLimit;
	private int conLimit;
	
	private String [] conName = new String[50];
	private  String [] delName = new String[50];
	private  String [] resName = new String[50];

    private ArrayList<Double> [] connections = new ArrayList[50];
    private ArrayList<Double> [] deltas = new ArrayList[50];
    private ArrayList<Double> [] results = new ArrayList[50];
	public GraphDetails(TreeMap<String, ArrayList<Double>> parameters) {
		super();
		this.parameters = parameters;
		
		int cIndex = -1;
		int dIndex = -1;
		int rIndex = -1;
		Iterator hmIterator = parameters.entrySet().iterator(); 
		boolean firstEntry = false;
		while (hmIterator.hasNext()) { 
			Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
			String key = (String)mapElement.getKey();
			ArrayList<Double> value = (ArrayList<Double>)mapElement.getValue();
			//System.out.println("Key :"+key+" value: "+value);	

			if(key.contains("Connection from")) {

				cIndex++;
				getConnections()[cIndex] = new ArrayList<Double>();
				getConnections()[cIndex].addAll(value);
				getConName()[cIndex] = key;
				

			}
			else if(key.contains("RES")) {
				rIndex++;
				getResults()[rIndex] = new ArrayList<Double>();
				getResults()[rIndex].addAll(value);
				getResName()[rIndex] = key;
			}
			//else if(!key.contains("input") &&!key.contains("Connection from")&&!key.contains("RES" )&&key.contains(select)) {
			
			else if(!key.contains("input") &&!key.contains("Connection from")&&!key.contains("RES" )) {
				
				if(!firstEntry) {
					firstEntry = true;
					setFirstDeltaString(key);
				}
				
				dIndex++;
				getDeltas()[dIndex] = new ArrayList<Double>();
				getDeltas()[dIndex].addAll(value);
				getDelName()[dIndex] = key;


			}


		}
		setConLimit(cIndex);
		setDeltaLimit(dIndex);

	}



	public int getDeltaLimit() {
		return deltaLimit;
	}



	public void setDeltaLimit(int deltaLimit) {
		this.deltaLimit = deltaLimit;
	}



	public int getConLimit() {
		return conLimit;
	}



	public void setConLimit(int conLimit) {
		this.conLimit = conLimit;
	}



	public String getFirstDeltaString() {
		return firstDeltaString;
	}



	public void setFirstDeltaString(String firstDeltaString) {
		this.firstDeltaString = firstDeltaString;
	}



	public String[] getConName() {
		return conName;
	}



	public String[] getDelName() {
		return delName;
	}



	public String[] getResName() {
		return resName;
	}



	public ArrayList<Double>[] getConnections() {
		return connections;
	}



	public ArrayList<Double>[] getDeltas() {
		return deltas;
	}



	public ArrayList<Double>[] getResults() {
		return results;
	}
	
	public int getDeltas(String regExpression,String delnames[], double deltas [][]) {
		
		int posMark[] = new int[50];
		int delPos = 0;
		for(int i=0;i<=getDeltaLimit();i++) {
			
			if(Pattern.matches(regExpression, getDelName()[i])) {
				posMark[delPos] = i;
				delPos++;
			}
		}
		for(int j=0;j<delPos;j++) {
			int pos = posMark[j];
			delnames[j] = getDelName()[pos];
			deltas[j] = convertListToArrayDouble(getConnections()[pos]);
			
		}
		return delPos;
	}
	
	public int getConnectionsOfDelta(String expression, String conNames[],double connections [][]) {
		
		int posMark[] = new int[50];
		int num = 0;
		for(int i=0;i<=getConLimit();i++) {
			
			if(getConName()[i].contains("to "+expression)) {
		
				posMark[num] = i;
				num++;
				
			}
		}
		for(int j=0;j<num;j++) {
			int pos = posMark[j];
			conNames[j] = getConName()[pos];
			connections[j] = convertListToArrayDouble(getConnections()[pos]);
			
		}
		return num;
		
	}
	
	public static double []  convertListToArrayDouble(List<Double> dListVls) {
		
		double dValues[] = new double[dListVls.size()];
		for(int i=0;i<dListVls.size();i++) {
			
			dValues[i] = dListVls.get(i);
		}
		return dValues;
		
		
	}

}
