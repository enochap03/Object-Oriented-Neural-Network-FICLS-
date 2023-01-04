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

import utility.StringOperators;

public class DisplayWeight {
	

	
public static void display(ArrayList<Layer> layers,TreeMap<String, ArrayList<Double>> parameters,int j){
		
		boolean lastLayer = false;
		
		if(j==0) {
			for(int i= 0;i<layers.size()-1;i++){
				
			
				//ArrayList<ExtendedNeuronFunction> neurons = (ArrayList<ExtendedNeuronFunction>) layers.get(i).getNeurons();
				displayLayer(layers.get(i).getNodes(),parameters);
				//System.out.println("------------------------------------------------End of layer ----------------------------------------");


			}
			 ExtendedLayerFunction llayer =  (ExtendedLayerFunction) layers.get(layers.size()-1);
			 displayfinalLayer(llayer.getExNodes(),parameters);
			
			
		}
		
		
		
		 ExtendedLayerFunction llayer =  (ExtendedLayerFunction) layers.get(layers.size()-1);
		 List<ExtendedNodeFunction> lnodes = llayer.getExNodes();
		//ArrayList<Node> neurons = (ArrayList<Node>) layers.get(layers.size()-1).getNodes();
		
		for(int k=0;k<lnodes.size();k++) {

			
			String key="RES"+lnodes.get(k).getName();
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
	
	private static void  displayfinalLayer(List<ExtendedNodeFunction> extnodes, TreeMap<String, ArrayList <Double>> parameters) {
		
		
		
		for( int i = 0 ;i<extnodes.size();i++){


			ExtendedNodeFunction neuron = extnodes.get(i);
			//String [] temp = neuron.getName().split(",");
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
	private static TreeMap<String, ArrayList<Double>>  displayLayer(List<Node> nodes,TreeMap<String, ArrayList<Double>> parameters){


		for( int i = 0 ;i<nodes.size();i++){


			Node neuron = nodes.get(i);
			//String [] temp = neuron.getName().split(",");
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

				//String [] temp1 = nneuron.getName().split(",");

				String connode = "Connection from " + neuron.getName()+" to "+nneuron.getName();


				if(!parameters.containsKey(connode)) {

					ArrayList<Double> delvalue1 = new ArrayList<Double>();
					delvalue1.add(wpair.getWeight());
					parameters.put(connode,delvalue1);
				}
				else {

					ArrayList<Double> delvalue1 = parameters.get(connode);
					delvalue1.add(wpair.getWeight());

				}

				//System.out.println("<"+StringOperators.superscript(temp1[0])+temp1[1]+" "+wpair.getWeight()+">");
			}
			//System.out.println("End of node"+neuron.getName());


		}
		return parameters;
		
	}
	



}
