package layout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import utility.MathCalculation;


// extended layer for incorpration of business rules
public class ExtendedLayerFunction extends Layer {

	
	
	private static final long serialVersionUID = 6280094626287847402L;
	
	
	// constant strings
	public static final String prefixeeStat="\" epochs  for Input combination (\"";
	public static final String prefixrarrow =") -->";
	 public static final String prefixassstat = "after assessing with business rule"; 
	
	 // list of exclusive  nodes
	private List<ExtendedNodeFunction> exNodes = new ArrayList<ExtendedNodeFunction>();


	public List<ExtendedNodeFunction> getExNodes() {
		return exNodes;
	}


	public void setExNodes(List<ExtendedNodeFunction> exNodes) {
		this.exNodes = exNodes;
	}


	public void assertBusinessRule(int a , int b, String properties,int epoch) {
		String [] types = null;
		
		 types = properties.split(","); 
		
		System.out.print(epoch+ prefixeeStat+a+" ,"+b+prefixrarrow);
		for(int i=0;i<getExNodes().size();i++) {
			
			if(i>0)
				System.out.print(",");
			double assertDifference = MathCalculation.absoluteDiffandPercentage(getExNodes().get(i).assertBusinessRule(a,b,types[i]), getExNodes().get(i).getValue());
			System.out.print(" "+types[i]+prefixassstat+String.valueOf(new DecimalFormat("##.##").format(assertDifference)+"%"));
		}
		System.out.println();
		
	}
		
}
