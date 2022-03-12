package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject {

	private List<Road> roads;
	private Map<Junction,Road> junction_roads;
	private List<List<Vehicle>> queueing_list;
	private int nextGreen;
	private int lastGreen;
	private  LightSwitchingStrategy lsStrategy;
	private  DequeuingStrategy dqStrategy;
	private int xCoor, yCoor;
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy
			dqStrategy, int xCoor, int yCoor) throws IllegalArgumentException{
		
			super(id);
			
			if(lsStrategy==null || dqStrategy== null || xCoor<0 || yCoor<0) {
				throw new IllegalArgumentException("Parametros de Junction no validos");
			}
			queueing_list= new ArrayList<>();
			junction_roads = new HashMap<>();
			roads = new ArrayList<>();
			this.lsStrategy=lsStrategy;
			this.dqStrategy=dqStrategy;
			this.xCoor=xCoor;
			this.yCoor=yCoor;  
			nextGreen=-1;
			lastGreen=0;
	} 
	
	void addIncommingRoad(Road r)throws IllegalArgumentException{
		
		if(r.getDest()!=this) {
			
			throw new IllegalArgumentException("Carretera no valida");
		}
		
		roads.add(r);
		LinkedList <Vehicle>l= new LinkedList<>();
		queueing_list.add(l);
		
		
		
	}
	
	void addOutGoingRoad(Road r) throws IllegalArgumentException{
		
		junction_roads.put(r.getDest(), r);
		
		    if(roadTo(r.getDest())!=r) {
		    	throw new IllegalArgumentException("Carreteras no validas.");
		    }
	
		
		    if(r.getSrc()!=this) {
		    	throw new IllegalArgumentException("Carreteras no validas.");
		    }
		
	}
	
	void enter(Vehicle v) { 
		Road r = v.getRoad();
		int i = roads.indexOf(r);
		queueing_list.get(i).add(v);
		
	}
	
	Road roadTo(Junction j) { 
		return this.junction_roads.get(j);
	}
	

	@Override
	void advance(int time) { 
		if(nextGreen!=-1) {
			List<Vehicle> lista = queueing_list.get(nextGreen);
			if(lista.size()!=0) {
				List<Vehicle> l= dqStrategy.dequeue(lista);
				for(Vehicle c : l ) {
					c.moveToNextRoad();
					lista.remove(c);
				}
			
				
			}
			
		}
		int next= lsStrategy.chooseNextGreen(roads, queueing_list,nextGreen, lastGreen, time);
		
		if(nextGreen!=next){
			nextGreen=next;
			lastGreen=time;
		}
	}

	@Override 
	public JSONObject report() {

		JSONObject junct1 = new JSONObject(); 
		
		junct1.put("id",_id);
		if(nextGreen==-1) {
			junct1.put("green" , "none");
		}else {
			junct1.put("green" , roads.get(nextGreen).getId());
		}
		
		
		JSONArray queue = new JSONArray();
		for(int i =0; i< roads.size();i++) {
			JSONObject q1= new JSONObject();
			q1.put("road",roads.get(i).getId());
			List<Vehicle>queueList= queueing_list.get(i);
			JSONArray arr = new JSONArray();
			
			for(Vehicle v : queueList) {
				arr.put(v.getId());
			}
			q1.put("vehicles", arr);
			queue.put(q1);
		}
		
		junct1.put("queues", queue);
	
		
		return junct1;
		
	}
}
