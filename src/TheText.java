import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Adam Buckley
 * This is a class that takes in a .txt file (for the moment)
 * and is testing if I can turn the simply first count the instances of
 * letters in a an ASCII word.
 */

public class TheText {

	// This Map maps ASCII characters to the no. of instances of that charactar in a given sentence.
	public static Map<String, Integer> wordMap = new HashMap<>();
	public static Map<String, MyBinaryTree> treeMap = new HashMap<>();

	public static void main(String[] args) {

		try {
			File file = new File("resources/mississipiriver.txt");

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			// while there is still more characters in this line to be read and processed...
			while ((line = bufferedReader.readLine()) != null) {

				for (int i = 0 ; i < line.length() ; i++)
				{
					// Derive the string version of the letter.
					String stringVersion = String.valueOf(line.charAt(i));

					if (wordMap.containsKey(stringVersion))
					{
						// Below line assigns to num the number which that was assigned
						// that letter previously.
						int num = wordMap.get(stringVersion);
						// now plus that number by 1 and add it back to the wordMap as was
						// but with num incremented by 1.
						num++;
						wordMap.put(stringVersion, num);
					}
					else
					{
						// This happens if the letter wasn't in the Map to begin with,
						// so put the new letter into the map and assign 1 to it's integer mapping.
						wordMap.put(stringVersion,1);
					}
				}
				//System.out.println(wordMap);
			}
			fileReader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		buildForests();
		MyBinaryTree tree = buildBinaryTreeFromMap();
		
		System.out.println("TREE:");
		tree.printTheArrayList();
		
		tree.traverseTheTree(0);
	}

	// for each letter....build its very own tree OBJECT.
	public static void buildForests()
	{
		for(String key : wordMap.keySet())
		{	
			// tree map maps a key to a letter to it's own tree object;
			treeMap.put(key, new MyBinaryTree(key));
		}
	}

	public static MyBinaryTree buildBinaryTreeFromMap()
	{
		while(wordMap.size() > 1)
		{
			String minimumKey = null;
			int minimumValue = 0;

			String secondLastMinimumKey = null;
			int secondLastMinimumValue = 0;

			for(String key : wordMap.keySet())
			{
				int theValue = wordMap.get(key);

				if (minimumKey == null || theValue < minimumValue)
				{
					minimumValue = theValue;
					minimumKey = key;
				}
			}

			for(String key : wordMap.keySet())
			{
				int theValue = wordMap.get(key);

				if ((secondLastMinimumKey == null || theValue < secondLastMinimumValue) && !(minimumKey.equals(key)))
				{
					secondLastMinimumValue = theValue;
					secondLastMinimumKey = key;
				}
			}

			String theNewStringKey = minimumKey + secondLastMinimumKey;
			int theValueTwoAdded = minimumValue + secondLastMinimumValue;
			wordMap.put(theNewStringKey, theValueTwoAdded);

			//System.out.println(minimumKey + secondLastMinimumKey);

			wordMap.remove(minimumKey);
			wordMap.remove(secondLastMinimumKey);

			System.out.println("TEST:" + "PASSED IN PARAM IS:" + secondLastMinimumKey);
			treeMap.put(theNewStringKey, (treeMap.get(minimumKey)).merge(treeMap.get(secondLastMinimumKey), theNewStringKey));

			//treeMap.binaryTreeInArray.add

			System.out.println("BUILD:" + minimumKey + secondLastMinimumKey);

			treeMap.remove(minimumKey);
			treeMap.remove(secondLastMinimumKey);
		}	
		//System.out.println(treeMap.values().iterator().next().getArrayList().size());
		return treeMap.values().iterator().next();
	}
}