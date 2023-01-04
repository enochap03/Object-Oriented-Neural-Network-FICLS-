package utility;


/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

public class ComputeGradient {

	public static double sigGrad(double x){

		return (x*(1-x));
	}
	public static double tanhGrad(double x){

		return (1-(Math.pow(Math.tanh(x), 2)));
	}
	public static double reluGrad(double x){	
		if (x>0)
			return 1;
		else
			return 0;
	}
}
