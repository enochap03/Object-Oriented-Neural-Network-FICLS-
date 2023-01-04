package layout;

import java.lang.Math;

import layout.business.BusinessRuleDispatcher;
import layout.business.BusinessRules;

public class ExtendedNodeFunction extends Node {

	//extended neuron contains business prameters and rules
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private double business_loss;
		public ExtendedNodeFunction() {
			super();
		}
		public double  assertBusinessRule(int a, int b,String type) {
			
			BusinessRules brules;
			BusinessRuleDispatcher rleDispatcher = new BusinessRuleDispatcher();
			brules = rleDispatcher.getBusinessRules(a,b,type);
			return brules.assertBusinessRule();
			//return((1-Math.abs(brules.assertBusinessRule() - getValue()))*100);
	
		}
		public double getBusiness_loss() {
			return business_loss;
		}
		public void setBusiness_loss(double business_loss) {
			this.business_loss = business_loss;
		}
		
		
		


}
