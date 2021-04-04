package lexer;

public class BooleanOperationNode extends StatementNode{
	
	private Node leftnode;
	private Token operationtoken;
	private Node rightnode;
	
	public BooleanOperationNode(Node left, Token operation, Node right) {
		// TODO Auto-generated constructor stub
		this.leftnode = left;
		this.operationtoken = operation;
		this.rightnode = right;
	}
	
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return leftnode.ToString()+" "+ operationtoken.getType()+" " + rightnode.ToString();
	}
}
