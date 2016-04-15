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

	// (below) maps string to string.. maps single letters to the huffman code for that letter in binary.
	public static Map<String, String> huffmanCodeMap = new HashMap<>();

	//public static Map<String, String> letterToHuffmanCodeMap = new HashMap<>();
	static boolean reachEndOfTree = false;
	String MyWordInHuffmanCodeForm;
	String myArrayListInString =  "";

	int traverseCount;

	// CONSTRUCTOR USED IN MERGE METHOD.
	public MyBinaryTree(String key)
	{
		binaryTreeInArray.add(key);
	}

	public MyBinaryTree(ArrayList<String> list)
	{
		binaryTreeInArray = list;
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

	// the traverseTheTree method is what populates the huffmanCodeMap.
	// Traverse the tree to produce a table of characters with their Huffman code.
	// this is a pre-order taversal... this method is fully recursive and ends completely if the first OR the
	// second if is NOT fulfilled.

	// This method is a RECURSIVE method, that is the only reason i have the String input called "prefix"
	//the reason i have prefix is so that i CAN keep a running total so to speak of my
	public void traverseTheTree(int index, String prefix)
	{
		if (index < binaryTreeInArray.size())
		{
			String letterValue = binaryTreeInArray.get(index);
			if (letterValue != null)
			{
				// if it has a length one one I.E is it a SINGLE LETTER i.e is it a LEAF node.
				if (binaryTreeInArray.get(index).length() == 1)
				{
					// ok so it's defintly a single letter, time to put it into the map
					System.out.println(letterValue + " " +prefix);
					huffmanCodeMap.put(binaryTreeInArray.get(index), prefix);

					//huffmanCodeMap.put(binaryTreeInArray.get(index), indexHuffmanRunningTotal);

					// reset it...
					//indexHuffmanRunningTotal = "";
				}
				else
				{
					System.out.println(letterValue);	

					// if we get into this else, it is not a leaf node, the letter is NOT a single character
					// indexLeft is the non-leaf node's LEFT child.
					int indexLeft = 2 * index + 1;

					// indexLeft is the non-leaf node's RIGHT child.
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
			System.out.println("NEW MAP ENTRY ------------------:");
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
		for(int i = 0 ; i < binaryTreeInArray.size() ; i++)
		{
			String LettingCurrentlyAt = String.valueOf(myArrayListInString.charAt(i));

			myArrayListInString = myArrayListInString.concat(LettingCurrentlyAt);
		}
		try
		{
			OutputStream output = new FileOutputStream("compressed.bin");

			// writing
			BitOutputStream bitOutput = new BitOutputStream(output);

			// this is the magic number 129: put it at the start of the compressed binary text.
			bitOutput.write(8, 129);

			// I Am putting the size of the tree as the second header.
			bitOutput.write(8, binaryTreeInArray.size());

			// Time for the the 3rd header, the third header is the tree itself.
			// now.. i am putting the tree as another header after the size of the tree (8 bit) header. 
			// the tree will be in binary form so 1010101010 and the length of text of 101010 binary text of the
			// tree is specified to me by the size of the binary tree header.
			for(int i = 0 ; i < binaryTreeInArray.size() ; i++)
			{
				int leftChild = 2*i+1;
				int rightChild = 2*i+2;

				// if its a leaf node..
				if ((binaryTreeInArray.get(leftChild)) == null && (binaryTreeInArray.get(rightChild)) == null)
				{
					// it a leaf node so a 1 (binary) must be written followed by the binary representation of the 
					// letter at that node location in ASCII form.

					// write a 1
					bitOutput.write(1,1);

					String letterOfTheLeafNode = binaryTreeInArray.get(i);
					// the below will work BECAUSE its just a SINGLE character (the 0 works).
					char theSingleLetterInCharForm = letterOfTheLeafNode.charAt(0);

					// the line below gives me the ASCII version of the single letter.
					int theLetterInAscii = (int) theSingleLetterInCharForm;

					bitOutput.write(8,theLetterInAscii);

					System.out.println("kkkkkkkkkkkkk" + theLetterInAscii); // just a print test
				}
				else if ((binaryTreeInArray.get(i)) == null) // if the string at that location IS NULL.
				{
					// the node i am currently at (place in the arraylist) IS NULL.

					// if im at a null node i have to write absolutely nothing and do nothing.
					// Remember you only added nodes to IDENTIFY if a node has children or not.

					// MORE EXPLICIT EXPLANATION: ignore that null char and just keep looping....
					bitOutput.write(1,1);
					bitOutput.write(1,0);
					bitOutput.write(1,1);

					//trying to put 101
				}
				else //else the node i am at is a NON LEAF NODE and it is a NOT a null node (null nodes are always leaf
					// nodes anyway.
				{
					bitOutput.write(1,0);
					// its a 0 so just output the 0 and thats it.
				}
			}

			// give the global variable which is string, an empty string....
			MyWordInHuffmanCodeForm = "";
			
			for(int i = 0 ; i < TheText.theWord.length() ; i++)
			{
				char theLetterAtThatPosition = TheText.theWord.charAt(0);
				
				for(String key : huffmanCodeMap.keySet())
				{
					if (String.valueOf(theLetterAtThatPosition) == key)
					{
						// .get(key) gets me the value at that place in the huffmancodemap (yes i realize hash maps are UNSORTED).
						MyWordInHuffmanCodeForm = MyWordInHuffmanCodeForm.concat(huffmanCodeMap.get(key)); //
					}
				}
			}
			
			// now myWordInHuffmanForm is the huffman binary code in order.
			MyWordInHuffmanCodeForm = "";
			
			// FINALLY after the magic no. AND the size of the array AND the arraylist (binary) in String form, the ACTUAL binary version of the word
			// is actually compressed, finally. this is straight forword... go through the String, if the String variable
			// is a 1 write a 1 to the bin file.
			for(int i = 0 ; i < MyWordInHuffmanCodeForm.length() ; i++)
			{
				char c = MyWordInHuffmanCodeForm.charAt(i);

				if (c == '1')
				{
					// if its a 1, write 1, this will write a 1 binary variable to the bin file.
					bitOutput.write(1,1);
				}
				else if (c == '0')
				{
					// if it's a 0, write 0.
					bitOutput.write(1,0);
				}
			}
			output.close();
			bitOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void DecompressItOutOfFile()
	{
		// ok print out the array before i clear the array (to test).
		System.out.println("array before clear: " + binaryTreeInArray);

		// the below line makes the array null.
		binaryTreeInArray = new ArrayList<String>();

		// ok print out the array to make sure its cleared.
		System.out.println("check if the array is truly cleared: " + binaryTreeInArray);

		// and print it out again after i make the binary tree again below...
		try
		{
			InputStream input = new FileInputStream("compressed.bin");
			// getting it out of the file and giving me a word

			BitInputStream bitInput = new BitInputStream(input);

			// how do i get the number of bits in line below... its not just the word to be decompressed
			// in int form

			if (bitInput.read() == 129)
			{
				//ok so the magic no. is 129 YES, do proceed..

				//ok so the first 8 bits IS the magic number... time to take in the actual tree's size in binary form.

				// the below line gives me back int form of the binary 8 bits after the magic number in the 
				// compressed file and the 8 bits after the magic number IS the size of the tree in binary form.
				int sizeOfTheBinaryTree = bitInput.read();

				// while the size of the binary tree is not reached yet.. keep going...
				for(int i = 0 ; i < sizeOfTheBinaryTree ; i++)
				{
					// ok we are going bit by bit.
					int tempSingleBit = bitInput.read(1);

					if(tempSingleBit == 1)
					{
						// if its 1, its a leaf node so lets extract the letter of that leaf node

						// String.valueOf() gets me the ascii version of the 8 bites (1 byte) after 
						// the 1 bit has been read, the first 1 bit encountered is not counted,
						// it is simply identifying to me that it's a leaf node.

						// now take that ascii (in string form) and get the LETTER which naturally corresponds to that 
						// ascii number.

						//convert the ascii of the single letter in string form to an int first..

						char theActualLetterOfTheAsciiNum = (char) (bitInput.read());

						binaryTreeInArray.add(String.valueOf(theActualLetterOfTheAsciiNum));

						// how do i get the letters in the right order. ??? Priority que??
					}
					else if(tempSingleBit == 0) // or a null TO BE FIXED DIFFERENCIATE 0 and nulls from the original array
					{
						// if its a 0, its a non leaf node, just add to the array list a NON-single character String.
						binaryTreeInArray.add("xx");
					}
				}

				//step 2

				for (int i = 0 ; i < binaryTreeInArray.size(); i++)
				{
					// if the node has within it, a string which is 1 character long.
					if (binaryTreeInArray.get(i).length() == 1)
					{
						// set its children to null

						int leftChildOfCurrentArrayPosition = (2 * i) + 1;
						int rightChildOfCurrentArrayPosition = (2 * i) + 2;

						binaryTreeInArray.set(leftChildOfCurrentArrayPosition, null);
						binaryTreeInArray.set(rightChildOfCurrentArrayPosition, null);
					}
					else if (binaryTreeInArray.get(i) == null) // (below) null nodes in the tree (in my arraylist) naturally have children which are null so..
					{
						int leftChildOfCurrentArrayPosition = (2 * i) + 1;
						int rightChildOfCurrentArrayPosition = (2 * i) + 2;

						binaryTreeInArray.set(leftChildOfCurrentArrayPosition, null);
						binaryTreeInArray.set(rightChildOfCurrentArrayPosition, null);
					}
				}
				
				// now my binaryTreeInArray arraylist is set up in a way where the non-leaf nodes are xx, the leaf
				// nodes are their letter and the null nodes are null.
				
				// clear the huffmanCodeMap hash map
				
				huffmanCodeMap.clear();
				
				// the traverse the tree method will populate the huffmanCodeMap again correctly (decompression side).
				traverseTheTree(0, "");
				
				// ok so the huffmanCodeMap is populated appropriately.
				
				for (int i = 0 ; i < ; i++)
				{
					
				}
				
				
				
				
				
				
				// now all the headers have been decompressed and now i have to decompress the actual word which
				// is right now in binary huffman code format
				// i get the order through the decompressing of the word which was compressed
				
				
				bitInput.read();
			}
			else
			{
				// its not the magic number........ the decompresser program is now declaring "hey i cannot 
				// decompress this whole binary file because i am not programmed to decompresser it because
				// the magic number i.d is not what i need.

				// give back to the user in text that you don't decompress it.
			}
			input.close();
			bitInput.close();
		} catch (IOException e)
		{
		}
	}

	public void preOrderTraversal(ArrayList<String> binaryTreeInArray)
	{
	}
}