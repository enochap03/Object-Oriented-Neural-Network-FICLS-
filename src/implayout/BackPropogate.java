package implayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import layout.ExtendedLayerFunction;
import layout.ExtendedNodeFunction;
import layout.Layer;
import layout.NN;
import layout.Node;
import layout.WeightPair;
import neuralnetworks.properies.ConstantsAndConventions;
import utility.ComputeGradient;

//class contains methods for back propagation and new weight updating
public class BackPropogate {

	static long count = ConstantsAndConventions.zero;
	 
	//iterates into the last layer to compute the regression loss of each node.
	static void findLoseBackwardPass(ArrayList<Layer>layers, int j, double otArray[][], boolean businessLoss) throws IOException {


		ExtendedLayerFunction llayer =  (ExtendedLayerFunction) layers.get(layers.size()-1);
		List<ExtendedNodeFunction> lnodes = llayer.getExNodes();
		double loss = 0;

		for(int i = 0;i<NN.getNumOfOutput();i++) {
			loss = otArray[j][i] - lnodes.get(i).getValue(); 

			if(businessLoss)
				lnodes.get(i).setDelta(-((2/NN.getNumOfOutput())*loss)*ComputeGradient.sigGrad(lnodes.get(i).getValue())+(lnodes.get(i).getBusiness_loss()));
			else 
				lnodes.get(i).setDelta(-((2/NN.getNumOfOutput())*loss)*ComputeGradient.sigGrad(lnodes.get(i).getValue()));

		}
		propogatelossbackward(layers);


	}
	//traverse each layer in reverse order to propagate gradient/error
	public static void propogatelossbackward(List<Layer>layers){
		for(int i = (layers.size()-2);i>-1;i--){
			NodewiseLossTransfer(layers.get(i).getNodes(),i);
		}
	}
	//propagate gradient/error to each nodes in a layer
	private static void NodewiseLossTransfer(List<Node> nodes, int pos) {
		for (Node node : nodes) {
			HashMap<Node,WeightPair> map = node.getConNodes();
			Iterator hmIterator = map.entrySet().iterator(); 
			double  loseFromPrevNode = 0;
			while (hmIterator.hasNext()) { 
				Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
				Node nnode = (Node) mapElement.getKey();
				WeightPair wpair = (WeightPair) mapElement.getValue();
				wpair.setUpdatedWeight(wpair.getUpdatedWeight()+(nnode.getDelta()*node.getValue()));
				if(pos>0)
					loseFromPrevNode = loseFromPrevNode + nnode.getDelta()*wpair.getWeight();
			} 
			if(pos>0)

				node.setDelta(ComputeGradient.sigGrad(node.getValue())*loseFromPrevNode);//local lose combined from the previous layer	
		}
	}
	
	//iterates to each layers of neural network to update with new weight
	public static void updateNNWeights(ArrayList<Layer> layers) {
		for(int i=0;i<layers.size()-1;i++){
			Layer lyr = layers.get(i);
			updateEachLayerWegiht(lyr);
		}
	}
	
	//new weight updating done for every connection in the layer
	
	private static void updateEachLayerWegiht(Layer lyr) {

		List<Node> nodes = lyr.getNodes();
		HashMap<Node, WeightPair> map;
		for (Node node : nodes) {
			map  = node.getConNodes();
			Iterator hmIterator = map.entrySet().iterator(); 
			while (hmIterator.hasNext()) { 
				Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
				WeightPair wpair = (WeightPair)mapElement.getValue();
				wpair.setWeight(updateNewWeightToNodeGCD(wpair.getWeight(),wpair.getUpdatedWeight()));
				wpair.setUpdatedWeight(0.0);
			} 
		}
	}
	
	// gradient descent update
	private static double updateNewWeightToNodeGCD(double prev, double lose){
		
		return (prev - (NN.lrate*lose));
		
	}
}
