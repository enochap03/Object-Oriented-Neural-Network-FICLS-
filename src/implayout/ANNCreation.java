package implayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import layout.ExtendedLayerFunction;

import layout.ExtendedNodeFunction;

import layout.Layer;
import layout.Node;

import layout.WeightPair;
import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;
import neuralnetworks.properies.ConstantsAndConventions;
import utility.GenerateRandomWeight;


// class contains methods to map connection between two layers. 
public class ANNCreation {

//initial random assignment between -1 and 1
	static GenerateRandomWeight rw = new GenerateRandomWeight(ConstantsAndConventions.mone, ConstantsAndConventions.one);
	
	//method maps input layer to the neural network
	public Layer mapInputLayer(InputLayer inLayer,int number) {

		List<Node> nodes = createNodes(inLayer.getNumOfInputs(),number);
		Layer lay = new Layer();
		lay.setNodes(nodes);
		return lay;
	}
	//method maps hidden layer to the neural network
	public Layer mapHiddenLayer(Layer prev,  HiddenLayer hlayer,int index) {
		List<Node> prevLayerNodes = prev.getNodes();
		List<Node> nodes = createNodes(hlayer.getNumOfNeurons(),index);
		for (Node node : prevLayerNodes) {
			HashMap<Node,WeightPair> map = new HashMap<Node,WeightPair>();
			for(int i=1;i<nodes.size();i++){
				Node nnode = nodes.get(i);
				map.put(nnode,new WeightPair(rw.getRandomNumber()));
			}
			node.setConNodes(map);
		}
		Layer lay = new Layer();
		lay.setNodes(nodes);
		return lay;
	}
	//method maps output layer to the neural network
	public ExtendedLayerFunction mapOutputLayer(Layer prev,  OutputLayer optLayer,int index) {
		List<Node> prevLayerNodes = prev.getNodes();
		List<ExtendedNodeFunction> exnodes = createExtendedNodes(optLayer.getNumOfNeurons()-1,index);
		for (Node node : prevLayerNodes) {
			HashMap<Node,WeightPair> map = new HashMap<Node,WeightPair>();
			for(int i=0;i<exnodes.size();i++){
				Node nneuron = exnodes.get(i);
				map.put(nneuron,new WeightPair(rw.getRandomNumber()));
			}
			node.setConNodes(map);
		}
		ExtendedLayerFunction lay = new ExtendedLayerFunction();
		lay.setExNodes(exnodes);
		return lay;
	}
	// method creates node/neuron
	private static List<Node> createNodes(int num,int indexnum){
		
		List<Node> nodes=  new ArrayList<Node>();
		for(int i = 0 ;i<=num; i++){
			Node node = new Node();
			node.setName(ConstantsAndConventions.prefixN + String.valueOf(indexnum)+","+String.valueOf(i+1));
			if(i==0){
				node.setValue(1.0);
			}
			nodes.add(node);
		}
		return nodes;

	}
	// method creates extended node for business rule embedding 
private static List<ExtendedNodeFunction> createExtendedNodes(int num, int indexnum){
		
		List<ExtendedNodeFunction> neurons=  new ArrayList<ExtendedNodeFunction>();
		for(int i = 0 ;i<=num; i++){
			ExtendedNodeFunction node = new ExtendedNodeFunction();
			node.setName(ConstantsAndConventions.prefixN + String.valueOf(indexnum)+","+String.valueOf(i+1));
			if(i==0){
				node.setValue(1.0);
			}
			neurons.add(node);
		}
		return neurons;

	}

}
