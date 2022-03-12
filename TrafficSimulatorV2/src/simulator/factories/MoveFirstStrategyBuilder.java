package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MostCrowdedStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy> {
	private static final String MF_STRATEGY ="move_first_dqs";
	public MoveFirstStrategyBuilder() {
		super(MF_STRATEGY);
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		
		MoveFirstStrategy r = new MoveFirstStrategy();
		return r;
		
		
	}

}
 