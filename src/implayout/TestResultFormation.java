package implayout;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Properties;



import layout.NN;
import neuralnetworks.properies.ConstantsAndConventions;
import neuralnetworks.properies.NeuralNetworkDispatcher;
import neuralnetworks.properies.NeuralNetworkSequenceAndProperties;
import utility.file.InputFromFile;


// class formats  the neural network output(decimal, spacing , order etc.,) for GUI (swing frames)
public class TestResultFormation {
	static FileReader reader;   
	static NN network = new NN();
	static double inArray[][] ;
	static double otArray[][] ;
	static double predOtArrray[][];
	static String weightfile;
	static String neuralNetworkName;
	static String substring ="yes";
	static String subChar ="y";
 static BaseMethods  bsExSeq = new  ImpBaseMethodsForAnn();


 // load input and actual output for testing from the data set file
	private static void  loadData(String fileName){
		
		 inArray = new double [NN.getTrainingSize()][NN.getNumOfInput()];
		 otArray = new double [NN.getTrainingSize()][NN.getNumOfOutput()]; 
		 //input file name for training
		 InputFromFile.loadFromCSVFile(fileName,inArray,otArray);
		 bsExSeq.setInputOutputData(inArray, otArray);

	}
	// loads and sets training configuration from the neuralnetowrk.properties file and examines the trained neural network result
	public static String[] getResult() throws IOException{
		
		reader = new FileReader("neuralnetwork.properties"); 
		Properties p=new Properties();
		p.load(reader); 
		String neuralNetworkName = p.getProperty("neural_network_name");
		String batchSize = p.getProperty("batch_size");
		
		String businessLoss = p.getProperty("business_loss");
		NeuralNetworkDispatcher nnd = new NeuralNetworkDispatcher();
		NeuralNetworkSequenceAndProperties nns = nnd.getNeuralNetwork(neuralNetworkName);
		HashMap<String,String>  properties = nns.getProperties();
		
		String fileName = properties.get(ConstantsAndConventions.inputFileName);
		
		 if(businessLoss.toLowerCase().contains(substring)||businessLoss.toLowerCase().contains(subChar))
			 weightfile = properties.get(ConstantsAndConventions.weighMatrixWithBusinessLoss);
		 else 
			 weightfile = properties.get(ConstantsAndConventions.weighMatrix);
		
		int bsize = Integer.parseInt(batchSize);
		NN.setTrainingSize(bsize);
		bsExSeq.loadWeightMatrix(weightfile);
		loadData(fileName);
		return test();
	}
	
	//  examines the trained neural network result for the specified trained file
	public static String[] getResult(String FileNameWeight) throws IOException{
		
		reader = new FileReader("neuralnetwork.properties"); 
		Properties p=new Properties();
		p.load(reader); 
		String neuralNetworkName = p.getProperty("neural_network_name");
		String batchSize = p.getProperty("batch_size");
		NeuralNetworkDispatcher nnd = new NeuralNetworkDispatcher();
		NeuralNetworkSequenceAndProperties nns = nnd.getNeuralNetwork(neuralNetworkName);
		HashMap<String,String>  properties = nns.getProperties();
		String fileName = properties.get(ConstantsAndConventions.inputFileName);
		weightfile = FileNameWeight;
		int bsize = Integer.parseInt(batchSize);
		NN.setTrainingSize(bsize);
		bsExSeq.loadWeightMatrix(FileNameWeight);
		loadData(fileName);
		return test();
	}
	
	// returns the trained result with formating for GUI Swing frame
	private static String [] test() throws IOException {
		
		
		String outPut[] = new String[4];
		final DecimalFormat df = new DecimalFormat("0.000000000000");
		for(int j=0;j<NN.trainingSize;j++) {

				bsExSeq.initializeInput(j);
				StringBuffer sb = new StringBuffer();
				double result [] = bsExSeq.test();
				sb.append("(");
				for( int index = 0 ;index<NN.getNumOfInput();index++){ 
					sb.append(String.valueOf((int)inArray[j][index]));
					if (index != NN.getNumOfInput()-1)
						sb.append(",");
				}
				sb.append(")");
				sb.append("   ");
				for( int index = 0;index<NN.getNumOfOutput();index++){
					sb.append("("+String.valueOf((int)otArray[j][index])+")");
					sb.append(" <--  ");
					sb.append("("+String.valueOf(df.format(result[index]))+")");
				}
				outPut[j] = sb.toString();		
		}
		return outPut;
	}
	

}
