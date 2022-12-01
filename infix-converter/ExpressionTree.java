public class ExpressionTree {
	private Node root;
	private ArrayStack<ExpressionTree> stack = new ArrayStack<>();
	
	public ExpressionTree(Node n) {
		root = n;
	}

	
	public ExpressionTree convert(String exp) {
		String[] parts = exp.split(" ");
		for (String part : parts) {
			if (Character.isDigit(part.charAt(0))) {
				stack.push(new ExpressionTree(new Node(part)));
			}
			else {
				Node right = stack.pop().root;
				Node left = stack.pop().root;
				stack.push(new ExpressionTree(new Node(part, left, right)));
			}
		}
		return stack.pop();
	}
	
	public void prefix() {
		prefixHelp(root);
		System.out.println();
	}
	
	private void prefixHelp(Node n) {
		if (n!=null) {
			System.out.print(n);
			prefixHelp(n.left);
			prefixHelp(n.right);
		}
	}
	
	public void infix() {
		infixHelp(root);
		System.out.println();
	}
	
	private void infixHelp(Node n) {
		if (n!=null) {
			if (!(n.left == null && n.right == null)) System.out.print("(");
			infixHelp(n.left);
			System.out.print(n);
			infixHelp(n.right);
			if (!(n.left == null && n.right == null)) System.out.print(")");
		}
	}
	
	public void postfix() {
		postfixHelp(root);
		System.out.println();
	}
	
	private void postfixHelp(Node n) {
		if (n!=null) {
			postfixHelp(n.left);
			postfixHelp(n.right);
			System.out.print(n);
		}
	}
}
