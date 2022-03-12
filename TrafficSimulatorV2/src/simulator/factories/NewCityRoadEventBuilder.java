package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.NewCityRoadEvent;


public class NewCityRoadEventBuilder extends SuperRoadEventBuilder{
	private static final String CR_EVENT ="new_city_road";
	public NewCityRoadEventBuilder() {
		super(CR_EVENT);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		super.createTheInstance(data);
		return new NewCityRoadEvent(time, id, src, dest, length, co2limit, maxspeed, w);
	
	}

}
