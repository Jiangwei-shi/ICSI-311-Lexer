package lexer;

public class IfNode extends StatementNode{

	private Node leftnode;
	private Token rightnode;
	
	public IfNode(Node left, Token right) {
		// TODO Auto-generated constructor stub
		this.leftnode = left;
		this.rightnode = right;
	}
	
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "IFNODE{"+leftnode.ToString() + " THEN " +"label(" +rightnode.getValue()+ ")}" ;
	}
}
