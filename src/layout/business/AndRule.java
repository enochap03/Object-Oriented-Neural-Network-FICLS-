package layout.business;

public class AndRule implements BusinessRules {

	private int a;
	private int b;
	
	public AndRule(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override
	public int assertBusinessRule() {
		// TODO Auto-generated method stub
		return a&b;
	}

}
