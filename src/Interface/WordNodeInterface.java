package Interface;

public interface WordNodeInterface {
	public void setLeft(WordNode node);
	public WordNode getLeft();
	public void setWord(String s);
	public String getWord();
	public void setCount(int i);
	public int getCount();
	public void setContextLink(ContextNode s);
	public ContextNode getContextLink();
	public void setRight(WordNode node);
	public WordNode getRight();
	public void setRightThread(boolean b);
	public boolean getRightThreaded();
	public void setLeftThread(boolean b);
	public boolean getLeftThreaded();
}
