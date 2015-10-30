package Interface;

import java.util.ArrayList;

public class WordNode implements WordNodeInterface {
	
	private WordNode leftNode,rightNode;
	private String word;
	private int count;
	private ContextNode context;
	private boolean isRightTheaded, isLeftTheaded;
	
	public WordNode() {}
	
	public void setLeft(WordNode node) {
		leftNode = node;
	}
	public WordNode getLeft() {
		return leftNode;
	}
	public void setWord(String s) {
		word = s;
	}
	public String getWord() {
		return word;
	}
	public void setCount(int i) {
		count = i;
	}
	public int getCount() {
		return count;
	}
	public void setContextLink(ContextNode s) {
		context = s;
	}
	public ContextNode getContextLink() {
		return context;
	}
	public void setRight(WordNode node) {
		rightNode = node;
	}
	public WordNode getRight() {
		return rightNode;
	}
	public void setRightThread(boolean b) {
		isRightTheaded = b;
	}
	public boolean getRightThreaded() {
		return isRightTheaded;
	}
	public void setLeftThread(boolean b) {
		isLeftTheaded = b;
	}
	public boolean getLeftThreaded() {
		return isLeftTheaded;
	}
	
	public boolean isRightThreaded() {
		return isRightTheaded;
	}
	public boolean isLeftThreaded() {
		return isLeftTheaded;
	}
	
	//methods operate on trees
	public void buildHead() {
		setWord("Head");
		setRight(this);
		setRightThread(false);
		setLeft(this);
		setLeftThread(true);
	}
	
	public void addNode(String word, ArrayList<Object> context) {
		if (isLeftThreaded()) {
			//adding data
			WordNode node = createAReadyNode(word, context);
			
			//set to the left of Head node
			insertToLeft(node);
			
			//finish adding first node
			return;
		}
		WordNode head = this;
		WordNode temp = head.getLeft();
		//looking for the right place to insert, i.e. temp location
		while (temp != head) {
			if ((word.compareTo(temp.getWord()) < 0)) {
				if (temp.isLeftThreaded()) {
					WordNode node = createAReadyNode(word, context);
					temp.insertToLeft(node);
					return;
				} else {
					temp = temp.getLeft();
				}
			} else if ((word.compareTo(temp.getWord()) > 0)) {
				if (temp.isRightThreaded()) {
					WordNode node = createAReadyNode(word, context);
					temp.insertToRight(node);
					return;
				} else {
					temp = temp.getRight();
				}
			} else {
				temp.updateContextData(context);
				return;
			}
		}
//		System.out.println(word);
//		System.out.println(temp.getWord());
//		System.out.println("" + word.compareTo(temp.getWord()));
	}

	private WordNode createAReadyNode(String word, ArrayList<Object> context) {
		WordNode node = new WordNode();
		node.setWord(word);
		node.setCount(1);
		ContextNode contextNode = new ContextNode();
		contextNode.addContext(context);
		node.setContextLink(contextNode);
		return node;
	}
	
	//methods operate on nodes
	public void incrementCount() {
		count++;
	}
	
	public void insertToLeft(WordNode node) {
		node.setRight(this);
		node.setRightThread(true);
		node.setLeft(getLeft());
		node.setLeftThread(getLeftThreaded());
		setLeft(node);
		setLeftThread(false);
	}
	
	public void insertToRight(WordNode node) {
		node.setLeft(this);
		node.setLeftThread(true);
		node.setRight(getRight());
		node.setRightThread(getRightThreaded());
		setRight(node);
		setRightThread(false);
	}
	
	private void updateContextData(ArrayList<Object> context) {
		incrementCount();
		ContextNode node = getContextLink();
		node.addContext(context);
	}
	
	@Override 
	public String toString() {
		String result = "";
		result += "Word: " + word + " \n";
		result += getContextLink().toString();		
		return result;
	}
}
