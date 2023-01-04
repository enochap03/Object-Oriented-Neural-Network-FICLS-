package implayout;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import layout.ExtendedLayerFunction;
import neuralnetworks.properies.ConstantsAndConventions;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public interface BaseMethods{
	
	
	public void setInputOutputData(double[][] in, double[][] ot);
	public void establishNn(ArrayList<Object> seq);
	public void initializeInput(int batchNum);
	public void forwardPass(int batchNo, String businessProperty);
	public void backwardPass(int rowNum, boolean businessLose) throws IOException ;
	public void updateWeights();
	public boolean saveWeightMatrix(String fileName);
	public void loadWeightMatrix(String fileName);
	public double[] test();
	public void saveNeuralNetworkInstances(TreeMap<String, ArrayList<Double>> parameters,int j);
	public ExtendedLayerFunction getExtendedLayerFunction();
	public double[] initializeInputForExtendedNode(int batchNum);

}
