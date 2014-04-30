package expr;

import java.util.Scanner;

public class TokenTester {

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	    System.out.print("Enter an expression: ");
	    String input = in.nextLine();
	    ExpressionTokenizer tokenizer = new ExpressionTokenizer(input);
	 
	    String token;
	    while (tokenizer.peekToken()!=null) {
	      token = tokenizer.nextToken();	    
	      System.out.println(token);
	    }   
	}

}
