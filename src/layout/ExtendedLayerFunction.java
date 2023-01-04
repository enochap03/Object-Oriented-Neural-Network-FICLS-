package layout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import layout.business.BuildXorRules;
import utility.MathCalculation;

public class ExtendedLayerFunction extends Layer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6280094626287847402L;
	
	private List<ExtendedNodeFunction> exNodes = new ArrayList<ExtendedNodeFunction>();


	public List<ExtendedNodeFunction> getExNodes() {
		return exNodes;
	}


	public void setExNodes(List<ExtendedNodeFunction> exNodes) {
		this.exNodes = exNodes;
	}


	public void assertBusinessRule(int a , int b, String properties,int ephoc) {
		String [] types = null;
		
		 types = properties.split(","); 
		
		System.out.print("After "+ephoc+ " ephocs  for Input combination ("+a+" ,"+b+") -->" );
		for(int i=0;i<getExNodes().size();i++) {
			
			if(i>0)
				System.out.print(",");
			double assertDifference = MathCalculation.absoluteDiffandPercentage(getExNodes().get(i).assertBusinessRule(a,b,types[i]), getExNodes().get(i).getValue());
			System.out.print(" "+types[i]+" after assessing with business rule "+String.valueOf(new DecimalFormat("##.##").format(assertDifference)+"%"));
		}
		System.out.println();
		
	}
		
}
