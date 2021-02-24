package lexer;
/*
 * author Jiangwei Shi
 * input rule
 * I use whitespace as the separate symbol.Whinch means you need type " " after each character
 * example
 * Input is:A 4 * -3 Me F: POOP 4 "abd cd"
 * Output is: identifier(A) number(4) times number(-3) identifier(Me) label(F) identifier(POOP) number(4) string(abd cd) EndOfLine 
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Basic {

	public static void main (String[] args)  throws getException{
		//identify if the argument is one, If only one, then continue, else throw getException
		if(args.length != 1) 
		{   
			throw new getException("You can only put one argument in it");								
		}		
		//use StringBuilder and ReadAllLine to get the string in the file
		StringBuilder str = new StringBuilder();
		//Use the list which is Token type to store the Token type of String
		List<Token> holdToken = new ArrayList<>();												
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            for (String line : lines) {
            str.append(line);
            str.append(" ");
            System.out.print("Input is: "+ line + " ");  
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace(); //find the exception place 
        }
        
        
		Lexer lex = new Lexer();
		holdToken = lex.lexeme(str.toString());															
		//use toStr method that in token to print it all
		System.out.print("Output is: ");
		//
		for(int i=0;i<holdToken.size();i++) {
			holdToken.get(i).toStr();
		}
	}
}