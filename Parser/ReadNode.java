package lexer;

import java.util.ArrayList;
import java.util.List;

public class ReadNode extends StatementNode{
	private List<Node> value = new ArrayList<>();
	public ReadNode(List<Node> s1) 
	{	
		this.value = s1;
	}
	public List<Node> getDatanode()
	{
		return value;					
	}
	@Override
	public String ToString() {
	      StringBuilder SB = new StringBuilder("ReadNode[");
	        for(int i = 0;i<value.size();i++) {
	            SB.append(value.get(i).ToString());
	            SB.append(",");
	        	}
	        SB.delete(SB.length()-1, SB.length());
	        SB.append("]");
	        return SB.toString(); 
		// TODO Auto-generated method stub

	}
}
