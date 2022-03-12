package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {
	private static final String RR_EVENT = "round_robin_lss";
	public RoundRobinStrategyBuilder() { 
		super(RR_EVENT);
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) throws IllegalArgumentException {
	
		if(data!= null && data.has("timeslot")) {
			Integer i = Integer.valueOf(data.getInt("timeslot")); 
			return new RoundRobinStrategy(i);
		} 
		
		return new RoundRobinStrategy(1); 
	}

}
 