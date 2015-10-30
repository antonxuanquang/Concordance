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
	
	public static HashMap<String, Integer> buildCommonWordsHash() throws FileNotFoundException{
//		String fileName = getFileName();
		String fileName = "C:\\Users\\Quang Nguyen\\OneDrive\\mon hoc\\"
				+ "Fall 2015\\CoSc 20803\\Concordance\\MostCommonWords.txt";
		
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
	
	static String getFileName() {
		JFileChooser fc = new JFileChooser("C:\\Users\\Quang Nguyen\\OneDrive\\mon hoc\\Fall 2015\\CoSc 20803\\Concordance");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}
}