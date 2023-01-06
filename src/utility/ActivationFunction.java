package utility;

// contains list of activation function
public class ActivationFunction {


	public static double sigmoid(double x) {
		return (1/( 1 + Math.pow(Math.E,(-1*x))));

	}
	
	public static double tanh(double x) {
		return (Math.tanh(x));
		
	}
	public static double relu(double x) {
		
		return Math.max(0, x);
	}

}
