package layout.business;

public class OrRule implements BusinessRules {

	private int a;
	private int b;
	
	public OrRule(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int assertBusinessRule() {
		// TODO Auto-generated method stub
		return a|b;
	}

}
