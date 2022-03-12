package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {
	private static final String VE_EVENT ="new_vehicle";
	public NewVehicleEventBuilder() {
		super(VE_EVENT);
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws IllegalArgumentException {
		
		Integer time = data.getInt("time");
		String id = data.getString("id");
		Integer maxSpeed = data.getInt("maxspeed");
		Integer contClass = data.getInt("class");
		JSONArray itinerary = data.getJSONArray("itinerary");
		List<String> lista=new ArrayList<>();
		
		for (int i = 0; i < itinerary.length(); i++) {
			lista.add(itinerary.getString(i));  
		}
		
		if(time==null||id==null||maxSpeed==null||contClass==null||lista==null) { 
			throw new IllegalArgumentException("Parametros vacíos");
		}
		
		return new NewVehicleEvent(time, id, maxSpeed, contClass, lista);
	}

}
