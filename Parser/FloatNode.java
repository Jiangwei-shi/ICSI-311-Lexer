package lexer;


public class FloatNode extends Node {    //extends from node
	
	private String Value;
	
	public FloatNode(String value) {	//get the value from parser
		this.Value =value;
	}

	public String getFloatNode()
	{
		return Value;
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "FloatNode"+"("+Value+")";      //return the string as a node 
	}
}
