package corelationcofficient;

public class ComputeCorrelationCofficient {
	
	public static double computeCorrelationCofficient(double x[], double y[], int startPosition) {

		double sumX = 0, sumY=0, sumXY=0;
		double squareSumX=0, squareSumY=0;
		for(int i =0;i<x.length;i++) {

			if(i<startPosition)
				continue;
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			sumXY = sumXY + x[i]*y[i];
			squareSumX = squareSumX + x[i]*x[i];
			squareSumY = squareSumY + y[i]*y[i];

		}

		double corr = (double)((x.length-startPosition) * sumXY - sumX * sumY)/
				(double)(Math.sqrt(((x.length-startPosition) * squareSumX -
						sumX * sumX) * ((x.length-startPosition) * squareSumY - 
								sumY * sumY)));

		return corr;

	}

	/*public static void main(String args[]) {
		
		float x[] = {1,2,3,4,5,6,7,8,9,10};
		float y[] = {3.89f,8.69f,14.56f,18.569f,24.89f,30.256f,33.456f,40,43.29f,55};
		float cor = computeCorrelationCofficient(x,y,4);
		System.out.println("Corelation cofficient "+cor);
	}*/
}
