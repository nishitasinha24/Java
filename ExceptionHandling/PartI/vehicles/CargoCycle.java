package vehicles;

public class CargoCycle extends Bicycle{
	public CargoCycle() {}
	
	public CargoCycle(int noOfWheels, int cargoSpace, String color, boolean isElectric) {
		super();
		this.setNoOfWheels(noOfWheels);
		this.setCargoSpace(cargoSpace);
		this.setColor(color);
		this.setIsElectric(isElectric);
	}
	
	public String toString() {
		String cargocycle =  super.toString() + " Type: Cargo Cycle ";
		return cargocycle;
	}
	
	public static boolean equals(CargoCycle c1, CargoCycle c2) {
		if ((c1.getNoOfWheels() == c2.getNoOfWheels()) && (c1.getCargoSpace() == c2.getCargoSpace()) && 
				c1.getColor() == c2.getColor() && c1.getIsElectric() == c2.getIsElectric()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String args[]) {
		CargoCycle c1 = new CargoCycle(3, 40, "brown", true);
		CargoCycle c2 = new CargoCycle(4, 45, "red", false);
		System.out.println(c1.equals(c2));
	}
}