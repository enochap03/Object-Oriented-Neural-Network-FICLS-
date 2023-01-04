package plot;

import java.io.Serializable;

public class PlotInfo implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7351625183417748372L;
	

	public PlotInfo(int ephocs, int isize, double[] input, int osize, double output) {
		super();
		this.ephocs = ephocs;
		input= new double[isize];
		this.input = input;
		//output = new double[osize];
		this.output = output;
	}
	private int ephocs;
	private double input[];
	private double output;
	public int getEphocs() {
		return ephocs;
	}
	public void setEphocs(int ephocs) {
		this.ephocs = ephocs;
	}
	public double[] getInput() {
		return input;
	}
	public void setInput(double[] input) {
		this.input = input;
	}
	public double getOutput() {
		return output;
	}
	public void setOutput(double output) {
		this.output = output;
	}

	
	
	

}
