import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyBinaryTree
{
	ArrayList<String> binaryTreeInArray = new ArrayList<String>();
	public static Map<String, String> huffmanCodeMap = new HashMap<>();
	
	int traverseCount;

	public MyBinaryTree(String key)
	{
		binaryTreeInArray.add(key);
	}

	// this method allows the actual tree to go from many trees (singular.. initially) and changes it into
	// exactly 1 binary tree
	public MyBinaryTree merge(MyBinaryTree tree, String theNewStringKey)
	{
		//binaryTreeInArray.addAll(tree.getArrayList());
		if (binaryTreeInArray.size() == tree.getArrayList().size())
		{
			binaryTreeInArray.addAll(tree.getArrayList());
		}
		else
		{
			if (binaryTreeInArray.size() > tree.getArrayList().size())
			{
				System.out.println("OR HERE");
				int numDif = binaryTreeInArray.size() - tree.getArrayList().size();
				
				for(int i = 0 ; i < numDif ; i++)
				{
					binaryTreeInArray.add(null);
				}
				
				binaryTreeInArray.addAll(tree.getArrayList());
			}
			else if (tree.getArrayList().size() > binaryTreeInArray.size())
			{
				System.out.println("here");
				int theNumDif = tree.getArrayList().size() - binaryTreeInArray.size();
				
				for(int i = 0 ; i < theNumDif ; i++)
				{
					binaryTreeInArray.add(null);
				}
				
				binaryTreeInArray.addAll(tree.getArrayList());
			}
		}
		binaryTreeInArray.add(0, theNewStringKey);
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
}
