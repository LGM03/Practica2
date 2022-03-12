package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy> {

	private static final String MC_STRATEGY ="most_crowded_lss";
	public MostCrowdedStrategyBuilder() {
		super(MC_STRATEGY);
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) throws IllegalArgumentException {
		
		
		if(data!= null && data.has("timeslot")) {
			Integer i = Integer.valueOf(data.getInt("timeslot")); 
			return new MostCrowdedStrategy(i);
		} 
		 
		return new MostCrowdedStrategy(1);
		
	
	}

}
