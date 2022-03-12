package simulator.model;
import java.util.List;
import simulator.misc.Pair;

public class SetContClassEvent extends Event{

	private List<Pair<String,Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		if(cs==null) {
			throw new IllegalArgumentException("Par String-Integer no valido");
		}
			this.cs=cs;
		
		}

	
	@Override
	void execute(RoadMap map)  throws IllegalArgumentException{
		for(Pair<String,Integer> c: cs) {
			if (map.getVehicles(c.getFirst())==null) {
				throw new IllegalArgumentException("No existen vehiculos a los que asignar contaminacion");
			}
			map.getVehicles(c.getFirst()).setContClass(c.getSecond());
		} 
		
	}

}
