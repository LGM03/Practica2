package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	protected  Junction src;
	protected  Junction dest;
	protected  int maxSpeed; //general
	protected  int speedLimit;//actual
	protected  int contLimit;
	protected  int totalCO2;
	protected  int length;
	protected  List<Vehicle> vehicleList; 
	protected  Weather weather;
	protected Ordenar miOrden;
	
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws IllegalArgumentException {
			super(id);
			if(maxSpeed<=0||contLimit<0||length<=0|| weather==null||srcJunc==null||destJunc==null){ 
				throw new IllegalArgumentException("Parámetros de carretera no validos");
			}
			
			src=srcJunc;
			dest= destJunc;
			this.maxSpeed=maxSpeed;
			this.contLimit=contLimit;
			this.length=length;
			this.weather=weather;
			vehicleList= new ArrayList<Vehicle>();
			totalCO2=0;
			speedLimit=maxSpeed;
			miOrden = new Ordenar(); 
			src.addOutGoingRoad(this);
			dest.addIncommingRoad(this);
	}

	void enter(Vehicle v) throws IllegalArgumentException  {
		if(v.getSpeed()!=0 || v.getLocation()!=0) {
			throw new IllegalArgumentException("Entrada a la carretera no valida");
		}
		
		vehicleList.add(v);
	}
	
	void exit(Vehicle v) {
		vehicleList.remove(v);
	}
	
	void setWeather(Weather w) throws IllegalArgumentException {
		if(w==null) {
			throw new IllegalArgumentException("Clima no valido");
		}
		weather=w;
	}
	
	void addContamination(int c) throws IllegalArgumentException {
		if(c<0) {
			throw new IllegalArgumentException("CO2 no valido");
		}
		totalCO2+=c;
	}
	
	abstract void reduceTotalContamination();
	
	abstract void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	@Override
	void advance(int time) {
		reduceTotalContamination();
		updateSpeedLimit();
		
		for(Vehicle v: vehicleList) {
			v.setSpeed(calculateVehicleSpeed(v));
			v.advance(time);
		}

		vehicleList.sort(miOrden);
	}
	public static class Ordenar implements Comparator <Vehicle>{
		@Override
		public int compare(Vehicle v1, Vehicle v2) {
			if(v1.getLocation()>v2.getLocation()) {
				return -1;
			}else if(v1.getLocation()<v2.getLocation()) {
				return 1;
			}
			return 0;
		}
	}
	
	@Override
	public JSONObject report() {
		JSONObject road1 = new JSONObject(); 
		JSONArray roadArray = new JSONArray();
		road1.put("id",_id);
		road1.put("speedlimit" , speedLimit);
		road1.put("weather" ,weather.toString());
		road1.put("co2",totalCO2);
		for(Vehicle e : vehicleList){
			roadArray.put(e._id);
		}
		road1.put("vehicles" , roadArray);
		return road1;
	}

	public Junction getSrc() {
		return src;
	}

	public Junction getDest() {
		return dest;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getSpeedLimit() {
		return speedLimit;
	}

	public int getContLimit() {
		return contLimit;
	}

	public int getTotalCO2() {
		return totalCO2;
	}

	public int getLength() {
		return length;
	}

	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(vehicleList);
	}

	public Weather getWeather() {
		return weather;
	}

	
	
	
}
