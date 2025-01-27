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
		//StringBuilder str = new StringBuilder();
		//Use the list which is Token type to store the Token type of String
		List<Token> holdToken = new ArrayList<>();	
		List<String> Stringlist =new ArrayList<>();	
		Parser holdNode = new Parser();	//Assignment 2	
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            System.out.println("Input is: "); 
            for (String line : lines) {
            Stringlist.add(line+" ");
            System.out.println(line + " ");  
            }
        } catch (Exception e) {
            e.printStackTrace(); //find the exception place 
        }
        System.out.println("");
        System.out.println("lexer Output is: ");
        for(int j=0;j<Stringlist.size();j++)
        {
        	Lexer lex = new Lexer();
    		holdToken = lex.lexeme(Stringlist.get(j));														
    		//use toStr method that in token to print it all
    		for(int i=0;i<holdToken.size();i++)    
    		{	
    			
    				holdToken.get(i).toStr();
    		while(holdToken.get(i).getType() == Token.TokenType.EndOfLine)
        		{
    				System.out.println("");
    				break;
    			}
    			 
    		}												
        }
        System.out.println("");
        System.out.print("Parser Output is: ");
        for(int k=0;k<Stringlist.size();k++)
        {
        	Lexer lex = new Lexer();
    		holdToken = lex.lexeme(Stringlist.get(k));														
    		System.out.println("");
    		System.out.print(holdNode.parser(holdToken).ToString()); //parser		
        }
		
	}
}