package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
	String id;
	int maxSpeed;
	int contClass;
	List<String> itinerary;
	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
			super(time);
			this.id=id;
			this.maxSpeed = maxSpeed;
			this.contClass=contClass;
			this.itinerary=itinerary;
	}

	@Override 
	void execute(RoadMap map) {
		List<Junction>listaCruces = new ArrayList<>();
		for(String id : itinerary) {
			listaCruces.add(map.getJunction(id));
		}

		Vehicle v =new Vehicle(id, maxSpeed, contClass, listaCruces);
		v.moveToNextRoad();
		map.addVehicle(v);
	}

}
