package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import Interface.WordNode;

public class ConcordanceBuilder {
	
	public ConcordanceBuilder() {}
	
	public static WordNode buildConcordance () throws FileNotFoundException{
//		String fileName = getFileName();
		String fileName = "C:\\Users\\Quang Nguyen\\OneDrive\\mon hoc\\"
				+ "Fall 2015\\CoSc 20803\\Concordance\\AChristmasCarol.txt";
		
		if (fileName == null) {
			throw new FileNotFoundException("Unsuccessfully load text file !!");
		}
		
		try {
			BufferedReader br = new BufferedReader (new FileReader(fileName));
			String paragraph = "";
			String line;
			int paragraphCount = 0;
			int sentenceCount = 0;
			while ((line = br.readLine()) != null) {
				if (line.trim().equals("")) {
					paragraphCount++;
					Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
				    Matcher reMatcher = re.matcher(paragraph);
					String sentence = "";
				    while (reMatcher.find()) {
				    	sentence += " " + reMatcher.group();
				    	String lastCharacter = sentence.substring(sentence.length() - 1, 
								sentence.length());
				    	if (".!?'".contains(lastCharacter)) {
				    		sentenceCount++;
				    		System.out.println(sentence + " " + sentenceCount);
				    		sentence = "";
				    	}
					}
					paragraph = "";
				} else {
					paragraph += " " + line;
				}
				

				
			}
			
		} catch (IOException e) {}
		
		return null;
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
