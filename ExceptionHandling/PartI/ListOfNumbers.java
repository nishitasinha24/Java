import java.io.*;

import java.util.List;
import java.util.ArrayList;
 
public class ListOfNumbers {
	
    private ArrayList<RDFTriple<Integer, Integer, Integer>> rdfTripleList;
    private String fileName;
 
    public ListOfNumbers () {
        // create an ArrayList of Pairs of Integers
    	this.rdfTripleList = new ArrayList<RDFTriple<Integer,Integer, Integer>>();
    }
    
    public ArrayList getRdfTripleList() {
    	return this.rdfTripleList;
    }
    
    public void createList() {
    	for (int i = 0 ; i< 100 ; i++) {
    		Integer number1 = (int) (Math.random()*10000);
    		Integer number2 = (int) (Math.random()*10000);
    		Integer number3 = (int) (Math.random()*10000);
    		// fill the existing list with RDFTriple objects
    		// of three numbers.
    		RDFTriple<Integer, Integer, Integer> newRDFTriple = new RDFTriple<Integer, Integer, Integer>(number1, number2, number3);
    		this.rdfTripleList.add(newRDFTriple);
    	}
    }
    

    public ListOfNumbers (String fileName) {
    	this();
    	this.fileName = fileName;	
    }
    
    public void readList() throws IOException{
    	System.out.println("Executing ReadList -> ");
    	this.rdfTripleList.clear();
    	
    	RandomAccessFile input = null;
        String line = null;
        File file = new File(fileName);
        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
            	String[] nums = line.split(" ");
            	System.out.println(nums[0] + " " + nums[1] + " " + nums[2]);
            	RDFTriple<Integer, Integer, Integer> newRDFTriple = new RDFTriple<Integer, Integer, Integer>(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]),
            			Integer.parseInt(nums[2]));
            	this.rdfTripleList.add(newRDFTriple);
            }
            System.out.println("rdfTripleList length is " + this.rdfTripleList.size());
            return;
        } catch (IOException e) {
        	System.out.println(e);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    	
    }
    
    public void writeList() {
        PrintWriter out = null;
        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter(this.fileName));
            for (int i = 0; i < rdfTripleList.size(); i++)
                out.println(rdfTripleList.get(i).getSubj() + " " + rdfTripleList.get(i).getPred() + " " + rdfTripleList.get(i).getObj());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                                 e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
    
    public static void cat(String fileName) throws IOException{
        RandomAccessFile input = null;
        String line = null;
        File file = new File(fileName);
        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
    	ListOfNumbers listOfNumbers = new ListOfNumbers("numberfile.txt");
    	ListOfNumbers.cat("numberfile.txt");
    	listOfNumbers.readList();
    }

}
