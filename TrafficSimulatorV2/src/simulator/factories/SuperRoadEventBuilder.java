package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Weather;

public class SuperRoadEventBuilder extends Builder<Event> {   

	Integer time ;
	String id;
	String src;
	String dest;
	Integer co2limit ;
	Integer length;
	Integer maxspeed;
	Weather w;
	
	SuperRoadEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws IllegalArgumentException{
	
		time = data.getInt("time");
		id= data.getString("id"); 
		src= data.getString("src"); 
		dest= data.getString("dest"); 
		co2limit = data.getInt("co2limit");
		length = data.getInt("length");
		maxspeed = data.getInt("maxspeed");
		
		String weather = data.getString("weather");
		 w= Weather.valueOf(weather);
 
		if(time==null||id==null||src==null||dest==null||co2limit==null||length==null||maxspeed==null) { 
			throw new IllegalArgumentException("Parametros no validos");
		} 
		
		
		return null;
	}

}
