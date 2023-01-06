package implayout;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;

import corelationcofficient.ComputeCorrelationCofficient;
import corelationcofficient.GraphDetails;
import plot.SaveLoadPlotParametersDetails;
import layout.ExtendedLayerFunction;
import layout.NN;

import neuralnetworks.properies.ConstantsAndConventions;
import neuralnetworks.properies.NeuralNetworkDispatcher;
import neuralnetworks.properies.NeuralNetworkSequenceAndProperties;
import utility.HashMapUtilityFunctions;
import utility.file.InputFromFile;


// Train class is used to train the neural network also  contains section for selection of neural network  
 
public class Train {

	
	static FileReader reader;   
	static double inArray[][] ;
	static double otArray[][] ;
	static String weightfile;
	static String neuralNetworkName;
	static String graphParameter; 
	static String businessLoss;
	static String substring ="yes";
	static String subChar ="y";
	static HashMap<String,String> properties;
	
	//constants
	 public static final String from="from";
	
	// create instances of primitive neural network operation for ANN
 static BaseMethods  bsExSeq = new  ImpBaseMethodsForAnn();

// sets neural network hyperparameters
	public static  void setHyperParameters(int bSize, double lrate,int ephocs, int saveAfterEvery ) {
		NN.setTrainingSize(bSize);
		NN.setLrate(lrate);
		NN.setEphocs(ephocs);
		NN.setSaveAfterEvery(saveAfterEvery);
		
	}
	 // load input and actual output from dataset file for testing
	private static void  loadData(String fileName){
		
		 inArray = new double [NN.getTrainingSize()][NN.getNumOfInput()];
		 otArray = new double [NN.getTrainingSize()][NN.getNumOfOutput()]; 
		 InputFromFile.loadFromCSVFile(fileName,inArray,otArray);
		 bsExSeq.setInputOutputData(inArray, otArray);

	}
	//  gets neural network configuration from neuralnetwork.properties file and starts the training
	public static void main(String args[]) throws IOException{
		
		reader = new FileReader("neuralnetwork.properties"); 
		Properties p=new Properties();
		p.load(reader); 
		String learngRate  = p.getProperty("learning_rate");
		double lrate = Double.parseDouble(learngRate);
		String epochs = p.getProperty("epochs");
		int ephocs = Integer.parseInt(epochs);
		String batchSize = p.getProperty("batch_size");
		int bsize = Integer.parseInt(batchSize);
		String saveAfterEverhy = p.getProperty("save_after_every");
		int saveAfter = Integer.parseInt(saveAfterEverhy);
		neuralNetworkName = p.getProperty("neural_network_name");
		businessLoss  = p.getProperty("business_loss");
		NeuralNetworkDispatcher nnd = new NeuralNetworkDispatcher();
		NeuralNetworkSequenceAndProperties nns = nnd.getNeuralNetwork(neuralNetworkName);
		properties = nns.getProperties();
		setHyperParameters(bsize,lrate,ephocs,saveAfter);
		System.out.println("Training started");
		bsExSeq.establishNn(nns.getSequence());
		loadData(properties.get(ConstantsAndConventions.inputFileName));
		train();
	}
	
	// saves neural network instances by making using of primitive instances of neural network operation for ANN
	private static void saveNeuralNetworkInstances(TreeMap<String, ArrayList<Double>> parameters) {


		for(int j=0;j<NN.trainingSize;j++) {
			bsExSeq.initializeInput(j);
			bsExSeq.forwardPass(j,properties.get(ConstantsAndConventions.outputProperties));
			bsExSeq.saveNeuralNetworkInstances(parameters,j);

		}
	}
	
