package data_structures_hw4;

import java.util.ArrayList;
import java.util.List;

public class Converter {

	private String infix;
	private ArrayStack<String> arrayStack;
	
	public Converter(String infix) {
		this.infix = infix;
		this.arrayStack = new ArrayStack<>();
	}
	
	public String toPostFix() {
		String output = "";
		List<String> parsed = parse(this.infix.toCharArray());
		for (String a : parsed) {
			if (Character.isDigit(a.charAt(0))) {
				output = output + a + " ";
			} else {
				output = output + processOperator(a);
			}
		}
		while(!(arrayStack.isEmpty())) {
			output = output + arrayStack.pop() + " ";
		}
		return output;
	}
	
	private String processOperator(String operator) {
		String result = "";
		if (arrayStack.isEmpty() || operator.equals("(")) {
			arrayStack.push(operator);
		}
		
		else if (operator.equals("^")) {
			if (!arrayStack.isEmpty()) {
				while (arrayStack.top().equals("*") || arrayStack.top().equals("/") || arrayStack.top().equals("+") || arrayStack.top().equals("-")) {
					result = result + arrayStack.pop() + " ";
					if (arrayStack.isEmpty()) break;
				}
					
			}
			if (!(arrayStack.isEmpty())) {
				if (!(arrayStack.top().equals("("))) result = result + arrayStack.pop() + " ";
			}
			arrayStack.push(operator);
		}
		
		else if (operator.equals("*") || operator.equals("/")) {
			if (!arrayStack.isEmpty()) {
				while (arrayStack.top().equals("+") || arrayStack.top().equals("-")) {
					result = result + arrayStack.pop() + " ";
					if (arrayStack.isEmpty()) break;
				}
			}
			if (!(arrayStack.isEmpty())) {
				if (!(arrayStack.top().equals("("))) result = result + arrayStack.pop() + " ";
			}
			arrayStack.push(operator);
		}
		
		else if (operator.equals("+") || operator.equals("-")) {
			if (!arrayStack.isEmpty()) {
				if (!(arrayStack.top().equals("("))) result = result + arrayStack.pop() + " ";
			}
			arrayStack.push(operator);
		}
		
		else if (operator.equals(")")) {
			String temp;
			while (arrayStack.top() != null) {
				temp = arrayStack.pop();
				if (temp.equals("(")) break;
				result = result + temp + " ";
			}
			while (!arrayStack.isEmpty() && !arrayStack.top().equals("(")) {
				result = result + arrayStack.pop() + " ";
			}
		}
		
		return result;
	}
	
	
	private static List<String> parse(char[] input) {
	    List<String> parsed = new ArrayList<String>();
	    for (int i = 0; i < input.length; ++i) {
	        char c = input[i];
	        if (Character.isDigit(c)) {
	            String number = input[i] + "";
	            for (int j = i + 1; j < input.length; ++j) {
	                if (Character.isDigit(input[j])) {
	                    number += input[j];
	                    i = j;
	                } else {
	                    break;
	                }
	            }
	            parsed.add(number);
	        } else if (c == '*' || c == '/' || 
	                   c == '+' || c == '^' || 
	                   c == '-' || c == '(' || c == ')') {
	            parsed.add(c + "");
	        }
	    }
	    return parsed;
	}
}
