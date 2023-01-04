package neuralnetworks.properies;

import implayout.LayerSequence;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;

import java.util.ArrayList;
import java.util.HashMap;




public class XorSequence implements NeuralNetworkSequenceAndProperties {

	public ArrayList<Object> getSequence() {
		LayerSequence layer = new LayerSequence();
		layer.addLayer(new InputLayer(2));
		layer.addLayer(new HiddenLayer(2));
		layer.addLayer(new OutputLayer(1));;
		return (ArrayList<Object>) layer.getColl();
	}

	@Override
	public HashMap<String, String> getProperties() {
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put(ConstantsAndConventions.inputFileName, "InputFileX.csv");
		properties.put(ConstantsAndConventions.weighMatrix, "XorWeights");
		properties.put(ConstantsAndConventions.weighMatrixWithBusinessLoss, "XorWeightsWithBusinessLoss");
		//output property in sequential order with coma seperator
		properties.put(ConstantsAndConventions.outputProperties, "Xor");
		properties.put(ConstantsAndConventions.graphParameters, "XorGraph");
		properties.put(ConstantsAndConventions.graphParametersWithBusinessLoss, "XorGraphWithBusinessLoss");
		return properties;
	}

}
