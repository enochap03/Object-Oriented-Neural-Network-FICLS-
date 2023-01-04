package plot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProcessPlot implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -949683978204819889L;
	private ArrayList<PlotInfo> plotDetails = new ArrayList<PlotInfo>();
	private Date dateTime = null;
	private  int inputSize;
	private int outputSize;
	

	public ProcessPlot(int inputSize, int outputSize) {
		super();
		this.inputSize = inputSize;
		this.outputSize = outputSize;
	}
	public ArrayList<PlotInfo> getPlotDetails() {
		return plotDetails;
	}
	public void setPlotDetails(ArrayList<PlotInfo> plotDetails) {
		this.plotDetails = plotDetails;
	}
	public void addPlotDetails(int ephocs, double input[],double output) {
		
		plotDetails.add(new PlotInfo(ephocs,getInputSize(),input,getOutputSize(),output));
	}
	public PlotInfo getPlotDetails(int index) {
		
		return plotDetails.get(index);
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "ProcessPlot [plotDetails=" + plotDetails + "]";
	}
	public int getInputSize() {
		return inputSize;
	}
	public int getOutputSize() {
		return outputSize;
	}
	

}
