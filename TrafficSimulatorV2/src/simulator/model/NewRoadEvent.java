package simulator.model;

public abstract class NewRoadEvent extends Event {

	int time;
	String id;
	String srcJun;
	String destJunc;
	int length;
	int co2Limit;
	int maxSpeed;
	Weather weather;
	Junction src;
	Junction dest;
	public NewRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
			{
			super(time); 

			this.id=id;
			this.srcJun=srcJun;
			this.destJunc=destJunc;
			this.length=length;
			this.co2Limit=co2Limit;
			this.maxSpeed=maxSpeed;
			this.weather=weather;
			
			} 

	protected abstract Road createRoad();
	
	@Override
	void execute(RoadMap map) {
		 src = map.getJunction(srcJun);
		 dest = map.getJunction(destJunc);
		 Road r = createRoad();
		 map.addRoad(r);
	}

}
