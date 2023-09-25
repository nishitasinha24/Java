package vehicles;

public class Car extends Vehicle{
	private int noOfDoors;
	
	public Car() {}
	
	public Car(int noOfWheels, int cargoSpace, String color, int noOfDoors) {
		super(noOfWheels, cargoSpace, color);
		try {
			if(noOfDoors < 2) {
				System.out.println("Inside try block");
				throw new VehicleException("No. of doors is invalid, car can't have less than 2 doors");
			}
			this.noOfDoors = noOfDoors;
		}
		catch(VehicleException e) {
			System.out.println(e);
		}
	}
	
	public int getNoOfDoors() {
		return this.noOfDoors;
	}
	
	public void setNoOfDoors(int noOfDoors) {
		try {
			if(noOfDoors < 2) {
				throw new VehicleException("No. of doors is invalid, car can't have less than 2 doors");
			}this.noOfDoors = noOfDoors;
		}
		catch(VehicleException e) {
			System.out.println(e);
		}
	}
	
	public String pressGasPedal() {
		return "accelerating";
	}
	
	public String toString() {
		String vehicle = super.toString();
		String s1 = vehicle + " Subclass Name: Car, Fields - Number of doors: " + this.noOfDoors;
		return s1;
	}
	
	public static boolean equals(Car c1, Car c2) {
		if ((c1.getNoOfWheels() == c2.getNoOfWheels()) && (c1.getCargoSpace() == c2.getCargoSpace()) && 
				(c1.getColor() == c2.getColor()) && (c1.getNoOfDoors() == c2.getNoOfDoors())) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main() {
		Car c1 = new Car(4, 50, "red", 2);
		
		Car c2 = new Car(4, 30, "blue", 1);
		System.out.println(c1.equals(c2));
		System.out.println(c1.getNoOfDoors());
	}
	
	
}