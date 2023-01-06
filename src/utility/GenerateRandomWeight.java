package utility;

import java.util.Random;

//random number generation  between 0 and 1 

public class GenerateRandomWeight {

	 Random r = new Random();
     double rangeMin = 0f;
	 double rangeMax = 1f;

public GenerateRandomWeight(double rangeMin, double rangeMax) {
		super();
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
}

public double getRandomNumber() {
	
	return ( this.rangeMin + (rangeMax - rangeMin) * r.nextDouble());
	
}









	   


	
}
