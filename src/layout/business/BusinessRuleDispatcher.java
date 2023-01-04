package layout.business;

public class BusinessRuleDispatcher {
	
	public BusinessRules getBusinessRules(int a , int b, String type) {

	if(type == null)
		return null; 
	if(type.equalsIgnoreCase("Xor"))
		return  new BuildXorRules(a, b);
	else if(type.equalsIgnoreCase("Xnor"))
		return  new BuildXNorRules(a,b);
	return null;
	}

}
