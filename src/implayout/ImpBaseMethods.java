package implayout;




import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import layout.ExtendedLayerFunction;
import layout.HoldLayoutDetails;
import layout.Layer;
import layout.NN;
import transfer.SaveLoadWeights;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class ImpBaseMethods implements BaseMethods {

	private ArrayList<Layer> layers ;
	private double inArray[][];
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



	public void initializeInput(int batchNum) {
		
	
		
		LoadInputToNeauralNetwork.setInputToInputLayer(layers.get(0),getInArray(),NN.numOfInput,batchNum);
		// TODO Auto-generated method stub

	}
	
	public double[] initializeInputForExtendedNode(int batchNum) {
		
		
		return LoadInputToNeauralNetwork.getInput(layers.get(0),getInArray(),NN.numOfInput,batchNum);
		// TODO Auto-generated method stub

	}

	public void forwardPass(int batchNum, String businessProperty) {
		
		ForwardFlow.forwardpropogate(getLayers(),batchNum,getInArray(),businessProperty);
		
	}
	public double[] test(){
		
		return TestWeight.test(getLayers());
	}

	public void backwardPass(int rowNum,boolean businessLoss) throws IOException {
		
		BackPropogate.findLoseBackwardPass(getLayers(),rowNum,getOtArray(), businessLoss);
	}
	
	public void updateWeights() {
		
		BackPropogate.updateNNWeights(getLayers());
	}

	
	public boolean saveWeightMatrix(String fileName){
		
		HoldLayoutDetails lytdetails = new HoldLayoutDetails();
		lytdetails.setLayers(getLayers());
		lytdetails.setInput(NN.getNumOfInput());
		lytdetails.setOutput(NN.getNumOfOutput());
		
		boolean b = SaveLoadWeights.storeWeighToDesk(lytdetails, fileName);
		return b;
	
		
		
	}
	
	public void loadWeightMatrix(String fileName){
		HoldLayoutDetails laytdetails = SaveLoadWeights.getWeighFromDesk(fileName);
		setLayers(laytdetails.getLayers());
		NN.setNumOfInput(laytdetails.getInput());
		NN.setNumOfOutput(laytdetails.getOutput());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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


	public void establishNn(ArrayList<Object> seq) {
		
		setLayers(new EstablishConnection().executeSequences(seq));
		// TODO Auto-generated method stub
		
	}
	


	public  void saveNeuralNetworkInstances (TreeMap<String, ArrayList<Double>> parameters,int j ) {
		DisplayWeight.display(getLayers(), parameters, j );
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExtendedLayerFunction getExtendedLayerFunction() {
		
		return (ExtendedLayerFunction) (getLayers().get(getLayers().size()-1));
		// TODO Auto-generated method stub
		
	}





}
