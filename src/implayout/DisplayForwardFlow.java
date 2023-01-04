package implayout;

import java.util.ArrayList;
import java.util.HashMap;

import layout.Layer;
import layout.Node;
import layout.WeightPair;
import utility.ActivationFunction;


/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class DisplayForwardFlow {

	public static void forwardpropogate(ArrayList<Layer> layers){
		
		boolean lastLayer = false;
		for(int i=1;i<layers.size();i++){
			ArrayList<Node> prevNeurons = (ArrayList<Node>) layers.get(i-1).getNodes();
			ArrayList<Node> curneurons = (ArrayList<Node>) layers.get(i).getNodes();
			if(i==layers.size()-1){
				lastLayer = true;	
			}
			computeNodeValue(prevNeurons,curneurons,lastLayer);		
		}
	}
	

	private static void computeNodeValue(ArrayList<Node> prev, ArrayList<Node> cur, boolean  lastLayer){
		
		int index = 1;
		if(lastLayer)
				index =0;
		for( int i = index  ;i<cur.size();i++){
			Node neuron = cur.get(i);
			neuron.setValue(computeFromPrevNode(prev,neuron));
		}
		
	}
	

	private static double computeFromPrevNode(ArrayList<Node> prev, Node cur){
		double value = 0;
		for (Node neuron : prev) {
			HashMap<Node,WeightPair> map = neuron.getConNodes();
			WeightPair wp = map.get(cur);
			if(wp!=null)
				value = value + neuron.getValue()*wp.getWeight();
			else
				return cur.getValue();
		}
		return (ActivationFunction.sigmoid(value));
	}
	
	
}
