package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveAllStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy> {
	private static final String MA_STRATEGY ="move_all_dqs";
	public MoveAllStrategyBuilder() {
		super(MA_STRATEGY);
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		
		MoveAllStrategy r = new MoveAllStrategy();
	
		return r;
		
		
	}

	 
	
}
