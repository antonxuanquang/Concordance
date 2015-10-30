package Model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {
	
	static ArrayList<String> splitIntoParagraphs(String content) {
		ArrayList<String> garagraphs = new ArrayList<String>();
		String[] lines = content.split("\n");
		garagraphs.add("");
		for (String line: lines) {
			if (line.trim().equals("")) {
				garagraphs.add("");
			} else {
				String currentSentence = garagraphs.get(garagraphs.size() - 1);
				currentSentence += " " + line;
				garagraphs.set(garagraphs.size() - 1, currentSentence);
			}
		}
		garagraphs.remove(0); // remove title
		return garagraphs;
	}
	
	static ArrayList<String> splitIntoSentences(String paragraph) {
		ArrayList<String> sentences = new ArrayList<String>();
		Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
	    Matcher reMatcher = re.matcher(paragraph);
	    while (reMatcher.find()) {
	    	sentences.add(reMatcher.group());
		}
		return sentences;
	}
	
	static ArrayList<String> splitIntoWords (String sentence) {
		ArrayList<String> words = new ArrayList<String>();
		sentence = sentence.replaceAll("[!?,.'`:;_\"--(){}1234567890\\[\\]]", "");
		String[] strs = sentence.split("\\s+");
		for (String word: strs) {
			words.add(word.toLowerCase());
		}
		return words;
	}
}
