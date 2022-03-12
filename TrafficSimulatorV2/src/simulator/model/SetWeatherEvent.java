 package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {
	private List<Pair<String,Weather>> ws;
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) throws IllegalArgumentException {
		super(time);
		if(ws==null) {
			throw new IllegalArgumentException("Weather no valido");
		}
		this.ws = ws;
		
	}
	@Override
	void execute(RoadMap map)throws IllegalArgumentException {
		for(Pair<String,Weather> w : ws) {
			if(map.getRoad(w.getFirst())==null) {
				throw new IllegalArgumentException("No existe ninguna carretera a la que asociar un Weather");
			}
			map.getRoad(w.getFirst()).setWeather(w.getSecond());
		}
		
	}

}
