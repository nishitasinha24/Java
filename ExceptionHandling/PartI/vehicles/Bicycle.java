package vehicles;

public class Bicycle extends Vehicle{
	private boolean isElectric;
	
	public Bicycle() {}
	
	public Bicycle(int noOfWheels, int cargoSpace, String color, boolean isElectric) {
		super(noOfWheels, cargoSpace, color);
		try {
			if(noOfWheels != 2) {
				throw new VehicleException("No. of wheels is invalid, bicycle should have 2 wheels");
			}
			if(cargoSpace != 0) {
				throw new VehicleException("Cargo space is invalid, bicycle should have 0 cargo space");
			}
			this.isElectric = isElectric;
		}
		catch(VehicleException e) {
			System.out.println(e);
		}
	}
	
	public void setIsElectric(boolean isElectric) {
		this.isElectric = isElectric;
	}
	
	public boolean getIsElectric() {
		return this.isElectric;
	}
	
	public String pedal() {
		return "Pedaling";
	}
	
	public String toString() {
		String vehicle = super.toString();
		String s1 = vehicle + " Subclass Name: Bicycle, Fields - Is Electric: " + this.isElectric;
		return s1;
	}
	
	public static boolean equals(Bicycle b1, Bicycle b2) {
		if ((b1.getNoOfWheels() == b2.getNoOfWheels()) && (b1.getCargoSpace() == b2.getCargoSpace()) && 
				(b1.getColor() == b2.getColor()) && (b1.getIsElectric() == b2.getIsElectric())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String args[]) {
		Bicycle b1 = new Bicycle(2, 0, "black", true);
		Bicycle b2 = new Bicycle(2, 0, "blue", false);
		System.out.println(b1.equals(b2));
	}
}