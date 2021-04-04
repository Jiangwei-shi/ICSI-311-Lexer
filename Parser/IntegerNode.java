package lexer;


public class IntegerNode extends Node {  //extends from node

	private String integerNode;
	
	public IntegerNode(String value) {		//get the value from parser
		this.integerNode =value;
	}

	public String getinterNode()
	{
		return integerNode;					
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "IntNode"+"("+integerNode+")";    //return the string as a node 
	}
	
}
