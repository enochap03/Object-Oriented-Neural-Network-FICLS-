package neuralnetworks.properies;

import java.util.ArrayList;
import java.util.HashMap;

import implayout.LayerSequence;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;


//Neural network for Xor function with four layer
public class XorSequenceA implements NeuralNetworkSequenceAndProperties {


	
	
	public ArrayList<Object> getSequence() {
		LayerSequence layer = new LayerSequence();
		layer.addLayer(new InputLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new OutputLayer(1));;
		return (ArrayList<Object>) layer.getColl();
	}

	public HashMap<String, String> getProperties() {
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put(ConstantsAndConventions.inputFileName, "InputFileX.csv");
		properties.put(ConstantsAndConventions.weighMatrix, "XorWeightsA");
		properties.put(ConstantsAndConventions.weighMatrixWithBusinessLoss, "XorWeightsAWithBusinessLoss");
		//output property in sequential order with coma seperator
		properties.put(ConstantsAndConventions.outputProperties, "Xor");
		properties.put(ConstantsAndConventions.graphParameters, "XorGraphA");
		properties.put(ConstantsAndConventions.graphParametersWithBusinessLoss, "XorGraphAWithBusinessLoss");
		return properties;
	}

}
