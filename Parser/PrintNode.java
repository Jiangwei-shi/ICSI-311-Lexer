package lexer;

import java.util.ArrayList;
import java.util.List;

public class PrintNode extends StatementNode{					//pass the Node list and return a Node

	private List<Node> value = new ArrayList<>();
	public PrintNode(List<Node> s1) 
	{	
		this.value = s1;
	}
	public List<Node> getprintnode()
	{
		return value;					
	}
	@Override
	public String ToString() {
	      StringBuilder SB = new StringBuilder("PrintNode{");
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
