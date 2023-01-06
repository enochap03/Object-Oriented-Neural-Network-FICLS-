package layout;

import java.io.Serializable;

import java.util.HashMap;

// Node or neuron class
public class Node implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String name;
	private double value;
	private double delta;
	private HashMap<Node,WeightPair> conNodes = new HashMap<Node,WeightPair>();
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return the delta
	 */
	public double getDelta() {
		return delta;
	}
	/**
	 * @param delta the delta to set
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}
	
	public HashMap<Node, WeightPair> getConNodes() {
		return conNodes;
	}
	public void setConNodes(HashMap<Node, WeightPair> conNodes) {
		this.conNodes = conNodes;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Neuron [name=" + name + ", value=" + value + ", delta=" + delta + ", conNodes=" + conNodes + "]";
	}
	
}
