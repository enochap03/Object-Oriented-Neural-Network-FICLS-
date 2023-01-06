package corelationcofficient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;
import implayout.Tabulation;
import implayout.TestResultFormation;
import plot.SaveLoadPlotParametersDetails;

public class TestCorelationCofficient {
	
	
	static String FileName ="XorXnor";
	static String weightName="Weights";
	static String graphName="Graph";
	static String displacement="V10";
	static String compWeightName=FileName+weightName+displacement;
	static String compGraphName=FileName+graphName+displacement;
	
	
	public static void main(String args[]) throws IOException {
		
		
		final DecimalFormat df = new DecimalFormat("0.00");
		TreeMap<String,ArrayList<Double>> parameters = SaveLoadPlotParametersDetails.getPlotDetailsFromDesk(compGraphName);
		
		
		GraphDetails gdetails = new GraphDetails(parameters);
	  
		String firstString = gdetails.getFirstDeltaString();
		
		int  index = Integer.parseInt(firstString.substring(firstString.indexOf("N")+1,firstString.indexOf(",")));
		String emptyRow[]   =   { "", "", "", "" };
		String emptyRow1[]   =   { "   ", "   ", "  ", "  " };
		int x = index; int size = 200;
		String splitBy ="to";
		if(index>3)
			size = 350;
		else if(index>2)
			size = 250;
		else if(index >1)
			size = 200;
		String data[][] = new String[25][4];
		int position = 0;
		while(x>1 ) {
			String X = String.valueOf(x);
			String compileExpression = "[Nn]"+X+"+,[1-9]";
			double deltas[][] = new double[25][5000] ;
			String delnames[] = new String[25];
			int num = gdetails.getDeltas(compileExpression,delnames,deltas);
			//System.out.println(deltas);
			//System.out.println(num);
			for(int i=0;i<num;i++)  {
				
				double connections[][] = new double[50][5000];
				String conNames[] = new String[50];
				System.out.println(delnames[i]);
				int conNum = gdetails.getConnectionsOfDelta(delnames[i],conNames,connections);
				
				String headRow [] = new String[4];
				String valRow [] = new String[4];
				for(int j=0;j<conNum;j++) {
					
					//double corr = ComputeCorrelationCofficient.computeCorrelationCofficient(deltas[i], connections[j], getConvergingPosition(connections[j]));
					if(j==0) {
						headRow[0] = delnames[i];
						valRow[0] = "";
					}
					double corr = ComputeCorrelationCofficient.computeCorrelationCofficient(deltas[i], connections[j], 0);
					System.out.println(conNames[j]+"   Correlation cofficient ->  "+df.format(corr));
					String str = conNames[j].substring(conNames[j].lastIndexOf("from")+"from".length(),conNames[j].length());
					//System.out.println(str);
					String[] nodes = str.split(splitBy);
					headRow[j+1] = nodes[0];
					valRow[j+1] = df.format(corr);
				
				}
				
				data[position] = headRow;
				data[position+1] = valRow;
				data[position+2] = emptyRow;
				position = position +3;
				
				
			}

			x--;
		}
		if(index==1) {
			for(int i=0;i<4;i++) {
				data[position] = emptyRow1;
				data[position+1] = emptyRow1;
				data[position+2] = emptyRow1;
				position = position +3;
			}

		}
		String res [] = TestResultFormation.getResult(compWeightName);
		
		
		        String[] columnNames = { "Nodes", "", "Connections from", "" };
		new Tabulation(data,columnNames, size,res);
		
	}

}
