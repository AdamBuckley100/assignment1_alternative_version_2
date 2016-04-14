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

			bitOutput.write(8, binaryTreeInArray.size());

			// now.. i am putting the tree as another header after the size of the tree (8 bit) header. 
			// the tree will be in binary form so 1010101010 and the length of text of 101010 binary text of the
			// tree is specified to me by the size of the binary tree header.
			for(int i = 0 ; i < binaryTreeInArray.size() ; i++)
			{
				int leftChild = 2*i+1;
				int rightChild = 2*i+2;

				if ((binaryTreeInArray.get(leftChild)) == null && (binaryTreeInArray.get(rightChild)) == null)
				{

					// it a leaf node so a 1 (binary) must be written followed by the binary representation of the 
					// letter at that node location in ASCII form.

					// write a 1
					bitOutput.write(1,1);

					String letterOfTheLeafNode = binaryTreeInArray.get(i);
					// the below will work BECAUSE its just a SINGLE character
					char theSingleLetterInCharForm = letterOfTheLeafNode.charAt(0);

					// code now has ascii. because the line below give me the ASCII version of the single letter.
					int theLetterInAscii = (int) theSingleLetterInCharForm;

					bitOutput.write(8,theLetterInAscii);

					System.out.println("kkkkkkkkkkkkk" + theLetterInAscii);
				}
				else
				{
					bitOutput.write(1,0);
					// its a 0 so just output the 0 and thats it.
				}
			}

			// FINALLY after the magic no. and the arraylist in String form, the ACTUAL binary version of the word
			// is actually compressed.
			for(int i = 0 ; i < MyWordInHuffmanCodeForm.length() ; i++)
			{
				char c = MyWordInHuffmanCodeForm.charAt(i);

				if (c == '1')
				{
					// if its a 1, write 1.
					bitOutput.write(1,1);
				}
				else if (c == '0')
				{
					// if it's a 0, write 0.
					bitOutput.write(1,0);
				}
			} 

			//finally its time to get the a header that goes after everything EXCEPT the compressed word and this
			//header is going to be a LONG variable and this long variable says the NUMBER of actual binary bits
			//in the compressed binary text which is in binary form. (Note: i am using this extra header instead of
			// using any eof char etc.

			long theNumberOfBitsInTheCompressedText = MyWordInHuffmanCodeForm.length();

			// now we have the num of bits in the binary compressed text.
			// now change this theNumberOfBitsInTheCompressedText long value to binary form
			// so i can put it into the final header.

			//change long to int. (cast that long to an int).
			int theNumberOfBitsInTheCompressedTextinIntForm  = (int) theNumberOfBitsInTheCompressedText;

			bitOutput.write(8,theNumberOfBitsInTheCompressedTextinIntForm);

			//Final task...below I am actually putting the binary version of the actual word to be compressed to
			// and write it to the file after the header(s).

			for(int i = 0 ; i < MyWordInHuffmanCodeForm.length() ; i++)
			{
				char c = MyWordInHuffmanCodeForm.charAt(i);

				if (c == '1')
				{
					bitOutput.write(1,1);
				}
				else if (c == '0')
				{
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
		// ok print out the array before the clear to test.
		System.out.println("array before clear: " + binaryTreeInArray);

		// makes it null.
		binaryTreeInArray = new ArrayList<String>();

		// ok print out the array to make sure its cleared.
		System.out.println("check if the array is truly cleared: " + binaryTreeInArray);

		// and print it out again after i make out the binary tree again below...
		try
		{
			InputStream input = new FileInputStream("compressed.bin");

			// getting it out of the file and giving me a word

			BitInputStream bitInput = new BitInputStream(input);

			// how do i get the number of bits in line below... its not just the word to be decompressed
			// in int form

			String AtWhatLocationIAmAtOfDecompression = "";

			if (bitInput.read() == 129)
			{
				//ok so thr magic no. is 129 YES, do proceed..

				//ok so the first 8 bits IS the magic number... time to take in the actual tree

				// ok now get the length of the tree in binary form

				// the below line gives me back int form of the binary 8 bits after the magic number in the 
				// compressed file and the 8 bits after the magic number IS the size of the tree in binary form.
				int sizeOfTheBinaryTree = bitInput.read();

				// while the size of the binary tree is not reached yet.. keep going...
				for(int i = 0 ; i < sizeOfTheBinaryTree ; i++)
				{

					// Slightly aside: does the read method AUTOMATICALLY read the NEXT bit
					// number. <-- im 99% sure it does.
					// ok we are going bit by bit.
					int tempSingleBit = bitInput.read(1);

					if(tempSingleBit == 1)
					{
						// if its 1, its a leaf node so lets extract the letter of that leaf node

						// when read has no param, it automatically reads 8 bits (i.e. 1 byte)....
						// String.valueOf() gets me the ascii version of the 8 bites (1 byte) after 
						// the 1 bit has been read, the first 1 bit encountered is not counted,
						// it is simply identifying to me that it's a leaf node.
						String tempLetterAsciiInBinaryTextForm = String.valueOf(bitInput.read());

						// now take that ascii (in string form) and get the LETTER which naturally corresponds to that 
						// ascii number.

						//convert the ascii of the single letter in string form to an int first..

						int intVersionOfTheAscii = Integer.valueOf(tempLetterAsciiInBinaryTextForm);

						char theActualLetterOfTheAsciiNum = (char) intVersionOfTheAscii;

// how do i get the letters in the right order.
					}
					else if(tempSingleBit == 0)
					{

					}
				}
			}
			else
			{
				// its not the magic number........ the decompresser program is now declaring "hey i cannot 
				// decompress this whole binary file because i am not programmed to decompresser it because
				// the magic number i.d is not what i need.
			}


			int theMagicNumber = 0;

			do
			{
				bitOutput.read(1,1);
			}
			while (theMagicNumber != 129);





			int whatsInTheBinFileInIntForm =  bitInput.read(MyWordInHuffmanCodeForm.length());

			System.out.println("DECOMPRESS IS" + whatsInTheBinFileInIntForm);

			// so I will assume i'm reading it from LEFT TO RIGHT.

			//first check for magic number which is 129 (8 bit size)..

			int thePotentialMagicNumberInIntForm = bitInput.read(8);

			//convert those binary bits which are in int for to a string

			String thePotentialMagicNumberInStringForm = Integer.toString(thePotentialMagicNumberInIntForm);

			if (thePotentialMagicNumberInStringForm == "10000001")
			{
				//it is the magic number.... proceed
			}
			else
			{
				//its not the magic number........ the decompresser program is now declaring "hey i cannot 
				// decompress this whole binary file because i am not programmed to decompresser it because
				// the magic number i.d is not what i need.
			}


			//bitInput.read(howManyBits)


			input.close();
			bitInput.close();
		} catch (IOException e) {
		}
	}

	public void preOrderTraversal(ArrayList<String> binaryTreeInArray)
	{

	}
}