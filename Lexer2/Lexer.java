package lexer;
/*
 * author Jiangwei Shi
 */
import java.util.*;
/* this Lexer implement state machine
 * get each chars into a Token list and return the Tokenlist
 */
public class Lexer{
	HashMap<String, Token.TokenType> knownWords = new HashMap<String, Token.TokenType>();
	public List<Token> lexeme(String s) throws getException {
		
		//create a token list to put all the tokens in it
		List<Token> listofToken = new ArrayList<>();
		
		//use StringBuilder to combine the numbers and then toString it
		StringBuilder str = new StringBuilder();
		
		//initialize the state as 1
 		int state = 1;
 		
 		//state machine and get the tokens
 		//case 3 deal with the  '.'   case 4 deal with the '-' and case 2 deal with '-' '.'
		for(int i=0;i<s.length();i++) {
			
			char c = s.charAt(i);
			
			switch(state) {
			case 1:
				if(Character.isDigit(c))
				{
					str.append(Character.toString(c));
					state = 14;					
				}
				//if it is "print"
				else if (c == 'p')
				{
					str.append(Character.toString(c));
					state = 9;
				}
				else if(Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7; 
				}			
				else if (c=='<')
				{
					str.append(Character.toString(c));
					state = 5 ;
				}
				else if (c=='>')
				{
					str.append(Character.toString(c));
					state = 6;
				}	
				else if (c == '=')
				{
					Token t = new Token(Token.TokenType.equals);
					listofToken.add(t);
				}
				else if (c == '(')
				{
					Token t = new Token(Token.TokenType.lparen);
					listofToken.add(t);
				}
				else if (c == ')')
				{
					Token t = new Token(Token.TokenType.rparen);
					listofToken.add(t);
				}			
				else if(c=='+')
				{
					Token t = new Token(Token.TokenType.plus);
					listofToken.add(t);
				}
				else if(c=='-')
				{
					str.append(Character.toString(c));
					state = 4;
				}
				else if(c=='*')
				{
					Token t = new Token(Token.TokenType.times);
					listofToken.add(t);
				}
				else if(c=='/')
				{
					Token t = new Token(Token.TokenType.divide);
					listofToken.add(t);
				}
				else if(c=='.')
				{
					str.append(Character.toString(c));
					state = 3;
				}
				else if(c==' ')
				{
					if(str.length()!=0) 
					{
						Token t = new Token(str.toString(),Token.TokenType.number);
						listofToken.add(t);
						str.delete(0, str.length());
					}
				}
				else if (c == '"')
				{					
					state = 8;
				}				
				else 
				{
					state = -1;
				}
				break;
				
			case 2:// is a minus number
				if(Character.isDigit(c))
				{
					str.append(Character.toString(c));
				}
				else if(c=='.')
				{
					str.append(Character.toString(c));
					state = 3;
				}
				else if(c==' ')
				{
					Token t = new Token(str.toString(),Token.TokenType.number);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else
				{
					state = -1;
				}
				break;
			case 3://if . have 
				if(Character.isDigit(c) && i==(s.length()-1)){
					str.append(Character.toString(c));
					Token t = new Token(str.toString(),Token.TokenType.number);
					listofToken.add(t);
				}else if(Character.isDigit(c)){
					str.append(Character.toString(c));
				}else if(c==' ')
				{
					Token t = new Token(str.toString(),Token.TokenType.number);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}else {
					state = -1;
				}
				break;
			case 4:// identify if it is a minus number or just miuns
				if(c==' ')
				{
					Token t = new Token(Token.TokenType.minus);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if(Character.isDigit(c)){
					str.append(Character.toString(c));
					state = 2;
				}
				else state = -1;
				break;
			case 5 :// boolean is < or <= or <>
				if(c==' ')
				{
					Token t = new Token(Token.TokenType.lessthan);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if(c=='=')
				{
					Token t = new Token(Token.TokenType.lessthanequals);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if(c== '>')
				{
					Token t = new Token(Token.TokenType.Notequals);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else state = -1;
				break;
			case 6 ://boolean is > or >=
				if(c == ' ')
				{
					Token t = new Token(Token.TokenType.greaterthan);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if(c == '=')
				{
					Token t = new Token(Token.TokenType.greaterthanequals);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else state = -1;
				break;
			case 7:// letter
				if(Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}	
				else if(c == '$')					
				{
					str.append(Character.toString(c));
					Token t = new Token(str.toString(),Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else if(c == '%') 
				{
					str.append(Character.toString(c));
					Token t = new Token(str.toString(),Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else if(c == ' ')
				{
					Token t = new Token(str.toString(),Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else if (c == ':')
				{
					Token t = new Token(str.toString(),Token.TokenType.label);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else state = -1;
				break;
			case 8 ://after " 
				if(c == '"'){

						Token t = new Token(str.toString(),Token.TokenType.string);
						listofToken.add(t);
						str.delete(0, str.length());
						state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else if(c==' ')
				{
					str.append(Character.toString(c));
				}				
				else state = -1; 				
				break;				
			case 9 ://boolean if is "print"
				if(c== 'r')
				{
					str.append(Character.toString(c));
					state = 10;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else if (c== ' ')
				{
					Token t = new Token(Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if (c==':')
				{
						Token t = new Token(str.toString(),Token.TokenType.label);
						listofToken.add(t);
						str.delete(0, str.length());
						state = 1 ;					
				}
				else state=-1;
				break;
			case 10://boolean if is "print"
				if(c== 'i')
				{
					str.append(Character.toString(c));
					state = 11;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else if (c== ' ')
				{
					Token t = new Token(Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if (c==':')
				{
						Token t = new Token(str.toString(),Token.TokenType.label);
						listofToken.add(t);
						str.delete(0, str.length());
						state = 1 ;					
				}
				else state=-1;
				break;
			case 11://boolean if is "print"
				if(c== 'n')
				{
						str.append(Character.toString(c));
						state = 12;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else if (c== ' ')
				{
					Token t = new Token(Token.TokenType.identifier);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1;
				}
				else if (c==':')
				{
						Token t = new Token(str.toString(),Token.TokenType.label);
						listofToken.add(t);
						str.delete(0, str.length());
						state = 1 ;					
				}
				else state=-1;
				break;
			case 12://boolean if is "print"
			if(c== 't')
			{
				str.append(Character.toString(c));
				state = 13;					
			}
			else if (Character.isLetter(c))
			{
				str.append(Character.toString(c));
			}
			else if (c== ' ')
			{
				Token t = new Token(Token.TokenType.identifier);
				listofToken.add(t);
				str.delete(0, str.length());
				state = 1;
			}
			else if (c==':')
			{
					Token t = new Token(str.toString(),Token.TokenType.label);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;					
			}
			else state=-1;
			break;
			case 13:
				if (c == ':')
				{
					Token t = new Token(str.toString(),Token.TokenType.label);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else if (c == ' ') 
				{
					str.delete(0, str.length());
					Token t = new Token(Token.TokenType.print);
					listofToken.add(t);					
					knownWords.put("print",Token.TokenType.print);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else state =-1;
				break;
			case 14:
				if (Character.isDigit(c))
				{
					str.append(Character.toString(c));
				}
				else if (c == ' ')
				{
					Token t = new Token(str.toString(),Token.TokenType.number);
					listofToken.add(t);
					str.delete(0, str.length());
					state =1;
				}
				else if (c == '.')
				{
					str.append(Character.toString(c));
					state = 3;
				}
				else state =-1;
				break;
			case -1:
				throw new getException("Unknow character,please input right character");
			}
		}			
		//add a EndOfLine token at the end
		Token end = new Token(Token.TokenType.EndOfLine);
		listofToken.add(end);	
		return listofToken;
	}
	}