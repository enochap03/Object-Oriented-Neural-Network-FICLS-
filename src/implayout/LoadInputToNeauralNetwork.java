package implayout;

import java.util.List;

import layout.Layer;
import layout.Node;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class LoadInputToNeauralNetwork {
	
	public static void setInputToInputLayer( Layer layer, double[][] js,  int numInput, int batchNum){
		List<Node> nodes = layer.getNodes();
		for(int i=1;i<=numInput;i++){
			nodes.get(i).setValue(js[batchNum][i-1]);
		}
	}
	public static double[] getInput( Layer layer, double[][] js,  int numInput, int batchNum){
		
		
		List<Node> nodes = layer.getNodes();
		for(int i=1;i<=numInput;i++){
			nodes.get(i).setValue(js[batchNum][i-1]);
		}
		 return js[batchNum];
		
	}
	
	

}
