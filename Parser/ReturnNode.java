package lexer;

import java.util.List;

public class ReturnNode extends StatementNode{
	
	public ReturnNode(List<Token> s) 
	{		//get the value from parser
	}

	public Node getinterNode()
	{
		return null;					
	}
	public String ToString() {
		// TODO Auto-generated method stub
		return "RETURN";
	}
}
