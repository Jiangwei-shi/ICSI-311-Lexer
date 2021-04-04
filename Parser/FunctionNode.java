package lexer;

import java.util.ArrayList;
import java.util.List;

public class FunctionNode extends StatementNode{
	
	private Token Functionnamevalue;
	private List<Node> value = new ArrayList<>();

	public FunctionNode(Token functionname, List<Node> FunctionList) 
	{
		// TODO Auto-generated constructor stub
		this.Functionnamevalue = functionname;
		this.value = FunctionList;
	}
	@Override
	public String ToString() {
	      StringBuilder SB = new StringBuilder("FunctionNode{" + Functionnamevalue.getType() +",");
	        for(int i = 0;i<value.size();i++) {
	            SB.append(value.get(i).ToString());
	            SB.append(",");
	        	}
	        SB.delete(SB.length()-1, SB.length());
	        SB.append("}");
	        return SB.toString(); 
		// TODO Auto-generated method stub

	}
}