	// after configuration of hyperparameter trains starts from this method
	private static void train(){

		try {
			
			final DecimalFormat df = new DecimalFormat("0.00");
			TreeMap<String, ArrayList<Double>> graphParameters = new TreeMap<String, ArrayList<Double>>(new DescOrder());
			ArrayList<Double> input = new ArrayList<Double>();
			graphParameters.put("input", input);
			boolean includeBusinessloss = false;
			 if(businessLoss.toLowerCase().contains(substring)||businessLoss.toLowerCase().contains(subChar))
				 includeBusinessloss = true;
			 // actual training begins with ephocs 
			for (int k=0;k<NN.getEphocs();k++){
				
				if(k==0) {
					
					
					input.add((double) NN.count);
					saveNeuralNetworkInstances(graphParameters);
				}
				
				
				for(int j=0;j<NN.trainingSize;j++) {

					bsExSeq.initializeInput(j);
					bsExSeq.forwardPass(j,properties.get(ConstantsAndConventions.outputProperties));
					bsExSeq.backwardPass(j,includeBusinessloss);
				
				}
				NN.count++;
				
					
				if(NN.count%NN.getSaveAfterEvery()==0) {
					
					
				
					input.add((double) NN.count);
					saveNeuralNetworkInstances(graphParameters);
					
					if(includeBusinessloss) {
						for(int i =0;i<NN.trainingSize;i++) {

							double inputData[] = bsExSeq.initializeInputWithReturn(i);
							bsExSeq.forwardPass(i,properties.get(ConstantsAndConventions.outputProperties));
							ExtendedLayerFunction exlfun = bsExSeq.getExtendedLayerFunction();
							exlfun.assertBusinessRule((int)inputData[0], (int)inputData[1],properties.get(ConstantsAndConventions.outputProperties),k+1);

						}
						System.out.println("-------------------------------------------------------------------------------------------------------------------------");
					}
				}
				bsExSeq.updateWeights();
			}
			// training ends
			
			
			// section for storing trained weights into file
			boolean status  = false;
			if(includeBusinessloss)
				status = bsExSeq.saveWeightMatrix(properties.get(ConstantsAndConventions.weighMatrixWithBusinessLoss));
			else 
				status = bsExSeq.saveWeightMatrix(properties.get(ConstantsAndConventions.weighMatrix));
			System.out.println("Saved "+status);
			
			// removes redundant  and zero's from the HashMap
			HashMapUtilityFunctions.removeZeros(graphParameters);
			Iterator hmIterator = graphParameters.entrySet().iterator(); 
			
			// stores the saved plot details into persistent storage
			SaveLoadPlotParametersDetails spd = new SaveLoadPlotParametersDetails();
			boolean gsave = false;
			if(includeBusinessloss)
				gsave = spd.storePlotDetailsToDesk(graphParameters, properties.get(ConstantsAndConventions.graphParametersWithBusinessLoss));
			else
				gsave = spd.storePlotDetailsToDesk(graphParameters, properties.get(ConstantsAndConventions.graphParameters));
			if(gsave)
				System.out.println("Graph details saved sucessfully");
			
			// section begins for formating the output and displaying in GUI Swing frame
			
			GraphDetails gdetails = new GraphDetails(graphParameters);
			  
			String firstString = gdetails.getFirstDeltaString();
			
			int  index = Integer.parseInt(firstString.substring(firstString.indexOf(ConstantsAndConventions.prefixN)+1,firstString.indexOf(",")));
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
				String compileExpression = "[Nn]"+X+"+,[1-9]"; // regular expression to obtain node in order
				double deltas[][] = new double[25][5000] ;
				String delnames[] = new String[25];
				int num = gdetails.getDeltas(compileExpression,delnames,deltas); // obtain node loss and its corresponding connection weight
				
				for(int i=0;i<num;i++)  {
					
					double connections[][] = new double[50][5000];
					String conNames[] = new String[50];
					System.out.println(delnames[i]);
					int conNum = gdetails.getConnectionsOfDelta(delnames[i],conNames,connections);
					
					String headRow [] = new String[4];
					String valRow [] = new String[4];
					for(int j=0;j<conNum;j++) {
						if(j==0) {
							headRow[0] = delnames[i];
							valRow[0] = "";
						}
						double corr = ComputeCorrelationCofficient.computeCorrelationCofficient(deltas[i], connections[j], 0);
						System.out.println(conNames[j]+"   Correlation cofficient ->  "+df.format(corr)); // heading
						String str = conNames[j].substring(conNames[j].lastIndexOf(from)+from.length(),conNames[j].length()); // 
						String[] nodes = str.split(splitBy);
						headRow[j+1] = nodes[0];
						valRow[j+1] = df.format(corr);
					
					}
					
					// formating
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
			String res [] = TestResultFormation.getResult();
			
			
			        String[] columnNames = { "Nodes", "", "Connections from", "" };
			new Tabulation(data,columnNames, size,res); 

		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	} 


	
	
	
	
	
	}
		
	

