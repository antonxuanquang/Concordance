package Interface;

import java.util.ArrayList;

public class ContextNode {
	
	int paragraphNum, sentenceNum;
	String context;
	ContextNode node;
	
	public ContextNode() {}
	
	public int getParagraphNum() {
		return paragraphNum;
	}
	public void setParagraphNum(int i) {
		paragraphNum = i;
	}
	public int getSentenceNum() {
		return sentenceNum;
	}
	public void setSentenceNum(int i) {
		sentenceNum = i;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String s) {
		context = s;
	}
	public ContextNode getNext() {
		return node;
	}
	public void setNext(ContextNode n) {
		node = n;
	}
	
	
	// methods operate on individual node
	
	//methods operate on links
	@Override
	public String toString() {
		String result = "";
		ContextNode current = this;
		while (current != null) {
			result += current.getContext() + "\n";
			current = current.getNext();
		}
		return result;
	}
	
	private ContextNode getLastNode() {
		ContextNode node = this;
		while (node.getNext() != null) {
			node = node.getNext();
		}
		return node;
	}
	
	public void addContext(ArrayList<Object> context) {
		ContextNode node = new ContextNode();
		node.setContext((String) context.get(0));
		node.setParagraphNum((int)context.get(1));
		node.setSentenceNum((int)context.get(2));
		getLastNode().setNext(node);
	}
}
