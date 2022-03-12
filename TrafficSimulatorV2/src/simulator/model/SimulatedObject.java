package simulator.model;

import org.json.JSONObject;

public abstract class SimulatedObject {

	protected String _id;

	SimulatedObject(String id) throws IllegalArgumentException {
		if(id==null || id.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("_id no valido");
		}
		_id = id;
	}

	public String getId() {
		return _id;
	}

	@Override
	public String toString() {
		return _id; 
	}

	abstract void advance(int time);

	abstract public JSONObject report();
}
