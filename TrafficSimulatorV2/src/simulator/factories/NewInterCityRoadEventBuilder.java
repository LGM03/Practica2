package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewInterCityRoadEvent;

public class NewInterCityRoadEventBuilder extends SuperRoadEventBuilder {
	private static final String IC_EVENT ="new_inter_city_road";
	public NewInterCityRoadEventBuilder() {
		super(IC_EVENT);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		super.createTheInstance(data);
		return new NewInterCityRoadEvent(time, id, src, dest, length, co2limit, maxspeed, w);
	
	}
}
 