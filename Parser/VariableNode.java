package lexer;

public class VariableNode extends Node{
	
	private String Value;
	
	public VariableNode(String value) {	//get the value from parser
		this.Value =value;
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "VariableNode"+"("+Value+")";
	}

}
