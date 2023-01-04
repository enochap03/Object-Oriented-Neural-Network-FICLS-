package utility;

import java.util.Random;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

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
