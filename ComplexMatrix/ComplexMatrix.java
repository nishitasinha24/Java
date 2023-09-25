package complexmatrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComplexMatrix{
	int m; 
	int n; 
	
	Complex[][] complexMatrix = new Complex[m][n];
	public ComplexMatrix(){}
	
	public ComplexMatrix(int m, int n){
		this.m = m;
		this.n = n;
		for(int i = 0;i<this.m;i++) {
			for(int j=0;j<this.n;j++) {
				this.complexMatrix[i][j] = new Complex(0,0);
			}
		}
	}
	
	public ComplexMatrix(Complex[][] input){
		this.m = input.length;
		this.n = input[0].length;
		this.complexMatrix = input;
	}
	
	public Complex[][] getMatrix() {
		return this.complexMatrix;
	}
	
	public int getNoRows(){
		return this.complexMatrix.length;
	}
	
	public int getNoColumns() {
		return this.complexMatrix[0].length;
	}
	
	public String toString() {
		String s1 = "";
		for(int i=0;i<this.m;i++) {
			for(int j=0;j<this.n;j++) {
				s1 += this.complexMatrix[i][j].toString() + " ";
			}
			s1 += '\n';
		}
		return s1;
	}
	
	public ComplexMatrix add(ComplexMatrix cm){
		Complex[][] s = new Complex[this.m][this.n];
		try {
			if(this.m != cm.getNoRows() || this.n != cm.getNoColumns())
			{
				throw new MatrixDimensionException("Invalid dimensions, addition not possible!");
			}
			Complex[][] tmp = cm.getMatrix();
			for(int i=0; i<this.m;i++) {
				for(int j=0; j<this.n;j++) {
					s[i][j] = this.complexMatrix[i][j].add(tmp[i][j]);
				}
			}
		}
		catch(MatrixDimensionException e) {
			System.out.println(e);
		}
		return new ComplexMatrix(s);
	}
	
	public ComplexMatrix mult(ComplexMatrix cm) {
		Complex[][] product = new Complex[this.m][cm.getNoColumns()];
		try {
			if(this.n != cm.getNoRows()) {
				throw new MatrixDimensionException("Invalid dimensions, multiply not possible!");
			}
			Complex[][] tmp = cm.getMatrix();
			Complex mul = new Complex();
			Complex s = new Complex();
	        for (int i = 0; i < this.m; i++) {
	            for (int j = 0; j < cm.getNoColumns(); j++) {
	                for (int k = 0; k < cm.getNoRows(); k++) {
	                	mul = this.complexMatrix[i][k].multiply(tmp[k][j]);
	                	s = s.add(mul);
	                	product[i][j] = s;
	                }
	            }
	            
	        }
		}
		catch(MatrixDimensionException e) {
			System.out.println(e);
		}
		return new ComplexMatrix(product);
	}
	
	public static ComplexMatrix read(String filename) {
		File f = new File(filename);
		Complex[][] result = new Complex[100][100];
		String line = null;
		int noOfRows = -1;
		int noOfCols = -1;
				
		try {
			Scanner sc = new Scanner(f);
			
			while(sc.hasNextLine()) {
				try {
					line = sc.nextLine();
					noOfRows += 1;
					String[] row = line.split("\\s+");
					
					if(noOfCols == -1) 
						noOfCols = row.length;
					else {
						if(noOfCols != row.length) 
							throw new IncompatibleMatrixException("Incompatible Matrix Exception!");
					}
					for(int i=0; i<row.length; i++) {
						String[] elements = row[i].split("_");
						result[noOfRows][i] = new Complex(Double.parseDouble(elements[0].trim()), Double.parseDouble(elements[1].trim()));
					}
					
				}
				catch(IncompatibleMatrixException e) {
					System.out.println("Exception thrown: "+e);

				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found error!");
		}

		Complex[][] newMatrix = new Complex[noOfRows][noOfCols];
		for(int i = 0; i < noOfRows; i++) {
			for(int j = 0; j < noOfCols; j++) {
				newMatrix[i][j] = result[i][j];
			}
		}
		
		return new ComplexMatrix(newMatrix);
	}
	
	public void write(String filename) {
		try {
			  File dir = new File(filename);
			  dir.createNewFile();
		      FileWriter myWriter = new FileWriter(filename);
		      for(int i=0;i<this.m;i++) {
		    	  for(int j=0;j<this.n;j++) {
		    		  double real = this.complexMatrix[i][j].getReal();
		    		  double imag = this.complexMatrix[i][j].getImaginary();
		    		  String s = Double.toString(real)+"_"+Double.toString(imag)+" ";
		    		  myWriter.write(s);
		    	  }
		    	  myWriter.write("\n");
		      }
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		//Creating a new matrix
		Complex[][] matrix1 = new Complex[5][5];
		for(int i = 0; i < 5;i++) {
			for(int j = 0; j < 5; j++) {
				matrix1[i][j] = new Complex(3,5);
			}
		}
		
		Complex[][] matrix2 = new Complex[5][5];
		for(int i = 0; i < 5;i++) {
			for(int j = 0; j < 5; j++) {
				matrix2[i][j] = new Complex(2,6);
			}
		}
		
		ComplexMatrix cm1 = new ComplexMatrix(matrix1);
		ComplexMatrix cm2 = new ComplexMatrix(matrix2);
		
		//Addition
		System.out.println("Addition: ");
		System.out.println(cm1.add(cm2));
		
		// Multiplication
		System.out.println("Multiplication: ");
		System.out.println(cm1.mult(cm2));
		
		//Reading from 1st text file using read() method
		System.out.println("File read -> complexmatrix1.txt");
		ComplexMatrix cm3 = read("complexmatrix1.txt");
		System.out.println(cm3.toString());
		
		//Reading from 2nd text file using read() method
		System.out.println("File read -> complexmatrix2.txt");
		ComplexMatrix cm4 = read("complexmatrix2.txt");
		System.out.println(cm4.toString());
		
		//Reading from 3rd text file using read() method
		System.out.println("File read -> complexmatrix3.txt");
		ComplexMatrix cm5 = read("complexmatrix3.txt");
		System.out.println(cm5.toString());
		
		//Creating a text file using write() method
		ComplexMatrix cm7 = new ComplexMatrix(matrix1);
		cm7.write("written.txt");
	}
}