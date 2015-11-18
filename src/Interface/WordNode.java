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
	/**
	 * Set the root node to be head.
	 * Set left non-threaded link pointing to itself.
	 * Set right threaded link pointing to itself.
	 */
	public void buildHead() {
		setWord("Head");
		setRight(this);
		setRightThread(false);
		setLeft(this);
		setLeftThread(true);
	}

	/**
	 * 
	 * Construct threaded binary search tree.  
	 * 
	 * @param word word from data set
	 * @param context sentence in the data set where word is sitting in.
	 */
	public void addNode(String word, ArrayList<Object> context) {
		if (isLeftThreaded()) {
			WordNode node = createAReadyNode(word, context); //adding data
			insertToLeft(node); //set to the left of Head node
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
	}

	
	/**
	 * 
	 * Creat a Word Node ready to be added into tree.
	 * 
	 * @param word Words from text file.
	 * @param context Sentence where the the word is sitting in. 
	 * @return Word Word containing data (word, count and context data).
	 */
	private WordNode createAReadyNode(String word, ArrayList<Object> context) {
		WordNode node = new WordNode();
		node.setWord(word);
		node.setCount(1);
		
		
		ContextNode contextNode = new ContextNode();
		contextNode.setContext((String) context.get(0));
		contextNode.setParagraphNum((int)context.get(1));
		contextNode.setSentenceNum((int)context.get(2));
		node.setContextLink(contextNode);
		return node;
	}
	
	
	/**
	 * 
	 * Algorithm: Staring from head of the tree, visit in order successor of currently visiting node until reaching the root.
	 * 
	 * @return String that contains all words and its context in the file.
	 */
	public String tInOrder() {
		String result = "";
		WordNode head = this;
		WordNode temp = head;
		do {
			temp = findInOrderSuccessor(temp);
			if (temp != head) {
				result += temp.getWord() + " " + temp.getCount() + "\n";
				result += temp.toString() + "\n";
			}
		} while (temp != head);		
		return result;
	}
	
	/**
	 * 
	 * Algorithm: One to the right, all to the left if not right-threaded. 
	 * 
	 * @param temp currently visiting Word Node
	 * @return in order successor of the visiting Word Node
	 */
	public WordNode findInOrderSuccessor(WordNode temp) {
		WordNode node = temp.getRight();
		if (!temp.isRightThreaded()) {
			while (!node.isLeftThreaded()) {
				node = node.getLeft();
			}
		}
		return node;
	}

	
	
	//methods operate on nodes
	/**
	 * Increment the count
	 */
	private void incrementCount() {
		count++;
	}
	
	/**
	 * 
	 * 1. Set right threaded link to the node preceding node in parameter
	 * 2. Left link has the same property of the node preceding node in parameter
	 * 3. Set left link of preceding node to the node in parameter 
	 * 
	 * @param node Word Node about to be added into tree
	 */
	private void insertToLeft(WordNode node) {
		node.setRight(this);
		node.setRightThread(true);
		node.setLeft(getLeft());
		node.setLeftThread(getLeftThreaded());
		setLeft(node);
		setLeftThread(false);
	}
	
	/***
	 * 
	 * 1. Set right threaded link to the node preceding node in parameter
	 * 2. Left link has the same property of the node preceding node in parameter
	 * 3. Set left link of preceding node to the node in parameter 
	 * 
	 * @param node Word Node about to be added into tree
	 */
	private void insertToRight(WordNode node) {
		node.setLeft(this);
		node.setLeftThread(true);
		node.setRight(getRight());
		node.setRightThread(getRightThreaded());
		setRight(node);
		setRightThread(false);
	}
	
	/** 
	 * @param context Array of Context
	 */
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

	public WordNode delete() {
		if (isLeftThreaded()) {
			WordNode temp = getRight();
			temp.setRightThread(getRightThreaded());
			return temp;
		} else if (isRightThreaded()) {
			WordNode temp = getLeft();
			temp.setLeftThread(getLeftThreaded());
			return temp;
		} else {
			WordNode temp = getRight();
			WordNode successor = this ;
			if (!temp.isLeftThreaded()) {
				while (!temp.isLeftThreaded()) {
					successor = temp;
					temp = temp.getLeft();
				}
				if (temp.isRightThreaded()) {
					successor.setLeft(temp);
				} else {
					successor.setLeft(temp.getRight());
				}
				temp.setRight(getRight());
				temp.setRightThread(getRightThreaded());
			}
			temp.setLeft(getLeft());
			temp.setLeftThread(getLeftThreaded()); 
			return temp;
		}
	}
}
