package lexer;

public class NextNode extends StatementNode {

	Node Nextnode;
	public NextNode(Node NextNode) {		//get the value from parser
		this.Nextnode = NextNode;
	}

	public Node getinterNode()
	{
		return Nextnode;					
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "NextNode("+Nextnode.ToString()+")";
	}
}
