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
	MyBinaryTree tree;


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
				System.out.println(wordMap);
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
		// if will always be > 1...
		while(wordMap.size() > 1)
		{
			String minimumKey = null;
			int ValueOfMinimumKey = 0;

			String secondLastMinimumKey = null;
			int secondLastMinimumValue = 0;

			// the keys in the wordMap is the STRING (LETTER i.e a)
			for(String key : wordMap.keySet())
			{
				// below: get the value of key we are currently looking at
				// (remember value: is just the NUMBER OF TIMES THAT LETTER IS
				// IN THE WORD/PHRASE
				int theValue = wordMap.get(key);

				// if min key is null (which it is first time round) OR
				// theValue of the key we are currently looking at is LESS than
				// what the value of the minimum key is now......
				if (minimumKey == null || theValue < ValueOfMinimumKey)
				{
					// below: minimum key is appropriately assigned.
					ValueOfMinimumKey = theValue;
					minimumKey = key;
				}
			}

			// going through all the keys (letters) again...
			for(String key : wordMap.keySet())
			{
				// get the value of the key (letter) which we are currently looking at
				int theValue = wordMap.get(key);

				// if second late min key is null (which it is first time round) OR
				// theValue of the key we are currently looking at is LESS than
				// what the value of the second last minimum key is now...... 
				// AND the key we are currently looking at IS NOT what the minimum key IS then....
				if ((secondLastMinimumKey == null || theValue < secondLastMinimumValue) && !(minimumKey.equals(key)))
				{
					// below: second last minimum key is appropriately assigned.
					secondLastMinimumValue = theValue;
					secondLastMinimumKey = key;
				}
			}

			// the new LETTER COMBO is the LETTER of the minimum key and the LETTER of the second late minimum key
			// string concatenated.
			String theNewStringKey = minimumKey + secondLastMinimumKey;
			int theValueTwoAdded = ValueOfMinimumKey + secondLastMinimumValue;
			//  the brand new combined node added to the WORDMAP map.
			wordMap.put(theNewStringKey, theValueTwoAdded);

			//System.out.println(minimumKey + secondLastMinimumKey);

			// the old children of the NEWLY ADDED node is being deleted from WORDMAP. SO WE DONT DEAL WITH THEM AGAIN.
			wordMap.remove(minimumKey);
			wordMap.remove(secondLastMinimumKey);

			//System.out.println("TEST:" + "PASSED IN PARAM IS:" + secondLastMinimumKey);
			
			//treemap maps a LETTER (string) to its ACTUAl MyBinaryTree OBJECT.
			//eventually treeMap will have just ONE thing in it (string to object mapping).
			//so put into treeMap the combined LETTERS (single string variable) and the ACTUAL MyBinaryTree OBJECT
			// which IS the two single MyBinaryObjects (min and secondlastmin) together into one single MOTHER NODE.
			treeMap.put(theNewStringKey, (treeMap.get(minimumKey)).merge(treeMap.get(secondLastMinimumKey), theNewStringKey));

			//treeMap.binaryTreeInArray.add

			System.out.println("BUILD:" + minimumKey + secondLastMinimumKey);

			treeMap.remove(minimumKey);
			treeMap.remove(secondLastMinimumKey);
		}	
		//System.out.println(treeMap.values().iterator().next().getArrayList().size());
		return treeMap.values().iterator().next();
	}

	public int getHeightOfTreeFromFirstArrayList()
	{
		// I need to find the height of the tree....

		int height = 0;

		while (MyBinaryTree.reachEndOfTree == false)
		{
			int currentLevel = 0; // starting at 0 (root node)
			tree.getLettersOnLevel(currentLevel);
			height++;
		}

		int realHeight = height+1;
		return realHeight;
	}
}