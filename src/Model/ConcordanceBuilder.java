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
	
	public static WordNode buildConcordance (WordNode concordanceTree, HashMap<String, Integer> commonWords) throws FileNotFoundException{
//		String fileName = getFileName();
		String fileName = "C:\\Users\\Quang Nguyen\\OneDrive\\mon hoc\\"
				+ "Fall 2015\\CoSc 20803\\Concordance\\AChristmasCarol(large).txt";
		
//		FirstParagraph, AChristmasCarol, AChristmasCarol(large)
		
		if (fileName == null) {
			throw new FileNotFoundException("Unsuccessfully load text file !!");
		}
		
		try {
			
			long start = System.nanoTime();
			
			
			String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
			ArrayList<String> paragraphs = Splitter.splitIntoParagraphs(content);
			int paragraphCount = 0;
			int sentenceCount = 0;
			for (String paragraph: paragraphs) {
				paragraphCount++;
				ArrayList<String> sentences = Splitter.splitIntoSentences(paragraph);
				for (String sentence: sentences) {
					sentenceCount++;
//					System.out.println(sentenceCount + " " + sentence);
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
			
			
			
			long stopTime = System.nanoTime();
			long elapsed = stopTime - start;
			System.out.println("elasped time: " + elapsed/1.0e9);
			
			
		} catch (IOException e) {}
		return concordanceTree;
	}
	
	
	
	static String getFileName() {
		JFileChooser fc = new JFileChooser("C:\\Users\\Quang Nguyen\\OneDrive\\mon hoc\\Fall 2015\\CoSc 20803\\Concordance");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}
	
}
