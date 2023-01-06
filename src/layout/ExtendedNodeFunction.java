package layout;


import layout.business.BusinessRuleDispatcher;
import layout.business.BusinessRules;

// extended node for embedding  business rule
public class ExtendedNodeFunction extends Node {


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
		}
		public double getBusiness_loss() {
			return business_loss;
		}
		public void setBusiness_loss(double business_loss) {
			this.business_loss = business_loss;
		}
		
		
		


}
