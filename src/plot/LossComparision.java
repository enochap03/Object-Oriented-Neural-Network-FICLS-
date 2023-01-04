package plot;


import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.internal.series.MarkerSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import implayout.EstablishConnection;
import neuralnetworks.properies.ConstantsAndConventions;
import neuralnetworks.properies.NeuralNetworkDispatcher;
import neuralnetworks.properies.NeuralNetworkSequenceAndProperties;

/**
 * Millisecond Scale
 *
 * <p>Demonstrates the following:
 *
 * <ul>
 *   <li>Millisecond Scale
 *   <li>LegendPosition.OutsideS
 *   <li>Two YAxis Groups - both on left
 *   <li>Zooming by dragging a selection box over area of interest
 */
public class LossComparision implements ExampleChart<XYChart> {
	
	 private static Properties p = null;
	 private  static String fileName;
	 private static String  fileNameBusiness;
	private static  HashMap<String, String> properties = null;
	private TreeMap<String,ArrayList<Double>> parametersBusinessloss =null;
	private TreeMap<String,ArrayList<Double>> parameters =null;
	public static int numOutput = 0;
	public static String outputProperties;
	


	public TreeMap<String, ArrayList<Double>> getParameters() {
			return parameters;
		}

		public void setParameters(TreeMap<String, ArrayList<Double>> parameters) {
			this.parameters = parameters;
		}

		
	public TreeMap<String, ArrayList<Double>> getParametersBusinessloss() {
			return parametersBusinessloss;
		}

		public void setParametersBusinessloss(TreeMap<String, ArrayList<Double>> parametersBusinessloss) {
			this.parametersBusinessloss = parametersBusinessloss;
		}

	public LossComparision(TreeMap<String,ArrayList<Double>> parameters, TreeMap<String,ArrayList<Double>>parametersBusinessloss ) {
			super();
			this.parameters = parameters;
			this.parametersBusinessloss = parametersBusinessloss;
			//System.out.println(parameters);
		}
	
	
	

  public static void main(String[] args) {

	  
	  
	 
	  FileReader reader;
	  try {
		  reader = new FileReader("parameters.properties");

		  p =new Properties();
		  p.load(reader); 
	  } catch (IOException e) {
		
		e.printStackTrace();
	} 
	  String neuralNetworkName = p.getProperty("neural_network_name");
	  NeuralNetworkDispatcher nnd = new NeuralNetworkDispatcher();
	  NeuralNetworkSequenceAndProperties nns = nnd.getNeuralNetwork(neuralNetworkName);
	  numOutput = new EstablishConnection().getNumberOfOutput(nns.getSequence()); 
	  properties = nns.getProperties();

	  fileName = properties.get(ConstantsAndConventions.graphParameters);
	  fileNameBusiness =  properties.get(ConstantsAndConventions.graphParametersWithBusinessLoss);
	  outputProperties = properties.get(ConstantsAndConventions.outputProperties);
	  TreeMap<String,ArrayList<Double>> parameters = SaveLoadPlotParametersDetails.getPlotDetailsFromDesk(fileName);
	  TreeMap<String,ArrayList<Double>> parametersBusinessloss = SaveLoadPlotParametersDetails.getPlotDetailsFromDesk(fileNameBusiness);
	  ExampleChart<XYChart> exampleChart = new LossComparision(parameters,parametersBusinessloss);
	  XYChart chart = exampleChart.getChart();
	  new SwingWrapper<>(chart).displayChart();
  }

  @Override
  public XYChart getChart() {

	  // Create Chart
	  XYChart chart =
			  new XYChartBuilder().width(1500).height(1000).title("Loss Comparision").xAxisTitle("------------------------------------------------------------------------------------------------------------------------------------------------------->   Ephocs   >-------------------------------------------------------------------------------------------------------------------------------------------------------").yAxisTitle("---------------------------------------------------------------------------------------<   Loss   <---------------------------------------------------------------------------------------").build();

	  // Customize Chart
	  chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
	  chart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);
	  chart.getStyler().setZoomEnabled(true);
	  
