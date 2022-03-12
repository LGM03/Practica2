package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	private TrafficSimulator sim;
	private Factory<Event> eventsFactory;
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) throws IllegalArgumentException{
		if(sim==null || eventsFactory==null) {
			throw new IllegalArgumentException("Parametros de controlador no validos");
		}
		this.sim=sim;
		this.eventsFactory=eventsFactory;
	}

	public void loadEvents(InputStream in) throws IllegalArgumentException{
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja= new JSONArray();
		ja= jo.getJSONArray("events");
		if(ja==null) {
			throw new IllegalArgumentException("Fallo en load Events");
		}
		for(int i=0; i<ja.length();i++) {
			Event e = eventsFactory.createInstance(ja.getJSONObject(i));
			sim.addEvent(e);
		}
	}
	
	public void run(int n, OutputStream out) {  
		
		JSONArray ja= new JSONArray();
		
		for(int i=0;i<n;i++) {
			sim.advance();
			ja.put(sim.report());
		}
		
		JSONObject jo= new JSONObject();
		jo.put("states", ja);
		PrintStream p= new PrintStream(out);
		p.print(jo.toString(2));
		
	}
	
	public void reset() {
		
		sim.reset();
		
	}
}
