package layout.business;


// specific rule XNOR rule class
import java.io.Serializable;

public class BuildXNorRules implements BusinessRules,Serializable {


	private static final long serialVersionUID = 1L;
	private int a;
	private int b;
	
	public BuildXNorRules(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	

	@Override
	public int assertBusinessRule() {
		
		
		return new OrRule(new AndRule(new ComplementRule(a).assertBusinessRule(), new ComplementRule(b).assertBusinessRule()).assertBusinessRule(), new AndRule(a,b).assertBusinessRule()).assertBusinessRule();
		
	}
	
}
