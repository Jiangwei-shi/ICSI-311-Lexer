package lexer;

public class StringNode extends Node{
	
	private String stringNode;
	
	public StringNode(String value) {		//get the value from parser
		this.stringNode =value;
	}

	public String getinterNode()
	{
		return stringNode;					
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "StringNode"+"("+stringNode+")";
	}

}
