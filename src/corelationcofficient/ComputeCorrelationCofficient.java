package corelationcofficient;

// class computes correlation coefficient between two variable x and y.
public class ComputeCorrelationCofficient {
	
	public static double computeCorrelationCofficient(double xValues[], double yValues[], int startPosition) {

		double sumationX = 0, sumationY=0, sumationXY=0;
		double squareSumationX=0, squareSumationY=0;
		for(int i =0;i<xValues.length;i++) {

			if(i<startPosition)
				continue;
			sumationX = sumationX + xValues[i];
			sumationY = sumationY + yValues[i];
			sumationXY = sumationXY + xValues[i]*yValues[i];
			squareSumationX = squareSumationX + xValues[i]*xValues[i];
			squareSumationY = squareSumationY + yValues[i]*yValues[i];

		}

		double corrCoff = (double)((xValues.length-startPosition) * sumationXY - sumationX * sumationY)/
				(double)(Math.sqrt(((xValues.length-startPosition) * squareSumationX -
						sumationX * sumationX) * ((xValues.length-startPosition) * squareSumationY - 
								sumationY * sumationY)));

		return corrCoff;

	}
}
