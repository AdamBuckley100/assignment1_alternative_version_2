import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MyBinaryTree
{
	// the binaryTreeInArray is the FIRST array, the flawed one, there will be two
	ArrayList<String> binaryTreeInArray = new ArrayList<String>();
	public static Map<String, String> huffmanCodeMap = new HashMap<>();
	static boolean reachEndOfTree = false;

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
	public void traverseTheTree(int index)
	{
		if (index < binaryTreeInArray.size())
		{
			String val = binaryTreeInArray.get(index);
			if (val != null)
			{
				//System.out.println(val);	

				int indexLeft = 2 * index + 1;
				int indexRight = 2 * index + 2;

				if (binaryTreeInArray.get(indexLeft) != null && binaryTreeInArray.get(indexRight) != null)
				{
					//its a leaf node! SIFT UP ONE (cus we alredy printed) and then ask if there is a right node or not.


					//following is the sift up....

					int siftedUpIndex = ((index-1)/2);
					askIfThereIsARightNode(siftedUpIndex);

				}
				else
				{
					// if the letter at index's place in arry is not null.
					if (binaryTreeInArray.get(indexLeft) != null)
					{
						traverseTheTree(indexLeft);
					}
					else
					{
						traverseTheTree(indexRight);
					}
				}
				traverseTheTree(indexLeft);
				traverseTheTree(indexRight);
			}
		}
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
			traverseTheTree(indexRight);
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
}