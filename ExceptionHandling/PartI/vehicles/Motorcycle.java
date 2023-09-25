package vehicles;

import java.util.Arrays;

public class Motorcycle extends Vehicle{
	private String[] accessories;
	
	public Motorcycle(){}
	
	public Motorcycle(int noOfWheels, int cargoSpace, String color, String[] accessories) {
		super(noOfWheels, cargoSpace, color);
		try {
			if(noOfWheels != 2) {
				throw new VehicleException("No. of wheels is invalid, motorcycle should have 2 wheels");
			}
			if(cargoSpace != 0) {
				throw new VehicleException("Cargo space is invalid, motorcycle should have 0 cargo space");
			}
			this.accessories = accessories;
		}
		catch(VehicleException e) {
			System.out.println(e);
		}
		this.accessories = accessories;
	}
	
	public void setAccessories(String[] accessories) {
		this.accessories = accessories;
	}
	
	public String[] getAccessories() {
		return this.accessories;	
	}
	
	public String twistThrottle() {
		return "accelerating";
	}
	
	public String toString() {
		String vehicle = super.toString();
		String accessoriesList = "";
		for (int i = 0; i < this.accessories.length; i++) {
			accessoriesList = accessoriesList + (accessories[i]) + " ";
			}
		String s1 = vehicle + " Subclass Name: Motorcycle, Fields - Accessories: " + accessoriesList;
		return s1;
	}
	
	public static boolean equals(Motorcycle m1, Motorcycle m2) {
		if ((m1.getNoOfWheels() == m2.getNoOfWheels()) && (m1.getCargoSpace() == m2.getCargoSpace()) && 
				((m1.getColor() == m2.getColor()) && (Arrays.equals(m1.accessories, m2.accessories)))) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main() {
		String accessories1[] = {"Helmet clip", "Grip warmers"};
		Motorcycle m1 = new Motorcycle(2, 0, "black", accessories1);
		String accessories2[] = {"Helmet clip", "Usb charger"};
		Motorcycle m2 = new Motorcycle(2, 0, "grey", accessories2);
		System.out.println(m1.equals(m2));
	}
}