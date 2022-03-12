package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event> {
	private static final String SW_EVENT = "set_weather";
	public SetWeatherEventBuilder() {
		super(SW_EVENT);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		Integer time = data.getInt("time");
		JSONArray info = data.getJSONArray("info");
		List<Pair<String,Weather>> lista = new ArrayList<>();
		
		for (int i = 0; i < info.length(); i++) {
			Pair<String,Weather> l;
			
			JSONObject jo = info.getJSONObject(i);
			
			String roadID = jo.get("road").toString();
			String weather = jo.getString("weather");
			Weather w= Weather.valueOf(weather);

			 l =  new Pair(roadID,w);
			 lista.add(l);
		 
		}
		
		if(time==null || lista==null) { 
			throw new IllegalArgumentException("Parametros vacíos");
		}
		
		return new SetWeatherEvent(time,lista);
	}

}