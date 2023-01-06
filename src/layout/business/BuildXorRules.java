package layout.business;

import java.io.Serializable;

//specific rule XOR rule class
public class BuildXorRules implements BusinessRules,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int a;
	private int b;
	
	public BuildXorRules(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int assertBusinessRule() {
		
		
		return new OrRule(new AndRule(new ComplementRule(a).assertBusinessRule(),b).assertBusinessRule(), new AndRule(a,new ComplementRule(b).assertBusinessRule()).assertBusinessRule()).assertBusinessRule();
		
	}
	
	public static void main(String args[]) {
		double value = 0;
		double bvalue = new BuildXorRules(1,1).assertBusinessRule();
		double absvalue=  Math.abs(value-bvalue);
		double reached = (1-absvalue)*100;
		System.out.println("Achieved so far after assessing with business rule "+reached+"%");
	}

}
