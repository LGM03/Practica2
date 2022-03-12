package simulator.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONObject;

public class Vehicle extends SimulatedObject{

	private int maxSpeed;
	private int speed;
	private VehicleStatus status;
	private Road road;
	private int location;
	private int contClass; 
	private int totalCO2;
	private int distance;
	private List<Junction> itinerary;


	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws IllegalArgumentException{
			super(id);                                                            
			if (maxSpeed<=0 || contClass<0|| contClass>10|| itinerary.size() <2) { 
				
				throw new IllegalArgumentException("Parametros de coche no validos");
			}
			this.maxSpeed=maxSpeed;
			this.contClass=contClass;
			status = status.PENDING;
			location =0 ;
			totalCO2=0;
			this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
			
	}

	@Override
	void advance(int time) {
	
		if(status == status.TRAVELING) {
			int location2;
			location2 = Math.min(location+speed, road.getLength());
			int c = contClass*(location2-location);
			distance += location2-location;
			location=location2;
			totalCO2+=c;
			road.addContamination(c);
			if(location>=road.getLength()) { 
				road.getDest().enter(this);
				status = status.WAITING;
				speed=0;
			}
		}
	}

	void moveToNextRoad() throws IllegalArgumentException{
		                                                  
		int i; 
		if(status!=status.PENDING  && status!=status.WAITING ) {
			throw new IllegalArgumentException("Ruta no valida");
		}
		if(status==status.PENDING) { 
			i=0;
		} 
		else {
			i = itinerary.indexOf(road.getDest());
		}
		if(i+1<itinerary.size()) {
			Junction j = itinerary.get(i);
			
			Junction j1 = itinerary.get(i+1);
			if(status==status.WAITING) {
				road.exit(this);
				
			}
			road= j.roadTo(j1);
			if(road!=null) {
				location=0;
				road.enter(this);
				status= status.TRAVELING;
					 
			}
		}else { 
			road.exit(this);
			status= status.ARRIVED;
		}
	}
	@Override
	public JSONObject report() {
		JSONObject car1 = new JSONObject(); 
		car1.put("id",_id);
		car1.put("speed" , speed);
		car1.put("distance" , distance);
		car1.put("co2",totalCO2);
		car1.put("class",contClass );
		car1.put("status",status.toString());
		if(status!=status.PENDING &&  status!=status.ARRIVED ) {
			car1.put("road" , road._id);
			car1.put("location" ,location);
		}
		return car1;
	}

	void setSpeed(int s) throws IllegalArgumentException {
		if(s<0) {
			throw new IllegalArgumentException("Velocidad no valida");
		}
		if(status==status.TRAVELING) {
			speed = Math.min(s,maxSpeed);
		}
		
	}
 
	void setContClass(int c) throws IllegalArgumentException  {
		if(c<0 || c>10) {
			throw new IllegalArgumentException("Contaminacion no valida");
		}
		this.contClass = c;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public Road getRoad() {
		return road;
	}

	public int getLocation() {
		return location;
	}

	public int getContClass() {
		return contClass;
	}

	public int getTotalCO2() {
		return totalCO2;
	}

	public List<Junction> getItinerary() {
		return itinerary;
	}

	

}
