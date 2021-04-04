package lexer;

public class LabeledStatementNode extends StatementNode{
	
	private String labelsting;
	Node labelnode;
	public LabeledStatementNode(String value, Node labelNode) {		//get the value from parser
		this.labelsting =value;
		this.labelnode = labelNode;
	}

	public String getinterNode()
	{
		return labelsting + labelnode;					
	}
	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "labeledStatementNode"+"(LabelName("+labelsting+"),"+labelnode.ToString()+")";
	}

}
