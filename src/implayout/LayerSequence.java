package implayout;

import java.util.ArrayList;
import java.util.List;

import neuralnetworks.layers.HiddenLayer;
import neuralnetworks.layers.InputLayer;
import neuralnetworks.layers.OutputLayer;





/**
 * @author Enoch Arulprakash & A. Martin
 *
 */
public class LayerSequence extends ArrayList<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9031811013827551777L;
	/**
	 * 
	 */
	
	private List<Object> coll = new ArrayList<Object>();
	
	

	public void addLayer(Object o)  {
		 
		if (o instanceof InputLayer || o instanceof HiddenLayer || o instanceof OutputLayer ) {
			
			coll.add(o);
			
		}
	}


	/**
	 * @return the coll
	 */
	public List<Object> getColl() {
		return coll;
	}
	
	
	

}
