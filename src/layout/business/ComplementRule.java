package layout.business;

//subrule complement class
public class ComplementRule implements BusinessRules {

	private int a;
	public ComplementRule(int a) {
		super();
		this.a = a;
	}


	@Override
	public int assertBusinessRule() {
		// TODO Auto-generated method stub
		if(a==0)
			return 1;
		else 
		return 0;
	}

}
