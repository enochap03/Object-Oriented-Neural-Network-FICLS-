package implayout;

import java.util.ArrayList;
import java.util.HashMap;

import layout.ExtendedLayerFunction;
import layout.ExtendedNodeFunction;
import layout.Layer;
import layout.Node;
import layout.WeightPair;
import layout.business.BusinessRuleDispatcher;
import layout.business.BusinessRules;
import utility.ActivationFunction;


/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class ForwardFlow {

	public static void forwardpropogate(ArrayList<Layer> layers, int batchNo, double iArray[][], String property){

		
		for(int i=1;i<layers.size();i++){
			
			if(i==layers.size()-1){	
				ArrayList<Node> prevNeurons = (ArrayList<Node>) layers.get(i-1).getNodes();
				ExtendedLayerFunction extLyrFun	= (ExtendedLayerFunction) 	layers.get(i);
				ArrayList<ExtendedNodeFunction> exnode  = (ArrayList<ExtendedNodeFunction>) extLyrFun.getExNodes();	
				computeNodeValueLstLayer(prevNeurons,exnode,batchNo,iArray,property);	
			}
			else {
				
				ArrayList<Node> prevNeurons = (ArrayList<Node>) layers.get(i-1).getNodes();
				ArrayList<Node> curneurons = (ArrayList<Node>) layers.get(i).getNodes();
				computeNodeValue(prevNeurons,curneurons);	
			}
				
		}
	}
	private static void computeNodeValueLstLayer(ArrayList<Node> prev, ArrayList<ExtendedNodeFunction> cur,int batchNo, double iArray[][],String businessProperty){
		
	
		 String [] types = null;
		 types = businessProperty.split(",");	
		for( int i = 0  ;i<cur.size();i++) {		
			double nodeValue = computeFromPrevNodeConnections(prev,cur.get(i));
			ExtendedNodeFunction exnode = cur.get(i);
			exnode.setValue(nodeValue);
			assertBusinessRules(batchNo,iArray,types[i],exnode); 
		}
		
	}
	
private static void computeNodeValue(ArrayList<Node> prev, ArrayList<Node> cur){

		for( int i = 1  ;i<cur.size();i++){
			Node node = cur.get(i);
			node.setValue(computeFromPrevNodeConnections(prev,node));
		}	
}

	private static double computeFromPrevNodeConnections(ArrayList<Node> prev, Node cur){
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
	
	private static void assertBusinessRules(int batchNo, double itArray[][],String type,  ExtendedNodeFunction xNode) {
		BusinessRules brules = null;
		BusinessRuleDispatcher rleDispatcher = new BusinessRuleDispatcher();
		brules = rleDispatcher.getBusinessRules((int)itArray[batchNo][0],(int)itArray[batchNo][1],type);
		xNode.setBusiness_loss(xNode.getValue() -  brules.assertBusinessRule());
	}
	
}
