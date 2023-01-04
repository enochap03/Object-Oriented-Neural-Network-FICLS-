package layout;

import java.io.Serializable;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

public class WeightPair implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933956835743137883L;
	private double weight;
	transient private double updatedWeight;
	
	public WeightPair(double weight) {
		super();
		this.weight = weight;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getUpdatedWeight() {
		return updatedWeight;
	}
	public void setUpdatedWeight(double updatedWeight) {
		this.updatedWeight = updatedWeight;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WPair [weight=" + weight + "]";
	}
	
	

}
