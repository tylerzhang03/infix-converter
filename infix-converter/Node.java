package data_structures_hw4;

public class Node {
	String element;
	Node left;
	Node right;
	
	public Node(String s) {
		this(s, null, null);
	}
	
	public Node(String s, Node left, Node right) {
		element = s;
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return element;
	}
}
