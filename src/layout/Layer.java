package layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enoch Arulprakash & A. Martin
 *
 */


public class Layer implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780372540280435227L;
	private List<Node> nodes = new ArrayList<Node>();



	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Layer [nodes=" + nodes + "]";
	}

	
	

}
