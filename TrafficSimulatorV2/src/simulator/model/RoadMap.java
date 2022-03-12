package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {		
	
	private List<Junction> junctionList;
	private List<Road> roadList;
	private List<Vehicle> vehicleList;
	private Map<String,Junction> junctionMap;
	private	Map<String, Road> roadMap;
	private Map<String, Vehicle> vehicleMap; 
	
	RoadMap(){
		junctionList = new ArrayList<>();
		roadList = new ArrayList<>();
		vehicleList = new ArrayList<>();
		junctionMap= new HashMap<>();
		roadMap = new HashMap<>();
		vehicleMap =  new HashMap<>();
	}
	
	void addJunction(Junction j)throws IllegalArgumentException {
		
		for(Junction e: junctionList) {
			if(e.getId().equalsIgnoreCase(j.getId())) {
				throw new IllegalArgumentException("Cruce no valido por ID");
			}
		}
		junctionList.add(j);
		junctionMap.put(j.getId(), j);
	}
	boolean checkJunctions(List<Junction> itinerary) { 
		for(int i =0; i< itinerary.size()-1;i++) {
			if(itinerary.get(i).roadTo(itinerary.get(i+1))==null) {
				return false;
			}
		}
		return true;
	}
	void addVehicle(Vehicle v) throws IllegalArgumentException{
		for(Vehicle e: vehicleList) {
			if(e.getId().equalsIgnoreCase(v.getId())) {
				throw new IllegalArgumentException("Vehiculo no valido por ID");
			}
		}
		if(!checkJunctions(v.getItinerary())) {
			throw new IllegalArgumentException("Vehiculo no valido por itinerario");
		}
		vehicleList.add(v);
		vehicleMap.put(v.getId(),v); 
	}
	
	void addRoad(Road r) throws IllegalArgumentException {
		for(Road ro: roadList) {
			if(ro.getId() .equals(r.getId())) {
				throw new IllegalArgumentException("El id de la carretera ya existia");
			}
		}
		
		if(!junctionList.contains(r.getDest()) || !junctionList.contains(r.getSrc())) {
			throw new IllegalArgumentException("No se pudo anadir la carretera");
		}
		roadList.add(r);
		roadMap.put(r.getId(), r);
	}
	
	public Junction getJunction(String id) {
		return junctionMap.get(id);
	}
	public Road getRoad(String id) {
		return roadMap.get(id);
	}
	
	public Vehicle getVehicles(String id) {
		return vehicleMap.get(id);
	}
	
	public List<Junction>getJunctions(){
		return Collections.unmodifiableList(junctionList);
		
	}
	
	public List<Road>getRoads(){
		return Collections.unmodifiableList(roadList);
	}
	
	public List<Vehicle> getVehicles(){
		return Collections.unmodifiableList(vehicleList);
	}
	void reset() {
		junctionList.clear();
		roadList.clear();
		vehicleList.clear();
		junctionMap.clear(); 
		roadMap.clear();
		vehicleMap.clear();
	}
	
	public JSONObject report() { 
		
		JSONObject j= new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for(Junction junct:junctionList) {
			jArray.put(junct.report());
		}
		j.put("junctions", jArray); 
		
		JSONArray jArray2 = new JSONArray();
		for(Road r:roadList) {
			jArray2.put(r.report());
		}
		j.put("roads", jArray2); 
		
		JSONArray jArray3 = new JSONArray();
		for(Vehicle v:vehicleList) {
			jArray3.put(v.report());
		}
		j.put("vehicles", jArray3); 
		
		return j;
		
	}
}
