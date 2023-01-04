package utility;

import java.util.Random;


/**
 * @author Enoch Arulprakash & A. Martin
 *
 */

public class GenerateRandomIntegerWeight {

	 Random r = new Random();
     int rangeMin = 0;
	 int rangeMax = 1;

public GenerateRandomIntegerWeight(int rangeMin, int rangeMax) {
		super();
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
}

public int getRandomNumber() {
	
	 return (int) ((Math.random() * (rangeMax - rangeMin)) + rangeMin);
	
	//return ( this.rangeMin + (rangeMax - rangeMin) * r.nextLong());
	
}







	   


	
}
