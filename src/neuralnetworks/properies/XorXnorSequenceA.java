package neuralnetworks.properies;

import implayout.LayerSequence;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;

import java.util.ArrayList;
import java.util.HashMap;


//Neural network for Xor and Xnor function with four layers
public class XorXnorSequenceA implements NeuralNetworkSequenceAndProperties {

	public ArrayList<Object> getSequence() {
		LayerSequence layer = new LayerSequence();
		layer.addLayer(new InputLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new OutputLayer(2));
		return (ArrayList<Object>) layer.getColl();
	}
	
	public  HashMap<String,String> getProperties(){
		
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put(ConstantsAndConventions.inputFileName, "InputFile.csv");
		properties.put(ConstantsAndConventions.weighMatrix, "XorXnorWeightsA");
		properties.put(ConstantsAndConventions.weighMatrixWithBusinessLoss, "XorXnorWeightsWithBusinessLossA");
		//output property in sequential order with coma seperator
		properties.put(ConstantsAndConventions.outputProperties, "Xor,Xnor");
		properties.put(ConstantsAndConventions.graphParameters, "XorXnorGraphA");
		properties.put(ConstantsAndConventions.graphParametersWithBusinessLoss, "XorXnorGraphWithBusinessLossA");
		return properties;	
		
	}


}
