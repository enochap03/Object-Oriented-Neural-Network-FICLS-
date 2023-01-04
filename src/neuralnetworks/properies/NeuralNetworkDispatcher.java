package neuralnetworks.properies;



import neuralnetworks.properies.XorSequence;
import neuralnetworks.properies.XorXnorSequence;

public class NeuralNetworkDispatcher {
	
	public NeuralNetworkSequenceAndProperties getNeuralNetwork(String name){
		
		if(name == null)
			return null;
		else if (name.equalsIgnoreCase("XorSequence"))
			return new XorSequence();
		else if (name.equalsIgnoreCase("XorXnorSequence")) 
			return new XorXnorSequence();
	
		else if (name.equalsIgnoreCase("XorSequenceA")) 
			return new XorSequenceA();
		else if (name.equalsIgnoreCase("XorXnorSequenceA")) 
			return new XorXnorSequenceA();

		// add for more neural network 
		return null;
		
	}	

}
