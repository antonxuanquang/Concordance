package Interface;

public interface ContextNodeInterface {
	public int getParagraphNum();
	public void setParagraphNum(int i);
	public int getSentenceNum();
	public void setSentenceNum(int i);
	public String getContext();
	public void setContext(String s);
	public ContextNode getNext();
	public void setNext(ContextNode n);
}
