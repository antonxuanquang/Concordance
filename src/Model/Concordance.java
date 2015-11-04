package Model;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

import Interface.WordNode;

public class Concordance {
	
	HashMap<String, Integer> commonWords = new HashMap<String, Integer>();
	WordNode concordanceTree;
	
	public Concordance() {
	}
	
	public void buildCommonWordsHash() throws FileNotFoundException {
		try {
			commonWords = CommonWords.buildCommonWordsHash();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}
	
	public void buildConcordance() throws FileNotFoundException {
		try {
			concordanceTree = ConcordanceBuilder.buildConcordance(commonWords);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}
	
	public WordNode getTree() {
		return concordanceTree;
	}
	
	public void resetCommonWordsHashTable() {
		commonWords = new HashMap<String, Integer>();
	}
}
