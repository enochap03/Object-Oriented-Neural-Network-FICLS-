package neuralnetworks.properies;

import implayout.LayerSequence;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;

import java.util.ArrayList;
import java.util.HashMap;



public class XorXnorSequence implements NeuralNetworkSequenceAndProperties {

	public ArrayList<Object> getSequence() {
		LayerSequence layer = new LayerSequence();
		layer.addLayer(new InputLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new OutputLayer(2));
		return (ArrayList<Object>) layer.getColl();
	}
	
	public  HashMap<String,String> getProperties(){
		
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put(ConstantsAndConventions.inputFileName, "XorXnorDataSet.csv");
		properties.put(ConstantsAndConventions.weighMatrix, "XorXnorWeights");
		properties.put(ConstantsAndConventions.weighMatrixWithBusinessLoss, "XorXnorWeightsWithBusinessLoss");
		//output property in sequential order with coma seperator
		properties.put(ConstantsAndConventions.outputProperties, "Xor,Xnor");
		properties.put(ConstantsAndConventions.graphParameters, "XorXnorGraph");
		properties.put(ConstantsAndConventions.graphParametersWithBusinessLoss, "XorXnorGraphWithBusinessLoss");
		return properties;	
		
	}


}
