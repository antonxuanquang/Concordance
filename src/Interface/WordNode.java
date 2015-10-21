package Interface;

public class WordNode implements WordNodeInterface {
	
	WordNode leftNode,rightNode;
	String word;
	int count;
	ContextNode context;
	boolean isRightTheaded, isLeftTheaded;
	
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
	public void setRightLink(WordNode node) {
		rightNode = node;
	}
	public WordNode getRightLink() {
		return rightNode;
	}
	public void setRightThread(boolean b) {
		isRightTheaded = b;
	}
	public boolean getRightThread() {
		return isLeftTheaded;
	}
	public void setLeftThread(boolean b) {
		isLeftTheaded = b;
	}
	public boolean getLeftThread() {
		return isLeftTheaded;
	}

}
