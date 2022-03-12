package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event> {
	
	private static final String NJ_EVENT ="new_junction";
	Factory<LightSwitchingStrategy> lssFactory;
	Factory<DequeuingStrategy> dqsFactory;
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory){
		super(NJ_EVENT);
		this.lssFactory=lssFactory;
		this.dqsFactory=dqsFactory;
	}
	
	@Override
	protected Event createTheInstance(JSONObject data) throws IllegalArgumentException {
		
		if(data== null || !data.has("time") || !data.has("id") || !data.has("coor") || !data.has("ls_strategy")|| !data.has("dq_strategy")) {
			throw new IllegalArgumentException("fallo instancia new Junction even builder");
		} 
		 
		Integer time = data.getInt("time"); 
		Integer xCoor, yCoor;
		
		 
		String id = data.getString("id");

		JSONArray j = data.getJSONArray("coor");
				
		xCoor= j.getInt(0);
		yCoor= j.getInt(1);
	
		LightSwitchingStrategy lss = lssFactory.createInstance(data.getJSONObject("ls_strategy"));
		DequeuingStrategy dqs= dqsFactory.createInstance(data.getJSONObject("dq_strategy"));
		
		if(time==null|| id==null || xCoor==null || yCoor==null) {
			throw new IllegalArgumentException("Parametros no validos");
		}
		return new NewJunctionEvent(time, id, lss,  dqs, xCoor,  yCoor);
	}
}
