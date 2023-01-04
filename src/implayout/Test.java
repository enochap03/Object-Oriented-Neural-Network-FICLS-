package implayout;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;



import layout.NN;
import neuralnetworks.properies.ConstantsAndConventions;
import neuralnetworks.properies.NeuralNetworkDispatcher;
import neuralnetworks.properies.NeuralNetworkSequenceAndProperties;
import utility.file.InputFromFile;

public class Test {
	static FileReader reader;   
	static NN network = new NN();
	static double inArray[][] ;
	static double otArray[][] ;
	static double predOtArrray[][];
	static String weightfile;
	static String neuralNetworkName;
	static String substring ="yes";
	static String subChar ="y";
 static BaseMethods  bsExSeq = new  ImpBaseMethods();


	
	private static void  loadData(String fileName){
		
		 inArray = new double [NN.getTrainingSize()][NN.getNumOfInput()];
		 otArray = new double [NN.getTrainingSize()][NN.getNumOfOutput()]; 
		 //input file name for training
		 InputFromFile.loadFromCSVFile(fileName,inArray,otArray);
		 bsExSeq.setInputOutputData(inArray, otArray);

	}
	public static void main(String args[]) throws IOException{
		
		
		/**
		 *  * @param bSize  * @param lrate  * @param ephocs
		 */

		reader = new FileReader("parameters.properties"); 
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
		System.out.println("Testing  continued");
		bsExSeq.loadWeightMatrix(weightfile);
		loadData(fileName);
		test();
	}
	

	
	private static void test() {
		for(int j=0;j<NN.trainingSize;j++) {

				bsExSeq.initializeInput(j);
				double result [] = bsExSeq.test();
				System.out.print("|");
				for( int index = 0 ;index<NN.getNumOfInput();index++){
					System.out.print(inArray[j][index]);
					if (index != NN.getNumOfInput()-1)
						System.out.print("   ");					
				}
				System.out.print("|");
				System.out.print("   ");
				for( int index = 0;index<NN.getNumOfOutput();index++){
					System.out.print("|"+otArray[j][index]);
					System.out.print(" <-- ");	
					System.out.print(result[index]+"|");
					System.out.print("   ");
				}
				System.out.println();
				
		}
	}
	

}
