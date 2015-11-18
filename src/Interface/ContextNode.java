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
	
	
	/**
	 * Loop through context link and concat context string into result variable
	 * @return String of all contexts begin from the Context Node calling this method
	 */
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
	
	/**
	 * @return The last node of Context Link, could be itself.
	 */
	private ContextNode getLastNode() {
		ContextNode node = this;
		while (node.getNext() != null) {
			node = node.getNext();
		}
		return node;
	}
	
	/**
	 * Create a new Context Node, add data, then append it to the link.
	 * @param context Array of context: context string at the first item, paragraph number at the 2nd item, sentence number at the 3rd item
	 */
	public void addContext(ArrayList<Object> context) {
		ContextNode node = new ContextNode();
		node.setContext((String) context.get(0));
		node.setParagraphNum((int)context.get(1));
		node.setSentenceNum((int)context.get(2));
		getLastNode().setNext(node);
	}
}
