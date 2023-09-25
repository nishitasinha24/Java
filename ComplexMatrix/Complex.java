package complexmatrix;

public class Complex implements Comparable<Complex>{
	
	private double a;
	private double b;
	
	public Complex() {
		this.a = 0.0;
		this.b = 0.0;
	}
	
	public Complex(double a) {
		this.a = a;
		this.b = 0.0;
	}
	
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public double getReal() {
		return this.a;
	}
	
	public void setReal(double a) {
		this.a = a;
	}
	
	public double getImaginary() {
		return this.b;
	}
	
	public void setImaginary(double b) {
		this.b = b;
	}
	
	public String toString() {
		String s1 = this.a + "+ i" + this.b;
		return s1;
	}
	
	public double getMagnitude() {
		double magnitude = Math.sqrt(this.a * this.a + this.b * this.b);
		return magnitude;
	}
	
	public Complex add(Complex c) {
		double real = this.a + c.getReal();
		double img = this.b + c.b;
		Complex s = new Complex(real, img);
		return s;
	}
	
	public Complex subtract(Complex c) {
		double real = this.a - c.getReal();
		double img = this.b - c.b;
		Complex s = new Complex(real, img);
		return s;
	}
	
	public Complex multiply(Complex c) {
		double real = this.a * c.getReal() - this.b *c.getImaginary();
		double img = this.a * c.getImaginary() - this.b *c.getReal();
		Complex product = new Complex(real, img);
		return product;
	}
	
	public Complex divide(Complex c) {
		double real = (this.a * c.getReal() + this.b * c.getImaginary()) / (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		double img = (this.b * c.a - this.a * c.getImaginary()) / (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		Complex quotient = new Complex(real, img);
		return quotient;
	}
	
	public int compareTo(Complex c) {
		double mag1 = this.getMagnitude();
		double mag2 = c.getMagnitude();
        if (mag1 == mag2) {
            return 0;
        } else if (mag1 > mag2) {
            return 1;
        } else {
            return -1;
        }
    }
	
	public static void main(String args[]) {
		Complex c1 = new Complex(5, 3);
		Complex c2 = new Complex(2, 7);
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
}
