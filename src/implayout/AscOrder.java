package implayout;

import java.io.Serializable;
import java.util.Comparator;

//class to sort object in ascending order
public class AscOrder implements Comparator<String>,Serializable {


	private static final long serialVersionUID = 1126135786452223322L;

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