	  String arr[][] = ObtainOutputValues.getOutputValues(properties.get(ConstantsAndConventions.inputFileName),numOutput);

	  List resNormal [] = getLoseParameter(getParameters(),numOutput);
	  List <Double>[]  plotNormalLoss= new ArrayList[numOutput];
	  List <Double>[] plotBusinessLoss = new ArrayList[numOutput];
	  //System.out.println(lossNormal.size());
	  int frequency =300;
	 
	  for(int index = 0;index<numOutput;index++) {
		  plotNormalLoss[index] = new ArrayList<Double>();
		  
		  for(int i=0;i<resNormal[index].size();i=i+(4*frequency)) {

			 double loss = 0;
			  for(int j=0;j<4;j++) {
				  double targetOutput = Double.parseDouble(arr[index][j%4]);
				  double obtained = (double) resNormal[index].get(i+j);
				  loss = loss + Math.abs(targetOutput- obtained);
				  //System.out.println("diff "+diff);

				  //System.out.println(loss);

			  }
			  //System.out.println("-------------------------------------------------------");

			  double average = loss/4;
			  //System.out.println("Average "+i+" "+average);
			  plotNormalLoss[index].add(average);
		  }
	  }
	  //System.out.println(plotNormalLoss.size());
	  
	  List resBusiness [] = getLoseParameter(getParametersBusinessloss(), numOutput);

	  for(int index = 0;index<numOutput;index++) {

		  plotBusinessLoss[index] = new ArrayList<Double>();
		  for(int i=0;i<resBusiness[index].size();i=i+(4*frequency)) {

			  double loss = 0;
			  for(int j=0;j<4;j++) {
				  double targetOutput = Double.parseDouble(arr[index][j%4]);
				  double obtained = (double) resBusiness[index].get(i+j);
				  loss = loss + Math.abs(targetOutput- obtained);

			  }


			  double average = loss/4;
			  plotBusinessLoss[index].add(average);
		  }
	  }
	  
	  Color []  color = {Color.blue,Color.red};
	  String output [] = outputProperties.split(",");
	  for(int i=0;i<numOutput;i++) { 
	  ((MarkerSeries) chart.addSeries(output[i]+" L2 + Business Loss", getAfterEvery(getParameters().get("input"),frequency), plotBusinessLoss[i]).setLineColor(color[i])).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(color[i]).setLineStyle(SeriesLines.SOLID).setLineWidth(1);
	  ((MarkerSeries) chart.addSeries(output[i]+" L2 Loss",getAfterEvery(getParameters().get("input"),frequency), plotNormalLoss[i]).setLineColor(color[i])).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(color[i]).setLineStyle(SeriesLines.DASH_DASH).setLineWidth(1);
	  
	  }
	  return chart;
  }
  
  public ArrayList<Double> getAfterEvery(ArrayList<Double> param, int freq) {
	  
	  ArrayList<Double> finalRes = new ArrayList<Double>();
	  for(int i=0;i<param.size();i=i+freq) {
		  
		  finalRes.add(param.get(i));
		  
	  }
	return finalRes;
	  
	  
	  
  }

  public ArrayList<Double>[] getLoseParameter(TreeMap<String,ArrayList<Double>> parameter,int outPut) {
	  Iterator hmIterator = parameter.entrySet().iterator(); 
	  ArrayList<Double> plotArray [] =  new ArrayList[outPut];

	  int index =1;
	  while (hmIterator.hasNext()) { 
		  String coma=",";
		  Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
		  String key = (String)mapElement.getKey();
		  ArrayList<Double> value = (ArrayList<Double>)mapElement.getValue();
		  if(key.contains("RES")) {
			 //System.out.println(value);
			  plotArray [outPut-index] = new ArrayList<Double>();
			  plotArray [outPut-index]  = value;
			  index++;
		  }
	  }
	  return plotArray;
  }
	 


  @Override
  public String getExampleChartName() {

    return getClass().getSimpleName() + " - Millisecond Scale with Two Separate Y Axis Groups";
  }
}
