package implayout;

import java.io.Serializable;
import java.util.Comparator;

public class DescOrder implements Comparator<String>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1126135786452223322L;

	@Override
	public int compare(String o1, String o2) {
		return o2.compareTo(o1);
	}

}
