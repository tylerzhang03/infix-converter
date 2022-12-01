import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Converter converter;
		ExpressionTree tree = new ExpressionTree(null);
		
		while (true) {
			System.out.println("Type your infix expression: ");
			converter = new Converter(scan.nextLine());
			tree = tree.convert(converter.toPostFix());
			
			System.out.print("Prefix: ");
			tree.prefix();
			System.out.print("Infix: ");
			tree.infix();
			System.out.print("Postfix: ");
			tree.postfix();
		}
	}
	
	

}
