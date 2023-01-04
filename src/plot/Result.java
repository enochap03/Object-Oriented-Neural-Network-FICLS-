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
public class Result implements ExampleChart<XYChart> {
	
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

	public Result(TreeMap<String,ArrayList<Double>> parameters, TreeMap<String,ArrayList<Double>>parametersBusinessloss ) {
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
	  ExampleChart<XYChart> exampleChart = new Result(parameters,parametersBusinessloss);
	  XYChart chart = exampleChart.getChart();
	  new SwingWrapper<>(chart).displayChart();
  }

  @Override
  public XYChart getChart() {

	  // Create Chart
	  XYChart chart =
			  new XYChartBuilder().width(1500).height(1000).title("Result Comparision").xAxisTitle("------------------------------------------------------------------------------------------------------------------------------------------------------->   Ephocs   >-------------------------------------------------------------------------------------------------------------------------------------------------------").yAxisTitle("---------------------------------------------------------------------------------------<   Range   <---------------------------------------------------------------------------------------").build();

	  // Customize Chart
	  chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
	  chart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);
	  chart.getStyler().setZoomEnabled(true);
	  
	  //String arr[][] = ObtainOutputValues.getOutputValues(properties.get(ConstantsAndConventions.inputFileName),numOutput);

	  List lossNormal [] = getLoseParameter(getParameters(),numOutput);
	  List <Double>[][]  resNormal= new ArrayList[2][4];
	  List <Double>[] [] resWithBus = new ArrayList[2][4];
	  
	  for(int i=0;i<4;i++) {
		  
		  resNormal[0][i] = new ArrayList<Double>();
		  resNormal[1][i] = new ArrayList<Double>();
		  resWithBus[0][i] = new ArrayList<Double>();
		  resWithBus[1][i] = new ArrayList<Double>();
	  }
	  //System.out.println(lossNormal.size());
	  int frequency =300;
	  for(int index =0;index<numOutput;index++) {
		  for(int i=0;i<lossNormal[index].size();i=i+(4*frequency)) {
			  for(int j=0;j<4;j++) {
				  // double actual = Double.parseDouble(arr[index][j%4]);
				  double dl = (double) lossNormal[index].get(i+j);
				  resNormal[index][j].add(dl); 
			  }

		  }
	  }
	  List lossBusiness [] = getLoseParameter(getParametersBusinessloss(), numOutput);
	  for(int index = 0;index<numOutput;index++) {
		  for(int i=0;i<lossBusiness[index].size();i=i+(4*frequency)) {
			  for(int j=0;j<4;j++) {
				  double dl = (double) lossBusiness[index].get(i+j);
				  resWithBus[index][j].add(dl);

			  }
		  }
	  }
	  String input [] [] = {{"Xor(0,0)","Xor(0,1)", "Xor(1,0),","Xor(1,1)"},{"Xnor(0,0)","Xnor(0,1)", "Xnor(1,0),","Xnor(1,1)"}};
	  
	  String print= "Xor"; int printIndex = -1;
	  if(print.equalsIgnoreCase("xnor"))
		  printIndex = 1;
	  else if(print.equalsIgnoreCase("xor"))
		  printIndex = 0;
	  Color []  color = {Color.black,Color.green,Color.blue,Color.red};
	  for(int i=0;i<4;i++) {
		  
		  ((MarkerSeries) chart.addSeries(input[printIndex][i]+" L2 + Business Loss", getAfterEvery(getParameters().get("input"),frequency), resWithBus[printIndex][i]).setLineColor(color[i])).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(color[i]).setLineStyle(SeriesLines.SOLID).setLineWidth(1);
		  ((MarkerSeries) chart.addSeries(input[printIndex][i]+" L2 Loss",getAfterEvery(getParameters().get("input"),frequency), resNormal[printIndex][i]).setLineColor(color[i])).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(color[i]).setLineStyle(SeriesLines.DASH_DOT).setLineWidth(1);
		  
		  
	  }
	  
	 /* for(int i=0;i<4;i++) {
		  
		  chart.addSeries(input[i], getAfterEvery(getParameters().get("input"),frequency), resWithBus[0][i]).setMarker(SeriesMarkers.CIRCLE).setLineStyle(SeriesLines.DASH_DOT).setLineWidth(1);
		  chart.addSeries(input[i], getAfterEvery(getParameters().get("input"),frequency), resWithBus[1][i]).setMarker(SeriesMarkers.CIRCLE).setLineStyle(SeriesLines.DASH_DOT).setLineWidth(1);
	  }*/
	  
	/*  Color []  color = {Color.black,Color.green};
	  String output [] = outputProperties.split(",");
	  for(int i=0;i<numOutput;i++) { 
	  ((MarkerSeries) chart.addSeries(output[i]+" With Business Loss", getAfterEvery(getParameters().get("input"),frequency), plotBusinessLoss[i]).setLineColor(color[i])).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(color[i]).setLineStyle(SeriesLines.SOLID).setLineWidth(1);
	  ((MarkerSeries) chart.addSeries(output[i]+" Without Business Loss",getAfterEvery(getParameters().get("input"),frequency), plotNormalLoss[i]).setLineColor(color[i])).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(color[i]).setLineStyle(SeriesLines.DASH_DASH).setLineWidth(1);
	  
	  }*/
	  
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
