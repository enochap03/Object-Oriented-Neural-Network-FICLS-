package implayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import layout.ExtendedLayerFunction;
import layout.HoldLayoutDetails;
import layout.Layer;
import layout.NN;
import transfer.SaveLoadWeights;


// class contains implementation of Base methods for Neural network primitive operations(ANN)
// interface and implementation class designed in such a way all primitive operation is accesable in single reference "ImpBaseMethods"
public class ImpBaseMethodsForAnn implements BaseMethods {

	// contails all layers of neural network
	private ArrayList<Layer> layers ;
	//input array for training neural network
	private double inArray[][];
	//output array contains actual output
	private double otArray[][];

	/**
	 * @return the layers
	 */
	public ArrayList<Layer> getLayers() {
		return layers;
	}

	/**
	 * @param layers the layers to set
	 */
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}

	
	
	/**
	 * @return the inArray
	 */
	public double[][] getInArray() {
		return inArray;
	}

	/**
	 * @param inArray the inArray to set
	 */
	public void setInArray(double[][] inArray) {
		this.inArray = inArray;
	}

	/**
	 * @return the otArray
	 */
	public double[][] getOtArray() {
		return otArray;
	}

	/**
	 * @param otArray the otArray to set
	 */
	public void setOtArray(double[][] otArray) {
		this.otArray = otArray;
	}

	@Override
	public String toString() {
		return "ImpBaseExecutionSeq [layers=" + layers + "]";
	}
	
	public void setInputOutputData(double[][] in, double[][] ot) {
		setInArray(in);
		setOtArray(ot);
	}

	public void setInputOutputForTestData(double[][] in, double[][] ot, double [][] pred) {
		setInArray(in);
		setOtArray(ot);
	}


// initializes input data into neural network input layer
	public void initializeInput(int batchNum) {
		
	
		
		LoadInputToNeauralNetwork.setInputToInputLayer(layers.get(0),getInArray(),NN.numOfInput,batchNum);
		// TODO Auto-generated method stub

	}
	
	// initializes input data into neural network input layer and also returns the input data 
	
	public double[] initializeInputWithReturn(int batchNum) {
		
		
		return LoadInputToNeauralNetwork.getInput(layers.get(0),getInArray(),NN.numOfInput,batchNum);
		// TODO Auto-generated method stub

	}
	// method calls ForwardFlow.forwardpropogate() for forward propagation
	public void forwardPass(int batchNum, String businessProperty) {
		
		ForwardFlow.forwardpropogate(getLayers(),batchNum,getInArray(),businessProperty);
		
	}
	// method calls checkForTest.test() for test result
	public double[] test(){
		
		return checkForTest.test(getLayers());
	}

	// method calls BackPropogate.findLoseBackwardPass() for backpropagation
	public void backwardPass(int rowNum,boolean businessLoss) throws IOException {
		
		BackPropogate.findLoseBackwardPass(getLayers(),rowNum,getOtArray(), businessLoss);
	}
	// method calls BackPropogate.updateNNWeights() for new weight updating
	public void updateWeights() {
		
		BackPropogate.updateNNWeights(getLayers());
	}

// methods is used to store trained weight
	public boolean saveWeightMatrix(String fileName){
		
		HoldLayoutDetails lytdetails = new HoldLayoutDetails();
		lytdetails.setLayers(getLayers());
		lytdetails.setInput(NN.getNumOfInput());
		lytdetails.setOutput(NN.getNumOfOutput());
		
		boolean b = SaveLoadWeights.storeWeighToDesk(lytdetails, fileName);
		return b;
	
		
		
	}
	// methods is used to load saved weight into neural network
	public void loadWeightMatrix(String fileName){
		HoldLayoutDetails laytdetails = SaveLoadWeights.getWeighFromDesk(fileName);
		setLayers(laytdetails.getLayers());
		NN.setNumOfInput(laytdetails.getInput());
		NN.setNumOfOutput(laytdetails.getOutput());
	}

// establishes neural network connection following the 'seq' object using EstablishConnection class
	public void establishNn(ArrayList<Object> seq) {
		
		setLayers(new EstablishConnection().executeSequences(seq));
		// TODO Auto-generated method stub
		
	}
	
	//method tracks neural network instances using SaveWeightInstances class

	public  void saveNeuralNetworkInstances (TreeMap<String, ArrayList<Double>> parameters,int j ) {
		SaveWeightInstances.recordInstances(getLayers(), parameters, j );
		// TODO Auto-generated method stub
		
	}

	// method return final or output layer which is extendedlayerfunction from our design
	@Override
	public ExtendedLayerFunction getExtendedLayerFunction() {
		
		return (ExtendedLayerFunction) (getLayers().get(getLayers().size()-1));
		// TODO Auto-generated method stub
		
	}





}
