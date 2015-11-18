package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

public class CommonWords{
	
	public CommonWords() {}
	
	
	/**
	 * 
	 * Build Hash map that contains all the common words
	 * 
	 * @return Hash Map that contains all the common words being the keys.
	 * @throws FileNotFoundException When user fail to load a file.
	 */
	public static HashMap<String, Integer> buildCommonWordsHash() throws FileNotFoundException{
		String fileName = getFileName();
		
		if (fileName == null) {
			throw new FileNotFoundException("Unsuccessfully load common words file !! ");
		}
		
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line=in.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line," \t\n,.;-?!()&/\\\"0123456789");
				while (st.hasMoreTokens()) {
					String s = st.nextToken().toLowerCase();
					if(hash.containsKey(s)) {
						int c = hash.get(s);
						c++;
						hash.put(s,c);
					}
					else {
						hash.put(s, 1);
					}
				}
			}
		} catch (IOException e) {}
		return hash;
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