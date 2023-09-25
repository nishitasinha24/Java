package PartII;
import java.util.HashSet;
import java.util.Set;

public class MathSet<E> extends HashSet<E> {
	public Set<E> intersection(Set<E> s2) {
		this.retainAll(s2);
		return this;    
    }
    
    public Set<E> union(Set<E> s2) {
    	this.addAll(s2);
		return this;
    }
    
    public <T> Set<Pair<E,T>> cartesianProduct(Set<T> s2) {
    	Set<Pair<E,T>> resultPair = new HashSet<Pair<E,T>>();
		for (E i: this) {
			for (T j: s2) {
				
				Pair<E, T> newPair = new Pair<E, T>(i, j);
				resultPair.add(newPair);
			}
		}
		return resultPair;
    }
    

	public static void main(String[] args) {
		// create two MathSet objects of the same type.
		// See if union and intersection works.
		
		// create another MathSet object of a different type
		// calculate the cartesian product of this set with one of the
		// above sets
		MathSet<Integer> s1 = new MathSet<Integer>();
		s1.add(5);
		s1.add(7);
		s1.add(9);
		MathSet<Integer> s2 = new MathSet<Integer>();
		s2.add(5);
		s2.add(7);
		s2.add(4);
		s2.add(6);
		s2.add(8);
		System.out.println(s1.intersection(s2));
		System.out.println(s1.union(s2));

		System.out.println(s1.cartesianProduct(s2));
		
		MathSet<String> s3 = new MathSet<String>();
		s3.add("apple");
		s3.add("banana");
		s3.add("orange");
		s3.add("mango");
		System.out.println("Cartesian Product of MathSet 3 with MathSet 1: " + s3.cartesianProduct(s1));
		
	}
}
