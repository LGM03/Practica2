package simulator.model;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator{

	private RoadMap r;
	private SortedArrayList<Event> eventList;
	private int time;

	public TrafficSimulator(){  
		time=0;
		r = new RoadMap();
		eventList=new SortedArrayList<>();

	} 
	
	public void addEvent(Event e){
		eventList.add(e);
	}
	
	public void advance() {
		time+=1;
		SortedArrayList<Event> listaAux= new SortedArrayList<>(); 
		for(Event e: eventList) {
			if(e.getTime()==time) {
				e.execute(r);
				
			}else {
				listaAux.add(e);
			}
		} 
		eventList=listaAux;
		List<Junction> junctionList = r.getJunctions();
		for(Junction j : junctionList) {
			j.advance(time);
		}
		List<Road> roadList = r.getRoads();
		for(Road r: roadList) {
			r.advance(time);
		}
	}
	
	public void reset() {
		r.reset();
		eventList.clear();
		time=0;
	}
	 
	public JSONObject report() {
		
		JSONObject j = new JSONObject();
		j.put("time",time);
		j.put("state",r.report());
		
		return j;
		
	}
	
}
