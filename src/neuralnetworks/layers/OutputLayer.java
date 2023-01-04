package neuralnetworks.layers;

public class OutputLayer {
	
	private int numOfNeurons;
	

	public OutputLayer(int numOfNeurons) {
		super();
		this.numOfNeurons = numOfNeurons;
	}

	public int getNumOfNeurons() {
		return numOfNeurons;
	}

	public void setNumOfNeurons(int numOfNeurons) {
		this.numOfNeurons = numOfNeurons;
	}
	

}
