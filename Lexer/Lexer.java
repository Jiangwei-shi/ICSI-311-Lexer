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
				else if (c == 'P')
				{
					str.append(Character.toString(c));
					state = 9;
				}
				else if (c == 'D')
				{
					str.append(Character.toString(c));
					state = 15;
				}
				else if (c == 'R')
				{
					str.append(Character.toString(c));
					state = 19;
				}
				else if (c == 'G')
				{
					str.append(Character.toString(c));
					state = 31;
				}
				else if (c == 'F')
				{
					str.append(Character.toString(c));
					state = 28;
				}
				else if (c == 'N')
				{
					str.append(Character.toString(c));
					state = 41;
				}
				else if(c == 'I')
				{
					str.append(Character.toString(c));
					state = 23;
				}
				else if(c == 'T')
				{
					str.append(Character.toString(c));
					state = 45;
				}
				else if(c == 'S')
				{
					str.append(Character.toString(c));
					state = 47;
				}
				else if(c == 'L')
				{
					str.append(Character.toString(c));
					state = 60;
				}
				else if(c == 'M')
				{
					str.append(Character.toString(c));
					state = 70;
				}
				else if(c == 'V')
				{
					str.append(Character.toString(c));
					state = 77;
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
				else if (c == ',')
				{
					Token t = new Token(Token.TokenType.comma);
					listofToken.add(t);
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
					Token t = new Token(str.toString(),Token.TokenType.string);
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
					state = 7;
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
					state = 7;
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
					state = 7;
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
				state = 7;
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
					Token t = new Token(Token.TokenType.Print);
					listofToken.add(t);					
					knownWords.put("print",Token.TokenType.Print);
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
			case 15:
				if(c== 'a')
				{
					str.append(Character.toString(c));
					state = 16;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 16:
				if(c== 't')
				{
					str.append(Character.toString(c));
					state = 17;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 17:
				if(c== 'a')
				{
					str.append(Character.toString(c));
					state = 18;					
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 18:
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
					Token t = new Token(Token.TokenType.Data);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.Data);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
				}
				else state =-1;
				break;
			case 19:
				if(c== 'e')
				{
					str.append(Character.toString(c));
					state = 20;						
				}
				else if(c== 'E')
				{
					str.append(Character.toString(c));
					state = 36;						
				}
				else if(c== 'A')
				{
					str.append(Character.toString(c));
					state = 55;						
				}
				else if(c== 'I')
				{
					str.append(Character.toString(c));
					state = 65;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 20:
				if(c== 'a')
				{
					str.append(Character.toString(c));
					state = 21;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 21:
				if(c== 'd')
				{
					str.append(Character.toString(c));
					state = 22;					
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 22:
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
					Token t = new Token(Token.TokenType.Read);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.Read);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 23:
				if(c== 'n')
				{
					str.append(Character.toString(c));
					state = 24;						
				}
				else if(c== 'F')
				{
					str.append(Character.toString(c));
					state = 51;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 24:
				if(c== 'p')
				{
					str.append(Character.toString(c));
					state = 25;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 25:
				if(c== 'u')
				{
					str.append(Character.toString(c));
					state = 26;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 26:
				if(c== 't')
				{
					str.append(Character.toString(c));
					state = 27;					
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 27:
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
					Token t = new Token(Token.TokenType.Input);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.Input);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 28:
				if(c== 'O')
				{
					str.append(Character.toString(c));
					state = 29;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 29:
				if(c== 'R')
				{
					str.append(Character.toString(c));
					state = 30;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 30:
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
					Token t = new Token(Token.TokenType.FOR);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.FOR);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 31:
				if(c== 'O')
				{
					str.append(Character.toString(c));
					state = 32;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 32:
				if(c== 'S')
				{
					str.append(Character.toString(c));
					state = 33;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 33:
				if(c== 'U')
				{
					str.append(Character.toString(c));
					state = 34;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 34:
				if(c== 'B')
				{
					str.append(Character.toString(c));
					state = 35;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 35:
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
					Token t = new Token(Token.TokenType.GOSUB);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.GOSUB);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 36:
				if(c== 'T')
				{
					str.append(Character.toString(c));
					state = 37;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 37:
				if(c== 'U')
				{
					str.append(Character.toString(c));
					state = 38;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 38:
				if(c== 'R')
				{
					str.append(Character.toString(c));
					state = 39;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 39:
				if(c== 'N')
				{
					str.append(Character.toString(c));
					state = 40;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 40:
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
					Token t = new Token(Token.TokenType.RETURN);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.RETURN);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;	
			case 41:
				if(c== 'E')
				{
					str.append(Character.toString(c));
					state = 42;						
				}
				if(c== 'U')
				{
					str.append(Character.toString(c));
					state = 74;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 42:
				if(c== 'X')
				{
					str.append(Character.toString(c));
					state = 43;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 43:
				if(c== 'T')
				{
					str.append(Character.toString(c));
					state = 44;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 44:
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
					Token t = new Token(Token.TokenType.NEXT);
					listofToken.add(t);					
					knownWords.put("Data",Token.TokenType.NEXT);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 45:
				if(c== 'o')
				{
					str.append(Character.toString(c));
					state = 46;						
				}
				else if(c== 'H')
				{
					str.append(Character.toString(c));
					state = 52;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 46:
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
					Token t = new Token(Token.TokenType.To);
					listofToken.add(t);					
					knownWords.put("To",Token.TokenType.To);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 47:
				if(c== 'T')
				{
					str.append(Character.toString(c));
					state = 48;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 48:
				if(c== 'E')
				{
					str.append(Character.toString(c));
					state = 49;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 49:
				if(c== 'P')
				{
					str.append(Character.toString(c));
					state = 50;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 50:
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
					Token t = new Token(Token.TokenType.STEP);
					listofToken.add(t);					
					knownWords.put("Step",Token.TokenType.STEP);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 51:
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
					Token t = new Token(Token.TokenType.IF);
					listofToken.add(t);			
					knownWords.put("IF",Token.TokenType.IF);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 52:
				if(c== 'E')
				{
					str.append(Character.toString(c));
					state = 53;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 53:
				if(c== 'N')
				{
					str.append(Character.toString(c));
					state = 54;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 54:
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
					Token t = new Token(Token.TokenType.THEN);
					listofToken.add(t);	
					knownWords.put("THEN",Token.TokenType.THEN);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 55:
				if(c== 'N')
				{
					str.append(Character.toString(c));
					state = 56;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 56:
				if(c== 'D')
				{
					str.append(Character.toString(c));
					state = 57;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 57:
				if(c== 'O')
				{
					str.append(Character.toString(c));
					state = 58;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 58:
				if(c== 'M')
				{
					str.append(Character.toString(c));
					state = 59;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 59:
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
					Token t = new Token(Token.TokenType.RANDOM);
					listofToken.add(t);	
					knownWords.put("RANDOM",Token.TokenType.RANDOM);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 60:
				if(c== 'E')
				{
					str.append(Character.toString(c));
					state = 61;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 61:
				if(c== 'F')
				{
					str.append(Character.toString(c));
					state = 62;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 62:
				if(c== 'T')
				{
					str.append(Character.toString(c));
					state = 63;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 63:
				if(c== '$')
				{
					str.append(Character.toString(c));
					state = 64;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 64:
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
					Token t = new Token(Token.TokenType.LEFT$);
					listofToken.add(t);	
					knownWords.put("LEFT$",Token.TokenType.LEFT$);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 65:
				if(c== 'G')
				{
					str.append(Character.toString(c));
					state = 66;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 66:
				if(c== 'H')
				{
					str.append(Character.toString(c));
					state = 67;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 67:
				if(c== 'T')
				{
					str.append(Character.toString(c));
					state = 68;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 68:
				if(c== '$')
				{
					str.append(Character.toString(c));
					state = 69;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 69:
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
					Token t = new Token(Token.TokenType.RIGHT$);
					listofToken.add(t);	
					knownWords.put("RIGHT$",Token.TokenType.RIGHT$);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 70:
				if(c== 'I')
				{
					str.append(Character.toString(c));
					state = 71;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 71:
				if(c== 'D')
				{
					str.append(Character.toString(c));
					state = 72;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 72:
				if(c== '$')
				{
					str.append(Character.toString(c));
					state = 73;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 73:
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
					Token t = new Token(Token.TokenType.MID$);
					listofToken.add(t);	
					knownWords.put("MID$",Token.TokenType.MID$);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 74:
				if(c== 'M')
				{
					str.append(Character.toString(c));
					state = 75;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 75:
				if(c== '$')
				{
					str.append(Character.toString(c));
					state = 76;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 76:
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
					Token t = new Token(Token.TokenType.NUM$);
					listofToken.add(t);	
					knownWords.put("NUM$",Token.TokenType.NUM$);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 77:
				if(c== 'A')
				{
					str.append(Character.toString(c));
					state = 78;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 78:
				if(c== 'L')
				{
					str.append(Character.toString(c));
					state = 79;						
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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
			case 79:
				if(c== '$')
				{
					str.append(Character.toString(c));
					state = 80;						
				}
				else if (c == ':')
				{
					Token t = new Token(str.toString(),Token.TokenType.label);
					listofToken.add(t);
					str.delete(0, str.length());
					state = 1 ;
				}
				else if (c == ' ') 
				{
					str.delete(0, str.length());
					Token t = new Token(Token.TokenType.VAL);
					listofToken.add(t);	
					knownWords.put("VAL",Token.TokenType.VAL);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
				}
				else state =-1;
				break;
			case 80:
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
					Token t = new Token(Token.TokenType.VAL$);
					listofToken.add(t);	
					knownWords.put("VAL$",Token.TokenType.VAL$);
					state = 1 ;
				}
				else if (Character.isLetter(c))
				{
					str.append(Character.toString(c));
					state = 7;
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