package corelationcofficient;

// class computes correlation coefficient between two variable x and y.
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
}
