import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import vehicles.*;

/* your tasks:
 * create a class called VehicleException
 * createVehicle should throw a VehicleException
 * in main(), you should catch the VehicleException
 * 
 */
public class ReadVehicleFile {

	public static Vehicle createVehicle(String vehicleName) throws VehicleException{
		
		/* if vehicleName is "Motorcycle" return Motorcycle();
		 * if vehicleName is "Car" return Car();
		 * if vehicleName is "Bicycle" return Bicycle();
		 * if vehicleName is "CargoCycle" return CargoCycle();
		 * if it is not any one of these, it should throw
		 * a VehicleException
		 */
		String[] splitData = vehicleName.split(",");
		if(splitData[0].equals("Motorcycle")) {
			if(Integer.parseInt(splitData[1]) != 2) {
				throw new VehicleException("No. of wheels is invalid, motorcycles can only have 2 wheels");
			}
			String[] accessories = Arrays.copyOfRange(splitData, 4, splitData.length);
			Vehicle v = new Motorcycle(Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]),splitData[3],accessories);
			return v;
		}
		else if(splitData[0].equals("Car")) {
			if(Integer.parseInt(splitData[4]) < 2) {
				throw new VehicleException("No. of cars is invalid, cars can only have 2 or 4 doors");
			}
			Vehicle v = new Car(Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]),splitData[3],Integer.parseInt(splitData[4]));
			return v;
		}
		else if(splitData[0].equals("Bicycle")) {
			if(Integer.parseInt(splitData[1]) != 2) {
				throw new VehicleException("No. of wheels is invalid, bicycles can only have 2 wheels");
			}
			Vehicle v = new Bicycle(Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]),splitData[3],Boolean.parseBoolean(splitData[4]));
			return v;
		}
		else if(splitData[0].equals("CargoCycle")) {
			if(Integer.parseInt(splitData[1]) != 2 && Integer.parseInt(splitData[1]) != 3 && Integer.parseInt(splitData[1]) != 4 ) {
				throw new VehicleException("No. of wheels is invalid, cargocycles can only have 2,3 or 4 wheels");
			}
			Vehicle v = new CargoCycle(Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]),splitData[3],Boolean.parseBoolean(splitData[4]));
			return v;
		}
		else {
			throw new VehicleException("Vehicle type is not valid!");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		File f = new File("vehicles.txt");
		
		String inString = null;
		
		/* create a loop to read the file line-by-line */
		
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				try {
					inString = sc.nextLine();
					Vehicle vehicle = createVehicle(inString);
					vehicleList.add(vehicle);
				}
				catch(VehicleException e) {
					System.out.println("Exception is: "+e);
					System.err.println("Cannot create Vehicle: " + inString);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		System.out.println(vehicleList);
		
	}
}
