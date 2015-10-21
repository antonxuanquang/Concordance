package Interface;

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

}
