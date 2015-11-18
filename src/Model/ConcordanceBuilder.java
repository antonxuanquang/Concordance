package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import Interface.WordNode;

public class ConcordanceBuilder {
	
	public ConcordanceBuilder() {}
	
	public static WordNode buildConcordance (HashMap<String, Integer> commonWords) throws FileNotFoundException{
		String fileName = getFileName();
		
		
		WordNode concordanceTree = new WordNode();
		concordanceTree.buildHead();
		
		if (fileName == null) {
			throw new FileNotFoundException("Unsuccessfully load text file !!");
		}
		
		try {
			String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
			ArrayList<String> paragraphs = Splitter.splitIntoParagraphs(content);
			int paragraphCount = 0;
			int sentenceCount = 0;
			for (String paragraph: paragraphs) {
				paragraphCount++;
				ArrayList<String> sentences = Splitter.splitIntoSentences(paragraph);
				for (String sentence: sentences) {
					sentenceCount++;
					ArrayList<String> words = Splitter.splitIntoWords(sentence);
					for (String word: words) {
						if (!commonWords.containsKey(word)) {
							ArrayList<Object> context = new ArrayList<Object> ();
							context.add(sentence);
							context.add(paragraphCount);
							context.add(sentenceCount);
							concordanceTree.addNode(word, context);
						}
					}
				}
			}
		} catch (IOException e) {}
		return concordanceTree;
	}
	
	
	/**
	 * 
	 * Get path of a file
	 * 
	 * @return Path leading to selected file
	 */
	static String getFileName() {
		JFileChooser fc = new JFileChooser("C:\\");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}
	
}
