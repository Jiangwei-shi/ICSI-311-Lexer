package lexer;
/*
 * author Jiangwei Shi
 */
import java.util.ArrayList;
import java.util.List;
import lexer.MathOpNode.Mathoptype;


public class Parser {
	
	//Pass list of Token and return a Node
	public Node parser(List<Token> s) throws getException				
	{		
		Node result = Statements(s);//pass the list into Expression
		return result;	
	}
	
	//Statements method
	Node Statements(List<Token> s) throws getException 					
	{
		Node left ;
		List<Node> Nodelist = new ArrayList<>();
		while(s.size() != 0 && s.get(0).getType() != Token.TokenType.EndOfLine)
		{
			Node right = null;
			right = Statement(s);
			Nodelist.add(right);			
		}
		left =new StatementsNode(Nodelist);
		return left;
	}
	
	//statement method , Print , Data , Read , identifier , Expression
	Node Statement(List<Token> s) throws getException 					
	{
		Node right;
		if (MatchAndRemove(Token.TokenType.Print,s) != null)    //if it is print or String 
		{
			right = PrintStatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.Data,s) != null)
		{
			right = Datestatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.Read,s) != null)
		{
			right = readstatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.Input,s) != null)
		{
			right = Inputstatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.GOSUB,s) != null)
   		{
			right = GOSUBStatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.FOR,s) != null)
   		{
			right = FORStatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.NEXT,s) != null)
   		{
			right = NextStatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.RETURN,s) != null)
   		{
			right = RETURNStatement(s);
		}
		else if (MatchAndRemove(Token.TokenType.IF,s) != null)
   		{
			right = IFStatement(s);
		}
		else if (s.get(0).getType() == Token.TokenType.RANDOM || s.get(0).getType() == Token.TokenType.LEFT$
				|| s.get(0).getType() == Token.TokenType.RIGHT$ || s.get(0).getType() == Token.TokenType.MID$
				|| s.get(0).getType() == Token.TokenType.NUM$ || s.get(0).getType() == Token.TokenType.VAL
				|| s.get(0).getType() == Token.TokenType.VAL$)
   		{
			right = FunctionStatement(s);
		}	
		else if (s.get(0).getType() == Token.TokenType.label)
   		{
			right = LabeledStatement(s);
		}		
		else if (s.get(0).getType() == Token.TokenType.identifier)
		{							
			right = Assignment(s);				
		}
		else 
		{
			right = Expression(s);			
		}
		return right;
	}

	Node FunctionStatement(List<Token> s) throws getException {
		Node Function = null;
		Token functionname;
		functionname =s.get(0);
		s.remove(0);
		List<Token> functionList = new ArrayList<>();
		List<Node> FunctionList = new ArrayList<>();	
		if(MatchAndRemove(Token.TokenType.lparen, s) != null)
		{
		while(MatchAndRemove(Token.TokenType.rparen, s) == null) //if is lparen
		{
			while(MatchAndRemove(Token.TokenType.comma,s)==null && s.get(0).getType() != Token.TokenType.rparen)//if not comma and not Endofline, Then enter the while loop
			{
				functionList.add(s.get(0));
				s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			functionList.add(end);	
			FunctionList.add(Expression(functionList));
			functionList.removeAll(functionList);			
		}
		Function = new FunctionNode(functionname , FunctionList);
		// TODO Auto-generated method stub
		return Function;
		}
		else 
			throw new getException("the right type should be functionname ( entity , entity )");
	}

	Node IFStatement(List<Token> s) throws getException {
		Node left = null;
		Token right = null;
		Node IFnode = null;
		List<Token> TokenList = new ArrayList<>();	
		// TODO Auto-generated method stub		
		while(MatchAndRemove(Token.TokenType.THEN,s)==null) 
		{
			while(s.get(0).getType() != Token.TokenType.THEN)//if not THEN, Then enter the while loop
			{
					TokenList.add(s.get(0));
					s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			TokenList.add(end);	
			left = BooleanOperationstatement(TokenList);
			TokenList.removeAll(TokenList);		
		}
		right = s.get(0);
		s.remove(0);
		IFnode = new IfNode(left,right);
		return IFnode;
	}
	

	Node BooleanOperationstatement(List<Token> s) throws getException {
		Node left = null;
		Token operation;
		Node right = null;
		Node BooleanOperation;
		List<Token> leftexpression = new ArrayList<>();
		List<Token> rightexpression = new ArrayList<>();
		while(s.get(0).getType() != Token.TokenType.lessthan && s.get(0).getType() != Token.TokenType.greaterthan
				&& s.get(0).getType() != Token.TokenType.lessthanequals && s.get(0).getType() != Token.TokenType.greaterthanequals
				&& s.get(0).getType() != Token.TokenType.Notequals && s.get(0).getType() != Token.TokenType.equals) 
		{
			while(s.get(0).getType() != Token.TokenType.lessthan && s.get(0).getType() != Token.TokenType.greaterthan
					&& s.get(0).getType() != Token.TokenType.lessthanequals && s.get(0).getType() != Token.TokenType.greaterthanequals
					&& s.get(0).getType() != Token.TokenType.Notequals && s.get(0).getType() != Token.TokenType.equals)//if not THEN, Then enter the while loop
			{
				leftexpression.add(s.get(0));
				s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			leftexpression.add(end);	
			left = Expression(leftexpression);	
		}
		operation = s.get(0);
		s.remove(0);
		while(s.get(0).getType() != Token.TokenType.EndOfLine) 
		{
			while(s.get(0).getType() != Token.TokenType.EndOfLine)//if not THEN, Then enter the while loop
			{
				rightexpression.add(s.get(0));
				s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			rightexpression.add(end);	
			right = Expression(rightexpression);	
		}
		BooleanOperation = new BooleanOperationNode(left, operation ,right);
		return BooleanOperation;
	}

	//For loop method	
	Node FORStatement(List<Token> s) throws getException {
		Node left ;
		Node startvalue;
		Node endvalue;
		Node Stepvalue;
		Node ForNode;
		left = factor(s);
		if(MatchAndRemove(Token.TokenType.equals, s) != null)
		{
			startvalue = factor(s);
			if(MatchAndRemove(Token.TokenType.To, s) != null)
			{
				endvalue = factor(s);
				if(MatchAndRemove(Token.TokenType.STEP, s) != null)
					{
						Stepvalue = factor(s);
						ForNode = new ForNode(left,startvalue,endvalue,Stepvalue);
						return ForNode;
					}
					else 
					{
						ForNode = new ForNode(left,startvalue,endvalue);
						return ForNode;
					}
			}
		}
		return null;
	}

	//return method
	Node RETURNStatement(List<Token> s) throws getException
	{
		Node right;
		if(s.get(0).getType()==Token.TokenType.EndOfLine)
		{
			right = new ReturnNode(s);	
			return right;
		}
		else
			throw new getException("RETURN is alone, You couldn't add anything after it");		
	}

	//Hold list of Token and return a Node	***under Input case hold list of variables and string
	Node Inputstatement(List<Token> s) throws getException 
	{	
		Node left = null;
		List<Node> listhold = new ArrayList<>();
		listhold = Inputlist(s);
		left = new InputNode(listhold);									//pass Node list and return a Node
		return left;
	}
	
	//Hold list of Token and return a Node list   ***under Input case hold list of variables and string
	List<Node> Inputlist(List<Token> s) throws getException {
		List<Token> TokenList = new ArrayList<>();
		List<Node> DataList = new ArrayList<>();		
		while(s.size() != 0 &&s.get(0).getType() != Token.TokenType.number)
		{
			while(MatchAndRemove(Token.TokenType.comma,s)==null && MatchAndRemove(Token.TokenType.EndOfLine,s)==null)//if not comma and not Endofline, Then enter the while loop
			{
				TokenList.add(s.get(0));
				s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			TokenList.add(end);	
			DataList.add(Expression(TokenList));
			TokenList.removeAll(TokenList);			
		}
		return DataList ;	
	}
	
	//Hold list of Token and return a Node	***under read case hold list of variables
	Node readstatement(List<Token> s) throws getException 
	{
		if(s.get(0).getType() == Token.TokenType.identifier)
		{
		Node left = null;
		List<Node> listhold = new ArrayList<>();
		listhold = readlist(s);
		left = new ReadNode(listhold);									//pass Node list and return a Node
		return left;
		}
		else 
		return null;
	}
	
	//Hold list of Token and return a Node list   ***under read case hold list of variables
	List<Node> readlist(List<Token> s) throws getException {
		List<Token> TokenList = new ArrayList<>();
		List<Node> DataList = new ArrayList<>();		
		while(s.size() != 0 && s.get(0).getType() == Token.TokenType.identifier) 
		{			
			while(MatchAndRemove(Token.TokenType.comma,s)==null && MatchAndRemove(Token.TokenType.EndOfLine,s)==null)//if not comma and not Endofline, Then enter the while loop
			{
				TokenList.add(s.get(0));
				s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			TokenList.add(end);	
			DataList.add(Expression(TokenList));
			TokenList.removeAll(TokenList);	
		}
		return DataList ;	
	}
	
	//Hold list of Token and return a Node	***under Data case hold list of STRING, IntegerNode and FloatNode. 
	Node Datestatement(List<Token> s) throws getException 
	{	
		Node left = null;
		List<Node> listhold = new ArrayList<>();
		listhold = Datelist(s);
		left = new DataNode(listhold);									//pass Node list and return a Node
		return left;
	}
	
	//Hold list of Token and return a Node list   ***under Data case hold list of STRING, IntegerNode and FloatNode. 
	List<Node> Datelist(List<Token> s) throws getException {
		List<Token> TokenList = new ArrayList<>();
		List<Node> DataList = new ArrayList<>();
		while(s.size() != 0 && s.get(0).getType() != Token.TokenType.identifier) 
		{
			while(MatchAndRemove(Token.TokenType.comma,s)==null && MatchAndRemove(Token.TokenType.EndOfLine,s)==null)//if not comma and not Endofline, Then enter the while loop
			{
					TokenList.add(s.get(0));
					s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			TokenList.add(end);	
			DataList.add(Expression(TokenList));
			TokenList.removeAll(TokenList);			
		}
		return DataList ;	
	}
	
	//Hold list of Token and return a Node	***under Print case
	Node PrintStatement(List<Token> s) throws getException				
	{
		Node left = null;
		List<Node> listhold = new ArrayList<>();
		listhold =printList(s);
		left = new PrintNode(listhold);									//pass Node list and return a Node
		return left;	
	}
	
	//Hold list of Token and return a Node list   ***under Data case	
	List<Node> printList(List<Token> s) throws getException				
	{			
		List<Token> TokenList = new ArrayList<>();
		List<Node> printList = new ArrayList<>();
		while(s.size()!= 0) 
		{

			while(MatchAndRemove(Token.TokenType.comma,s)==null && MatchAndRemove(Token.TokenType.EndOfLine,s)==null)//if not comma and not Endofline, Then enter the while loop
			{
			
					TokenList.add(s.get(0));
					s.remove(0);				
			}
			Token end = new Token(Token.TokenType.EndOfLine);
			TokenList.add(end);	
			printList.add(Assignment(TokenList));
			TokenList.removeAll(TokenList);		

		}
		return printList ;			
	}
	
	//label
	Node LabeledStatement(List<Token> s1) throws getException
	{
		String left;
		Node right;
		Node labeledStatementNode;
		left = s1.get(0).getValue();
		s1.remove(0);
		right = Assignment(s1);
		labeledStatementNode = new LabeledStatementNode(left, right);
		return labeledStatementNode;		
	}
	
	//GOSUB
	Node GOSUBStatement(List<Token> s1) throws getException
	{
		Node left;
		Node right;
		if(s1.get(0).getType() == Token.TokenType.identifier)
		{
		left = factor(s1);
		right = new GosubNode(left);
		return right;	
		}
		else 
			throw new getException("GOSUB requires a single IDENTIFIER");		
	}
	
	//NEXT
	Node NextStatement(List<Token> s1) throws getException
	{
		Node left;
		Node right;
		if(s1.get(0).getType() == Token.TokenType.identifier)
		{
		left = factor(s1);
		right = new NextNode(left);
		return right;	
		}
		else 
			throw new getException("Next requires a single IDENTIFIER");	
	}
	
	//pass an identifier and Token list 
	Node Assignment(List<Token> s1) throws getException				
	{
		Node left;
		Node right;
		Node Assignment;
		if(s1.get(1).getType() == Token.TokenType.equals)
		{
			left = factor(s1);
			s1.remove(0);
			right = Expression(s1);
			Assignment = new AssignmentNode(Mathoptype.equal, left, right);
			return Assignment;
		}
		else
		return Expression(s1);		
	}
	
	//+,-Expression
	Node Expression(List<Token> a) throws getException //deal with +,-
	{	
		Node left;
		Node right;
		Node MathOpNode;
		left = term(a);									//recursion
		if(MatchAndRemove(Token.TokenType.plus,a) != null)//if is plus match it and remove it
		{
			right =Expression(a);
			MathOpNode = new MathOpNode(Mathoptype.Add, left, right);
			return MathOpNode;
		}
		else if (MatchAndRemove(Token.TokenType.minus,a) != null)//if is minus match it and remove it
		{
			right =Expression(a);
			MathOpNode = new MathOpNode(Mathoptype.Subtract, left, right);
			return MathOpNode;
		}
		return left;		
	}
	
	//*,term
	Node term(List<Token> b) throws getException 
	{	
		Node left;
		Node right;
		Node MathOpNode;
		left = factor(b);   						//recursion
		if(MatchAndRemove(Token.TokenType.times,b) != null)//if is times match it and remove it
		{
			right =term(b);
			MathOpNode = new MathOpNode(Mathoptype.Multyply, left, right);
			return MathOpNode;
		}
		else if (MatchAndRemove(Token.TokenType.divide,b) != null)//if is divide match it and remove it
		{
			right =term(b);
			MathOpNode = new MathOpNode(Mathoptype.Divide, left, right);
			return MathOpNode;
		}
		return left;
	}
	
	//factor
	Node factor(List<Token> c) throws getException
	{	
		Token hold = MatchAndRemove(Token.TokenType.number,c);////if is number match it and remove it
		if (hold != null)
		{
			if(hold.getValue().indexOf(".")>0)
				{				
					FloatNode floatnode =new FloatNode(hold.getValue());
					return floatnode;
				}
			else
				{
					IntegerNode intnode = new IntegerNode(hold.getValue());
					return intnode;
				}
		}
		else if (c.get(0).getType() == Token.TokenType.identifier)
		{
			VariableNode variablenode =new VariableNode(c.get(0).getValue());
			c.remove(0);
			return variablenode;
		}
		else if (c.get(0).getType() == Token.TokenType.string)
		{
			StringNode stringNode =new StringNode(c.get(0).getValue());
			c.remove(0);
			return stringNode;
		}
		else if (MatchAndRemove(Token.TokenType.lparen,c) != null )
		{
			List<Token> parenfunction = new ArrayList<>();	

			while(MatchAndRemove(Token.TokenType.rparen,c)==null)
			{
				parenfunction.add(c.get(0));
				c.remove(0);
			}		
			Token end = new Token(Token.TokenType.EndOfLine);
			parenfunction.add(end);	
			return Expression(parenfunction);		
		}
		else
			throw new getException("The first letter in the list should be number");
	} 
	
	//match and remove method
	public Token MatchAndRemove(Token.TokenType type,List<Token> d )  
	{		
		if(d.get(0).getType() == type )
		{			
			Token hold = new Token(type);
			hold = d.get(0);
			d.remove(0);
			return hold;			
		}
		return null;		
	}
	
}

