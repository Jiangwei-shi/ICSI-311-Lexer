package lexer;
/*
 * author Jiangwei Shi
 */
public class Token {
	
	//private the value and the type in token
	private TokenType Token;
	private String value;
	
	//set the value
	public void setValue(String s) {
		
		this.value = s;
	}
	
	//get and return the value
	public String getValue()
	{
		return value;
	}
	
	//enum types
	public enum TokenType { 
		plus, minus, times, divide, equals, number, EndOfLine
		,label,lparen,rparen,lessthan,greaterthan,lessthanequals,greaterthanequals,Notequals,string,identifier,print
	}
	
	//set the Token type
	public void setType(TokenType t){
		this.Token = t;
	}
	
	//get and return the Token type
	public TokenType getType()
	{
		return Token;
	}
	
	//put the type in token when new it.
	public Token(TokenType t)
	{
		setType(t);
	}
	
	//put both value and type in token when new it.
	public Token(String s, TokenType t)
	{
		setValue(s);
		setType(t);
	}
	
	//toString method: when there is no value, just print the type
	public void toStr()
	{
		if(getValue()==null)
		{
		System.out.print(getType()+ " ");
		}else {
		System.out.print(getType()+ "(" + getValue()+") ");
		}	
	}

}