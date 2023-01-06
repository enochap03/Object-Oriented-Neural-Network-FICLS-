package implayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import layout.ExtendedLayerFunction;
import layout.ExtendedNodeFunction;
import layout.Layer;
import layout.Node;
import layout.WeightPair;
import neuralnetworks.properies.ConstantsAndConventions;

// class saves each instances of neural network in a storage(HashMap) to compute correlation between loss and connection weight
public class SaveWeightInstances {
	

// main method for tracking the instances 	
public static void recordInstances(ArrayList<Layer> layers,TreeMap<String, ArrayList<Double>> parameters,int j){
		

		
		if(j==0) { // check for the first batch
			for(int i= 0;i<layers.size()-1;i++){
				
			
				
				trackLayer(layers.get(i).getNodes(),parameters);
				


			}
			 ExtendedLayerFunction llayer =  (ExtendedLayerFunction) layers.get(layers.size()-1);
			 trackfinalLayer(llayer.getExNodes(),parameters);
			
			
		}
		 ExtendedLayerFunction llayer =  (ExtendedLayerFunction) layers.get(layers.size()-1);
		 List<ExtendedNodeFunction> lnodes = llayer.getExNodes();
		for(int k=0;k<lnodes.size();k++) {

			
			String key=ConstantsAndConventions.prefixRes+lnodes.get(k).getName();
			if(!parameters.containsKey(key)) {
				
				ArrayList<Double> resvalue = new ArrayList<Double>();
				resvalue.add(lnodes.get(k).getValue());
				parameters.put(key,resvalue);
			}
			else {
				
				ArrayList<Double> resvalue = parameters.get(key);
				resvalue.add(lnodes.get(k).getValue());
			}
		}
		
	
	}
//tracks nodes , connection weight, loss from the layer for last or output layer 
	private static void  trackfinalLayer(List<ExtendedNodeFunction> extnodes, TreeMap<String, ArrayList <Double>> parameters) {
		for( int i = 0 ;i<extnodes.size();i++){
			ExtendedNodeFunction neuron = extnodes.get(i);
			if(!parameters.containsKey(neuron.getName())) {

				ArrayList<Double> delvalue = new ArrayList<Double>();
				delvalue.add(neuron.getDelta());
				parameters.put(neuron.getName(),delvalue);
			}
			else {

				ArrayList<Double> delvalue = parameters.get(neuron.getName());
				delvalue.add(neuron.getDelta());
			}
		}
		
	}
	//tracks nodes , connection weight, loss from the layer 
	private static TreeMap<String, ArrayList<Double>>  trackLayer(List<Node> nodes,TreeMap<String, ArrayList<Double>> parameters){


		for( int i = 0 ;i<nodes.size();i++){


			Node neuron = nodes.get(i);
			if(!parameters.containsKey(neuron.getName())) {

				ArrayList<Double> delvalue = new ArrayList<Double>();
				delvalue.add(neuron.getDelta());
				parameters.put(neuron.getName(),delvalue);
			}
			else {

				ArrayList<Double> delvalue = parameters.get(neuron.getName());
				delvalue.add(neuron.getDelta());
			}

			HashMap<Node,WeightPair> map = neuron.getConNodes(); 
			Iterator hmIterator  = map.entrySet().iterator();
			double loseFromPrevNode = 0; 
			while (hmIterator.hasNext())
			{ 
				Map.Entry mapElement = (Map.Entry)hmIterator.next();
				Node nneuron = (Node) mapElement.getKey();
				WeightPair wpair =(WeightPair) mapElement.getValue();
				String connode = ConstantsAndConventions.prefixFrom + neuron.getName()+ConstantsAndConventions.prefixTo +nneuron.getName();
				if(!parameters.containsKey(connode)) {

					ArrayList<Double> delvalue1 = new ArrayList<Double>();
					delvalue1.add(wpair.getWeight());
					parameters.put(connode,delvalue1);
				}
				else {

					ArrayList<Double> delvalue1 = parameters.get(connode);
					delvalue1.add(wpair.getWeight());

				}
			}

		}
		return parameters;
		
	}
}
