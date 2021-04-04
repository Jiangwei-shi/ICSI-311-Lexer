package lexer;

import lexer.MathOpNode.Mathoptype;

public class AssignmentNode extends StatementNode{
	private Node right;
	private Node left;
	private Mathoptype Nodetype;

	public AssignmentNode(Mathoptype s, Node l, Node r) {			//pass an identifer and = and expression and return a Node
		// TODO Auto-generated constructor stub
		this.Nodetype = s;
		this.left=l;
		this.right=r;
	}
	public Node getRight() 
	{
		return right;
	}
	public Node getleft()
	{
		return left;
	}

	public Mathoptype getmathOP()
	{
		return Nodetype;
	}
	@Override
	public String ToString() 
	{		
		return "AssignmentNode("+Nodetype+","+left.ToString()+","+right.ToString()+")";
	}

}
