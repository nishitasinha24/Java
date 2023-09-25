package PartI;

import java.util.Stack;

public class BalancedParentheses {

	public static boolean isBalanced(String inString) {
		Stack<String> stack = new Stack<String>();
		
		for (int i = 0; i < inString.length(); i++) {
			if (inString.charAt(i) == '(' || inString.charAt(i) == '{' || inString.charAt(i) == '[') {
				stack.push("" + inString.charAt(i));
			} else if (inString.charAt(i) == ')') {
				if (stack.size() <= 0) {
					return false;
				}
				String temp = stack.pop();
				if (!temp.equals("(")) {
					return false;
				}
			} else if (inString.charAt(i) == '}') {
				if (stack.size() <= 0) {
					return false;
				}
				String temp = stack.pop();
				if (!temp.equals("{")) {
					return false;
				}
			} else if (inString.charAt(i) == ']') {
				if (stack.size() <= 0) {
					return false;
				}
				String temp = stack.pop();
				if (!temp.equals("[")) {
					return false;
				}
			}
		}
		
		if (stack.size() > 0) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean result = isBalanced("(()()()())");
		System.out.println("Is the given string (()()()()) balanced?: " + result);
		result = isBalanced("(((())))");
		System.out.println("Is the given string (((()))) balanced?: " + result);
		result = isBalanced("((((((())");
		System.out.println("Is the given string ((((((()) balanced?: " + result);
	}
}
