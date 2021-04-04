package lexer;

public class GosubNode extends StatementNode{

	Node Gosubnode;
	public GosubNode(Node GosubNode) {		//get the value from parser
		this.Gosubnode = GosubNode;
	}

	public Node getinterNode()
	{
		return Gosubnode;					
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "GosubNode("+Gosubnode.ToString()+")";
	}

}
