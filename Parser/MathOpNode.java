package lexer;


public class MathOpNode extends Node {
	enum Mathoptype
	{
		Add,Subtract,Multyply,Divide,IntNode,MathopNode,equal;
	}
	private Node right;
	private Node left;
	private Mathoptype Nodetype;

	public MathOpNode(Mathoptype s, Node l, Node r) {
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
		return "MathopNode("+Nodetype+","+left.ToString()+","+right.ToString()+")";
	}
}
