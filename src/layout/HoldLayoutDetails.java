package layout;

import java.io.Serializable;
import java.util.ArrayList;

// pojo class for entire neural network persistence 
public class HoldLayoutDetails implements Serializable {
	

	private static final long serialVersionUID = 2002237546753044985L;
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	private int input;
	private int output;
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
	}
	public int getOutput() {
		return output;
	}
	public void setOutput(int output) {
		this.output = output;
	}
	@Override
	public String toString() {
		return "HoldLayoutDetails [layers=" + layers + ", input=" + input
				+ ", output=" + output + "]";
	}
	
}
