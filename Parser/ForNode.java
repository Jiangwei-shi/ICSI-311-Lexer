package lexer;

public class ForNode extends StatementNode {
	Node left ;
	Node startvalue;
	Node endvalue;
	Node stepvalue;
	
	public ForNode(Node left1,Node startvalue1,Node endvalue1) {		//get the value from parser
		this.left = left1;
		this.startvalue = startvalue1;
		this.endvalue = endvalue1;
	}
	
	public ForNode(Node left1,Node startvalue1,Node endvalue1,Node stepvalue1) {		//get the value from parser
		this.left = left1;
		this.startvalue = startvalue1;
		this.endvalue = endvalue1;
		this.stepvalue =stepvalue1;
	}

	public Node getinterNode()
	{
		return left;					
	}
	
	public String ToString() 
	{
		if(stepvalue == null)
		{
		// TODO Auto-generated method stub
		return "ForNode("+left.ToString()+" equals "+"startvalue"+startvalue.ToString()+" to "+"endvalue"+endvalue.ToString()+" Step IntNode(1)" +")";
		}
		else
		{
		return "ForNode("+left.ToString()+" equals "+"startvalue"+startvalue.ToString()+" to "+"endvalue"+endvalue.ToString()+" Step "+stepvalue.ToString() +")";
		}
	}
	
}
