package PartI;
import java.util.ArrayList;


public class MyStack<E> {

	private ArrayList<E> ar = new ArrayList<E>();

	public boolean empty() {
		if (ar.size() <= 0) {
			return true;
		}
		return false;
	}
	
	public E peek() {
		if (ar.size() <= 0) {
			return null;
		}
		return ar.get(ar.size() - 1);
	}
	
	public E pop() {
		if (ar.size() > 0) {
			return ar.remove(ar.size() - 1);
		}
		return null;
	}
	
	public E push(E num) {
		ar.add(num);
		return num;
	}
	
	public int search(E num) {
		int pos = 1;
		for (int i = ar.size() - 1; i >= 0; i--) {
			if (ar.get(i) == num) {
				return pos;
			}
			pos += 1;
		}
		return -1;
	}
	
	public void print() {
		for (int i = 0; i < ar.size(); i++) {
			System.out.print(ar.get(i) + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.print();
		System.out.println("Is Stack Empty? -  " + stack.empty());
		System.out.println("Top element " + stack.peek());
		System.out.println("Before popping -> ");
		stack.print();
		stack.pop();
		System.out.println("After popping -> ");
		stack.print();
		System.out.println("Before pushing -> ");
		stack.print();
		stack.push(80);
		System.out.println("After pushing -> ");
		stack.print();
		System.out.println("Position of the searched element - 1 :" + stack.search(1));
	}
}
