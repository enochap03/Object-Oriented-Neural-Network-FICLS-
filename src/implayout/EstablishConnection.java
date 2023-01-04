package implayout;

import java.util.ArrayList;

import layout.ExtendedLayerFunction;

import layout.Layer;
import layout.NN;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;



/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class EstablishConnection {
	
	public ArrayList<Layer>  executeSequences(ArrayList<Object> slist) {
		ArrayList<Layer> layers = new ArrayList<Layer>();
		Layer prevLayer = null;
		ANNCreation nnCreation = new ANNCreation();
		int index =1;
		for (Object object : slist) {
			
			if(object instanceof  InputLayer ){
				
				InputLayer ipInfo = (InputLayer) object;
				prevLayer = nnCreation.mapInputLayer(ipInfo,index);
				NN.setNumOfInput(ipInfo.getNumOfInputs());
				layers.add(prevLayer);
			}
			else if (object instanceof  HiddenLayer ){
				
				HiddenLayer hlayer = (HiddenLayer) object;
				prevLayer = nnCreation.mapHiddenLayer(prevLayer,hlayer,index);
				layers.add(prevLayer);
			}
			else if (object instanceof  OutputLayer ){
				
				OutputLayer optlayer = (OutputLayer) object;
				prevLayer = nnCreation.mapOutputLayer(prevLayer,optlayer,index);
				NN.setNumOfOutput(optlayer.getNumOfNeurons());
				layers.add(prevLayer);
			}
			index++;
			
		}
		
		return  layers;
	}
	
	public int  getNumberOfOutput(ArrayList<Object> slist) {
	
		int num = 0;
		for (Object object : slist) {
			
			
			 if (object instanceof  OutputLayer ){
				
				OutputLayer optlayer = (OutputLayer) object;
				num = optlayer.getNumOfNeurons();
			}
			
			
		}
		
		return  num;
	}

}
