package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy {

	int timeSlot;
	public MostCrowdedStrategy(int timeSlot){
		this.timeSlot=timeSlot;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		
		if(roads.size()==0) {
			return -1;
		}if(currGreen==-1) {
			
			int max=-1,pos=0;
			for(List<Vehicle> c :qs) {
				if(c.size()>max) {
					max=c.size();
					pos = qs.indexOf(c);
				}
			}
			return pos;
		}else if(currTime-lastSwitchingTime <timeSlot) {
			return currGreen;
			
		}else{
			int max=-1,pos=0;
			for(int i =0; i<roads.size(); i++) {
				int a = ((currGreen+1)+i)%roads.size() ;
				if(qs.get(a).size()>max) {
					max=qs.get(a).size();
					pos=a;
				}
			}
			
			return pos;
		}
	
		
		
	}

}
