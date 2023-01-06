package implayout;

import java.util.List;

import layout.Layer;
import layout.Node;


// class does the function of loading input data into neural network input layer
public class LoadInputToNeauralNetwork {
	
	//method loads input data into neural network input layer without returning
	public static void setInputToInputLayer( Layer layer, double[][] js,  int numInput, int batchNum){
		List<Node> nodes = layer.getNodes();
		for(int i=1;i<=numInput;i++){
			nodes.get(i).setValue(js[batchNum][i-1]);
		}
	}
	//method loads input data into neural network input layer with returning
	public static double[] getInput( Layer layer, double[][] js,  int numInput, int batchNum){
		
		
		List<Node> nodes = layer.getNodes();
		for(int i=1;i<=numInput;i++){
			nodes.get(i).setValue(js[batchNum][i-1]);
		}
		 return js[batchNum];
		
	}
	
	

}
