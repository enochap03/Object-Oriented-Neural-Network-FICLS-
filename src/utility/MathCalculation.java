package utility;


// calculated absolute difference percentage between a & b
public class MathCalculation {
	
	public static double absoluteDiffandPercentage(double a, double b) {
	
		return  (1-Math.abs(a - b))*100;
	
	}

}
