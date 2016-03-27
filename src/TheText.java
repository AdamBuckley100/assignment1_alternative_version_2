import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TheText {
	
	public static Map<String, Integer> wordMap = new HashMap<>();

	public static void main(String[] args) {
		try {
			File file = new File("resources/mississipiriver.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// add line by line to heap:
			//Heap<String> heap = new Heap<String>();
			//Map<String,Integer> wordMap = new HashMap<String,Integer>();
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			
			//String[] splitArrays;
			while ((line = bufferedReader.readLine()) != null) {
				// \\s+ means all types of white space and any amount
				// my splitArrays has: the two+ tokens in it starting from 0 to no. of single words-1.
				
				for (int i = 0 ; i < line.length() ; i++)
				{
				//String[] splitArrays = line.split("\\s+");
				String stringVersion = String.valueOf(line.charAt(i));
			//	splitArrays.add(stringVersion);
				
				if (wordMap.containsKey(stringVersion))
				{
					int num = wordMap.get(stringVersion);
					num++;
					wordMap.put(stringVersion, num);
				}
				else
				{
					wordMap.put(stringVersion,1);
				}
			
				//System.out.println(splitArrays[0]);
			}
			//System.out.println("Contents of file:");
			//System.out.println(wordMap.size());
			System.out.println(wordMap);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}