package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event> {
	private static final String SC_EVENT = "set_cont_class";
	public SetContClassEventBuilder() { 
		super(SC_EVENT);
	}
	
	@Override
	protected Event createTheInstance(JSONObject data) throws IllegalArgumentException {
		Integer time = data.getInt("time");
		JSONArray info = data.getJSONArray("info");
		List<Pair<String,Integer>> lista = new ArrayList<>();
		
		
		for (int i = 0; i < info.length(); i++) {
			Pair<String,Integer> l;
			
			JSONObject jo = info.getJSONObject(i);
			
			String vehicleID = jo.get("vehicle").toString();
			Integer clas= jo.getInt("class");
			

			 l =  new Pair(vehicleID,clas);
			 lista.add(l);
		 
		}
		if(time==null || lista==null) { 
			throw new IllegalArgumentException("Parametros vacíos");
		}
		return new SetContClassEvent(time, lista);
		
	}

}
