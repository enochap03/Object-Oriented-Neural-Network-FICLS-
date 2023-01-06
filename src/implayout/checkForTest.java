package implayout;

import java.util.ArrayList;
import java.util.HashMap;

import layout.ExtendedLayerFunction;
import layout.ExtendedNodeFunction;
import layout.Layer;
import layout.Node;
import layout.WeightPair;
import utility.ActivationFunction;

public class checkForTest {
	
	
	public static double[] test(ArrayList<Layer> layers){

		double prediArray[] = null;
		boolean lastLayer = false;
		for(int i=1;i<layers.size();i++){
			
			if(i==layers.size()-1){
				lastLayer = true;	
				ArrayList<Node> prevNeurons = (ArrayList<Node>) layers.get(i-1).getNodes();
				ExtendedLayerFunction extLyrFun	= (ExtendedLayerFunction) 	layers.get(i);
				ArrayList<ExtendedNodeFunction> exnode  = (ArrayList<ExtendedNodeFunction>) extLyrFun.getExNodes();	
				prediArray = TestcomputeFinalLayerNodeValue(prevNeurons,exnode);
			}
			else {
				
				ArrayList<Node> prevNeurons = (ArrayList<Node>) layers.get(i-1).getNodes();
				ArrayList<Node> curneurons = (ArrayList<Node>) layers.get(i).getNodes();
				computeNodeValue(prevNeurons,curneurons,lastLayer);	
			}
				
		}
		return prediArray;
	}
private static void computeNodeValue(ArrayList<Node> prev, ArrayList<Node> cur, boolean  lastLayer){
		
		int index = 1;
		if(lastLayer)
				index =0;
		for( int i = index  ;i<cur.size();i++){
			Node node = cur.get(i);
			node.setValue(computeFromPrevNode(prev,node));
		}
		
	}

	private static double computeFromPrevNode(ArrayList<Node> prev, Node cur){
		double value = 0;
		for (Node node : prev) {
			HashMap<Node,WeightPair> map = node.getConNodes();
			WeightPair wp = map.get(cur);
			if(wp!=null)
				value = value + node.getValue()*wp.getWeight();
			else
				return cur.getValue();
		}
		return (ActivationFunction.sigmoid(value));
	}
	

		
	private static double[] TestcomputeFinalLayerNodeValue(ArrayList<Node> prev, ArrayList<ExtendedNodeFunction> cur){
		
		double pred[] = new double[cur.size()];
		for(int index =0 ;index<cur.size();index++){
			
			ExtendedNodeFunction BN = cur.get(index);
			BN.setValue(ActivationFunction.sigmoid(computeFromPrevForLastNode(prev,BN)));
			pred[index]= BN.getValue();
		}
		return pred;
	}
	
	private static double computeFromPrevForLastNode(ArrayList<Node> prev, ExtendedNodeFunction cur){
		double value = 0;
	
		for (Node node : prev) {
			HashMap<Node,WeightPair> map = node.getConNodes();
			WeightPair wp = map.get(cur);
			if(wp!=null)
				value = value + node.getValue()*wp.getWeight();
		}
		return value;
	}


}
