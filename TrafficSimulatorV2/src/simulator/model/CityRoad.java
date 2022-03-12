package simulator.model;

public class CityRoad extends Road {

	private final int STORM_OR_WINDY=10;
	private final int OTHERS=2;
	
	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws IllegalArgumentException {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		
	}

	

	@Override
	void reduceTotalContamination() {
		if(weather==weather.STORM || weather==weather.WINDY ) {
			totalCO2=Math.max(totalCO2-STORM_OR_WINDY,0);
		}else {
			totalCO2=Math.max(totalCO2-OTHERS,0);
		}
		
	}

	@Override
	void updateSpeedLimit() {
		speedLimit=maxSpeed;
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		
		return ((11-v.getContClass())*speedLimit)/11;
	}

}
