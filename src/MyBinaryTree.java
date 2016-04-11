import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyBinaryTree
{
	// the binaryTreeInArray is the FIRST array, the flawed one, there will be two
	ArrayList<String> binaryTreeInArray = new ArrayList<String>();
	public static Map<String, String> huffmanCodeMap = new HashMap<>();
	//public static Map<String, String> letterToHuffmanCodeMap = new HashMap<>();
	static boolean reachEndOfTree = false;
	String MyWordInHuffmanCodeForm;
	String myArrayListInString =  "";

	//String indexHuffmanRunningTotal = "";

	int traverseCount;

	// CONSTRUCTOR USED IN MERGE METHOD.
	public MyBinaryTree(String key)
	{
		binaryTreeInArray.add(key);
	}


	// returns true if it IS all nulls, returns false if it is *NOT* all nulls.
	public boolean isAllNulls(ArrayList<String> list)
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			if (list.get(i) != null)
			{
				return false;
			}
		}
		return true;
	}


	// This method allows two single MyBinaryTree objects to be combined into ONE COMBINED MyBinaryTree Object,
	// which takes on the LETTERING of the two initial nodes COMBINED (string concatenated).
	public MyBinaryTree merge(MyBinaryTree tree, String theNewStringKey)
	{
		// the array which will eventually have the FINISHED correctly placing of the two nodes given to this method
		ArrayList<String> theArrayOfTheTwoCombinedNodes = new ArrayList<String>();

		// put into place 0 the LETTERING of the combined two nodes, to start with.
		theArrayOfTheTwoCombinedNodes.add(theNewStringKey);

		boolean dualNullGivenBack = false;
		int i = 0;
		do
		{
			ArrayList<String> levelXLettersArrayTempThis = getLettersOnLevel(i);
			// is the add all below valid?
			//ArrayList<String> checkWhatLettersThisTreeHas = theArrayOfTheTwoCombinedNodes.addAll(levelXLettersArrayTempThis);

			ArrayList<String> levelXLettersArrayTempParamTree = tree.getLettersOnLevel(i);
			// is the add all below valid?
			//theArrayOfTheTwoCombinedNodes.addAll(levelXLettersArrayTempParamTree);	

			if ((isAllNulls(levelXLettersArrayTempThis) == true) && (isAllNulls(levelXLettersArrayTempParamTree) == true))
			{
				System.out.println("hi");
				// cus if both "soon to be combined" subtrees BOTH have ALL nulls returned when the letterings/nodes at a certain
				// level are checked.... then I can be SURE that the level we are CURRENTLY looking at is a level that does
				// not exist and i should not add this BOTH subtrees all nulls to the final and single arraylist.
				dualNullGivenBack = true;
			}
			else
			{
				theArrayOfTheTwoCombinedNodes.addAll(levelXLettersArrayTempThis);
				theArrayOfTheTwoCombinedNodes.addAll(levelXLettersArrayTempParamTree);
			}
			// i++ because the next level must be checked.
			i++;
		}
		while (dualNullGivenBack == false);

		this.binaryTreeInArray = theArrayOfTheTwoCombinedNodes;

		return this;
	}

	public ArrayList<String> getArrayList()
	{
		return binaryTreeInArray;
	}

	// Traverse the tree to produce a table of characters with their Huffman code.
	// this is a preorder taverse... this method is fully recursive and ends completely the first time
	// the first if OR the second if is NOT fulfilled.
	public void traverseTheTree(int index, String prefix)
	{
		if (index < binaryTreeInArray.size())
		{
			String val = binaryTreeInArray.get(index);
			if (val != null)
			{
				if (binaryTreeInArray.get(index).length() == 1)
				{
					// ok so it's defintly a single letter, time to put it into the map
					System.out.println(val + " " +prefix);
					huffmanCodeMap.put(binaryTreeInArray.get(index), prefix);

					//huffmanCodeMap.put(binaryTreeInArray.get(index), indexHuffmanRunningTotal);

					// reset it...
					//indexHuffmanRunningTotal = "";
				}
				else
				{
					System.out.println(val);	

					int indexLeft = 2 * index + 1;

					int indexRight = 2 * index + 2;

					traverseTheTree(indexLeft, prefix + "0");
					traverseTheTree(indexRight, prefix + "1");
				}
			}
		}
	}

	public void printOutTheHuffMap()
	{
		System.out.println("PRINT OUT MAP:");
		for(String key : huffmanCodeMap.keySet())
		{
			System.out.println("NEW MAP ENTRY:");
			System.out.println(key + " " + huffmanCodeMap.get(key));
		}
	}

	// uncompression - unhuffing - decompression
	public void unhuffingDecompressionProgram()
	{

	}

	public void askIfThereIsARightNode(int aSiftedUpIndex)
	{
		// 2k + 2 is the right child.
		// indexRight means right child.
		int indexRight = 2 * aSiftedUpIndex + 2;

		String theLetterOfSiftedUp = binaryTreeInArray.get(aSiftedUpIndex);

		//System.out.println(val);


		// if the letter at index's place in array is not null.
		// talking about the right child... does the right child exist or not?
		if (binaryTreeInArray.get(indexRight) != null)
		{
			//System.out.println(a);
			//traverseTheTree(indexRight);
		}
		else
		{
		}
	}

	public void printTheArrayList()
	{
		for(int i =0 ;i < binaryTreeInArray.size() ; i++)
		{
			System.out.println(binaryTreeInArray.get(i));
		}
	}

	public ArrayList<String> getLettersOnLevel(int level)
	{
		int fromIndex = (int) (Math.pow(2,level)-1);
		int toIndex = (int) (Math.pow(2,level+1)-1);
		// the two lines above explained: lets say the int sent in to this method
		// was 2, so the nodes on the 2nd level of the three is nods 3-7 in the binaryTreeInArray Arraylist.
		// (level 2 is level 3 but we are counting the tree top to bottom starting from 0, NOT 1.

		// turn the two double values (node values) gotten above and convert them into int form (directly below):
		//nt fromIndexInIntForm = (int) (fromIndex * 1000000);
		//int toIndexInIntForm = (int) (toIndex * 1000000);
		// Got conversion from: http://stackoverflow.com/questions/24309489/convert-double-into-int

		if (toIndex <= binaryTreeInArray.size())
		{
			ArrayList<String> listToReturn = new ArrayList<String>(binaryTreeInArray.subList(fromIndex,toIndex));
			return listToReturn;
		}
		else // this level doesnt exist
		{
			int theNumOfNodesOnNonExistingLevel = toIndex - fromIndex;

			// so i need "theNumOfNodesOnNonExistingLevel" number of nulls to be returned now...

			// ASK: does the below line work (is valid)?
			ArrayList<String> listOfNull = new ArrayList<String>(Collections.nCopies(theNumOfNodesOnNonExistingLevel, null));

			return listOfNull;
		}
	}


	public void MakeStringMyHuffmanCode()
	{	
		MyWordInHuffmanCodeForm = "";
		//MyWordInHuffmanCodeForm = 
		// the keys in the wordMap is the STRING (LETTER i.e a)
		for(String key : huffmanCodeMap.keySet())
		{
			// below: get the value of key we are currently looking at
			// (remember value: is just the NUMBER OF TIMES THAT LETTER IS
			// IN THE WORD/PHRASE

			String huffmanCodeAtThatPoint = huffmanCodeMap.get(key);
			System.out.println("TESTTTTTTT + "  + huffmanCodeMap.get(key));

			// if min key is null (which it is first time round) OR
			// theValue of the key we are currently looking at is LESS than
			// what the value of the minimum key is now......

			//MyWordInHuffmanCodeForm = MyWordInHuffmanCodeForm.concat(huffmanCodeAtThatPoint);
			MyWordInHuffmanCodeForm = MyWordInHuffmanCodeForm.concat(huffmanCodeAtThatPoint);
		}
	}

	public void CompressItIntoFile(ArrayList<String> binaryTreeInArray)
	{
		//ArrayList<String> myHeader = binaryTreeInArray;
		for(int i = 0 ; i < binaryTreeInArray.size() ; i++)
		{
			//MyWordInHuffmanCodeForm = MyWordInHuffmanCodeForm.concat(huffmanCodeAtThatPoint);
			String LettingCurrentlyAt = String.valueOf(myArrayListInString.charAt(i));
			myArrayListInString = myArrayListInString.concat(LettingCurrentlyAt);
		}
		try {
			OutputStream output = new FileOutputStream("compressed.bin");

			// writing
			BitOutputStream bitOutput = new BitOutputStream(output);

			// this is the magic number: put it at the start of the compressed binary text.
			bitOutput.write(8, 129);

			// this is the array list in String form being used as the header at the front
			// of the binary text but after the magic number.
			for(int i = 1 ; i < myArrayListInString.length() ; i++)
			{
						// Derive the string version of the letter.
						Integer intVersionOfCharAt = Integer.valueOf(myArrayListInString.charAt(i));

						bitOutput.write(1,intVersionOfCharAt);
			} 
			
			// FINALLY after the magic no. and the arraylist in String form, the ACTUAL binary version of the word
			// is actually compressed.
			for(int i = 1 ; i < MyWordInHuffmanCodeForm.length() ; i++)
			{
						// Derive the string version of the letter.
						Integer intVersionOfCharAt = Integer.valueOf(MyWordInHuffmanCodeForm.charAt(i));

						bitOutput.write(1,intVersionOfCharAt);
			} 
			
			output.close();
			bitOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void DecompressItOutOfFile()
	{
		try {
			InputStream input = new FileInputStream("compressed.bin");

			// getting it out of the file and giving me a word
			BitInputStream bitInput = new BitInputStream(input);

			int whatsInTheBinFileInIntForm =  bitInput.read(MyWordInHuffmanCodeForm.length());

			System.out.println("DECOMPRESS IS" + whatsInTheBinFileInIntForm);
			input.close();
			bitInput.close();
		} catch (IOException e) {

		}
	}
	
	public void preOrderTraversal(ArrayList<String> binaryTreeInArray)
	{
		
	}

	//	public void myVersionOfRead(String path)
	//{	
	//}
}