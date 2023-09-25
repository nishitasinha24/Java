package vehicles;

public class Vehicle {
	private static int next_id = 0;
	private int noOfWheels;
	private int cargoSpace;
	private String color;
	private int id;
	
	public Vehicle() {
		Vehicle.next_id += 1;
		this.id = next_id;
	}
	
	public Vehicle(int noOfWheels, int cargoSpace, String color) {
		try {
			Vehicle.next_id += 1;
			this.id = next_id;
			this.noOfWheels = noOfWheels;
			if(cargoSpace < 0) 
				throw new VehicleException("Cargo Space is invalid, cannot be negative");
			this.cargoSpace = cargoSpace;
			this.color = color;}
		catch(VehicleException v) {
			System.out.println(v);
		}
	}
	
	public void setNoOfWheels(int noOfWheels) {
		this.noOfWheels = noOfWheels;
	}
	
	public int getNoOfWheels() {
		return this.noOfWheels;
	}
	
	public void setCargoSpace(int cargoSpace) {
		try {
		if(cargoSpace < 0) 
			throw new VehicleException("Cargo Space is invalid, cannot be negative");
		this.cargoSpace = cargoSpace;}
		catch(VehicleException v) {
			System.out.println(v);
		}
	}
	
	public int getCargoSpace() {
		return this.cargoSpace;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		String s1 = "Class: Vehicle, Fields - Id: " + this.id + " No. of wheels: " + this.noOfWheels + " Cargo Space: " + 
				this.cargoSpace + " Color: " + this.color;
		return s1;
	}
	
	public static boolean equals(Vehicle v1, Vehicle v2) {
		if ((v1.getNoOfWheels() == v2.getNoOfWheels()) && (v1.getCargoSpace() == v2.getCargoSpace()) 
				&& (v1.getColor() == v2.getColor())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String args[]) {
		System.out.println("Inside Vehicle");
	}
}